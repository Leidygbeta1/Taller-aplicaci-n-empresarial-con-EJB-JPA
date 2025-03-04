/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leidy.controller;

import com.leidy.dao.CursoDaoLocal;
import com.leidy.dao.EstudianteDaoLocal;
import com.leidy.model.Curso;
import com.leidy.model.Estudiante;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsignarCursoServlet", urlPatterns = {"/AsignarCursoServlet"})
public class AsignarCursoServlet extends HttpServlet {

    @EJB
    private EstudianteDaoLocal estudianteDao;

    @EJB
    private CursoDaoLocal cursoDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "listar":
                    listarAsignaciones(request, response);
                    break;
                case "eliminar":
                    eliminarCurso(request, response);
                    break;
                case "editar":
                    editarAsignacion(request, response);
                    break;
                default:
                    listarAsignaciones(request, response);
                    break;
            }
        } else {
            listarAsignaciones(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {
            guardarAsignacion(request, response);
        } else if ("actualizar".equals(accion)) {
            actualizarAsignacion(request, response);
        }
    }

private void listarAsignaciones(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    estudianteDao.flush();  // Asegura que los cambios se apliquen
    estudianteDao.clear();  // Limpia la caché del EntityManager

    List<Estudiante> listaEstudiantes = estudianteDao.listarTodos();
    List<Curso> listaCursos = cursoDao.listarTodos();

    // Crear lista de asignaciones basada en los estudiantes con cursos asignados
    List<Estudiante> listaAsignaciones = new ArrayList<>();
    for (Estudiante estudiante : listaEstudiantes) {
        if (!estudiante.getCursos().isEmpty()) {  // Solo agregar estudiantes con cursos asignados
            listaAsignaciones.add(estudiante);
        }
    }

    // 🔴 Imprimir los datos actualizados
    System.out.println("Lista de estudiantes y cursos actualizada:");
    for (Estudiante estudiante : listaAsignaciones) {
        System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido());
        for (Curso curso : estudiante.getCursos()) {
            System.out.println(" - Curso: " + curso.getNombreCurso());
        }
    }

    request.setAttribute("listaEstudiantes", listaEstudiantes);
    request.setAttribute("listaCursos", listaCursos);
    request.setAttribute("listaAsignaciones", listaAsignaciones);
    request.getRequestDispatcher("asignarCursos.jsp").forward(request, response);
}



 private void guardarAsignacion(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String idEstudianteParam = request.getParameter("idEstudiante");
    String[] cursosSeleccionados = request.getParameterValues("cursos");

    if (idEstudianteParam != null && !idEstudianteParam.isEmpty()) {
        Long idEstudiante = Long.parseLong(idEstudianteParam);
        Estudiante estudiante = estudianteDao.encontrarPorId(idEstudiante);

        if (estudiante != null) {
            Set<Curso> cursos = estudiante.getCursos(); // Obtener cursos actuales del estudiante

            if (cursosSeleccionados != null) {
                for (String codigoCurso : cursosSeleccionados) {
                    if (codigoCurso != null && !codigoCurso.isEmpty()) {
                        Curso curso = cursoDao.encontrarPorId(Long.parseLong(codigoCurso));
                        if (curso != null) {
                            cursos.add(curso);  // Agregar el curso sin borrar los anteriores
                        }
                    }
                }
            }

            estudiante.setCursos(cursos);  // Actualizar cursos del estudiante
            estudianteDao.actualizar(estudiante);
        }
    } else {
        System.out.println("ID de estudiante no válido");
    }

    response.sendRedirect("AsignarCursoServlet?accion=listar");
}


private void eliminarCurso(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String idEstudianteParam = request.getParameter("idEstudiante");
    String idCursoParam = request.getParameter("idCurso");

    if (idEstudianteParam != null && idCursoParam != null) {
        Long idEstudiante = Long.parseLong(idEstudianteParam);
        Long idCurso = Long.parseLong(idCursoParam);

        try {
            estudianteDao.eliminarCursoDeEstudiante(idEstudiante, idCurso);
            System.out.println("Eliminación exitosa del curso " + idCurso + " para el estudiante " + idEstudiante);
        } catch (Exception e) {
            System.out.println("Error al eliminar el curso: " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        System.out.println("ID de estudiante o curso no válido.");
    }
    
    // Redireccionar a la lista para actualizar la página
    response.sendRedirect("AsignarCursoServlet?accion=listar");
}





private void editarAsignacion(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String idEstudianteParam = request.getParameter("idEstudiante");
    if (idEstudianteParam != null && !idEstudianteParam.isEmpty()) {
        Long idEstudiante = Long.parseLong(idEstudianteParam);
        Estudiante estudiante = estudianteDao.encontrarPorId(idEstudiante);

        if (estudiante != null) {
            List<Curso> listaCursos = cursoDao.listarTodos();
            request.setAttribute("estudiante", estudiante);  // Enviar estudiante a JSP
            request.setAttribute("listaCursos", listaCursos);  // Enviar cursos a JSP
            request.setAttribute("modoEdicion", true);  // Bandera para el modo edición

            request.getRequestDispatcher("asignarCursos.jsp").forward(request, response);
            return;
        }
    }

    response.sendRedirect("AsignarCursoServlet?accion=listar");
}


  private void actualizarAsignacion(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    Long idEstudiante = Long.parseLong(request.getParameter("idEstudiante"));
    String[] cursosSeleccionados = request.getParameterValues("cursos");

    Estudiante estudiante = estudianteDao.encontrarPorId(idEstudiante);
    if (estudiante != null && cursosSeleccionados != null) {
        Set<Curso> cursos = estudiante.getCursos();  // Mantener los cursos actuales

        for (String codigoCurso : cursosSeleccionados) {
            Curso curso = cursoDao.encontrarPorId(Long.parseLong(codigoCurso));
            if (curso != null) {
                cursos.add(curso);  // Agregar cursos nuevos
            }
        }
        estudiante.setCursos(cursos);  // Actualizar cursos del estudiante
        
        estudianteDao.actualizar(estudiante);
        estudianteDao.flush();  // Forzar sincronización con la base de datos
    }

    response.sendRedirect("AsignarCursoServlet?accion=listar");
}


}

