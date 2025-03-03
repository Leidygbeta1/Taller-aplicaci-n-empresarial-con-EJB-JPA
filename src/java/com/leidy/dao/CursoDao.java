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

    @Override
    public void insertar(Curso curso) {
        em.persist(curso);
    }

    @Override
    public void actualizar(Curso curso) {
        em.merge(curso);
    }

    @Override
    public void eliminar(Curso curso) {
        em.remove(em.merge(curso));
    }

    @Override
    public Curso encontrarPorId(Long codigoCurso) {
        return em.find(Curso.class, codigoCurso);
    }

    @Override
    public List<Curso> listarTodos() {
        TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
        return query.getResultList();
    }
}
