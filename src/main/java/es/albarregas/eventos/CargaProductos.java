/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.eventos;

import es.albarregas.beans.Categorias;
import es.albarregas.beans.Marcas;
import es.albarregas.beans.Productos;
import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IMarcasDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Oscar
 */
@WebListener
public class CargaProductos implements ServletContextListener {

    @Override
    ////Método que carga todos lo productos, las categorías y las marcas cuando se inicia la aplicacion
    public void contextInitialized(ServletContextEvent sce) {
           DAOFactory daof = DAOFactory.getDAOFactory(1);
           IProductosDAO pdao=daof.getProductosDAO();
           ICategoriasDAO cdao=daof.getCategoriasDAO();
           IMarcasDAO mdao=daof.getMarcasDAO();
           ArrayList<Productos> productos=pdao.getProductos();
           ArrayList<Categorias> categorias = cdao.getCategorias();
           ArrayList<Marcas> marcas = mdao.getMarcas();
           ServletContext contexto = sce.getServletContext();
           synchronized (contexto) {
           contexto.setAttribute("prods", productos);
           contexto.setAttribute("categorias", categorias);
           contexto.setAttribute("marcas", marcas);
           }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      ServletContext contexto = sce.getServletContext();
      contexto.removeAttribute("marcas");
      contexto.removeAttribute("categorias");
      contexto.removeAttribute("prods");
    }
    
}
