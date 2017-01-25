/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controladores;

import es.albarregas.beans.Clientes;
import es.albarregas.beans.Usuarios;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        IUsuariosDAO udao = daof.getUsuariosDAO();
        IClientesDAO cdao = daof.getClientesDAO();
        Usuarios usu;
        Clientes cli;
        HttpSession sesion;
        if (request.getParameter("registro")!= null) {
            String where="Where Email='"+request.getParameter("usuario")+"'";
            usu = udao.getOne(where);
            if(usu==null){
            Usuarios usu3 =new Usuarios();
            sesion = request.getSession(true);
            usu3.setEmail(request.getParameter("usuario"));
            usu3.setClave(request.getParameter("pass"));
            
            udao.addUsuario(usu3);
            Usuarios usu2=udao.getOne(where);
            int idUsuario = usu2.getIdUsuario();
            cdao.inicializarClientes(idUsuario);
            String where2="Where IdCliente="+idUsuario;
            cli=cdao.getOne(where2);
            usu3.setIdUsuario(idUsuario);
            sesion.setAttribute("sesion", usu3);
            cli.setIdCliente(idUsuario);
            sesion.setAttribute("cliente", cli);
            }else{
                request.setAttribute("error3", "Este email ya esta registrado");
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        } else if(request.getParameter("lg")!= null){
            String where = "Where Email='"+request.getParameter("emLog")+"'";
            usu = udao.getOne(where);
            if(usu==null){
                request.setAttribute("error", "Email incorrecto");
            }else if(usu.getClave().equals(request.getParameter("passLog"))){
                sesion = request.getSession(true);
                int idCliente = usu.getIdUsuario();
                String where2="Where IdCliente="+idCliente;
                cli=cdao.getOne(where2);
                sesion.setAttribute("sesion", usu);
                cli.setIdCliente(idCliente);
                sesion.setAttribute("cliente", cli);
            }else{
                request.setAttribute("error2", "Clave incorrecta");
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        }else if (request.getParameter("cs").equals("s")){
            sesion=request.getSession();
            sesion.invalidate();
            response.sendRedirect("index.jsp");
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
