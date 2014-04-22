/**
 * 
 */
package co.innovate.rentavoz.repositories.facturacion.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.facturacion.Talonario;
import co.innovate.rentavoz.repositories.facturacion.TalonarioDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TalonarioDaoImpl
 * @date 21/04/2014
 * 
 */
@Repository("talonarioDao")
public class TalonarioDaoImpl extends GenericJpaRepository<Talonario, String>
		implements TalonarioDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.facturacion.TalonarioDao#
	 * cargarConsecutivoFactura(co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public Talonario cargarConsecutivoFactura(Sucursal sucursal)throws BaseException {
		
		Query query = getEntityManager().createQuery("SELECT t FROM Talonario t WHERE t.sucursal = :sucursal AND t.consecutivoFactura.activo = :activo AND t.usado = :usado ORDER BY t.consecutivo ASC");
		query.setParameter("sucursal", sucursal);
		query.setParameter("activo", Boolean.TRUE);
		query.setParameter("usado", Boolean.FALSE);
		query.setMaxResults(1);
		Talonario talonario=null;
			talonario=(Talonario) query.getSingleResult();
		if (talonario==null) {
			throw new BaseException("No se hay talonario para asignar consecutivo de factura");
		}
		return talonario;
	}
}
