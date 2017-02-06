/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.General;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Oscar
 */
public class GeneralDAO implements IGeneralDAO {

    String consulta;
    Statement sentencia;
    General gen = new General();

    @Override

    //Método para obtener el iva y los gastos de envio
    public General getGeneral() {
        consulta = "select * from general";
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    gen.setGastosEnvio(resultado.getFloat("gastosEnvio"));
                    gen.setIva(resultado.getFloat("Iva"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }

        return gen;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
