/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controladores;

import com.google.gson.Gson;
import es.albarregas.beans.Clientes;
import es.albarregas.beans.Direcciones;
import es.albarregas.beans.General;
import es.albarregas.beans.LineasPedidos;
import es.albarregas.beans.Pedidos;
import es.albarregas.beans.Productos;
import es.albarregas.beans.Provincias;
import es.albarregas.beans.Pueblos;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.IGeneralDAO;
import es.albarregas.dao.ILineasPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IProvinciasDAO;
import es.albarregas.dao.IPueblosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletContext;
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

        //Preguntamos si el usuario ha pulsado el botón de aceptar el pedido o de cancelarlo
        if (request.getParameter("boton") != null) {

            //Si el usuario ha pulsado el boton de cancelar se borra el pedido y las lineas de pedido asociadas a ese usuario cuyo estado es nuevo
            if (request.getParameter("boton").equals("can")) {
                IPedidosDAO pdao = daof.getPedidosDAO();
                String where = "where idPedido=" + request.getParameter("pedido");
                pdao.deletePedido(where);
                sesion.removeAttribute("pedido");
                sesion.removeAttribute("listalp");
                response.sendRedirect("index.jsp");

                //Si el usuario ha pulsado el botón de aceptar se le redirige a la pagina de finalizar la compra
            } else if (request.getParameter("boton").equals("ace")) {
                response.sendRedirect("JSP/finalizarCompraDatos.jsp");
            }

            //Se recogen los datos recogidos en el formulario de datos personales del usuario.
            //Si el usuario cambia algun dato se actualizan estos datos en la sesion y en la base de datos
        } else if (request.getParameter("aceptarDatos") != null) {
            Clientes cli = (Clientes) sesion.getAttribute("cliente");
            if (!cli.getNombre().equals(request.getParameter("nombre")) || !cli.getApellidos().equals(request.getParameter("apellidos")) || !cli.getNif().equals(request.getParameter("nif")) || !cli.getFechaNacimiento().equals(request.getParameter("fnacimiento"))) {
                IClientesDAO idao = daof.getClientesDAO();
                cli.setNombre(request.getParameter("nombre"));
                cli.setApellidos(request.getParameter("apellidos"));
                cli.setNif(request.getParameter("nif"));
                cli.setFechaNacimiento(Date.valueOf(request.getParameter("fnacimiento")));
                idao.updateClientes(cli);
                sesion.setAttribute("cliente", cli);
                response.sendRedirect("JSP/finalizarCompraDirecciones.jsp");
            }

            //Método por el cual mediante ajax el usuario selecciona una direccion y se cargan sus datos para su visualizacion en una vista
            //Se utiliza json para enviar la informacion
        } else if (request.getParameter("dir") != null && Integer.parseInt(request.getParameter("id")) != 0) {
            IPueblosDAO pbdao = daof.getPueblosDAO();
            IProvinciasDAO prodao = daof.getProvinciasDAO();
            ArrayList<String> lista = new ArrayList();
            IDireccionesDAO idao = daof.getDireccionesDAO();
            String where3 = " where idDireccion=" + request.getParameter("id");
            Direcciones dire = idao.getOne(where3);
            lista.add(dire.getNombreDireccion());
            lista.add(dire.getDireccion());
            lista.add(dire.getTelefono());
            lista.add(dire.getCodigoPostal());
            String where = " where codigoPostal='" + dire.getCodigoPostal() + "'";
            Pueblos pb = pbdao.getOne(where);
            String where2 = "where idProvincia=" + pb.getIdProvincia();
            Provincias pro = prodao.getOne(where2);
            lista.add(pro.getNombre());
            lista.add(pb.getNombre());
            String json = new Gson().toJson(lista);
            response.setContentType("application/json");
            response.getWriter().write(json);

            //Si el usuario no selecciona una direccion válida se vuelve a la pagina indicando un mensaje de error
        } else if (request.getParameter("aceptarDirecciones") != null) {
            if (Integer.parseInt(request.getParameter("sel")) == 0) {
                request.setAttribute("error", "Seleccione una direccion válida");
                request.getRequestDispatcher("JSP/finalizarCompraDirecciones.jsp").forward(request, response);
            } else {

                //Se añade a la sesión la direccion seleccionada por el usuario
                sesion.setAttribute("dirUsu", request.getParameter("sel"));
                response.sendRedirect("JSP/finalizarCompraPago.jsp");
            }
        } else if (request.getParameter("aceptarPago") != null) {

            //Accedemos si el usuario ha rellenado el último formulario el de pago
            //Aqui se cambia el estado al pedido de nuevo a remitido 
            //Se introduce en la tabla pedidos la base imponible,los gastos de envío, el iva y el idDirección del envio
            //Se introduce en la tabla lineaspedidos el precio unitario
            //Se actualiza la tablas producto con su nuevo stock (stock-cantidad)
            //Se borran la lista de lineaspedidos de la sesión
            //Se borra el pedido de la sesión
            IPedidosDAO pdao = daof.getPedidosDAO();
            IProductosDAO prdao = daof.getProductosDAO();
            IGeneralDAO gendao = daof.getGeneralDAO();
            ILineasPedidosDAO lpdao = daof.getLineasPedidosDAO();
            Double base = 0.0;
            Pedidos pedido = (Pedidos) sesion.getAttribute("pedido");
            Clientes cli = (Clientes) sesion.getAttribute("cliente");
            pedido.setIdCliente(cli.getIdCliente());
            ServletContext contexto = request.getServletContext();
            ArrayList<Productos> listap = (ArrayList) contexto.getAttribute("prods");
            pedido.setEstado('r');
            ArrayList<LineasPedidos> listalp = (ArrayList) sesion.getAttribute("listalp");
            for (int i = 0; i < listalp.size(); i++) {
                String where = "where idProducto= " + listalp.get(i).getIdProducto();
                Productos producto = prdao.getOne(where);
                if (producto.getStock() - listalp.get(i).getCantidad() < 0) {
                    pedido.setEstado('p');
                } else {

                    producto.setStock(producto.getStock() - listalp.get(i).getCantidad());
                }
                for (int j = 0; j < listap.size(); j++) {
                    if (listalp.get(i).getIdProducto() == listap.get(j).getIdProducto()) {
                        listap.get(i).setStock(producto.getStock());
                    }
                }
                String where2 = "where idPedido=" + pedido.getIdPedido() + " and numeroLinea=" + (i + 1);
                Productos prod = prdao.getOne(where);
                listalp.get(i).setPrecioUnitario((float) prod.getPrecioUnitario());
                base += listalp.get(i).getPrecioUnitario() * listalp.get(i).getCantidad();
                lpdao.updateLineaPedidoPrecio(listalp.get(i), where2);
                prdao.updateProductos(producto);
            }
            General gen = gendao.getGeneral();
            pedido.setGastosEnvio(gen.getGastosEnvio());
            pedido.setIva(gen.getIva());
            int idUser = Integer.parseInt(String.valueOf(sesion.getAttribute("dirUsu")));
            pedido.setIdDireccion(idUser);
            pedido.setBaseImponible(base);
            pdao.updatePedido(pedido);

            //Antes de borrar los pedidos y la lista de lineas de pedidos hay que pasara a la siguiente jsp para poder visualizar la factura
            contexto.setAttribute("prods", listap);
            request.setAttribute("lilp", listalp);
            sesion.removeAttribute("listalp");
            sesion.removeAttribute("pedido");

            //Datos necesarios para poder visualizar los datos en la factura
            request.setAttribute("cli", cli);
            request.setAttribute("ped", pedido);

            IDireccionesDAO ddao = daof.getDireccionesDAO();
            String where = "where idDireccion=" + idUser;
            Direcciones dir = ddao.getOne(where);
            request.setAttribute("dir", dir);
            IPueblosDAO pbdao = daof.getPueblosDAO();
            String where2 = "where codigoPostal=" + dir.getCodigoPostal();
            Pueblos pueblo = pbdao.getOne(where2);
            request.setAttribute("pue", pueblo);
            IProvinciasDAO prodao = daof.getProvinciasDAO();
            String where3 = "where idProvincia=" + pueblo.getIdProvincia();
            Provincias pro = prodao.getOne(where3);
            request.setAttribute("pro", pro);

            request.getRequestDispatcher("JSP/factura.jsp").forward(request, response);
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
