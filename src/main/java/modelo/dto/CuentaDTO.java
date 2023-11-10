package modelo.dto;

import modelo.entidades.Cuenta;
import modelo.jpa.JPAGenericDAO;


/**
 * 
 * Clase que une atributos de movimeinto y cuenta
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 */
public class CuentaDTO {

	private int id;
	private String nombre;
	private double totalcalculado;
	
	/**
	 * Construye un objeto de tipo CuentaDTO sin especificar sus atributos
	 */
	public CuentaDTO() {}

	/**
	 * 
	 * Construye un objeto de tipo CuentaDTO especificando sus atributos
	 * @param id ID único de la cuenta 
	 * @param nombre Nombre de la cuenta
	 * @param totalcalculado Total calculado de la cuenta
	 */
	public CuentaDTO(int id, String nombre, double totalcalculado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.totalcalculado = totalcalculado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getTotalcalculado() {
		return totalcalculado;
	}

	public void setTotalcalculado(double totalcalculado) {
		this.totalcalculado = totalcalculado;
	}
}
