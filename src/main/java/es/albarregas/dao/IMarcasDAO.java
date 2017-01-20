/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Marcas;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public interface IMarcasDAO {
    public ArrayList<Marcas> getMarcas();
    public void closeConnection();
    
}
