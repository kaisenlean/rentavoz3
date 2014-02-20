/**
 * 
 */
package co.innovate.rentavoz.logic.consumo.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.logic.consumo.ConsumoControllerService;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.LineaConsumo;
import co.innovate.rentavoz.services.linea.LineaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsumoControllerService
 * @date 19/02/2014
 *
 */
@Service(ConsumoControllerServiceImpl.SERVICE_NAME)
public class ConsumoControllerServiceImpl implements ConsumoControllerService{

	
	/**
	 * 19/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * logger
	 */
	private Logger logger = Logger.getLogger(ConsumoControllerServiceImpl.class);
	
	/**
	 * 19/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * lineaService
	 */
	@Autowired
	private LineaService lineaService;
	
	/**
	 * 19/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * SERVICE_NAME
	 */
	static final String SERVICE_NAME = "consumoControllerService";
	
	
	private static int NUMERO_LINEA=0;
	private static int MINUTOS_CLARO=1;
	private static int MINUTOS_OTROS_OPERADORES=2;
	private static int MINUTOS_FIJOS=3;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.logic.consumo.ConsumoControllerService#cargarConsumos(java.lang.String)
	 */
	@Override
	public List<LineaConsumo> cargarConsumosClaro(String pathFile) {

		List<LineaConsumo> consumos=new ArrayList<LineaConsumo>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		Date fechaConsumo=Calendar.getInstance().getTime();
		try {
	 
	 
			br = new BufferedReader(new FileReader(pathFile));
			while ((line = br.readLine()) != null) {
	 
				String[] consumo = line.split(cvsSplitBy);
				
				LineaConsumo lineaConsumo=new LineaConsumo();
				Linea linea= lineaService.findBNumeroObjeto(consumo[NUMERO_LINEA]);
				if (linea==null) {
					logger.error("linea : ".concat(consumo[NUMERO_LINEA]).concat(" no encontrada"));
					continue;
				}
				lineaConsumo.setLinea(linea);
				lineaConsumo.setClaro(Integer.valueOf(consumo[MINUTOS_CLARO]).intValue());
				lineaConsumo.setFijo(Integer.valueOf(consumo[MINUTOS_FIJOS]).intValue());
				lineaConsumo.setOtros(Integer.valueOf(consumo[MINUTOS_OTROS_OPERADORES]).intValue());
				lineaConsumo.setFecha(fechaConsumo);
				
				consumos.add(lineaConsumo);
	 
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
