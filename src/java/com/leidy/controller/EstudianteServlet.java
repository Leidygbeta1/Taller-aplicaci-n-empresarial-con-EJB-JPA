/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leidy.controller;

import com.leidy.dao.CursoDaoLocal;
import com.leidy.dao.EstudianteDaoLocal;
import com.leidy.model.Estudiante;
import java.io.IOException;
import java.util.List;
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
                case "editar":
                    editarEstudiante(request, response);
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
        String accion = request.getParameter("accion");

        if ("actualizar".equals(accion)) {
            actualizarEstudiante(request, response);
        } else if ("guardar".equals(accion)) {
            guardarEstudiante(request, response);
        } else {
            listarEstudiantes(request, response);
        }
    }

    private void listarEstudiantes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Estudiante> listaEstudiantes = estudianteDao.listarTodos();
        request.setAttribute("listaEstudiantes", listaEstudiantes);
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

    private void editarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idEstudiante = Long.parseLong(request.getParameter("idEstudiante"));
        Estudiante estudiante = estudianteDao.encontrarPorId(idEstudiante);
        request.setAttribute("estudiante", estudiante);
        listarEstudiantes(request, response);
    }

    private void actualizarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idEstudiante = Long.parseLong(request.getParameter("idEstudiante"));
        Estudiante estudiante = estudianteDao.encontrarPorId(idEstudiante);
        if (estudiante != null) {
            estudiante.setNombre(request.getParameter("nombre"));
            estudiante.setApellido(request.getParameter("apellido"));
            estudianteDao.actualizar(estudiante);
        }
        response.sendRedirect("EstudianteServlet?accion=listar");
    }

    private void guardarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);

        estudianteDao.insertar(estudiante);
        response.sendRedirect("EstudianteServlet?accion=listar");
    }

    @Override
    public String getServletInfo() {
        return "EstudianteServlet";
    }
}
