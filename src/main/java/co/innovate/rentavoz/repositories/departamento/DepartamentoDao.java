package co.innovate.rentavoz.repositories.departamento;

import java.util.List;

import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class DepartamentoDao
* @date 13/01/2014
*
*/
public interface DepartamentoDao extends GenericRepository<Departamento, Integer>{

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param criterio
	* @return
	*/
	public List<Departamento> findByCriterio(String criterio);
}
