package modelo.dao;

import java.util.List;


/**
 * Interfaz que contiene las firmas de los métodos de la clase JPAGenericDAO
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 * @param <T> Cambia dependiendo de la entidad que extienda
 * @param <ID> Define el ID unico de cada entidad
 */
public interface GenericDAO<T, ID> {
	
	public T getById(ID id);
	public List<T> get();
	public List<T> get(String[] attributes, String[] values); //like + and
	public List<T> get(String[] attributes, String[] values, String order, int index, int size);
	
	public void create(T entity);
	public void update(T entity);
	public void delete(T entity);
	public void deleteByID(ID id);
	
}