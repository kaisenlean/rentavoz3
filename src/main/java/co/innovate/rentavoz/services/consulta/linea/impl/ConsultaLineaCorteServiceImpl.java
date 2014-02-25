package co.innovate.rentavoz.services.consulta.linea.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.services.almacen.linea.consumo.LineaConsumoService;
import co.innovate.rentavoz.services.consulta.linea.ConsultaLineaCorteService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsultaLineaCorteServiceImpl
 * @date 23/02/2014
 *
 */
@Service("consultaLineaCorteService")
public class ConsultaLineaCorteServiceImpl implements ConsultaLineaCorteService, Serializable {

	
	
	@Autowired
	private LineaConsumoService lineaConsumoService;
	
	
	
	
	
	/**
	 * 23/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.consulta.linea.ConsultaLineaCorteService#findLineasRepoConsumos(co.innovate.rentavoz.model.facturacion.FechaFacturacion)
	 */
	@Override
	public List<Linea> findLineasRepoConsumos(FechaFacturacion fechaFacturacion) {
		return null;
	}

}
