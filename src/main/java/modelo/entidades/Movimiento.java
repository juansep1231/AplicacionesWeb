package modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La clase Movimiento representa un registro de movimiento de dinero entre dos cuentas.
 * Contiene atributos como el concepto del movimiento, su valor, la fecha en que se realizó,
 * y las cuentas de origen y destino del movimiento.
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 * 
 */
@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "concepto")
	private String concepto;
	
	@Column(name = "valor")
	private double valor;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cuenta_origen")
	private Cuenta origen;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cuenta_destino")
	private Cuenta destino;
	
	/**
	 * Crea una instancia de Movimiento con sus atributos inicializados en null.
	 */
	public Movimiento() {
	}
	

	/**
	 * Crea una instancia de Movimiento con los valores recibidos como parámetros.
	 * @param concepto concepto del movimiento
	 * @param valor valor del movimiento
	 * @param fecha fecha en que se realizó el movimiento
	 * @param origen cuenta de origen del movimiento
	 * @param destino cuenta de destino del movimiento
	 */
	public Movimiento(String concepto, double valor, Date fecha, Cuenta origen, Cuenta destino) {
		super();
		this.concepto = concepto;
		this.valor = valor;
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getConcepto() {
		return concepto;
	}



	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}



	public double getValor() {
		return valor;
	}



	public void setValor(double valor) {
		this.valor = valor;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public Cuenta getOrigen() {
		return origen;
	}



	public void setOrigen(Cuenta origen) {
		this.origen = origen;
	}



	public Cuenta getDestino() {
		return destino;
	}



	public void setDestino(Cuenta destino) {
		this.destino = destino;
	}



	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", concepto=" + concepto + ", valor=" + valor + ", fecha=" + fecha + ", origen="
				+ origen + ", destino=" + destino + "]";
	}
	
	
	
}
