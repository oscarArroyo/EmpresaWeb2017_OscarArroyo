/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.CaractProds;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public interface ICaractProdsDAO {
    public ArrayList<CaractProds> getCaractProds(String where);
    public void closeConnection();
}
