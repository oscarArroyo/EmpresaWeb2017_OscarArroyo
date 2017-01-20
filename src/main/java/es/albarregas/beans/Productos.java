/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author Oscar
 */
public class Productos implements Serializable {
    private int IdProducto;
    private String Denominacion;
    private String Descripcion;
    private double PrecioUnitario;
    private String Marca;
    private String Categoria;
    private String Imagen;
    private ArrayList<String> Imagenes;
    private String Oferta;
    private int Stock;
    private ArrayList<CaractProds> CaractProds;

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    
    public ArrayList<CaractProds> getCaractProds() {
        return CaractProds;
    }

    public void setCaractProds(ArrayList<CaractProds> CaractProds) {
        this.CaractProds = CaractProds;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }
    

    public String getOferta() {
        return Oferta;
    }

    public void setOferta(String Oferta) {
        this.Oferta = Oferta;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public ArrayList<String> getImagenes() {
        return Imagenes;
    }

    public void setImagenes(ArrayList<String> Imagenes) {
        this.Imagenes = Imagenes;
    }
    

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getDenominacion() {
        return Denominacion;
    }

    public void setDenominacion(String Denominacion) {
        this.Denominacion = Denominacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }
    
}
