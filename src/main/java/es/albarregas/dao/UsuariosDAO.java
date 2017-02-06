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
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class UsuariosDAO implements IUsuariosDAO {

    Usuarios usuario=null;
    String consulta;
    PreparedStatement preparada;
    Statement sentencia;

    @Override
    //Método para añadir un usuario
    public void addUsuario(Usuarios usu) {
        consulta = "insert into usuarios(Email,Clave,tipo) values(?,password(?),'u')";
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
    //Método para obtener un usuario según su idUsuario
    public Usuarios getOne(String where) {
        try {
            consulta = "Select idUsuario,Email,Clave,tipo,bloqueado from Usuarios " + where;
            sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            while (resultado.next()) {
                usuario = new Usuarios();
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setEmail(resultado.getString("Email"));
                usuario.setClave(resultado.getString("Clave"));
                usuario.setTipo(resultado.getString("tipo").charAt(0));
                usuario.setBloqueado(resultado.getString("bloqueado").charAt(0));
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
    @Override
    //Método para actualizar la fecha del último acceso de un usuario según su idUsuario
    public void updateFechaAcceso(Usuarios usu) {
        try {
            consulta = "update usuarios set ultimoAcceso=now() where idUsuario=?";
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);
            preparada.setInt(1, usu.getIdUsuario());
            preparada.executeUpdate();
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    @Override
    //Método para obtener el idUsuario,email,si esta bloqueado o no de todos los usuarios
    public ArrayList<Usuarios> getUsuarios() {
         ArrayList<Usuarios> lista = new ArrayList();
        consulta = "select idUsuario,email,bloqueado from usuarios";
        try {
            sentencia= ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    usuario = new Usuarios();
                    usuario.setIdUsuario(resultado.getInt("idUsuario"));
                    usuario.setEmail(resultado.getString("email"));
                    usuario.setBloqueado(resultado.getString("bloqueado").charAt(0));
                    lista.add(usuario);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }

        return lista;
    }
    @Override
    //Método para bloquear o desbloquear usuarios
    public void updateUsuariosBloqueados(String eleccion,int idUsuario) {
        try {
            String sql = "update Usuarios set bloqueado='"+eleccion+"' where idUsuario="+idUsuario;
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }
    }

