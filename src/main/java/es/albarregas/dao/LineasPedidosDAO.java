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
    public ArrayList<LineasPedidos> getLineasPedidos(String where) {
        ArrayList<LineasPedidos> lista = new ArrayList();
        consulta = "select IdPedido,NumeroLinea,IdProducto,Cantidad from lineaspedidos " + where;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    LineasPedidos lp = new LineasPedidos();
                    lp.setIdPedido(resultado.getInt("IdPedido"));
                    lp.setNumeroLinea(resultado.getInt("NumeroLinea"));
                    lp.setIdProducto(resultado.getInt("IdProducto"));
                    lp.setCantidad(resultado.getInt("Cantidad"));
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
    public void deleteLineaPedido(String where) {
       consulta = "delete from lineaspedidos "+where;    
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
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

   

    
    
}
