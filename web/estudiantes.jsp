<%-- 
    Document   : estudiantes
    Created on : 3/03/2025, 12:33:40 PM
    Author     : LEIDY GIRALDO
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Estudiantes</title>
</head>
<body>

<h2>Agregar Estudiante</h2>
<form action="EstudianteServlet" method="post">
    <label>Nombre:</label>
    <input type="text" name="nombre" required><br>
    <label>Apellido:</label>
    <input type="text" name="apellido" required><br>
    <label>Cursos:</label><br>
    <c:forEach var="curso" items="${listaCursos}">
        <input type="checkbox" name="cursos" value="${curso.codigoCurso}"> ${curso.nombreCurso}<br>
    </c:forEach>
    <input type="submit" value="Guardar">
</form>

<h2>Lista de Estudiantes</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Cursos Inscritos</th>
        <th>Acción</th>
    </tr>
    <c:forEach var="estudiante" items="${listaEstudiantes}">
        <tr>
            <td>${estudiante.idEstudiante}</td>
            <td>${estudiante.nombre}</td>
            <td>${estudiante.apellido}</td>
            <td>
                <c:forEach var="curso" items="${estudiante.cursos}">
                    ${curso.nombreCurso}<br>
                </c:forEach>
            </td>
            <td>
                <a href="EstudianteServlet?accion=eliminar&idEstudiante=${estudiante.idEstudiante}">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="index.jsp">Volver al Inicio</a>

</body>
</html>

