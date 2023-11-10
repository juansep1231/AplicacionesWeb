package modelo.entidades;

/**
 * Enumeración que define los tipos de cuenta disponibles en la aplicación.
 * Los tipos de cuenta pueden ser INGRESO, GASTO o INGRESOGASTO.
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 */
public enum CuentaTipo {
	/**
	 * Tipo de cuenta para ingresos.
	 */
	INGRESO ("INGRESO", 1),
	
	/**
	 * Tipo de cuenta para gastos.
	 */
	GASTO ("GASTO", 2),
	
	/**
	 * Tipo de cuenta para cuentas que representan tanto ingresos como gastos.
	 */
	INGRESOGASTO ("INGRESOGASTO",3);
	
	
	
	private String nombreTipo;
	private int idTipo;
	
	/**
	 * 
	 * Constructor para los tipos de cuenta.
	 * @param nombreTipo el nombre del tipo de cuenta
	 * @param id el identificador del tipo de cuenta
	 * 
	 */
	CuentaTipo(String nombreTipo, int id) {
		this.idTipo = id;
		this.nombreTipo = nombreTipo; 
	}
	
	
	public String getNombreTipo() {
		return nombreTipo;
	}
	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	
	
}
