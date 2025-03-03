<%-- 
    Document   : cursos
    Created on : 3/03/2025, 12:33:05 PM
    Author     : LEIDY GIRALDO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Cursos</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<div class="container">
    <h2>${empty curso ? 'Agregar Curso' : 'Editar Curso'}</h2>
    <form action="CursoServlet" method="post">
        <input type="hidden" name="accion" value="${empty curso ? 'guardar' : 'actualizar'}">
        <input type="hidden" name="codigoCurso" value="${not empty curso ? curso.codigoCurso : ''}">

        <label>Nombre del Curso:</label>
        <input type="text" name="nombreCurso" value="${not empty curso ? curso.nombreCurso : ''}" required>

        <label>Número de Créditos:</label>
        <input type="number" name="numeroCreditos" value="${not empty curso ? curso.numeroCreditos : ''}" required>

        <label>Semestre:</label>
        <input type="number" name="semestre" value="${not empty curso ? curso.semestre : ''}" required>

        <label>Número de Estudiantes Admitidos:</label>
        <input type="number" name="numeroEstudiantesAdmitidos" value="${not empty curso ? curso.numeroEstudiantesAdmitidos : ''}" required>

        <input type="submit" value="${empty curso ? 'Guardar' : 'Actualizar'}">
    </form>

    <h2>Lista de Cursos</h2>
    <table>
        <tr>
            <th>Código</th>
            <th>Nombre</th>
            <th>Créditos</th>
            <th>Semestre</th>
            <th>Estudiantes Admitidos</th>
            <th>Acción</th>
        </tr>
        <c:forEach var="curso" items="${listaCursos}">
            <tr>
                <td>${curso.codigoCurso}</td>
                <td>${curso.nombreCurso}</td>
                <td>${curso.numeroCreditos}</td>
                <td>${curso.semestre}</td>
                <td>${curso.numeroEstudiantesAdmitidos}</td>
                <td>
                    <a href="CursoServlet?accion=editar&codigoCurso=${curso.codigoCurso}">Editar</a> |
                    <a href="CursoServlet?accion=eliminar&codigoCurso=${curso.codigoCurso}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="back-link">
        <a href="index.jsp">Volver al Inicio</a>
    </div>
</div>

</body>
</html>


