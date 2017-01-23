/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pedidos;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Oscar
 */
public class PedidosDAO implements IPedidosDAO{

    Pedidos pedido;
    String consulta;
    PreparedStatement preparada;
    @Override
    public void addPedido(Pedidos pedido) {
         consulta = "insert into pedidos(fecha,estado,idCliente) values(now(),?,?)";
        try {
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);
            preparada.setString(1, pedido.getEstado());
            preparada.setInt(2, pedido.getIdCliente());
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
