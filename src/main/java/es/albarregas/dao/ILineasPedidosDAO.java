/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.LineasPedidos;

/**
 *
 * @author Oscar
 */
public interface ILineasPedidosDAO {
    public void addLineaPedido(LineasPedidos lp);
    public void closeConnection();
    
}
