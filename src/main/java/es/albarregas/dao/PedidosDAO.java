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
import java.util.ArrayList;

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
    //Método para añadir un pedido
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
    //Método para obtener el idPedido a partir del idCliente
    public Pedidos getOne(String where) {
        consulta ="select IdPedido,estado from pedidos " +where;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()){
                    pedido=new Pedidos();
                    pedido.setIdPedido(resultado.getInt("IdPedido"));
                    pedido.setEstado(resultado.getString("estado").charAt(0));
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
    //Método para borrar un pedido cuando no haya lineas de ese pedido
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
    //Método para actualizar el pedido de estado nuevo a estado remitido al cual se le añade los gastos de envio,iva e idDireccion
    public void updatePedido(Pedidos pedido) {
         try {
            String sql = "update Pedidos set estado=?,gastosEnvio=?,iva=?,idDireccion=?,baseImponible=? where idCliente=? and estado='n'";
            preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1,String.valueOf(pedido.getEstado()));
            preparada.setDouble(2, pedido.getGastosEnvio());
            preparada.setDouble(3, pedido.getIva());
            preparada.setInt(4, pedido.getIdDireccion());
            preparada.setDouble(5, pedido.getBaseImponible());
            preparada.setInt(6, pedido.getIdCliente());
            preparada.executeUpdate();
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    
    }

    @Override
    public ArrayList<Pedidos> getPedidos(String where) {
        ArrayList<Pedidos> lista = new ArrayList();
        consulta = "select idPedido,fecha,baseImponible,gastosEnvio,iva,IdPedido from pedidos " + where;
        System.out.println("Consulta: "+consulta);
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    pedido = new Pedidos();
                    pedido.setIdPedido(resultado.getInt("idPedido"));
                    pedido.setFecha(resultado.getDate("fecha"));
                    pedido.setBaseImponible(resultado.getDouble("baseImponible"));
                    pedido.setGastosEnvio(resultado.getDouble("gastosEnvio"));
                    pedido.setIva(resultado.getDouble("iva"));
                    pedido.setIdPedido(resultado.getInt("idPedido"));
                    lista.add(pedido);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return lista;
    }
    
}
