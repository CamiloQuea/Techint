/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Camilo
 */
public class ConductorEvaluacion {

    private String evaluacion_id;
    private String conductor_id;
    private String conductor_nombre;
    private String conductor_apellido;
    private String evaluador_id;
    private String evaluador_nombre;
    private String evaluador_apellido;
    private Double evaluacion_notaExamTeo;
    private Double evaluacion_notaExamPrac;

    public ConductorEvaluacion(String evaluacion_id, String conductor_id, String conductor_nombre, String conductor_apellido, String evaluador_id, String evaluador_nombre, String evaluador_apellido, Double evaluacion_notaExamTeo, Double evaluacion_notaExamPrac) {
        this.evaluacion_id = evaluacion_id;
        this.conductor_id = conductor_id;
        this.conductor_nombre = conductor_nombre;
        this.conductor_apellido = conductor_apellido;
        this.evaluador_id = evaluador_id;
        this.evaluador_nombre = evaluador_nombre;
        this.evaluador_apellido = evaluador_apellido;
        this.evaluacion_notaExamTeo = evaluacion_notaExamTeo;
        this.evaluacion_notaExamPrac = evaluacion_notaExamPrac;
    }

    public String getEvaluacion_id() {
        return evaluacion_id;
    }

    public void setEvaluacion_id(String evaluacion_id) {
        this.evaluacion_id = evaluacion_id;
    }

    public String getConductor_id() {
        return conductor_id;
    }

    public void setConductor_id(String conductor_id) {
        this.conductor_id = conductor_id;
    }

    public String getConductor_nombre() {
        return conductor_nombre;
    }

    public void setConductor_nombre(String conductor_nombre) {
        this.conductor_nombre = conductor_nombre;
    }

    public String getConductor_apellido() {
        return conductor_apellido;
    }

    public void setConductor_apellido(String conductor_apellido) {
        this.conductor_apellido = conductor_apellido;
    }

    public String getEvaluador_id() {
        return evaluador_id;
    }

    public void setEvaluador_id(String evaluador_id) {
        this.evaluador_id = evaluador_id;
    }

    public String getEvaluador_nombre() {
        return evaluador_nombre;
    }

    public void setEvaluador_nombre(String evaluador_nombre) {
        this.evaluador_nombre = evaluador_nombre;
    }

    public String getEvaluador_apellido() {
        return evaluador_apellido;
    }

    public void setEvaluador_apellido(String evaluador_apellido) {
        this.evaluador_apellido = evaluador_apellido;
    }

    public Double getEvaluacion_notaExamTeo() {
        return evaluacion_notaExamTeo;
    }

    public void setEvaluacion_notaExamTeo(Double evaluacion_notaExamTeo) {
        this.evaluacion_notaExamTeo = evaluacion_notaExamTeo;
    }

    public Double getEvaluacion_notaExamPrac() {
        return evaluacion_notaExamPrac;
    }

    public void setEvaluacion_notaExamPrac(Double evaluacion_notaExamPrac) {
        this.evaluacion_notaExamPrac = evaluacion_notaExamPrac;
    }

    
    
}
