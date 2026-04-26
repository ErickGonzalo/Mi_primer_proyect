module pe.edu.upeu {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // Permite que JavaFX acceda a los atributos de tus modelos (TableView)
    opens pe.edu.upeu.model to javafx.base;

    // Permite que FXML acceda al controlador
    opens pe.edu.upeu.controller to javafx.fxml;

    // Exporta tu paquete principal (donde está App.java)
    exports pe.edu.upeu;
    opens pe.edu.upeu.enums to javafx.base;
}