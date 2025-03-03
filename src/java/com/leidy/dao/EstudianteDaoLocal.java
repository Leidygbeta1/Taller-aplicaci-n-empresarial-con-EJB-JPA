/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leidy.dao;

import com.leidy.model.Estudiante;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EstudianteDaoLocal {

    // Insertar Estudiante
    void insertar(Estudiante estudiante);

    // Actualizar Estudiante
    void actualizar(Estudiante estudiante);

    // Eliminar Estudiante
    void eliminar(Estudiante estudiante);

    // Encontrar Estudiante por ID
    Estudiante encontrarPorId(Long idEstudiante);

    // Buscar Estudiante por Nombre
    List<Estudiante> buscarPorNombre(String nombre);

    // Listar Todos los Estudiantes
    List<Estudiante> listarTodos();
}

