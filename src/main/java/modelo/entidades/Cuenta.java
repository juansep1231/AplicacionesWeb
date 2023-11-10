package modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Esta clase representa un objeto Cuenta que contiene información sobre una cuenta, 
 * incluyendo su ID único, nombre, total y tipo.
 * Esta clase también está anotada con anotaciones JPA 
 * para indicar que es una clase de entidad que puede ser persistida en una base de datos.
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 * 
 */
@Entity
public class Cuenta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * El ID único de la cuenta.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * El nombre de la cuenta.
	 */
	@Column
	private String nombre;
	
	/**
	 * El total de la cuenta.
	 */
	@Column
	private double total;
	
	/**
	 * El tipo de la cuenta.
	 */
	@Enumerated
	private CuentaTipo tipo;
	
	public Cuenta() {}

	public Cuenta(String nombre, double total) {
		super();
		this.nombre = nombre;
		this.total = total;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	
	public CuentaTipo getTipo() {
		return tipo;
	}

	public void setTipo(CuentaTipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", nombre=" + nombre + ", total=" + total + "]";
	}
	
	
}
