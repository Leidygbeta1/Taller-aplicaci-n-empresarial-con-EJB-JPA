<%-- 
    Document   : index
    Created on : 3/03/2025, 11:40:51 AM
    Author     : LEIDY GIRALDO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inicio - Gestión Académica</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<div class="container">
    <h2>Bienvenido a la Gestión Académica</h2>
    <ul class="menu">
        <li><a href="CursoServlet?accion=listar" class="btn-link">Gestionar Cursos</a></li>
        <li><a href="EstudianteServlet?accion=listar" class="btn-link">Gestionar Estudiantes</a></li>
        <li><a href="AsignarCursoServlet?accion=listar" class="btn-link">Asignar Cursos a Estudiantes</a></li>
    </ul>
</div>

</body>
</html>



