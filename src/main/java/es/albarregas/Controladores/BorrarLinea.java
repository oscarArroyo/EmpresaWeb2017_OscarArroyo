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
import java.io.PrintWriter;
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
@WebServlet(name = "BorrarLinea", urlPatterns = {"/BorrarLinea"})
public class BorrarLinea extends HttpServlet {

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
        ILineasPedidosDAO lpdao = daof.getLineasPedidosDAO();
        IPedidosDAO pdao = daof.getPedidosDAO();
        HttpSession sesion = request.getSession();
        Clientes cliente = (Clientes)sesion.getAttribute("cliente");
        String where="where idCliente="+cliente.getIdCliente();
        Pedidos pedido = pdao.getOne(where);
        String where2= "where numerolinea="+request.getParameter("nl")+ " and idPedido="+pedido.getIdPedido() ;
        lpdao.deleteLineaPedido(where2);
        Pedidos pedidos = (Pedidos)sesion.getAttribute("pedido");
        String where3="where idPedido="+pedidos.getIdPedido();
        ArrayList<LineasPedidos> listalp = lpdao.getLineasPedidos(where3);
        pedidos.setLineasPedidos(listalp);
        sesion.setAttribute("pedido", pedidos);
        if(listalp.isEmpty()){
            pdao.deletePedido(where3);
            sesion.removeAttribute("pedido");
        }
        response.getWriter().write(String.valueOf(listalp.size()));
           
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
