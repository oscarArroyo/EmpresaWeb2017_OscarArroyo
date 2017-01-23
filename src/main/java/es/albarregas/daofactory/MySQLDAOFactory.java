package es.albarregas.daofactory;

import es.albarregas.dao.CaractProdsDAO;
import es.albarregas.dao.CategoriasDAO;
import es.albarregas.dao.ClientesDAO;
import es.albarregas.dao.ICaractProdsDAO;
import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IImagenesDAO;
import es.albarregas.dao.ILineasPedidosDAO;
import es.albarregas.dao.IMarcasDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.dao.ImagenesDAO;
import es.albarregas.dao.LineasPedidosDAO;
import es.albarregas.dao.MarcasDAO;
import es.albarregas.dao.PedidosDAO;
import es.albarregas.dao.ProductosDAO;
import es.albarregas.dao.UsuariosDAO;





public class MySQLDAOFactory extends DAOFactory{
 public IUsuariosDAO getUsuariosDAO() {
        return new UsuariosDAO();
 }
   public IProductosDAO getProductosDAO(){
       return new ProductosDAO();
   }
  public ICategoriasDAO getCategoriasDAO(){
      return new CategoriasDAO();
  }
  public IMarcasDAO getMarcasDAO(){
      return new MarcasDAO();
  }
  public IClientesDAO getClientesDAO(){
      return new ClientesDAO();
  }
  public IImagenesDAO getImagenesDAO(){
      return new ImagenesDAO();
  }
  public ICaractProdsDAO getCaractProdsDAO(){
      return new CaractProdsDAO();
  }
  public IPedidosDAO getPedidosDAO(){
      return new PedidosDAO();
  }
  public ILineasPedidosDAO getLineasPedidosDAO(){
      return new LineasPedidosDAO();
  }
}

    
    

