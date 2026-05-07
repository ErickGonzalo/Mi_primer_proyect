package pe.edu.upeu.repository;

import pe.edu.upeu.config.ConexionSQLite;
import pe.edu.upeu.enums.NivelEducativo;
import pe.edu.upeu.model.Escuela;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscuelaRepository {

    private ConexionSQLite conexion = new ConexionSQLite() ;

    public void guardar(Escuela e) {

        String sql = "INSERT INTO escuela(nombre,nivel,codigo,direccion,matricula) VALUES(?,?,?,?,?)";

        try (Connection conn = conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getNivel().name());
            ps.setString(3, e.getCodigo());
            ps.setString(4, e.getDireccion());
            ps.setInt(5, e.getMatricula());

            ps.executeUpdate();

            System.out.println("Guardado en BD ");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Escuela> listar() {

        List<Escuela> lista = new ArrayList<>();
        String sql = "SELECT * FROM escuela";

        try (Connection conn = conexion.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Escuela e = new Escuela(
                        rs.getString("nombre"),
                        NivelEducativo.valueOf(rs.getString("nivel").toUpperCase()), // 🔥 FIX ENUM
                        rs.getString("codigo"),
                        rs.getString("direccion"),
                        rs.getInt("matricula")
                );

                e.setId(rs.getInt("id"));

                lista.add(e);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public void actualizar(Escuela e) {

        String sql = "UPDATE escuela SET nombre=?, nivel=?, codigo=?, direccion=?, matricula=? WHERE id=?";

        try (Connection conn = conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getNivel().name());
            ps.setString(3, e.getCodigo());
            ps.setString(4, e.getDireccion());
            ps.setInt(5, e.getMatricula());
            ps.setInt(6, e.getId()); //

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void eliminar(int id) {

        String sql = "DELETE FROM escuela WHERE id=?";

        try (Connection conn = conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}