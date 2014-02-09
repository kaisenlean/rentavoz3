/**
 * 
 */
package co.innovate.rentavoz.repositories.caja.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.EstadoCuotaEnum;
import co.innovate.rentavoz.model.almacen.EstadoVentaEnum;
import co.innovate.rentavoz.model.caja.Caja;
import co.innovate.rentavoz.model.caja.EstadoCaja;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.model.venta.EstadoVentaItemCuotaEnum;
import co.innovate.rentavoz.model.venta.EstadoVentaItemEnum;
import co.innovate.rentavoz.repositories.caja.CajaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class CajaDaoImpl
 * @date 12/01/2014
 *
 */
 @Repository("cajaDao")
public class CajaDaoImpl extends GenericJpaRepository<Caja, Integer> implements CajaDao ,Serializable{


	/**
	 * 7/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ESTADO_CUOTA
	 */
	private static final String ESTADO_CUOTA = "estadoCuota";
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ESTADO_VENTA
	 */
	private static final String ESTADO_VENTA = "estadoVenta";
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * END
	 */
	private static final String END = "end";
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * START
	 */
	private static final String START = "start";
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * NUMBER_59
	 */
	private static final int NUMBER_59 = 59;
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * NUMBER_23
	 */
	private static final int NUMBER_23 = 23;
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ZERO
	 */
	private static final int ZERO = 0;


	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.caja.CajaDao#abrirCaja(co.innovate.rentavoz.model.profile.Usuario)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void abrirCaja(Usuario usuario) throws BaseException {
		Calendar inicio = Calendar.getInstance();
		inicio.setTime(new Date());
		Calendar fin = Calendar.getInstance();
		fin.setTime(new Date());
		inicio.set(Calendar.HOUR, ZERO);
		inicio.set(Calendar.HOUR_OF_DAY, ZERO);
		inicio.set(Calendar.MINUTE, ZERO);
		inicio.set(Calendar.SECOND, ZERO);
		inicio.set(Calendar.MILLISECOND, ZERO);

		fin.set(Calendar.HOUR, NUMBER_23);
		fin.set(Calendar.HOUR_OF_DAY, NUMBER_23);
		fin.set(Calendar.MINUTE, NUMBER_59);
		fin.set(Calendar.SECOND, NUMBER_59);
		fin.set(Calendar.MILLISECOND, NUMBER_59);

		Query query = getEntityManager().createQuery(
				"SELECT c FROM Caja c WHERE c.fecha BETWEEN :start AND :end ");
		query.setParameter("start", inicio.getTime());
		query.setParameter("end", fin.getTime());

		query.setMaxResults(1);
		List<Caja> lista = query.getResultList();

		if (lista.isEmpty()) {
			Caja caja = new Caja();
			caja.setEstado(EstadoCaja.ABIERTA);
			caja.setFecha(new Date());
			caja.setUsuario(usuario);
			save(caja);
		} else {

			Caja caja = lista.get(0);
			caja.setUsuario(usuario);
			save(caja);
		}
	}

	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.caja.CajaDao#valorCaja()
	 */
	@Override
	public double valorCaja(Tercero vendedor) throws BaseException {
		Calendar inicio = Calendar.getInstance();
		inicio.setTime(new Date());
		Calendar fin = Calendar.getInstance();
		fin.setTime(new Date());
		inicio.set(Calendar.HOUR, ZERO);
		inicio.set(Calendar.HOUR_OF_DAY, ZERO);
		inicio.set(Calendar.MINUTE, ZERO);
		inicio.set(Calendar.SECOND, ZERO);
		inicio.set(Calendar.MILLISECOND, ZERO);

		fin.set(Calendar.HOUR, NUMBER_23);
		fin.set(Calendar.HOUR_OF_DAY, NUMBER_23);
		fin.set(Calendar.MINUTE, NUMBER_59);
		fin.set(Calendar.SECOND, NUMBER_59);
		fin.set(Calendar.MILLISECOND, NUMBER_59);
		
		Query query = getEntityManager()
				.createQuery(new StringBuilder(
						"SELECT SUM(c.valor) FROM VentaItemCuota c WHERE c.idVenta.fecha BETWEEN :start AND :end  AND c.idVenta.estado = :estadoVenta AND c.estado = :estadoCuota AND c.idVenta.vendedor = :vendedor").toString());

		query.setParameter(START, inicio.getTime());
		query.setParameter(END, fin.getTime());
		query.setParameter(ESTADO_VENTA, EstadoVentaItemEnum.ACTIVO);
		query.setParameter(ESTADO_CUOTA, EstadoVentaItemCuotaEnum.PAGADA);
		query.setParameter("vendedor", vendedor);
		
		query.setMaxResults(1);
		
		Object salida = query.getSingleResult();
		if (salida==null) {
			
			return ZERO;
		}
		
		
		return Double.valueOf(salida.toString());
	}


	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.caja.CajaDao#valorCajaLineas()
	 */
	@Override
	public double valorCajaLineas(Tercero vendedor) throws BaseException {
		Calendar inicio = Calendar.getInstance();
		inicio.setTime(new Date());
		Calendar fin = Calendar.getInstance();
		fin.setTime(new Date());
		inicio.set(Calendar.HOUR, ZERO);
		inicio.set(Calendar.HOUR_OF_DAY, ZERO);
		inicio.set(Calendar.MINUTE, ZERO);
		inicio.set(Calendar.SECOND, ZERO);
		inicio.set(Calendar.MILLISECOND, ZERO);

		fin.set(Calendar.HOUR, NUMBER_23);
		fin.set(Calendar.HOUR_OF_DAY, NUMBER_23);
		fin.set(Calendar.MINUTE, NUMBER_59);
		fin.set(Calendar.SECOND, NUMBER_59);
		fin.set(Calendar.MILLISECOND, NUMBER_59);
		
		Query query = getEntityManager()
				.createQuery(new StringBuilder(
						"SELECT SUM(c.valorCuota) FROM Cuota c WHERE c.fechaPago BETWEEN :start AND :end  AND c.venta.estadoVenta = :estadoVenta AND c.estadoCuota = :estadoCuota AND c.venta.vendedor = :vendedor").toString());

		query.setParameter(START, inicio.getTime());
		query.setParameter(END, fin.getTime());
		query.setParameter(ESTADO_VENTA, EstadoVentaEnum.ACTIVA);
		query.setParameter(ESTADO_CUOTA, EstadoCuotaEnum.PAGADA);
		query.setParameter("vendedor", vendedor);
		
		query.setMaxResults(1);
		
		Object salida = query.getSingleResult();
		if (salida==null) {
			
			return ZERO;
		}
		
		
		return Double.valueOf(salida.toString());
	}

}
