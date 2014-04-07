/**
 * 
 */
package co.innovate.rentavoz.services.log.linea.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.log.linea.AccionLineaEnum;
import co.innovate.rentavoz.model.log.linea.LogLinea;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.log.linea.LogLineaDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.linea.LineaService;
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
	 * 1/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * TAREA
	 */
	private static final int TAREA = 2;
	/**
	 * 31/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * NUEVO_ICC
	 */
	private static final int NUEVO_ICC = 1;
	/**
	 * 16/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final int NUMERO_LINEA = 0;
	@Autowired
	private LogLineaDao logLineaDao;

	
	@Autowired
	private LineaService lineaService;
	
	
	private Logger logger= Logger.getLogger(LogLineaServiceImpl.class);
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

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.log.linea.LogLineaService#cargarLineaScid(java.lang.String)
	 */
	@Override
	public List<LogLinea>  cargarLineaScid(String pathFile,Tercero tercero) {
		
		List<LogLinea> consumos=new ArrayList<LogLinea>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		Date fechaConsumo=Calendar.getInstance().getTime();
		try {
	 
	 
			br = new BufferedReader(new FileReader(pathFile));
			while ((line = br.readLine()) != null) {
	 
				String[] repoDto = line.split(cvsSplitBy);
				
				LogLinea logLinea=new LogLinea();
				Linea linea= lineaService.findBNumeroObjeto(repoDto[NUMERO_LINEA]);
				
				if (linea==null) {
					logger.error("linea : ".concat(repoDto[NUMERO_LINEA]).concat(" no encontrada"));
					continue;
				}
				logLinea.setUsuarioCrea(tercero);
				logLinea.setLinea(linea);
				logLinea.setFecha(fechaConsumo);
				logLinea.setNuevoIcc(repoDto[NUEVO_ICC]);
				try {
					
					logLinea.setAccion(AccionLineaEnum.valueOf(repoDto[TAREA]));
				} catch (Exception e) {
					logger.error("No se ha cargado la enumeracion de la tarea para la linea : ".concat(linea.getLinNumero()));
				}
				consumos.add(logLinea);
	 
			}
	 
	 
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	 
	  
		
		return consumos;
	}
	
		
	
}
