/**
 * 
 */
package co.innovate.rentavoz.repositories.empresa.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Empresa;
import co.innovate.rentavoz.repositories.empresa.EmpresaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EmpresaDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("empresaDao")
public class EmpresaDaoImpl extends GenericJpaRepository<Empresa, Integer> implements EmpresaDao,Serializable
{

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
