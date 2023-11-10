package modelo.jpa;

import modelo.dao.CuentaDAO;
import modelo.dao.DAOFactory;
import modelo.dao.MovimientoDAO;
import modelo.dao.UsuarioDAO;

/**
 * Clase JPADAOFactory que extiende de la clase abstracta DAOFactory.
 * Implementa los métodos de creación de instancias de los DAOs de Usuario, Cuenta y Movimiento
 * mediante la creación de instancias de las clases JPAUsuarioDAO, JPACuentaDAO y JPAMovimientoDAO respectivamente.
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 * 
 */
public class JPADAOFactory extends DAOFactory {

	/**
	 * Método para crear una instancia del DAO de Usuario.
	 * @return una instancia de JPAUsuarioDAO.
	 */
	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new JPAUsuarioDAO();
	}

	/**
	 * Método para crear una instancia del DAO de Cuenta.
	 * @return una instancia de JPACuentaDAO.
	 */
	@Override
	public CuentaDAO getCuentaDAO() {
		return new JPACuentaDAO();
	}

	/**
	 * Método para crear una instancia del DAO de Movimiento.
	 * @return una instancia de JPAMovimientoDAO.
	 */
	@Override
	public MovimientoDAO getMovimientoDAO() {
		return new JPAMovimientoDAO();
	}

}
