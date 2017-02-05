/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Marcas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class MarcasDAO implements IMarcasDAO{

    @Override
    //Método para obtener el nombre y el id de todas las marcas
    public ArrayList<Marcas> getMarcas() {
        ArrayList<Marcas> mar = new ArrayList();
        String consulta = "select IdMarca,Denominacion from Marcas";
        Marcas marca;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    marca = new Marcas();
                    marca.setIdMarca(resultado.getInt("IdMarca"));
                    marca.setDenominacion(resultado.getString("Denominacion"));
                    mar.add(marca);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }

        return mar;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}