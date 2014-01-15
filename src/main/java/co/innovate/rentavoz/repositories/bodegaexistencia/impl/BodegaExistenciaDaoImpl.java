/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegaexistencia.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaIngreso;
import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.model.bodega.EstadoExistenciaEnum;
import co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaExistenciaDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("bodegaExistencia")
public class BodegaExistenciaDaoImpl extends GenericJpaRepository<BodegaExistencia, Integer> implements Serializable,BodegaExistenciaDao
{

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @param productoId
	 * @return
	 */
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao#findByBarcode(java.lang.String)
	 */
	public BodegaExistencia findByBarcode(String productoId) {
		Query query = getEntityManager()
				.createQuery(
						new StringBuilder(
								"SELECT e FROM BodegaExistencia e WHERE e.barCode = :proId")
								.toString());
		query.setParameter(new StringBuilder("proId").toString(),
				new StringBuilder(productoId).toString());
		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {

			return null;
		} else {

			return (BodegaExistencia) query.getSingleResult();
		}
	}
	
	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/10/2013
	* @param productoId
	* @param sucursal
	* @return
	 */
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao#findByBarcode(java.lang.String, co.innovate.rentavoz.model.Sucursal)
	 */
	public BodegaExistencia findByBarcode(String productoId,Sucursal sucursal) {
		Query query = getEntityManager()
				.createQuery(
						new StringBuilder(
								"SELECT e FROM BodegaExistencia e WHERE  e.sucursal = :suc and (e.barCode  = :proId or e.barCode2  = :proId or e.barCode3  = :proId)")
								.toString());
		query.setParameter(new StringBuilder("proId").toString(),
				new StringBuilder(productoId).toString());
		
		query.setParameter(new StringBuilder("suc").toString(),
				sucursal);

		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {

			return null;
		} else {

			return (BodegaExistencia) query.getSingleResult();
		}
	}
	

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 7/10/2013
	* @param productoId
	* @return
	*/
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao#findByBarcodeActivo(java.lang.String)
	 */
	public BodegaExistencia findByBarcodeActivo(String productoId) {
		Query query = getEntityManager()
				.createQuery(
						new StringBuilder(
								"SELECT e FROM BodegaExistencia e WHERE e.barCode = :proId AND e.estado =:estado")
								.toString());
		query.setParameter(new StringBuilder("proId").toString(),
				new StringBuilder(productoId).toString());
		query.setParameter("estado", EstadoExistenciaEnum.DISPONIBLE);
		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {

			return null;
		} else {

			return (BodegaExistencia) query.getSingleResult();
		}
	}
	
	
	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 21/10/2013
	* @param productoId
	* @param sucursal
	* @return
	 */
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao#findByBarcodeActivo(java.lang.String, co.innovate.rentavoz.model.Sucursal)
	 */
	public BodegaExistencia findByBarcodeActivo(String productoId,Sucursal sucursal) {
		Query query = getEntityManager()
				.createQuery(
						new StringBuilder(
								"SELECT e FROM BodegaExistencia e WHERE  e.estado =:estado AND e.sucursal = :suc AND ( e.barCode = :proId  or e.barCode2 = :proId or e.barCode3 = :proId)")
								.toString());
		query.setParameter(new StringBuilder("proId").toString(),
				new StringBuilder(productoId).toString());
		query.setParameter("estado", EstadoExistenciaEnum.DISPONIBLE);
		query.setParameter("suc", sucursal);
		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {

			return null;
		} else {

			return (BodegaExistencia) query.getSingleResult();
		}
	}
	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 21/10/2013
	* @param sucursal
	* @param bodegaItem
	* @return
	 */
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao#findByItemAndSucursal(co.innovate.rentavoz.model.Sucursal, co.innovate.rentavoz.model.bodega.BodegaItem)
	 */
	@SuppressWarnings("unchecked")
	public List<BodegaExistencia> findByItemAndSucursal(Sucursal sucursal, BodegaItem bodegaItem){
		
		Query query = getEntityManager().createQuery("SELECT b FROM BodegaExistencia b WHERE b.bodegaItemBean = :item AND b.sucursal = :sucursal");
		
		query.setParameter("item", bodegaItem);
		query.setParameter("sucursal", sucursal);
		
		return query.getResultList();
		
		
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao#findByIngreso(co.innovate.rentavoz.model.bodega.BodegaIngreso)
	 */
	@Override
	public List<BodegaExistencia> findByIngreso(BodegaIngreso bodegaIngreso) {
		Criterion criterion = Restrictions.eq("bodegaIngreso", bodegaIngreso);
		return findByCriteria(criterion);
	}

}
