/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Usuarios;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public interface IUsuariosDAO {
    public void addUsuario(Usuarios usu);
    public void closeConnection();
    public Usuarios getOne(String where);
    public void updateFechaAcceso(Usuarios usu);
    public ArrayList<Usuarios> getUsuarios();
    public void updateUsuariosBloqueados(String eleccion,int idUsuario);
    
}
