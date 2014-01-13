package co.innovate.rentavoz.services.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.services.GenericService;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project troca-co
 * @class GenericServiceImpl
 * @date 22/10/2013
 * 
 */
public abstract class GenericServiceImpl<C, PK extends Serializable> implements
		GenericService<C, PK> {

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#countAll()
	 */
	@Override
	public int countAll() {
		return getDao().countAll();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#countByExample(java.lang.Object)
	 */
	@Override
	public int countByExample(C exampleInstance) {
		return getDao().countByExample(exampleInstance);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#delete(java.lang.Object)
	 */
	@Override
	public void delete(C entity) {
		getDao().delete(entity);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#findAll()
	 */
	@Override
	public List<C> findAll() {
		return getDao().findAll();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#findByExample(java.lang.Object)
	 */
	@Override
	public List<C> findByExample(C exampleInstance) {
		return getDao().findByExample(exampleInstance);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#findById(java.io.Serializable)
	 */
	@Override
	public C findById(PK id) {
		return getDao().findById(id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#findById(java.io.Serializable,
	 *      java.lang.Class)
	 */
	@Override
	public Object findById(PK id, Class<?> clazz) {
		return getDao().findById(id, clazz);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#findByNamedQuery(java.lang.String,
	 *      java.lang.Object[])
	 */
	@Override
	public List<C> findByNamedQuery(String queryName, Object... params) {
		return getDao().findByNamedQuery(queryName, params);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#findByNamedQueryAndNamedParams(java.lang.String,
	 *      java.util.Map)
	 */
	@Override
	public List<C> findByNamedQueryAndNamedParams(String queryName,
			Map<String, ? extends Object> params) {
		return getDao().findByNamedQueryAndNamedParams(queryName, params);
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/10/2013
	 * @return
	 */
	public abstract GenericRepository<C, PK> getDao();

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#getEntityClass()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Class<C> getEntityClass() {
		Class<C> persistentClass;
		persistentClass = (Class<C>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return persistentClass;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.icesi.troca.repositories.GenericRepository#save(java.lang.Object)
	 */
	@Override
	public C save(C entity) {
		return getDao().save(entity);
	}

	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.GenericRepository#findByCriteria(int, int, org.hibernate.criterion.Criterion[])
	 */
	@Override
	public List<C> findByCriteria(int firstResult, int maxResults,
			Criterion... criterion) {
		return getDao().findByCriteria(firstResult, maxResults, criterion);
	}
}
