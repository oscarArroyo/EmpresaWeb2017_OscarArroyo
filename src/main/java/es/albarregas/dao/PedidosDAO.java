/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pedidos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Oscar
 */
public class PedidosDAO implements IPedidosDAO{

    Pedidos pedido;
    String consulta;
    PreparedStatement preparada;
    Statement sentencia;
    @Override
    public void addPedido(Pedidos pedido) {
         consulta = "insert into pedidos(fecha,estado,idCliente) values(now(),?,?)";
        try {
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);
            preparada.setString(1,String.valueOf(pedido.getEstado()));
            preparada.setInt(2, pedido.getIdCliente());
            preparada.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }
    @Override
    public Pedidos getOne(String where) {
        consulta ="select IdPedido from pedidos " +where;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()){
                    pedido=new Pedidos();
                    pedido.setIdPedido(resultado.getInt("IdPedido"));
                    }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return pedido;
    }
     @Override
    public void deletePedido(String where) {
        consulta = "delete from pedidos "+where;    
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

    @Override
    public void updatePedido(Pedidos pedido) {
        System.out.println("Entro updatePedidos");
         try {
            String sql = "update Pedidos set estado=? where idCliente=? and estado='n'";
            preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1,String.valueOf(pedido.getEstado()));
            preparada.setInt(2, pedido.getIdCliente());
            System.out.println("pedido.getIdCliente: "+pedido.getIdCliente());
            System.out.println("pedido.getEstado: "+pedido.getEstado());
            preparada.executeUpdate();
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    
    }
    
}
