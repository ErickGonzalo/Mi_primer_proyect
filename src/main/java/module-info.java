module pe.edu.upeu {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens pe.edu.upeu.model to javafx.base;
    opens pe.edu.upeu.controller to javafx.fxml;
    exports pe.edu.upeu;
    opens pe.edu.upeu.enums to javafx.base;
}