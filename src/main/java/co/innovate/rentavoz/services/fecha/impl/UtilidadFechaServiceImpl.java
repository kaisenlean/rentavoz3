/**
 * 
 */
package co.innovate.rentavoz.services.fecha.impl;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.services.fecha.UtilidadFechaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class UtilidadFechaServiceImpl
 * @date 26/01/2014
 *
 */
@Service("utilidadFechaService")
public class UtilidadFechaServiceImpl implements UtilidadFechaService,Serializable{

	/**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger= Logger.getLogger(UtilidadFechaServiceImpl.class);
	
	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.fecha.UtilidadFechaService#obtenerMesesPorRangoDeFechas(java.util.Date, java.util.Date)
	 */
	@Override
	public String obtenerMesesPorRangoDeFechas(Date inicio, Date fin)
			throws BaseException {
		
		if (inicio==null) {
			logger.error("Fecha de inicio no debe ser null");
			throw new BaseException("Fecha de inicio no debe ser null");
		}
		if (fin==null) {
			logger.error("Fecha de fin no debe ser null");
			throw new BaseException("Fecha de fin no debe ser null");
		}
		
		DateFormat format= new SimpleDateFormat("MMMM");
		
		StringBuffer buffer= new StringBuffer();
		buffer.append(format.format(inicio));
		buffer.append(" - ");
		buffer.append(format.format(fin));
		buffer.append("(");
		buffer.append(new SimpleDateFormat("yyyy").format(inicio));
		buffer.append(")");
		return buffer.toString().toUpperCase();
	}

}
