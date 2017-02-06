/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controladores;

import es.albarregas.beans.CaractProds;
import es.albarregas.beans.Productos;
import es.albarregas.dao.ICaractProdsDAO;
import es.albarregas.dao.IImagenesDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "MostrarProductoCompleto", urlPatterns = {"/MostrarProductoCompleto"})
public class MostrarProductoCompleto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Método para sacar todos los datos referentes a un producto(caracteristicas,imagenes,stock,etc) y enviarlos a la página jsp de producto.jsp
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IProductosDAO pdao = daof.getProductosDAO();
        IImagenesDAO idao = daof.getImagenesDAO();
        ICaractProdsDAO cdao = daof.getCaractProdsDAO();
        String where = "Where IdProducto=" + request.getParameter("producto");
        ArrayList<String> imagenes = idao.getImagenes(where);
        ArrayList<CaractProds> cp = cdao.getCaractProds(where);
        Productos pro = pdao.getOne(where);
        pro.setIdProducto(Integer.parseInt(request.getParameter("producto")));
        request.setAttribute("pro", pro);
        request.setAttribute("imagenes", imagenes);
        request.setAttribute("cp", cp);
        request.getRequestDispatcher("JSP/producto.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
