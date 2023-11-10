package modelo.jpa;

import modelo.dao.MovimientoDAO;
import modelo.entidades.Movimiento;

/**
 * Esta clase implementa la interfaz MovimientoDAO y proporciona métodos para acceder y 
 * manipular objetos de tipo Movimiento en una base de datos utilizando JPA.
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 */
public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento, Integer> implements MovimientoDAO {
	/**
	 * Crea una instancia de JPAMovimientoDAO.
	 */
	public JPAMovimientoDAO() {
		super(Movimiento.class);
	}
}
