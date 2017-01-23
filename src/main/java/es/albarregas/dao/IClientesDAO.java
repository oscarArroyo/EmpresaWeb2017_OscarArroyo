/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Clientes;


/**
 *
 * @author Oscar
 */
public interface IClientesDAO {
    public void inicializarClientes(int idUsuario);
    public void closeConnection();
    public Clientes getOne(String where);
    public void updateClientes(Clientes cliente);
}
