package pe.edu.upeu.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {


    private static final String URL = "jdbc:sqlite:data/escuelas_db";

    private Connection conexion;

    public Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL);
                System.out.println("Conectado a SQLite ");
            }
        } catch (SQLException e) {
            System.out.println("Error conexión : " + e.getMessage());
        }
        return conexion;
    }
}