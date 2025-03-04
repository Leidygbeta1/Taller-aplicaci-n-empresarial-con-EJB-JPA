/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leidy.model;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_curso")
    private Long codigoCurso;

    @Column(name = "nombre_curso", nullable = false)
    private String nombreCurso;

    @Column(name = "numero_creditos", nullable = false)
    private int numeroCreditos;

    @Column(name = "semestre", nullable = false)
    private int semestre;

    @Column(name = "numero_estudiantes_admitidos", nullable = false)
    private int numeroEstudiantesAdmitidos;

@ManyToMany(mappedBy = "cursos", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
private Set<Estudiante> estudiantes = new HashSet<>();


    // Getters y Setters

    public Long getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(Long codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getNumeroEstudiantesAdmitidos() {
        return numeroEstudiantesAdmitidos;
    }

    public void setNumeroEstudiantesAdmitidos(int numeroEstudiantesAdmitidos) {
        this.numeroEstudiantesAdmitidos = numeroEstudiantesAdmitidos;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}

