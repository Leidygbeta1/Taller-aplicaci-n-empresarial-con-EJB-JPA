/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leidy.dao;





import com.leidy.model.Estudiante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EstudianteDao implements EstudianteDaoLocal {

    @PersistenceContext(unitName = "TallerWebPU")
    private EntityManager em;

    @Override
    public void insertar(Estudiante estudiante) {
        em.persist(estudiante);
    }

    @Override
    public void actualizar(Estudiante estudiante) {
        em.merge(estudiante);
    }

    @Override
    public void eliminar(Estudiante estudiante) {
        em.remove(em.merge(estudiante));
    }

    @Override
    public Estudiante encontrarPorId(Long idEstudiante) {
        return em.find(Estudiante.class, idEstudiante);
    }

    @Override
    public List<Estudiante> listarTodos() {
        TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
        return query.getResultList();
    }
}

