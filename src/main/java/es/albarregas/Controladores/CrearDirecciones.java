/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controladores;

import es.albarregas.beans.Clientes;
import es.albarregas.beans.Direcciones;
import es.albarregas.beans.Provincias;
import es.albarregas.beans.Pueblos;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.IProvinciasDAO;
import es.albarregas.dao.IPueblosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CrearDirecciones", urlPatterns = {"/CrearDirecciones"})
public class CrearDirecciones extends HttpServlet {

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
            IPueblosDAO pbdao= daof.getPueblosDAO();
            IDireccionesDAO ddao=daof.getDireccionesDAO();
            IProvinciasDAO prodao=daof.getProvinciasDAO();
          
        if(request.getParameter("cancelar")!=null){
            response.sendRedirect("JSP/panelUsuario.jsp");
        }else if(request.getParameter("aceptar")!=null){
            String where=" where codigoPostal="+request.getParameter("cod");
            Pueblos pb=pbdao.getOne(where);
            Direcciones dir = new Direcciones();
            HttpSession sesion = request.getSession();
            Clientes cli = (Clientes)sesion.getAttribute("cliente");
            dir.setIdCliente(cli.getIdCliente());
            dir.setNombreDireccion(request.getParameter("nbdir"));
            dir.setDireccion(request.getParameter("dir"));
            dir.setCodigoPostal(request.getParameter("cod"));
            dir.setIdPueblo(pb.getIdPueblo());
            dir.setTelefono(request.getParameter("tlf"));
            ddao.addDireccion(dir);
            response.sendRedirect("JSP/panelUsuario.jsp");
        }else if(request.getParameter("btncodigo")!=null){
            System.out.println("entro btn");
            String where=" where codigoPostal="+request.getParameter("cod");
            Pueblos pb=pbdao.getOne(where);
            String where2="where idProvincia="+pb.getIdProvincia();
            Provincias pro =prodao.getOne(where2);
            response.getWriter().write(pb.getNombre() +"/"+pro.getNombre());
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
