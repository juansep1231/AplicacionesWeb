package modelo.dao;

import modelo.entidades.Usuario;

/**
 * 
 * Interfaz que contiene las firmas de los métodos de la clase JPAUsuarioDAO
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 */
public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {
	public Usuario autorizar(String nombre, String clave);
}
