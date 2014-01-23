/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegaexistencia.color.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.bodega.BodegaExistenciaColor;
import co.innovate.rentavoz.repositories.bodegaexistencia.color.BodegaExistenciaColorDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaExistenciaColorDaoImpl
 * @date 19/01/2014
 *
 */@Repository("bodegaExistenciaColorDao")
public class BodegaExistenciaColorDaoImpl extends GenericJpaRepository<BodegaExistenciaColor, Integer> implements BodegaExistenciaColorDao,Serializable{

	/**
	 * 19/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
