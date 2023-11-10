package modelo.dao;

import modelo.jpa.JPADAOFactory;

/**
 * 
 * Clase abstracta que implementa el patron de diseño Abstract Factory con ayuda del patron de diseño singleton
 * e inyeccion de dependencias, contiene las firmas de los metodos que se implementan en las clases concretas
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 */
public abstract class DAOFactory {
    // Sería mejor por inyección de objetos
    protected static DAOFactory factory = new JPADAOFactory();

    /**
     * Metodo por el cual se puede acceder a las entidades y ejecutar sus respectivos métodos
     * @return JPADAOFactory
     */
    public static DAOFactory getFactory() {
        return factory;
    }

    public abstract UsuarioDAO getUsuarioDAO();
    public abstract CuentaDAO getCuentaDAO();
    public abstract MovimientoDAO getMovimientoDAO();


}