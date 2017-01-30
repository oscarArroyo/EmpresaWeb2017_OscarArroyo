/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Provincias;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Oscar
 */
public class ProvinciasDAO implements IProvinciasDAO {
    String consulta;
    Statement sentencia;
    Provincias pro;
    @Override
    public Provincias getOne(String where) {
         consulta = "select idProvincia,nombre from provincias " + where;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    pro = new Provincias();
                    pro.setIdProvincia(resultado.getInt("idProvincia"));
                    pro.setNombre(resultado.getString("nombre"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return pro;
    }
    
      @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
