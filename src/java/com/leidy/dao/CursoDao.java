/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leidy.dao;

import com.leidy.model.Curso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CursoDao implements CursoDaoLocal {

    @PersistenceContext(unitName = "TallerWebPU")
    private EntityManager em;

    // Insertar Curso
    @Override
    public void insertar(Curso curso) {
        em.persist(curso);
    }

    // Actualizar Curso
    @Override
    public void actualizar(Curso curso) {
        em.merge(curso);
    }

    // Eliminar Curso
    @Override
    public void eliminar(Curso curso) {
        em.remove(em.merge(curso));
    }

    // Encontrar Curso por ID
    @Override
    public Curso encontrarPorId(Long codigoCurso) {
        return em.find(Curso.class, codigoCurso);
    }

    // Buscar Curso por Nombre
    @Override
    public List<Curso> buscarPorNombre(String nombre) {
        TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c WHERE c.nombreCurso LIKE :nombre", Curso.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }

    // Listar Todos los Cursos
    @Override
    public List<Curso> listarTodos() {
        TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
        return query.getResultList();
    }
}
