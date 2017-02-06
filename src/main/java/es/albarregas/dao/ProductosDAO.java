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

    //Método que carga todos los productos en la aplicacion mediante el evento de inicializacion del contexto
    public ArrayList<Productos> getProductos() {
        ArrayList<Productos> lista = new ArrayList();
        consulta = "select IdProducto,Productos.Denominacion,Descripcion,fueraCatalogo,PrecioUnitario,Marcas.Denominacion,Nombre,"
                + "(select Imagen from Imagenes where Imagenes.IdProducto=Productos.IdProducto limit 1) as Imagen,Oferta,Stock,StockMinimo from Productos"
                + " inner join Marcas using(IdMarca) inner join Categorias using(IdCategoria);";

        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                while (resultado.next()) {
                    producto = new Productos();
                    producto.setIdProducto(resultado.getInt("IdProducto"));
                    producto.setDenominacion(resultado.getString("Productos.Denominacion"));
                    producto.setDescripcion(resultado.getString("Descripcion"));
                    producto.setFueraCatalogo(resultado.getString("fueraCatalogo"));
                    producto.setPrecioUnitario(resultado.getDouble("PrecioUnitario"));
                    producto.setMarca(resultado.getString("Marcas.Denominacion"));
                    producto.setCategoria(resultado.getString("Nombre"));
                    producto.setImagen(resultado.getString("Imagen"));
                    producto.setOferta(resultado.getString("Oferta"));
                    producto.setStock(resultado.getInt("Stock"));
                    producto.setStockMinimo(resultado.getInt("StockMinimo"));
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

    //Método para obtener un producto según su idProducto
    public Productos getOne(String where) {
        consulta = "select idProducto,Productos.Denominacion,Productos.descripcion,PrecioUnitario,Stock,StockMinimo,Marcas.denominacion,Categorias.nombre from productos inner join marcas using (idMarca) inner join categorias using (idCategoria)" + where;
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
                    producto.setStockMinimo(resultado.getInt("StockMinimo"));
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

    //Método para actualizar el stock de un producto según su idProducto
    public void updateProductos(Productos pro) {
        try {
            String sql = "update Productos set stock=? where idProducto=?";
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, pro.getStock());
            preparada.setInt(2, pro.getIdProducto());
            preparada.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    @Override

    //Método para ofertar o desofertar un producto
    public void updateProductosOfertas(String eleccion, int idProducto) {
        try {
            String sql = "update Productos set oferta='" + eleccion + "' where idProducto=" + idProducto;
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    @Override

    //Método para bloquear o desbloquear un producto
    public void updateProductosBloqueados(String eleccion, int idProducto) {
        try {
            String sql = "update Productos set fueraCatalogo='" + eleccion + "' where idProducto=" + idProducto;
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    @Override

    //Método para actualizar el precio de un producto
    public void updateProductosPrecio(float precioNuevo, int idProducto) {
        try {
            String sql = "update Productos set precioUnitario='" + precioNuevo + "' where idProducto=" + idProducto;
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    @Override

    //Método para actualizar el stock de un producto
    public void updateProductosStock(int idProducto) {
        try {
            String sql = "update Productos set stock=20 where idProducto=" + idProducto;
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

}
