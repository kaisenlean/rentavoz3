/**
 * 
 */
package co.innovate.rentavoz.services.reportes.caja.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.services.caja.CajaService;
import co.innovate.rentavoz.services.reportes.caja.ReporteCajaService;
import co.innovate.rentavoz.services.reportes.caja.dto.ReporteCajaDto;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ReporteCajaServiceImpl
 * @date 27/02/2014
 *
 */
@Service("reporteCajaService")
public class ReporteCajaServiceImpl implements ReporteCajaService,Serializable{

	/**
	 * 27/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private CajaService cajaService;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.reportes.caja.ReporteCajaService#reporteCajasBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public List<ReporteCajaDto> reporteCajasBySucursal(Sucursal sucursal,
			Date fechaCierre) {
		
		List<ReporteCajaDto> lista= new ArrayList<ReporteCajaDto>();
		ReporteCajaDto dto=new ReporteCajaDto();
		dto.setSucursal(sucursal);
		dto.setCuotasLineas(cajaService.valorCajaLineasBySucursalDetalle(sucursal, fechaCierre));
		dto.setValorLineas(cajaService.valorCajaLineasBySucursal(sucursal, fechaCierre));
		dto.setValorEquipos(cajaService.valorCajaEquiposBySucursal(sucursal, fechaCierre));
		dto.setCuotasEquipos(cajaService.valorCajaEquiposBySucursalDetalle(sucursal, fechaCierre));
		dto.setTotal(dto.getValorEquipos()+dto.getValorLineas());
		lista.add(dto);
		return lista;
	}

	


}
