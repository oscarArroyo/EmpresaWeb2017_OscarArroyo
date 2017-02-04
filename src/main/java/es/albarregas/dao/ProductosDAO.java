/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Productos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class ProductosDAO implements IProductosDAO {

    String consulta;
    Productos producto;
    Statement sentencia;

    @Override
    public ArrayList<Productos> getProductos() {
        ArrayList<Productos> lista = new ArrayList();
        consulta = "select IdProducto,Productos.Denominacion,Descripcion,PrecioUnitario,Marcas.Denominacion,Nombre,"
                + "(select Imagen from Imagenes where Imagenes.IdProducto=Productos.IdProducto limit 1) as Imagen,Oferta,Stock from Productos"
                + " inner join Marcas using(IdMarca) inner join Categorias using(IdCategoria);";

        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    producto = new Productos();
                    producto.setIdProducto(resultado.getInt("IdProducto"));
                    producto.setDenominacion(resultado.getString("Productos.Denominacion"));
                    producto.setDescripcion(resultado.getString("Descripcion"));
                    producto.setPrecioUnitario(resultado.getDouble("PrecioUnitario"));
                    producto.setMarca(resultado.getString("Marcas.Denominacion"));
                    producto.setCategoria(resultado.getString("Nombre"));
                    producto.setImagen(resultado.getString("Imagen"));
                    producto.setOferta(resultado.getString("Oferta"));
                    producto.setStock(resultado.getInt("Stock"));
                    lista.add(producto);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }

        return lista;
    }

    @Override
    public Productos getOne(String where) {
        System.out.println("Entro getOne");
        consulta ="select idProducto,Productos.Denominacion,Productos.descripcion,PrecioUnitario,Stock,Marcas.denominacion,Categorias.nombre from productos inner join marcas using (idMarca) inner join categorias using (idCategoria)" +where;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    producto = new Productos();
                    producto.setIdProducto(resultado.getInt("idProducto"));
                    producto.setDenominacion(resultado.getString("Productos.Denominacion"));
                    producto.setDescripcion(resultado.getString("Productos.Descripcion"));
                    producto.setPrecioUnitario(resultado.getDouble("PrecioUnitario"));
                    producto.setStock(resultado.getInt("Stock"));
                    producto.setMarca(resultado.getString("Marcas.Denominacion"));
                    producto.setCategoria(resultado.getString("Categorias.Nombre"));
                    }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return producto;
    }
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    @Override
    public void updateProductos(Productos pro) {
        System.out.println("Entro updateProductos");
        try {
            String sql = "update Productos set stock=? where idProducto=?";
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, pro.getStock());
            preparada.setInt(2, pro.getIdProducto());
            System.out.println("producto.getStock: "+producto.getStock());
            System.out.println("producto.getIdProducto: "+producto.getIdProducto());
            preparada.executeUpdate();
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

}
