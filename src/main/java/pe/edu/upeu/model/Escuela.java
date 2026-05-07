package pe.edu.upeu.model;

import pe.edu.upeu.enums.NivelEducativo;

public class Escuela extends EntidadEducativa {

    private int id;
    private NivelEducativo nivel;
    private String codigo;
    private int matricula;

    public Escuela(String nombre, NivelEducativo nivel, String codigo, String direccion, int matricula) {
        super(nombre, direccion);
        this.nivel = nivel;
        this.codigo = codigo;
        this.matricula = matricula;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public NivelEducativo getNivel() { return nivel; }
    public void setNivel(NivelEducativo nivel) { this.nivel = nivel; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }
}