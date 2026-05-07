package pe.edu.upeu.service;

import pe.edu.upeu.enums.NivelEducativo;
import pe.edu.upeu.model.Escuela;
import pe.edu.upeu.repository.EscuelaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class EscuelaService {

    private EscuelaRepository repo = new EscuelaRepository();

    // 🔴 ANTES (lista en memoria)
    /*
    private List<Escuela> lista = new ArrayList<>();
    */

    public void guardar(Escuela e) {
        repo.guardar(e);
    }

    public List<Escuela> listarTodo() {
        return repo.listar();
    }

    public void actualizar(Escuela e) {
        repo.actualizar(e);
    }

    public void eliminar(Escuela e) {
        repo.eliminar(e.getId()); // 🔥 usa ID
    }

    public boolean existeCodigo(String codigo) {
        return listarTodo().stream()
                .anyMatch(e -> e.getCodigo().equalsIgnoreCase(codigo));
    }

    public List<Escuela> filtrarPorNivel(NivelEducativo nivel) {
        return listarTodo().stream()
                .filter(e -> e.getNivel() == nivel)
                .collect(Collectors.toList());
    }
}