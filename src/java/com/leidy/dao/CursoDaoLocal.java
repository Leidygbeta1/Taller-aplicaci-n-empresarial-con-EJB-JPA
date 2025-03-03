/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leidy.dao;

import com.leidy.model.Curso;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CursoDaoLocal {

    // Insertar Curso
    void insertar(Curso curso);

    // Actualizar Curso
    void actualizar(Curso curso);

    // Eliminar Curso
    void eliminar(Curso curso);

    // Encontrar Curso por ID
    Curso encontrarPorId(Long codigoCurso);

    // Buscar Curso por Nombre
    List<Curso> buscarPorNombre(String nombre);

    // Listar Todos los Cursos
    List<Curso> listarTodos();
}

