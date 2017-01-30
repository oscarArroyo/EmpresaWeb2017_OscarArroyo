/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Provincias;

/**
 *
 * @author Oscar
 */
public interface IProvinciasDAO {
     public Provincias getOne(String where);
     public void closeConnection();
}
