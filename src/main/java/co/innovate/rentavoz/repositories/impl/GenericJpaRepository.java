package co.innovate.rentavoz.repositories.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * JPA implementation of the GenericRepository. Note that this implementation
 * also expects Hibernate as JPA implementation. That's because we like the
 * Criteria API.
 * 
 * @author Jurgen Lust
 * @author $LastChangedBy: jlust $
 * 
 * @version $LastChangedRevision: 257 $
 * 
 * @param <C>
 *            The persistent type
 * @param <PK>
 *            The primary key type
 */
@Transactional
@SuppressWarnings("unchecked")
public class GenericJpaRepository<C, PK extends Serializable> implements
		GenericRepository<C, PK> {

	// ~ Instance fields
	// --------------------------------------------------------

	private final Class<C> persistentClass;
	private EntityManager entityManager;

	// ~ Constructors
	// -----------------------------------------------------------

	public GenericJpaRepository() {
		this.persistentClass = (Class<C>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public GenericJpaRepository(final Class<C> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	// ~ Methods
	// ----------------------------------------------------------------

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#countAll()
	 */
	@Override
	public int countAll() {
		return countByCriteria();
	}

	protected int countByCriteria(Criterion... criterion) {
		Session session = getEntityManager().unwrap(Session.class);
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		return Long.valueOf( crit.list().get(0).toString()).intValue();
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#countByExample(java.lang.Object)
	 */
	@Override
	public int countByExample(final C exampleInstance) {
		Session session = getEntityManager().unwrap(Session.class);
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());
		crit.add(Example.create(exampleInstance));

		return (Integer) crit.list().get(0);
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#delete(java.lang.Object)
	 */
	@Override
	public void delete(C entity) {
		getEntityManager().remove(
				getEntityManager().contains(entity) ? entity
						: getEntityManager().merge(entity));
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#findAll()
	 */
	@Override
	public List<C> findAll() {
		return findByCriteria();
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	public List<C> findByCriteria(final Criterion... criterion) {
		return findByCriteria(-1, -1, criterion);
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.GenericRepository#findByCriteria(int, int, org.hibernate.criterion.Criterion[])
	 */
	public List<C> findByCriteria(final int firstResult,
			final int maxResults, final Criterion... criterion) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria crit = session.createCriteria(getEntityClass());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		return crit.list();

	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#findByExample(java.lang.Object)
	 */
	@Override
	public List<C> findByExample(final C exampleInstance) {

		Session session = getEntityManager().unwrap(Session.class);
		Criteria crit = session.createCriteria(getEntityClass());
		return crit.list();
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#findById(java.io.Serializable)
	 */
	@Override
	public C findById(final PK id) {
		return getEntityManager().find(persistentClass, id);
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#findById(java.io.Serializable)
	 */
	@Override
	public Object findById(PK id, Class<?> class1) {
		return getEntityManager().find(class1, id);

	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#findByNamedQuery(java.lang.String,
	 *      java.lang.Object[])
	 */

	@Override
	public List<C> findByNamedQuery(final String name, Object... params) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		return query.getResultList();

	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#findByNamedQueryAndNamedParams(java.lang.String,
	 *      java.util.Map)
	 */

	@Override
	public List<C> findByNamedQueryAndNamedParams(final String name,
			final Map<String, ? extends Object> params) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		return query.getResultList();
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#getEntityClass()
	 */
	@Override
	public Class<C> getEntityClass() {
		return persistentClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#save(java.lang.Object)
	 */
	@Override
	public C save(C entity) {

		return getEntityManager().merge(entity);

	}

	/**
	 * set the JPA entity manager to use.
	 * 
	 * @param entityManager
	 */
	@Required
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
