/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leidy.dao;

import com.leidy.model.Curso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LEIDY GIRALDO
 */
@Local
public interface CursoDaoLocal {
    void insertar(Curso curso);
    void actualizar(Curso curso);
    void eliminar(Curso curso);
    Curso encontrarPorId(Long codigoCurso);
    List<Curso> listarTodos();
}
