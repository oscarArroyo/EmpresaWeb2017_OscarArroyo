/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controladores;

import es.albarregas.beans.Productos;
import es.albarregas.beans.Usuarios;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "Administracion", urlPatterns = {"/Administracion"})
public class Administracion extends HttpServlet {

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
        IProductosDAO pdao=daof.getProductosDAO();
        IUsuariosDAO udao=daof.getUsuariosDAO();
        //Si el admin pulsa cancelar se vuelve al panel
        if(request.getParameter("cancelar")!=null){
            response.sendRedirect("JSP/panelAdministrador.jsp");
        //Si el admin selecciona la opcion de ofertar producto, según chequeé o no el producto se ofertará o no
        //También se recargará el contexto
        }else if(request.getParameter("oferta")!=null){
           int idProducto=Integer.parseInt(request.getParameter("idP"));
           if(request.getParameter("chk").equals("on")){
               String eleccion="s";
            pdao.updateProductosOfertas(eleccion,idProducto);
            ServletContext contexto=request.getServletContext();
            ArrayList<Productos> listap=(ArrayList)contexto.getAttribute("prods");
            for (int i = 0; i < listap.size(); i++) {
                if(listap.get(i).getIdProducto()==idProducto){
                    listap.get(i).setOferta(eleccion);
                    break;
                }
            }
            contexto.setAttribute("prods", listap);
           }else if(request.getParameter("chk").equals("off")){
               String eleccion="n";
                pdao.updateProductosOfertas(eleccion,idProducto);
                ServletContext contexto=request.getServletContext();
            ArrayList<Productos> listap=(ArrayList)contexto.getAttribute("prods");
            for (int i = 0; i < listap.size(); i++) {
                if(listap.get(i).getIdProducto()==idProducto){
                    listap.get(i).setOferta(eleccion);
                    break;
                }
            }
            contexto.setAttribute("prods", listap);
           }
           //Si el admin selecciona la opcion de bloquear producto, según chequeé o no el producto se bloqueará o no 
           //También se recargará el contexto
        }else if(request.getParameter("bloPro")!=null){
         int idProducto=Integer.parseInt(request.getParameter("idP"));
           if(request.getParameter("chk").equals("on")){
               String eleccion="s";
            pdao.updateProductosBloqueados(eleccion,idProducto);
            ServletContext contexto=request.getServletContext();
            ArrayList<Productos> listap=(ArrayList)contexto.getAttribute("prods");
            for (int i = 0; i < listap.size(); i++) {
                if(listap.get(i).getIdProducto()==idProducto){
                    listap.get(i).setFueraCatalogo(eleccion);
                    break;
                }
            }
            contexto.setAttribute("prods", listap);
           }else if(request.getParameter("chk").equals("off")){
               String eleccion="n";
                pdao.updateProductosBloqueados(eleccion,idProducto);
                ServletContext contexto=request.getServletContext();
            ArrayList<Productos> listap=(ArrayList)contexto.getAttribute("prods");
            for (int i = 0; i < listap.size(); i++) {
                if(listap.get(i).getIdProducto()==idProducto){
                    listap.get(i).setFueraCatalogo(eleccion);
                    break;
                }
            }
            contexto.setAttribute("prods", listap);
           }
           //Si el admin selecciona la opcion de bloquear usuarios, se cargará una lista con los usuarios registrados y se mandará a la jsp de bloquearUsuarios
            }else if(request.getParameter("cu")!=null){
                ArrayList<Usuarios> listaUsuarios=udao.getUsuarios();
                request.setAttribute("listaUsuarios",listaUsuarios);
                request.getRequestDispatcher("JSP/bloquearUsuarios.jsp").forward(request, response);
           ////Si el admin selecciona la opcion de bloquear usuarios, según chequeé o no el usuario se bloqueará o no 
            }else if(request.getParameter("bloUsu")!=null){
                int idUsuario=Integer.parseInt(request.getParameter("idU"));
           if(request.getParameter("chk").equals("on")){
               String eleccion="s";
           udao.updateUsuariosBloqueados(eleccion,idUsuario);
           }else if(request.getParameter("chk").equals("off")){
               String eleccion="n";
                udao.updateUsuariosBloqueados(eleccion,idUsuario);
            }
           //Si el admin selecciona la opcion de cambiar el precio a los productos, según el numero introducido en la caja de texto se cambiará su precio
           //También se recargará el contexto
        }else if(request.getParameter("camPro")!=null){
            pdao.updateProductosPrecio(Float.parseFloat(request.getParameter("precio")), Integer.parseInt(request.getParameter("idP")));
            ServletContext contexto=request.getServletContext();
            ArrayList<Productos> listap=(ArrayList)contexto.getAttribute("prods");
            for (int i = 0; i < listap.size(); i++) {
                if(listap.get(i).getIdProducto()==Integer.parseInt(request.getParameter("idP"))){
                    listap.get(i).setPrecioUnitario(Double.parseDouble(request.getParameter("precio")));
                    break;
                }
            }
            contexto.setAttribute("prods", listap);
            //Si el admin selecciona la opcion de recargar el stock a los productos, se recargará en 20 el stock de ese producto
            //También se recargará el contexto
        }else if(request.getParameter("stPro")!=null){
            pdao.updateProductosStock(Integer.parseInt(request.getParameter("idP")));
            ServletContext contexto=request.getServletContext();
            ArrayList<Productos> listap=(ArrayList)contexto.getAttribute("prods");
            for (int i = 0; i < listap.size(); i++) {
                if(listap.get(i).getIdProducto()==Integer.parseInt(request.getParameter("idP"))){
                    listap.get(i).setStock(20);
                    break;
                }
            }
            contexto.setAttribute("prods", listap);
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
