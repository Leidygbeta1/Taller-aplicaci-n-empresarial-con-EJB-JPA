<%-- 
    Document   : asignarCursos
    Created on : 3/03/2025, 05:52:28 PM
    Author     : LEIDY GIRALDO
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Asignar Cursos a Estudiantes</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<div class="container">
    <h2>Asignar Cursos a Estudiantes</h2>
    <form action="AsignarCursoServlet" method="post">
        <input type="hidden" name="accion" value="${empty asignacion ? 'guardar' : 'actualizar'}">
        

        <label>Seleccionar Estudiante:</label>
        <select name="idEstudiante" required>
            <option value="">-- Seleccione un Estudiante --</option>
            <c:forEach var="estudiante" items="${listaEstudiantes}">
                <option value="${estudiante.idEstudiante}"
                    ${not empty asignacion and asignacion.estudiante.idEstudiante == estudiante.idEstudiante ? 'selected' : ''}>
                    ${estudiante.nombre} ${estudiante.apellido}
                </option>
            </c:forEach>
        </select>

        <label>Seleccionar Cursos:</label>
        <select name="cursos" multiple size="5">
            <c:forEach var="curso" items="${listaCursos}">
                <option value="${curso.codigoCurso}"
                    <c:if test="${not empty asignacion and fn:contains(asignacion.cursos, curso)}">selected</c:if>>
                    ${curso.nombreCurso}
                </option>
            </c:forEach>
        </select>

        <input type="submit" class="btn-submit" value="${empty asignacion ? 'Guardar' : 'Actualizar'}">
    </form>

    <h2>Estudiantes con Cursos Asignados</h2>
<table>
    <tr>
        <th>Estudiante</th>
        <th>Curso</th>
        <th>Acción</th>
    </tr>
    <c:forEach var="estudiante" items="${listaAsignaciones}">
        <c:forEach var="curso" items="${estudiante.cursos}">
            <tr>
                <td>${estudiante.nombre} ${estudiante.apellido}</td>
                <td>${curso.nombreCurso}</td>
                <td>
                    <a class="btn-delete" href="AsignarCursoServlet?accion=eliminar&idEstudiante=${estudiante.idEstudiante}&idCurso=${curso.codigoCurso}" onclick="return confirm('¿Estás seguro de que deseas eliminar este curso?')">Eliminar</a>

                </td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>

    <div class="back-link">
        <a href="index.jsp">Volver al Inicio</a>
    </div>
</div>

</body>
</html>



