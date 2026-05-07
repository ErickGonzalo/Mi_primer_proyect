package pe.edu.upeu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pe.edu.upeu.enums.NivelEducativo;
import pe.edu.upeu.model.Escuela;
import pe.edu.upeu.service.EscuelaService;

public class EscuelaController {

    @FXML private TextField txtNombre, txtCodigo, txtDireccion, txtMatricula;
    @FXML private ComboBox<NivelEducativo> cbNivel;
    @FXML private TableView<Escuela> tabla;

    @FXML private TableColumn<Escuela, String> colNombre, colCodigo, colDireccion;
    @FXML private TableColumn<Escuela, NivelEducativo> colNivel;
    @FXML private TableColumn<Escuela, Integer> colMatricula;

    private EscuelaService service = new EscuelaService();
    private ObservableList<Escuela> obsList = FXCollections.observableArrayList();

    private Escuela escuelaSeleccionada = null;

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));

        cbNivel.setItems(FXCollections.observableArrayList(NivelEducativo.values()));

        tabla.setItems(obsList);

        tabla.setOnMouseClicked(e -> {
            Escuela sel = tabla.getSelectionModel().getSelectedItem();
            if (sel != null) {
                escuelaSeleccionada = sel;

                txtNombre.setText(sel.getNombre());
                txtCodigo.setText(sel.getCodigo());
                txtDireccion.setText(sel.getDireccion());
                txtMatricula.setText(String.valueOf(sel.getMatricula()));
                cbNivel.setValue(sel.getNivel());
            }
        });

        actualizarTabla();
    }

    @FXML public void btnNuevo() {
        limpiar();
    }

    @FXML public void btnGuardar() {

        String nombre = txtNombre.getText().trim();
        String codigo = txtCodigo.getText().trim();
        String direccion = txtDireccion.getText().trim();
        NivelEducativo nivel = cbNivel.getValue();

        int matricula = Integer.parseInt(txtMatricula.getText());

        if (escuelaSeleccionada == null) {
            if (service.existeCodigo(codigo)) {
                alerta("Código duplicado");
                return;
            }

            service.guardar(new Escuela(nombre, nivel, codigo, direccion, matricula));

        } else {

            String nuevoCodigo = txtCodigo.getText();

            escuelaSeleccionada.setNombre(nombre);
            escuelaSeleccionada.setNivel(nivel);
            escuelaSeleccionada.setDireccion(direccion);
            escuelaSeleccionada.setMatricula(matricula);

            // escuelaSeleccionada.setCodigo(...);


            escuelaSeleccionada.setCodigo(nuevoCodigo);

            service.actualizar(escuelaSeleccionada);
        }

        actualizarTabla();
        limpiar();
    }

    @FXML public void btnEliminar() {
        Escuela sel = tabla.getSelectionModel().getSelectedItem();
        if (sel != null) {
            service.eliminar(sel);
            actualizarTabla();
        }
    }

    @FXML public void btnFiltrar() {
        obsList.setAll(service.filtrarPorNivel(cbNivel.getValue()));
    }

    @FXML public void btnListarTodo() {
        actualizarTabla();
    }

    private void actualizarTabla() {
        obsList.setAll(service.listarTodo());
    }

    private void limpiar() {
        txtNombre.clear();
        txtCodigo.clear();
        txtDireccion.clear();
        txtMatricula.clear();
        cbNivel.getSelectionModel().clearSelection();
        escuelaSeleccionada = null;
    }

    private void alerta(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).showAndWait();
    }
}