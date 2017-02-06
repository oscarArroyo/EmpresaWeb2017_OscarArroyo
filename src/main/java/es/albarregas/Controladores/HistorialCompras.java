/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controladores;

import es.albarregas.beans.Clientes;
import es.albarregas.beans.LineasPedidos;
import es.albarregas.beans.Pedidos;
import es.albarregas.dao.ILineasPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "HistorialCompras", urlPatterns = {"/HistorialCompras"})
public class HistorialCompras extends HttpServlet {

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
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IPedidosDAO pdao = daof.getPedidosDAO();
        ILineasPedidosDAO lpdao = daof.getLineasPedidosDAO();
        HttpSession sesion = request.getSession();
        Clientes cli = (Clientes) sesion.getAttribute("cliente");
        //Método que se encarga de buscar los pedidos de un usuario que este 'r'=remitido o 'p'=pendiente por falta de stock
        //Tambén se sacan las lineas de ese pedido
        String where = "where estado='p'or estado='r' and  idCliente=" + cli.getIdCliente();
        ArrayList<Pedidos> listaPedidos = pdao.getPedidos(where);
        ArrayList<LineasPedidos> listaLineas = new ArrayList();
        for (int i = 0; i < listaPedidos.size(); i++) {
            String where2 = "Where idPedido=" + listaPedidos.get(i).getIdPedido();
            ArrayList<LineasPedidos> lineas = lpdao.getLineasPedidos(where2);
            for (int j = 0; j < lineas.size(); j++) {
                listaLineas.add(lineas.get(j));
            }
        }
        request.setAttribute("historialPedidos", listaPedidos);
        request.setAttribute("historialLineas", listaLineas);
        request.getRequestDispatcher("JSP/historialCompras.jsp").forward(request, response);
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
