/**
 * 
 */
package co.innovate.rentavoz.repositories.menu.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.menu.MenuDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class MenuDaoImpl
 * @date 12/01/2014
 *
 */
@Repository(MenuDaoImpl.DAO_NAME)
public class MenuDaoImpl extends GenericJpaRepository<Menu, Integer> implements MenuDao,Serializable {

	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * DAO_NAME
	 */
	static final String DAO_NAME = "menuDao";
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * PADRE2
	 */
	private static final String PADRE2 = "padre";
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.menu.MenuDao#findTodos()
	 */
	@Override
	public List<Menu> findTodos() {
		return findAll();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.menu.MenuDao#findTodosByPadre(java.lang.String)
	 */
	@Override
	public List<Menu> findTodosByPadre(String padre) {
		Criterion criterion=Restrictions.eq(PADRE2, padre);
		return findByCriteria(criterion);
	}

}
