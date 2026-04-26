package pe.edu.upeu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pe.edu.upeu.model.Escuela;
import pe.edu.upeu.enums.NivelEducativo;
import pe.edu.upeu.service.EscuelaService;

public class EscuelaController {

    @FXML private TextField txtNombre, txtCodigo, txtDireccion, txtMatricula;
    @FXML private ComboBox<NivelEducativo> cbNivel;
    @FXML private TableView<Escuela> tabla;
    @FXML private TableColumn<Escuela, String> colNombre, colCodigo, colDireccion, colMatricula;
    @FXML private TableColumn<Escuela, NivelEducativo> colNivel;

    private EscuelaService service = new EscuelaService();
    private ObservableList<Escuela> obsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));

        cbNivel.setItems(FXCollections.observableArrayList(NivelEducativo.values()));

        service.guardar(new Escuela("Benito Juárez", NivelEducativo.PREPARATORIA, "CCT-001", "Av. Reforma 10", "450"));
        service.guardar(new Escuela("Miguel Hidalgo", NivelEducativo.PRIMARIA, "CCT-002", "Calle Independencia 5", "300"));

        actualizarTablaCompleta();
        tabla.setItems(obsList);
    }

    @FXML
    private void btnNuevo() {
        limpiarFormulario();
        txtNombre.requestFocus();
    }

    @FXML
    private void btnGuardar() {
        String nombre = txtNombre.getText().trim();
        String codigo = txtCodigo.getText().trim();
        NivelEducativo nivel = cbNivel.getValue();

        if (nivel == null || nombre.isEmpty() || codigo.isEmpty()) {
            mostrarAlerta("Error de Validación", "Nombre, Nivel y Código son campos obligatorios.");
            return;
        }

        if (service.existeCodigo(codigo)) {
            mostrarAlerta("Error de Duplicado", "La clave '" + codigo + "' ya está registrada.");
            return;
        }

        Escuela nueva = new Escuela(
                nombre,
                nivel,
                codigo,
                txtDireccion.getText().trim(),
                txtMatricula.getText().trim()
        );
        System.out.println("Escuela registrada:");
        System.out.println("Nombre: " + nueva.getNombre());
        System.out.println("Código: " + nueva.getCodigo());
        System.out.println("Nivel: " + nueva.getNivel());
        System.out.println("Dirección: " + nueva.getDireccion());
        System.out.println("Matrícula: " + nueva.getMatricula());

        service.guardar(nueva);
        actualizarTablaCompleta();
        limpiarFormulario();
    }

    @FXML
    private void btnEliminar() {
        Escuela seleccionada = tabla.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            service.eliminar(seleccionada);
            actualizarTablaCompleta();
        } else {
            mostrarAlerta("Advertencia", "Por favor, seleccione una escuela de la tabla para eliminar.");
        }
    }

    @FXML
    private void btnFiltrar() {
        NivelEducativo nivelSeleccionado = cbNivel.getValue();
        if (nivelSeleccionado != null) {
            obsList.setAll(service.filtrarPorNivel(nivelSeleccionado));
        } else {
            mostrarAlerta("Aviso", "Seleccione un nivel en el ComboBox para poder filtrar.");
        }
    }

    @FXML
    private void btnListarTodo() {
        actualizarTablaCompleta();
        cbNivel.getSelectionModel().clearSelection();
    }

    private void actualizarTablaCompleta() {
        obsList.setAll(service.listarTodo());
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtCodigo.clear();
        txtDireccion.clear();
        txtMatricula.clear();
        cbNivel.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}