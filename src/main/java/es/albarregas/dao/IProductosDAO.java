/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Productos;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public interface IProductosDAO {
    public ArrayList<Productos> getProductos();
    public Productos getOne(String where);
    public void closeConnection();
    
}
