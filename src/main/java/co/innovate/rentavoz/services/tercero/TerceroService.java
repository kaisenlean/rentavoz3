/**
 * 
 */
package co.innovate.rentavoz.services.tercero;

import java.util.List;

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class TerceroService
 * @date 12/01/2014
 *
 */
public interface TerceroService extends GenericService<Tercero, Integer>{

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @param criterio
	 * @return
	 */
	List<Tercero> findByCriterio(String criterio);

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 * @param val
	 * @return
	 */
	Tercero findByDocumento(String val);

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param user
	 * @return
	 */
	Tercero findByUsuario(Usuario user);

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/09/2013
	 * @param idCentrope
	 * @return
	 */
	List<Tercero> findByCentrope(Centrope idCentrope);

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param criterio
	 * @return
	 */
	List<Tercero> findByCriterioProveedor(String criterio);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 2/02/2014
	* @param criterio
	* @return
	*/
	List<Tercero> findColaboradorByCriterio(String criterio);
	
	
}
