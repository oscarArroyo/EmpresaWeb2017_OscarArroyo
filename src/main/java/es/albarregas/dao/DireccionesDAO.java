/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Direcciones;
import es.albarregas.beans.Productos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class DireccionesDAO implements IDireccionesDAO{
    String consulta;
    PreparedStatement preparada;
    Statement sentencia;
    Direcciones dir;
    @Override
    //Método para añadir una dirreccion al usuario
    public void addDireccion(Direcciones dir) {
       consulta = "insert into direcciones(idCliente,NombreDireccion,direccion,codigoPostal,idPueblo,telefono) values(?,?,?,?,?,?)";
        try {
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);
            preparada.setInt(1, dir.getIdCliente());
            preparada.setString(2, dir.getNombreDireccion());
            preparada.setString(3, dir.getDireccion());
            preparada.setString(4, dir.getCodigoPostal());
            preparada.setInt(5, dir.getIdPueblo());
            preparada.setString(6, dir.getTelefono());
            preparada.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }
    @Override
    //Método para obtener todas las direcciones de un usuario
    public ArrayList<Direcciones> getDirecciones(String where) {
        ArrayList<Direcciones> listadir = new ArrayList();
        consulta="select idDireccion,nombreDireccion,direccion,codigoPostal,telefono from direcciones "+where;
         try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    dir = new Direcciones();
                    dir.setIdDireccion(resultado.getInt("idDireccion"));
                    dir.setNombreDireccion(resultado.getString("nombreDireccion"));
                    dir.setDireccion(resultado.getString("direccion"));
                    dir.setCodigoPostal(resultado.getString("codigoPostal"));
                    dir.setTelefono(resultado.getString("telefono"));
                    listadir.add(dir);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return listadir;
    }
     @Override
     //Método para obtener una direccion de un usuario
    public Direcciones getOne(String where) {
        consulta="select idDireccion,nombreDireccion,direccion,codigoPostal,telefono from direcciones "+where;
        System.out.println(consulta);
        dir=new Direcciones();
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                 while (resultado.next()) {
                    dir.setIdDireccion(resultado.getInt("idDireccion"));
                    dir.setNombreDireccion(resultado.getString("nombreDireccion"));
                    dir.setDireccion(resultado.getString("direccion"));
                    dir.setCodigoPostal(resultado.getString("codigoPostal"));
                    dir.setTelefono(resultado.getString("telefono"));
                 }
                  
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
            return dir;
        
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

   

    
    
}
