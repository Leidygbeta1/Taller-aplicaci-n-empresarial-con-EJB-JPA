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
    
    @Override
    public void eliminarCursoDeEstudiante(Long idEstudiante, Long idCurso) {
        String sql = "DELETE FROM estudiantes_cursos WHERE id_estudiante = ? AND codigo_curso = ?";
        int resultado = em.createNativeQuery(sql)
            .setParameter(1, idEstudiante)
            .setParameter(2, idCurso)
            .executeUpdate();

        if (resultado > 0) {
            System.out.println("Curso eliminado exitosamente para el estudiante.");
        } else {
            System.out.println("No se encontró la relación para eliminar.");
        }

        em.flush();  // Forzar sincronización con la base de datos
        em.clear();  // Limpiar el cache para que recargue la lista actualizada
    }



    @Override
public void clear() {
    em.clear();  // Limpia la caché del EntityManager
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
            List<Estudiante> estudiantes = query.getResultList();

         // 🔄 Forzar la recarga manual de cada estudiante y sus cursos
         for (Estudiante estudiante : estudiantes) {
             em.refresh(estudiante);  // Forzar la recarga de los datos actualizados
         }
         return estudiantes;
    }
    
    @Override
    public void flush() {
    em.flush();
}
}

