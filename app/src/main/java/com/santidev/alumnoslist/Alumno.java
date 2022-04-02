package com.santidev.alumnoslist;

public class Alumno {

    private int id;
    private String nombre;
    private String carrera;
    private String matricula;

    public Alumno(String nombre, String carrera, String matricula){
        this.nombre = nombre;
        this.carrera = carrera;
        this.matricula = matricula;
    }
    public Alumno(int id,String nombre, String carrera, String matricula){
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
