package modelo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import modelo.dao.GenericDAO;

/**
 * Implementación genérica de las operaciones CRUD para entidades JPA.
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 * @param <T> Tipo de entidad.
 * @param <ID> Tipo de identificador de entidad.
 */
public class JPAGenericDAO<T, ID> implements GenericDAO<T, ID> {

	private Class<T> persistentClass;
	protected EntityManager em;

	/**
	 * Crea una nueva instancia de JPAGenericDAO con la clase de entidad persistente especificada.
	 * @param persistentClass Clase de entidad persistente.
	 */
	public JPAGenericDAO(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
		this.em = Persistence.createEntityManagerFactory("examenweb").createEntityManager();
	}

	/**
	 * Crea una registro de la entidad en la base de datos.
	 * @param entity Entidad a crear.
	 */
	public void create(T entity) {
		em.getTransaction().begin();
		try {
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR:JPAGenericDAO:create " + e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
	}
	

	/*
	 * Obtiene una entidad por su identificador.
	 * @param id Identificador de la entidad a obtener.
	 * @return Entidad con el identificador especificado.
	 */
	public T getById(ID id) {
		return em.find(persistentClass, id);
	}
	
	/**
	 * Actualiza una entidad existente en la base de datos.
	 * @param entity Entidad a actualizar.
	 */
	public void update(T entity) {
		em.getTransaction().begin();
		try {
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR:JPAGenericDAO:update " + e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

	}

	/**
	 * Elimina una entidad existente en la base de datos.
	 * @param entity Entidad a eliminar.
	 */
	public void delete(T entity) {
		em.getTransaction().begin();
		try {
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR:JPAGenericDAO:delete " + e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

	}

	/**
	 * Elimina una entidad por su identificador.
	 * @param id Identificador de la entidad a eliminar.
	 */
	public void deleteByID(ID id) {
		T entity = this.getById(id);
		if (entity != null)
			this.delete(entity);
	}

	/**
	 * @author carlos iniguez
	 * @return Lista de Entidades basado en parametros
	 * @param attributes Lista (String) de campos sobre los cuales se va a realizar
	 *                   la busqueda
	 * @param values     Lista (String) de valores que pueden tomar los campos de
	 *                   attributes
	 */
	@SuppressWarnings("unchecked")
	public List<T> get(String[] attributes, String[] values) {
		// Se crea un criterio de consulta
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		// Se establece la clausula FROM
		Root<T> root = criteriaQuery.from(this.persistentClass);
		// Se establece la clausula SELECT
		criteriaQuery.select(root); // criteriaQuery.multiselect(root.get(atr))
		// Se configuran los predicados, combinados por AND
		Predicate predicate = criteriaBuilder.conjunction();
		for (int i = 0; i < attributes.length; i++) {
			Predicate sig = criteriaBuilder.like(root.get(attributes[i]).as(String.class), values[i]);
			// Predicate sig =
			// criteriaBuilder.like(root.get(attributes[i]).as(String.class),values[i]);
			predicate = criteriaBuilder.and(predicate, sig);
		}
		// Se establece el WHERE
		criteriaQuery.where(predicate);

		Query query = em.createQuery(criteriaQuery);
		return query.getResultList();

	}

	/**
	 * @author carlos iniguez
	 * @return Lista de Usuarios basado en parametros y paginacion
	 * @param attributes Lista (String) de campos sobre los cuales se va a realizar
	 *                   la busqueda
	 * @param values     Lista (String) de valores que pueden tomar los campos de
	 *                   attributes
	 * @param order      Nombre del Campo por el cual se realizará el ordenamiento.
	 *                   Este siempre es Ascendente
	 * @param index      Numero de indice de fila para presentar ( paginacion)
	 * @param size       Numero de filas que se deben presentar (paginacion). Si
	 *                   pones -1 o 0 no se tiene paginacion.
	 */
	@SuppressWarnings("unchecked")
	public List<T> get(String[] attributes, String[] values, String order, int index, int size) {
		// Se crea un criterio de consulta
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		// Se establece la clausula FROM
		Root<T> root = criteriaQuery.from(this.persistentClass);
		// Se establece la clausula SELECT
		criteriaQuery.select(root); // criteriaQuery.multiselect(root.get(atr))
		// Se configuran los predicados, combinados por AND
		Predicate predicate = criteriaBuilder.conjunction();
		for (int i = 0; i < attributes.length; i++) {
			Predicate sig = criteriaBuilder.like(root.get(attributes[i]).as(String.class), values[i]);
			// Predicate sig =
			// criteriaBuilder.like(root.get(attributes[i]).as(String.class),
			// values[i]);
			predicate = criteriaBuilder.and(predicate, sig);
		}
		// Se establece el WHERE
		criteriaQuery.where(predicate);
		// Se establece el orden
		if (order != null)
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		// Se crea el resultado
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size); // Se realiza la query
			return tq.getResultList();
		} else {
			// Se realiza la query
			Query query = em.createQuery(criteriaQuery);
			return query.getResultList();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> get() {
		// Se crea un criterio de consulta
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		// Se establece la clausula FROM
		Root<T> root = criteriaQuery.from(this.persistentClass);
		// Se establece la clausula SELECT
		criteriaQuery.select(root); // criteriaQuery.multiselect(root.get(atr))
		
		Query query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}



}
