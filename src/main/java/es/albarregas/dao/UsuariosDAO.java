/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Oscar
 */
public class UsuariosDAO implements IUsuariosDAO {

    Usuarios usuario=null;
    String consulta;
    PreparedStatement preparada;

    @Override
    public void addUsuario(Usuarios usu) {
        consulta = "insert into usuarios(Email,Clave) values(?,?)";
        try {
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);
            preparada.setString(1, usu.getEmail());
            preparada.setString(2, usu.getClave());
            preparada.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public Usuarios getOne(String where) {
        try {
            consulta = "Select idUsuario,Email,Clave from Usuarios " + where;
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            while (resultado.next()) {
                usuario = new Usuarios();
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setEmail(resultado.getString("Email"));
                usuario.setClave(resultado.getString("Clave"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return usuario;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
