/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controladores;

import es.albarregas.beans.Clientes;
import es.albarregas.beans.Usuarios;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "PanelCliente", urlPatterns = {"/PanelCliente"})
public class PanelCliente extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IClientesDAO cdao = daof.getClientesDAO();
        if (request.getParameter("cancelar") != null) {
            response.sendRedirect("JSP/panelUsuario.jsp");
        } else if (request.getParameter("aceptar") != null) {
            HttpSession sesion = request.getSession(true);
            Clientes cliente = new Clientes();
            cliente.setNombre(request.getParameter("nombre"));
            cliente.setApellidos(request.getParameter("apellidos"));
            cliente.setNif(request.getParameter("nif"));
            cliente.setFechaNacimiento(Date.valueOf(request.getParameter("fnacimiento")));
            
            Usuarios usuario=(Usuarios)sesion.getAttribute("sesion");
            System.out.println(usuario.getIdUsuario());
            cliente.setIdCliente(usuario.getIdUsuario());
            Clientes cli2 = (Clientes) sesion.getAttribute("cliente");
            if(!cliente.getNombre().equals(cli2.getNombre())||!cliente.getApellidos().equals(cli2.getApellidos()) || !cliente.getNif().equals(cli2.getNif()) || !cliente.getFechaNacimiento().equals(cli2.getFechaNacimiento())){
            cdao.updateClientes(cliente);
            cliente.setFechaAlta(cli2.getFechaAlta());
            sesion.setAttribute("cliente", cliente);
            }
            response.sendRedirect("JSP/panelUsuario.jsp");
        }
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
