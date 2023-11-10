package modelo.jpa;

import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;


/**
 * Clase JPAUsuarioDAO que implementa la interfaz UsuarioDAO y hereda de la clase JPAGenericDAO.
 * Se encarga de gestionar las operaciones CRUD relacionadas con la entidad Usuario en la base de datos.
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 */
public class JPAUsuarioDAO extends JPAGenericDAO<Usuario, Integer> implements UsuarioDAO{

	/**
	 * Constructor de la clase que llama al constructor de la clase padre JPAGenericDAO con el parámetro Usuario.class.
	 */
	public JPAUsuarioDAO() {
		super(Usuario.class);
	}

	/**
	 * Método que busca un usuario en la base de datos que coincida con el nombre de usuario y la clave recibidos como parámetros.
	 * @param nombre El nombre de usuario a buscar.
	 * @param clave La clave de usuario a buscar.
	 * @return El usuario que coincide con el nombre y clave recibidos como parámetros, o null si no se encuentra ningún usuario.
	 */
	@Override
	public Usuario autorizar(String nombre, String clave) {
		
		String JPQL="SELECT p FROM Usuario p WHERE p.clave= :clave AND p.nombre= :nombre";
		javax.persistence.Query query= em.createQuery(JPQL);
		query.setParameter("clave", clave);
		query.setParameter("nombre", nombre);
		Usuario persona=null;
		try {
			persona=(Usuario) query.getSingleResult();
		} catch (Exception e) {
			persona=null;
		}
		return persona;
	}
	

}
