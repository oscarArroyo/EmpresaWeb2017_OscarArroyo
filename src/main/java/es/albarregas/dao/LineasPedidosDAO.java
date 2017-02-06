/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.LineasPedidos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class LineasPedidosDAO implements ILineasPedidosDAO {

    String consulta;
    PreparedStatement preparada;

    @Override

    //Método para añadir una linea de pedido
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

    //Método para obtener todas las lineas de pedido de un pedido
    public ArrayList<LineasPedidos> getLineasPedidos(String where) {
        ArrayList<LineasPedidos> lista = new ArrayList();
        consulta = "select IdPedido,NumeroLinea,IdProducto,Cantidad,precioUnitario from lineaspedidos " + where;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    LineasPedidos lp = new LineasPedidos();
                    lp.setIdPedido(resultado.getInt("IdPedido"));
                    lp.setNumeroLinea(resultado.getInt("NumeroLinea"));
                    lp.setIdProducto(resultado.getInt("IdProducto"));
                    lp.setCantidad(resultado.getInt("Cantidad"));
                    lp.setPrecioUnitario(resultado.getFloat("precioUnitario"));
                    lista.add(lp);
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

    //Método para borrar una linea de pedido segun el numeroLinea
    public void deleteLineaPedido(String where) {
        consulta = "delete from lineaspedidos " + where;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(consulta);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    @Override

    //Método para actualizar la cantidad de una lineaPedido
    public void updateLineaPedido(LineasPedidos lp, String where) {
        try {

            consulta = "update lineaspedidos set cantidad=? " + where;
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);
            preparada.setInt(1, lp.getCantidad());
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

    @Override

    //Método para poner el precio unitario en su lineaPedido correspondiente
    public void updateLineaPedidoPrecio(LineasPedidos lp, String where) {
        try {
            consulta = "update lineaspedidos set precioUnitario=? " + where;
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);
            preparada.setFloat(1, lp.getPrecioUnitario());
            preparada.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

}
