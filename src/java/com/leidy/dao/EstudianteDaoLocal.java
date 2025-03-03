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
    void insertar(Estudiante estudiante);
    void actualizar(Estudiante estudiante);
    void eliminar(Estudiante estudiante);
    Estudiante encontrarPorId(Long idEstudiante);
    List<Estudiante> listarTodos();
}

