/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controladores;

import com.google.gson.Gson;
import es.albarregas.beans.Clientes;
import es.albarregas.beans.Direcciones;
import es.albarregas.beans.LineasPedidos;
import es.albarregas.beans.Pedidos;
import es.albarregas.beans.Productos;
import es.albarregas.beans.Provincias;
import es.albarregas.beans.Pueblos;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IProvinciasDAO;
import es.albarregas.dao.IPueblosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "FinalizarPedido", urlPatterns = {"/FinalizarPedido"})
public class FinalizarPedido extends HttpServlet {

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
         HttpSession sesion = request.getSession();

       if (request.getParameter("boton")!=null){
       if(request.getParameter("boton").equals("can")){
           IPedidosDAO pdao=daof.getPedidosDAO();
           String where="where idPedido="+request.getParameter("pedido");
           pdao.deletePedido(where);
           sesion.removeAttribute("pedido");
           sesion.removeAttribute("listalp");
           response.sendRedirect("index.jsp");
       }else if(request.getParameter("boton").equals("ace")){
           response.sendRedirect("JSP/finalizarCompraDatos.jsp");
       }
       }else if(request.getParameter("aceptarDatos")!=null){
           Clientes cli =(Clientes) sesion.getAttribute("cliente");
           if(!cli.getNombre().equals(request.getParameter("nombre"))||!cli.getApellidos().equals(request.getParameter("apellidos"))||!cli.getNif().equals(request.getParameter("nif"))||!cli.getFechaNacimiento().equals(request.getParameter("fnacimiento"))){
               IClientesDAO idao = daof.getClientesDAO();
               cli.setNombre(request.getParameter("nombre"));
               cli.setApellidos(request.getParameter("apellidos"));
               cli.setNif(request.getParameter("nif"));
               cli.setFechaNacimiento(Date.valueOf(request.getParameter("fnacimiento")));
               idao.updateClientes(cli);
               sesion.setAttribute("cliente", cli);
           response.sendRedirect("JSP/finalizarCompraDirecciones.jsp");
           }
       }else if(request.getParameter("dir")!=null && Integer.parseInt(request.getParameter("id"))!=0){
           System.out.println("Entro en if");
           IPueblosDAO pbdao = daof.getPueblosDAO();
           IProvinciasDAO prodao = daof.getProvinciasDAO();
          
           ArrayList<String> lista= new ArrayList();
           IDireccionesDAO idao = daof.getDireccionesDAO();
           String where3=" where idDireccion="+request.getParameter("id");
           Direcciones dire=idao.getOne(where3);
           lista.add(dire.getNombreDireccion());
           lista.add(dire.getDireccion());
           lista.add(dire.getTelefono());
           lista.add(dire.getCodigoPostal());
           String where=" where codigoPostal='"+dire.getCodigoPostal()+"'";
           Pueblos pb=pbdao.getOne(where);
           String where2="where idProvincia="+pb.getIdProvincia();
           Provincias pro =prodao.getOne(where2);
           lista.add(pro.getNombre());
           lista.add(pb.getNombre());
           String json = new Gson().toJson(lista);
           response.setContentType("application/json");
           response.getWriter().write(json);
           
       }else if(request.getParameter("aceptarDirecciones")!=null){
           if(Integer.parseInt(request.getParameter("sel"))==0){
           request.setAttribute("error", "Seleccione una direccion válida");
           request.getRequestDispatcher("JSP/finalizarCompraDirecciones.jsp").forward(request, response);
           
           }else{
              
               sesion.setAttribute("dirUsu", request.getParameter("sel"));
               response.sendRedirect("JSP/finalizarCompraPago.jsp");
           }
       }else if(request.getParameter("aceptarPago")!=null){
           System.out.println("Entro aceptarPago");
           IPedidosDAO pdao = daof.getPedidosDAO();
           IProductosDAO prdao= daof.getProductosDAO();
           Pedidos pedido =(Pedidos) sesion.getAttribute("pedido");
           Clientes cli =(Clientes) sesion.getAttribute("cliente");
           pedido.setIdCliente(cli.getIdCliente());
           System.out.println("estado "+pedido.getEstado());
           pedido.setEstado('r');
           ArrayList<LineasPedidos> listalp = (ArrayList)sesion.getAttribute("listalp");
           for (int i = 0; i < listalp.size(); i++) {
               System.out.println("idProducto:" +listalp.get(i).getIdProducto());
               System.out.println("cantidad:" +listalp.get(i).getCantidad());
               String where = "where idProducto= "+listalp.get(i).getIdProducto();
               Productos producto = prdao.getOne(where);
               producto.setStock(producto.getStock()-listalp.get(i).getCantidad());
               prdao.updateProductos(producto);
           }
           pdao.updatePedido(pedido);
           sesion.removeAttribute("listalp");
           sesion.removeAttribute("pedido");
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
