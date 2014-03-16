/**
 * 
 */
package co.innovate.rentavoz.logic.tarea.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.logic.tarea.TareaController;
import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.tarea.EstadoTareaEnum;
import co.innovate.rentavoz.model.tarea.Tarea;
import co.innovate.rentavoz.model.tarea.TipoTareaEnum;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.repositories.centrope.ParametrosCentrope;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaService;
import co.innovate.rentavoz.services.centrope.CentropeService;
import co.innovate.rentavoz.services.tarea.TareaService;
import co.innovate.rentavoz.services.venta.item.VentaItemService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TareaCOntrollerImpl
 * @date 14/03/2014
 * 
 */
@Service("tareaController")
public class TareaControllerImpl implements TareaController, Serializable {

	/**
	 * 14/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TareaService tareaService;

	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private VentaItemService ventaItemService;
	
	@Autowired
	private CentropeService centropeService;
	
	
	

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.logic.tarea.TareaController#crearTareaAnulacionVenta(co.innovate.rentavoz.model.almacen.venta.Venta,
	 *      co.innovate.rentavoz.model.Tercero,
	 *      co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum)
	 */
	@Override
	public void crearTareaAnulacionVenta(Venta venta, Tercero solicitante,
			TareaCentropeEnum centropeEnum) throws BaseException {
		Tarea tarea = new Tarea();
		tarea.setCodTarea(venta.getIdVenta().toString());
		tarea.setCreador(solicitante.getNombreCompleto());
		tarea.setNombre("Solicitud de anulacion de factura de venta ".concat(venta.getIdVenta().toString()));
		tarea.setFechaCreacion(Calendar.getInstance().getTime());
		tarea.setEstado(EstadoTareaEnum.PENDIENTE);
		tarea.setDescripcion(venta.getJustificacionAnulacion());
		tarea.setGoDir("/paginas/almacen/venta/linea/anulacion.jsf");
		tarea.setTipoTarea(TipoTareaEnum.ANULACION_VENTA_LINEA);
		List<Centrope> centropes = null;
		switch (centropeEnum) {
		case ADMINISTRATIVO:
			centropes=centropeService.findByParametro(ParametrosCentrope.ADMINISTRATIVO);
			break;

		case OPERATIVO:
			centropes=centropeService.findByParametro(ParametrosCentrope.OPERATIVO);
			break;

		default:
			break;
		}
		if (centropes.isEmpty()) {
			tarea.setOwner(null);
		}
		else{
			tarea.setOwner(centropes.get(BigInteger.ZERO.intValue()));
		}
		
		tareaService.save(tarea);
		ventaService.save(venta);

	}



	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.logic.tarea.TareaController#crearTareaAnulacionVenta(co.innovate.rentavoz.model.almacen.venta.VentaLinea, co.innovate.rentavoz.model.Tercero, co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum)
	 */
	@Override
	public void crearTareaAnulacionVenta(VentaItem venta, Tercero solicitante,
			TareaCentropeEnum centropeEnum) throws BaseException {
		Tarea tarea = new Tarea();
		tarea.setCodTarea(venta.getIdVenta().toString());
		tarea.setCreador(solicitante.getNombreCompleto());
		tarea.setNombre("Solicitud de anulacion de factura de venta ".concat(venta.getIdVenta().toString()));
		tarea.setFechaCreacion(Calendar.getInstance().getTime());
		tarea.setEstado(EstadoTareaEnum.PENDIENTE);
		tarea.setDescripcion(venta.getJustificacionAnulacion());
		tarea.setGoDir("/paginas/almacen/venta/item/anulacion.jsf");
		tarea.setTipoTarea(TipoTareaEnum.ANULACION_VENTA_ITEM);
		List<Centrope> centropes = null;
		switch (centropeEnum) {
		case ADMINISTRATIVO:
			centropes=centropeService.findByParametro(ParametrosCentrope.ADMINISTRATIVO);
			break;

		case OPERATIVO:
			centropes=centropeService.findByParametro(ParametrosCentrope.OPERATIVO);
			break;

		default:
			break;
		}
		if (centropes.isEmpty()) {
			tarea.setOwner(null);
		}
		else{
			tarea.setOwner(centropes.get(BigInteger.ZERO.intValue()));
		}
		
		tareaService.save(tarea);
		ventaItemService.save(venta);

		
	}
	

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.logic.tarea.TareaController#finalizarTarea(co.innovate.rentavoz.model.tarea.Tarea, co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public void finalizarTarea(Tarea tarea, Tercero finaliza) {
		
		tarea.setUsuarioFinaliza(finaliza);
		tarea.setFechaFinaliza(Calendar.getInstance().getTime());
		tarea.setEstado(EstadoTareaEnum.FINALIZADA);
		tareaService.save(tarea);
		
	}



	
	
	

}
