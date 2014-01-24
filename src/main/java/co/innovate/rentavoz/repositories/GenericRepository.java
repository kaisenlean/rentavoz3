package co.innovate.rentavoz.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * Generic Repository, providing basic CRUD operations
 * 
 * @author Jurgen Lust
 * @author $LastChangedBy: jlust $
 * 
 * @version $LastChangedRevision: 257 $
 * 
 * @param <C>
 *            the entity type
 * @param <ID>
 *            the primary key type
 */
public interface GenericRepository<C, PK extends Serializable> {
	// ~ Methods
	// ----------------------------------------------------------------

	/**
	 * Count all entities
	 * 
	 * @return the number of entities
	 */
	int countAll();

	/**
	 * Count entities based on an example
	 * 
	 * @param exampleInstance
	 *            the search criteria
	 * @return the number of entities
	 */
	int countByExample(final C exampleInstance);

	/**
	 * delete an entity from the database
	 * 
	 * @param entity
	 *            the entity to delete
	 */
	void delete(final C entity);

	/**
	 * Load all entities
	 * 
	 * @return the list of entities
	 */
	List<C> findAll();

	/**
	 * Find entities based on an example
	 * 
	 * @param exampleInstance
	 *            the example
	 * @return the list of entities
	 */
	List<C> findByExample(final C exampleInstance);

	/**
	 * Find an entity by its primary key
	 * 
	 * @param id
	 *            the primary key
	 * @return the entity
	 */
	C findById(final PK id);

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/12/2013
	 * @param id
	 * @param clazz
	 * @return
	 */
	Object findById(final PK id, Class<?> clazz);

	/**
	 * Find using a named query
	 * 
	 * @param queryName
	 *            the name of the query
	 * @param params
	 *            the query parameters
	 * 
	 * @return the list of entities
	 */
	List<C> findByNamedQuery(final String queryName, Object... params);

	/**
	 * Find using a named query
	 * 
	 * @param queryName
	 *            the name of the query
	 * @param params
	 *            the query parameters
	 * 
	 * @return the list of entities
	 */
	List<C> findByNamedQueryAndNamedParams(final String queryName,
			final Map<String, ? extends Object> params);

	/**
	 * Get the Class of the entity
	 * 
	 * @return the class
	 */
	Class<C> getEntityClass();

	/**
	 * save an entity. This can be either a INSERT or UPDATE in the database
	 * 
	 * @param entity
	 *            the entity to save
	 * 
	 * @return the saved entity
	 */
	C save(final C entity);
	
	
	 List<C> findByCriteria(final int firstResult,
			final int maxResults, final Criterion... criterion);
	 
	 
	 
	 List<C> findByCriteria(final int firstResult,
				final int maxResults,Order order, final Criterion... criterion );

}
