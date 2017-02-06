/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controladores;

import es.albarregas.beans.Clientes;
import es.albarregas.beans.Direcciones;
import es.albarregas.beans.LineasPedidos;
import es.albarregas.beans.Pedidos;
import es.albarregas.beans.Usuarios;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.ILineasPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IUsuariosDAO;
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
        IPedidosDAO pdao = daof.getPedidosDAO();
        ILineasPedidosDAO lpdao = daof.getLineasPedidosDAO();
        IDireccionesDAO ddao = daof.getDireccionesDAO();
        String url;
        Usuarios usu;
        Clientes cli;
        HttpSession sesion;

        //Método al cual se accede si el usuario ha rellenado el registro
        if (request.getParameter("registro") != null) {

            //Preguntamos si el email esta en nuestra base de datos
            String where = "Where Email='" + request.getParameter("usuario") + "'";
            usu = udao.getOne(where);

            //Si no lo encontramos creamos un registro en la tabla usuarios con el email y la clave y otro en la tabla clientes con los datos vacíos
            //Solo se rellena el campo idCliente que es el mismo que el idUsuario que se acaba de crear
            //Estos dos registros se añaden a la sesion
            if (usu == null) {
                Usuarios usu3 = new Usuarios();
                sesion = request.getSession(true);
                usu3.setEmail(request.getParameter("usuario"));
                usu3.setClave(request.getParameter("pass"));
                udao.addUsuario(usu3);
                Usuarios usu2 = udao.getOne(where);
                int idUsuario = usu2.getIdUsuario();
                cdao.inicializarClientes(idUsuario);
                String where2 = "Where IdCliente=" + idUsuario;
                cli = cdao.getOne(where2);
                usu3.setIdUsuario(idUsuario);
                sesion.setAttribute("sesion", usu3);
                cli.setIdCliente(idUsuario);
                sesion.setAttribute("cliente", cli);
                url = "JSP/panelUsuario.jsp";
            } else {

                //Si el email esta registrado mostramos un mensaje de error
                request.setAttribute("error3", "Este email ya esta registrado");
                url = "JSP/registro.jsp";
            }
            request.getRequestDispatcher(url).forward(request, response);

            //Método al cual se accede si el usuario ha rellenado el formulario de logeo
        } else if (request.getParameter("lg") != null) {

            //Se busca si el email introducido en el formulario se encuentra en la base de datos
            String where = "Where clave=password('" + request.getParameter("passLog") + "') and email='" + request.getParameter("emLog") + "'";
            usu = udao.getOne(where);

            //Si no se encuentra se muestra un mensaje
            if (usu == null) {
                request.setAttribute("error", "Email incorrecto o contraseña incorrectos");

                //Si el email se encuentra y la clave es correcta se crea la sesion con el usuario y el cliente
                //Además se comprobará si tiene algun pedido nuevo y si tiene alguno se añadira a la sesión (carrito persistente)
                //También añadirá a la sesión las direcciones de ese cliente
            } else if (usu.getBloqueado() == 's') {
                request.setAttribute("error4", "Usuario bloqueado");
            } else {
                sesion = request.getSession(true);
                int idCliente = usu.getIdUsuario();
                String where2 = "Where IdCliente=" + idCliente;
                cli = cdao.getOne(where2);
                sesion.setAttribute("sesion", usu);
                cli.setIdCliente(idCliente);
                sesion.setAttribute("cliente", cli);
                udao.updateFechaAcceso(usu);
                Pedidos pedido = pdao.getOne(where2);
                if (pedido != null && pedido.getEstado() == 'n') {
                    where2 = "Where IdPedido=" + pedido.getIdPedido();
                    ArrayList<LineasPedidos> listalp = lpdao.getLineasPedidos(where2);
                    pedido.setLineasPedidos(listalp);
                    sesion.setAttribute("pedido", pedido);
                }
                String where3 = " Where IdCliente=" + idCliente;
                ArrayList<Direcciones> listadir = ddao.getDirecciones(where3);
                sesion.setAttribute("direcciones", listadir);
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);

            //Método para cerrar la sesión del usuario
        } else if (request.getParameter("cs").equals("s")) {
            sesion = request.getSession();
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
