<%-- 
    Document   : estudiantes
    Created on : 3/03/2025, 12:33:40 PM
    Author     : LEIDY GIRALDO
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Estudiantes</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<div class="container">
    <h2>${empty estudiante ? 'Agregar Estudiante' : 'Editar Estudiante'}</h2>
    <form action="EstudianteServlet" method="post">
        <input type="hidden" name="accion" value="${empty estudiante ? 'guardar' : 'actualizar'}">
        <input type="hidden" name="idEstudiante" value="${not empty estudiante ? estudiante.idEstudiante : ''}">

        <label>Nombre:</label>
        <input type="text" name="nombre" value="${not empty estudiante ? estudiante.nombre : ''}" required>

        <label>Apellido:</label>
        <input type="text" name="apellido" value="${not empty estudiante ? estudiante.apellido : ''}" required>

        <label>Cursos:</label><br>
        <select name="cursos" multiple size="5">
            <c:forEach var="curso" items="${listaCursos}">
                <option value="${curso.codigoCurso}"
                    <c:if test="${not empty estudiante and fn:contains(estudiante.cursos, curso)}">selected</c:if>>
                    ${curso.nombreCurso}
                </option>
            </c:forEach>
        </select><br>

        <input type="submit" value="${empty estudiante ? 'Guardar' : 'Actualizar'}">
    </form>

    <h2>Lista de Estudiantes</h2>
    <table>
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
                    <a href="EstudianteServlet?accion=editar&idEstudiante=${estudiante.idEstudiante}">Editar</a> |
                    <a href="EstudianteServlet?accion=eliminar&idEstudiante=${estudiante.idEstudiante}">Eliminar</a>
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




