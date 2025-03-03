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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EstudianteServlet", urlPatterns = {"/EstudianteServlet"})
public class EstudianteServlet extends HttpServlet {

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
                    listarEstudiantes(request, response);
                    break;
                case "eliminar":
                    eliminarEstudiante(request, response);
                    break;
                default:
                    listarEstudiantes(request, response);
                    break;
            }
        } else {
            listarEstudiantes(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String[] cursosSeleccionados = request.getParameterValues("cursos");

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);

        // Asignar cursos seleccionados al estudiante
        Set<Curso> cursos = new HashSet<>();
        if (cursosSeleccionados != null) {
            for (String codigoCurso : cursosSeleccionados) {
                Curso curso = cursoDao.encontrarPorId(Long.parseLong(codigoCurso));
                if (curso != null) {
                    cursos.add(curso);
                }
            }
        }
        estudiante.setCursos(cursos);

        estudianteDao.insertar(estudiante);
        response.sendRedirect("EstudianteServlet?accion=listar");
    }

    private void listarEstudiantes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Estudiante> listaEstudiantes = estudianteDao.listarTodos();
        List<Curso> listaCursos = cursoDao.listarTodos();
        request.setAttribute("listaEstudiantes", listaEstudiantes);
        request.setAttribute("listaCursos", listaCursos);
        request.getRequestDispatcher("estudiantes.jsp").forward(request, response);
    }

    private void eliminarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idEstudiante = Long.parseLong(request.getParameter("idEstudiante"));
        Estudiante estudiante = estudianteDao.encontrarPorId(idEstudiante);
        if (estudiante != null) {
            estudianteDao.eliminar(estudiante);
        }
        response.sendRedirect("EstudianteServlet?accion=listar");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
