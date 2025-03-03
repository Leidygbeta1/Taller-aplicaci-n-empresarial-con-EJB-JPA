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
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CursoServlet", urlPatterns = {"/CursoServlet"})
public class CursoServlet extends HttpServlet {

    @EJB
    private CursoDaoLocal cursoDao;

    @EJB
    private EstudianteDaoLocal estudianteDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "listar":
                    listarCursos(request, response);
                    break;
                case "eliminar":
                    eliminarCurso(request, response);
                    break;
                default:
                    listarCursos(request, response);
                    break;
            }
        } else {
            listarCursos(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreCurso = request.getParameter("nombreCurso");
        int numeroCreditos = Integer.parseInt(request.getParameter("numeroCreditos"));
        int semestre = Integer.parseInt(request.getParameter("semestre"));
        int numeroEstudiantesAdmitidos = Integer.parseInt(request.getParameter("numeroEstudiantesAdmitidos"));

        Curso curso = new Curso();
        curso.setNombreCurso(nombreCurso);
        curso.setNumeroCreditos(numeroCreditos);
        curso.setSemestre(semestre);
        curso.setNumeroEstudiantesAdmitidos(numeroEstudiantesAdmitidos);

        cursoDao.insertar(curso);

        response.sendRedirect("CursoServlet?accion=listar");
    }

    private void listarCursos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Curso> listaCursos = cursoDao.listarTodos();
        request.setAttribute("listaCursos", listaCursos);
        request.getRequestDispatcher("cursos.jsp").forward(request, response);
    }

    private void eliminarCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long codigoCurso = Long.parseLong(request.getParameter("codigoCurso"));
        Curso curso = cursoDao.encontrarPorId(codigoCurso);
        if (curso != null) {
            cursoDao.eliminar(curso);
        }
        response.sendRedirect("CursoServlet?accion=listar");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
