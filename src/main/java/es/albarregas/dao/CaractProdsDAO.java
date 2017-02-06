/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.CaractProds;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class CaractProdsDAO implements ICaractProdsDAO {

    @Override

    //Método para obtener todas las caracteristicas con su nombre y su valor
    public ArrayList<CaractProds> getCaractProds(String where) {
        ArrayList<CaractProds> cp = new ArrayList();
        CaractProds cps;
        String consulta = "select descripcion,nombre from caractProds inner join caracteristicas using(idCaracteristica)" + where;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    cps = new CaractProds();
                    cps.setDescripcion(resultado.getString("descripcion"));
                    cps.setNombre(resultado.getString("nombre"));
                    cp.add(cps);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }

        return cp;

    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
