/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class ImagenesDAO implements IImagenesDAO {

    @Override

    //Método para obtener todas las url de las imágenes de un producto
    public ArrayList<String> getImagenes(String where) {
        ArrayList<String> img = new ArrayList();
        String consulta = "select Imagen from imagenes " + where;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    img.add(resultado.getString("Imagen"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }

        return img;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
