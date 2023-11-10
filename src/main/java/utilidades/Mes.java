package utilidades;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Mes representa un mes del año con su id y nombre.
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 */
public class Mes {
	private int id;
	private String nombre;
	
	/**
	 * Crea un objeto Mes sin inicializar sus atributos.
	 */
	public Mes() {}

	/**
	 * Crea un objeto Mes con sus atributos inicializados.
	 * @param id el id del mes.
	 * @param nombre el nombre del mes.
	 * 
	 */
	public Mes(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
	
	
	/**
	 * Obtiene una lista con todos los meses del año.
	 * @return una lista los meses.                                                                      
	 */
	public static List<Mes> getMeses() {
		List<Mes> meses = new ArrayList<Mes>();
		meses.add(new Mes(1, "January"));
		meses.add(new Mes(2, "February"));
		meses.add(new Mes(3, "March"));
		meses.add(new Mes(4, "April"));
		meses.add(new Mes(5, "May"));
		meses.add(new Mes(6, "June"));
		meses.add(new Mes(7, "July"));
		meses.add(new Mes(8, "August"));
		meses.add(new Mes(9, "September"));
		meses.add(new Mes(10, "October"));
		meses.add(new Mes(11, "November"));
		meses.add(new Mes(12, "December"));
		return meses;
	}
}
