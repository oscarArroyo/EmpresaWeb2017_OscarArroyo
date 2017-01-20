/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Clientes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Oscar
 */
public class ClientesDAO implements IClientesDAO{
    Clientes cliente=null;
    String consulta;
    Statement sentencia;

    @Override
    public void inicializarClientes(int idUsuario) {
        consulta = "insert into Clientes(IdCliente,Nombre,Apellidos,NIF) values("+idUsuario+",' ',' ',' ')";
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(consulta);
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
