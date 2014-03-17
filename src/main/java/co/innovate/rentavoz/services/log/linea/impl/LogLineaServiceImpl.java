/**
 * 
 */
package co.innovate.rentavoz.services.log.linea.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.log.linea.AccionLineaEnum;
import co.innovate.rentavoz.model.log.linea.LogLinea;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.log.linea.LogLineaDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.log.linea.LogLineaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LogLineaServiceImpl
 * @date 16/03/2014
 *
 */
@Service("logLineaService")
public class LogLineaServiceImpl extends GenericServiceImpl<LogLinea, Integer>implements LogLineaService , Serializable {

	/**
	 * 16/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private LogLineaDao logLineaDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<LogLinea, Integer> getDao() {
		return logLineaDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.log.linea.LogLineaService#findByCriterio(java.util.Date, java.util.Date, java.lang.String, co.innovate.rentavoz.model.Tercero, co.innovate.rentavoz.model.log.linea.AccionLineaEnum, int, int, org.hibernate.criterion.Order)
	 */
	@Override
	public List<LogLinea> findByCriterio(Date start, Date end,
			String numeroLinea, Tercero creador,
			AccionLineaEnum accionLineaEnum, int maxRegistros,
			int registroInicial, Order order) {
		return logLineaDao.findByCriterio(start, end, numeroLinea, creador, accionLineaEnum, maxRegistros, registroInicial, order);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.log.linea.LogLineaService#countByCriterio(java.util.Date, java.util.Date, java.lang.String, co.innovate.rentavoz.model.Tercero, co.innovate.rentavoz.model.log.linea.AccionLineaEnum, int, int, org.hibernate.criterion.Order)
	 */
	@Override
	public int countByCriterio(Date start, Date end, String numeroLinea,
			Tercero creador, AccionLineaEnum accionLineaEnum) {
		return logLineaDao.countByCriterio(start, end, numeroLinea, creador, accionLineaEnum);
	}
}
