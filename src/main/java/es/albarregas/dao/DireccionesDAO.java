/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Direcciones;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Oscar
 */
public class DireccionesDAO implements IDireccionesDAO{
    String consulta;
    PreparedStatement preparada;
    @Override
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
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
