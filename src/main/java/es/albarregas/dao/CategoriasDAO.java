/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Categorias;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class CategoriasDAO implements ICategoriasDAO {

    @Override
    //Metodo para obtener todas las categorías y su id
    public ArrayList<Categorias> getCategorias() {
        ArrayList<Categorias> cat = new ArrayList();
        String consulta = "select IdCategoria,Nombre from Categorias";
        Categorias categoria;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    categoria = new Categorias();
                    categoria.setIdCategoria(resultado.getInt("IdCategoria"));
                    categoria.setNombre(resultado.getString("Nombre"));
                    cat.add(categoria);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }

        return cat;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
