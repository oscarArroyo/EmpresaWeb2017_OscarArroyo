/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Usuarios;

/**
 *
 * @author Oscar
 */
public interface IUsuariosDAO {
    public void addUsuario(Usuarios usu);
    public void closeConnection();
    public Usuarios getOne(String where);

    
}
