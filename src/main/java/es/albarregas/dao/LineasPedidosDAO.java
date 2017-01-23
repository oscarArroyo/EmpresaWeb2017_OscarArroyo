/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.LineasPedidos;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Oscar
 */
public class LineasPedidosDAO implements ILineasPedidosDAO{
    String consulta;
    PreparedStatement preparada;
    @Override
    public void addLineaPedido(LineasPedidos lp) {
        consulta = "insert into lineaspedidos(idpedido,numerolinea,idproducto,cantidad) values(?,?,?,?)";
        try {
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);
            preparada.setInt(1, lp.getIdPedido());
            preparada.setInt(2, lp.getNumeroLinea());
            preparada.setInt(3, lp.getIdProducto());
            preparada.setInt(4, lp.getCantidad());
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
