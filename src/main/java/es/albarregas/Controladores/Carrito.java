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
@WebServlet(name = "Carrito", urlPatterns = {"/Carrito"})
public class Carrito extends HttpServlet {

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
        ILineasPedidosDAO lpdao =daof.getLineasPedidosDAO();
        Pedidos pedido = new Pedidos();
        LineasPedidos lp = new LineasPedidos();
        ArrayList<LineasPedidos> listalp = new ArrayList();
        HttpSession sesion = request.getSession();
        
        if(sesion.getAttribute("pedido")==null){
        pedido.setEstado("n");
        Clientes cliente=(Clientes)sesion.getAttribute("cliente");
        pedido.setIdCliente(cliente.getIdCliente());
        String where="where idCliente="+cliente.getIdCliente()+" and estado='"+pedido.getEstado()+"'";
        pdao.addPedido(pedido);
        Pedidos pedido2=pdao.getOne(where);
        pedido.setIdPedido(pedido2.getIdPedido());
        sesion.setAttribute("pedido", pedido);
        
        }
        Pedidos pedido3=(Pedidos)sesion.getAttribute("pedido");
        pedido3.setIdPedido(pedido3.getIdPedido());
        lp.setIdPedido(pedido3.getIdPedido());
        lp.setCantidad(1);
        if(pedido3.getLineasPedidos()==null){
            lp.setNumeroLinea(1);
        }else{
            listalp=pedido3.getLineasPedidos();
            lp.setNumeroLinea(pedido3.getLineasPedidos().get(pedido3.getLineasPedidos().size()-1).getNumeroLinea()+1);

        }
        lp.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
        listalp.add(lp);
        lpdao.addLineaPedido(lp); 
        pedido.setLineasPedidos(listalp);
        sesion.setAttribute("pedido", pedido3);
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
