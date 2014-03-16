/**
 * 
 */
package co.innovate.rentavoz.services.tarea.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum;
import co.innovate.rentavoz.model.tarea.Tarea;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.tarea.TareaDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.tarea.TareaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TareaServiceImpl
 * @date 14/03/2014
 *
 */
@Service("tareaService")
public class TareaServiceImpl extends GenericServiceImpl<Tarea, Integer> implements TareaService,Serializable{

	
	/**
	 * 14/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TareaDao tareaDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Tarea, Integer> getDao() {
		return tareaDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.tarea.TareaService#findTareasPendientes(co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum)
	 */
	@Override
	public List<Tarea> findTareasPendientes(TareaCentropeEnum centropeEnum)
			throws BaseException {
		return tareaDao.findTareasPendientes(centropeEnum);
	}
}
