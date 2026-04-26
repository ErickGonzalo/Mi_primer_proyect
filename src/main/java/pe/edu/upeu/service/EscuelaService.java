package pe.edu.upeu.service;

import pe.edu.upeu.model.Escuela;
import pe.edu.upeu.enums.NivelEducativo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EscuelaService {
    private List<Escuela> lista = new ArrayList<>();

    public void guardar(Escuela escuela) {
        lista.add(escuela);
    }

    public List<Escuela> listarTodo() {
        return lista;
    }

    public void eliminar(Escuela escuela) {
        lista.remove(escuela);
    }

    public List<Escuela> filtrarPorNivel(NivelEducativo nivel) {
        return lista.stream()
                .filter(e -> e.getNivel() == nivel)
                .collect(Collectors.toList());
    }
    public boolean existeCodigo(String codigo) {
        return lista.stream()
                .anyMatch(e -> e.getCodigo().equals(codigo));
    }
}