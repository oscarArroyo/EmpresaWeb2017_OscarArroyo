/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pedidos;

/**
 *
 * @author Oscar
 */
public interface IPedidosDAO {
    public void addPedido(Pedidos pedido);
    public void closeConnection();
}
