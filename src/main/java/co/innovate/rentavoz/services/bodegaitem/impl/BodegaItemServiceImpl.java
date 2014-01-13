/**
 * 
 */
package co.innovate.rentavoz.services.bodegaitem.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.bodegaitem.BodegaItemDao;
import co.innovate.rentavoz.services.bodegaitem.BodegaItemService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaItemService
 * @date 13/01/2014
 *
 */
@Service("bodegaItemService")
public class BodegaItemServiceImpl extends GenericServiceImpl<BodegaItem, Integer> implements Serializable , BodegaItemService{

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private BodegaItemDao bodegaItemDao;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<BodegaItem, Integer> getDao() {
		return bodegaItemDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaitem.BodegaItemService#existReferencia(java.lang.String)
	 */
	@Override
	public boolean existReferencia(String ref) {
		return bodegaItemDao.existReferencia(ref);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaitem.BodegaItemService#findByCriterio(java.lang.String)
	 */
	@Override
	public List<BodegaItem> findByCriterio(String param) {
		return bodegaItemDao.findByCriterio(param);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaitem.BodegaItemService#findByNombre(java.lang.String)
	 */
	@Override
	public BodegaItem findByNombre(String nombre) {
		return bodegaItemDao.findByNombre(nombre);
	}

}
