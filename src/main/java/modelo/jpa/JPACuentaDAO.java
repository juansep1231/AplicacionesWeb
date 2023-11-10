package modelo.jpa;


import java.util.List;

import javax.persistence.Query;

import modelo.dao.CuentaDAO;
import modelo.dto.CuentaDTO;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaTipo;


/**
 * Clase que implementa la interfaz CuentaDAO y extiende de la clase JPAGenericDAO.
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 * 
 */

public class JPACuentaDAO extends JPAGenericDAO<Cuenta, Integer> implements CuentaDAO {

	/**
	 * Constructor sin parámetros que invoca al constructor de la superclase JPAGenericDAO.
	 */
	public JPACuentaDAO() {
		super(Cuenta.class);
	}
	
	/**
	 * Método que retorna una lista de objetos CuentaDTO con el consolidado de las
	 * cuentas de ingreso para un mes específico.
	 * @param mes El mes para el que se desea obtener el consolidado.
	 * @return Lista de objetos CuentaDTO con el consolidado de las cuentas de las cuentas
	 * 
	 */
	@Override
	public List<CuentaDTO> getConsolidadoCuentasIngreso(int mes) {
		String JPQL = "SELECT new modelo.dto.CuentaDTO( m.origen.id, m.origen.nombre, SUM(m.valor)) FROM Movimiento m "
				+ "WHERE m.origen.tipo= :tipo AND "
				+ "FUNC('MONTH', m.fecha)= :mes "
				+ "GROUP BY m.origen";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", CuentaTipo.INGRESO);
		query.setParameter("mes", mes);
		return query.getResultList();
	}
	
	
	/**
	 * 
	 * Método que retorna una lista de objetos CuentaDTO con el consolidado de las
	 * cuentas de egreso para un mes específico.
	 * @param mes El mes para el que se desea obtener el consolidado.
	 * @return Lista de objetos CuentaDTO con el consolidado de las cuentas de un mes especificado
	 *  
	 */
	@Override
	public List<CuentaDTO> getConsolidadoCuentsEgreso(int mes) {
		
		String JPQL = "SELECT new modelo.dto.CuentaDTO( m.destino.id, m.destino.nombre, SUM(m.valor)) FROM Movimiento m "
				+ "WHERE m.destino.tipo= :tipo AND "
				+ "FUNC('MONTH', m.fecha)= :mes "
				+ "GROUP BY m.destino";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", CuentaTipo.GASTO);
		query.setParameter("mes", mes);
		return query.getResultList();
	}
	

	/**
	 * Método que retorna una lista de objetos Cuenta con el consolidado de todas
	 * las cuentas de ingreso y egreso.
	 * @return Lista de objetos Cuenta con el consolidado de todas las cuentas 
	 */
	@Override
	public List<Cuenta> getConsolidadoCuentasIngresoEgreso() {
		String JPQL = "SELECT c FROM Cuenta c "
				+ "WHERE c.tipo= :tipo";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", CuentaTipo.INGRESOGASTO);
		return query.getResultList();
	}
	
	
	/**
	 * Método que retorna una lista de objetos Cuenta de un tipo específico.
	 * @param tipo El tipo de cuenta que se desea obtener.
	 * @return Lista de objetos Cuenta del tipo especificado.
	 */
	public List<Cuenta> getCuentasByType(CuentaTipo tipo){
		String JPQL = "SELECT c FROM Cuenta c "
				+ "WHERE c.tipo= :tipo";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", tipo);
		return query.getResultList();	
	}
}
