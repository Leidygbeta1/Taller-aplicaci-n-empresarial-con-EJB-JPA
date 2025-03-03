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

    // Insertar Estudiante
    @Override
    public void insertar(Estudiante estudiante) {
        em.persist(estudiante);
    }

    // Actualizar Estudiante
    @Override
    public void actualizar(Estudiante estudiante) {
        em.merge(estudiante);
    }

    // Eliminar Estudiante
    @Override
    public void eliminar(Estudiante estudiante) {
        em.remove(em.merge(estudiante));
    }

    // Encontrar Estudiante por ID
    @Override
    public Estudiante encontrarPorId(Long idEstudiante) {
        return em.find(Estudiante.class, idEstudiante);
    }

    // Buscar Estudiante por Nombre
    @Override
    public List<Estudiante> buscarPorNombre(String nombre) {
        TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.nombre LIKE :nombre", Estudiante.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }

    // Listar Todos los Estudiantes
    @Override
    public List<Estudiante> listarTodos() {
        TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
        return query.getResultList();
    }
}

