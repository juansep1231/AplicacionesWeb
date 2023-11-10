package modelo.dao;

import java.util.List;

import modelo.dto.CuentaDTO;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaTipo;

/**
 * 
 * Interfaz que contiene las firmas de los métodos de la clase JPACuentaDAO
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 */
public interface CuentaDAO extends GenericDAO<Cuenta, Integer> {
	
	public List<CuentaDTO> getConsolidadoCuentasIngreso(int mes);
	public List<CuentaDTO> getConsolidadoCuentsEgreso(int mes);
	public List<Cuenta> getConsolidadoCuentasIngresoEgreso();
	public List<Cuenta> getCuentasByType(CuentaTipo tipo);
}
