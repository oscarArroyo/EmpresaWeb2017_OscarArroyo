/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pueblos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Oscar
 */
public class PueblosDAO implements IPueblosDAO {

    String consulta;
    Statement sentencia;
    Pueblos pue;
    @Override
    //Método para obtener el idPueblo,nombre,idProvincia de un pueblo según el codigo postal
    public Pueblos getOne(String where) {
        consulta = "select idPueblo,nombre,idProvincia from pueblos " + where;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    pue = new Pueblos();
                    pue.setIdPueblo(resultado.getInt("idPueblo"));
                    pue.setNombre(resultado.getString("nombre"));
                    pue.setIdProvincia(resultado.getInt("idProvincia"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return pue;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
