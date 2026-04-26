package pe.edu.upeu.model;

import pe.edu.upeu.enums.NivelEducativo;

public class Escuela {
    private String nombre;
    private NivelEducativo nivel;
    private String codigo;
    private String direccion;
    private String matricula;

    public Escuela(String nombre, NivelEducativo nivel, String codigo, String direccion, String matricula) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.codigo = codigo;
        this.direccion = direccion;
        this.matricula = matricula;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public NivelEducativo getNivel() { return nivel; }
    public void setNivel(NivelEducativo nivel) { this.nivel = nivel; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}