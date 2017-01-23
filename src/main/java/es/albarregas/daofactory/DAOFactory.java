package es.albarregas.daofactory;

import es.albarregas.dao.ICaractProdsDAO;
import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IImagenesDAO;
import es.albarregas.dao.IMarcasDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.dao.ILineasPedidosDAO;




public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public abstract IUsuariosDAO getUsuariosDAO();
    public abstract IProductosDAO getProductosDAO();
    public abstract ICategoriasDAO getCategoriasDAO();
    public abstract IMarcasDAO getMarcasDAO();
    public abstract IClientesDAO getClientesDAO();
    public abstract IImagenesDAO getImagenesDAO();
    public abstract ICaractProdsDAO getCaractProdsDAO();
    public abstract IPedidosDAO getPedidosDAO();
    public abstract ILineasPedidosDAO getLineasPedidosDAO();
    
    public static DAOFactory getDAOFactory(int tipo){
        DAOFactory daof = null;
        
        switch(tipo){
            case MYSQL:
                daof = new MySQLDAOFactory();
                break;
            
        }
        
        return daof;
    }
    
}
