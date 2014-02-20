/**
 * 
 */
package co.innovate.rentavoz.services.almacen.linea.consumo.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.LineaConsumo;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao;
import co.innovate.rentavoz.services.almacen.linea.consumo.LineaConsumoService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LineaConsumoServiceImpl
 * @date 19/02/2014
 *
 */
@Service("lineaConsumoService")
public class LineaConsumoServiceImpl extends GenericServiceImpl<LineaConsumo, Integer> implements Serializable,LineaConsumoService {

	/**
	 * 19/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LineaConsumoDao lineaConsumoDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<LineaConsumo, Integer> getDao() {
		return lineaConsumoDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.linea.consumo.LineaConsumoService#findByLinea(co.innovate.rentavoz.model.almacen.Linea)
	 */
	@Override
	public List<LineaConsumo> findByLinea(Linea linea) {
		return lineaConsumoDao.findByLinea(linea);
	}

}
