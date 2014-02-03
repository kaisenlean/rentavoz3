/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegaexistencia.impl;

import java.io.Serializable;
import java.math.BigInteger;
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
	public BodegaExistencia findByBarcode(String productoId,List<Sucursal> sucursal) {
		
		Criterion criterion=Restrictions.disjunction().add(Restrictions.eq("barCode", productoId)).add(Restrictions.eq("barCode2", productoId)).add(Restrictions.eq("barCode3", productoId));
		Criterion criterion2=Restrictions.in("sucursal", sucursal);
		Criterion criterion3=Restrictions.eq("estado", EstadoExistenciaEnum.DISPONIBLE);
		List<BodegaExistencia> lista=findByCriteria(criterion,criterion2,criterion3);
		
		if (lista.isEmpty()) {
			return null;
		}else{
			
			return lista.get(BigInteger.ZERO.intValue());
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
	public List<BodegaExistencia> findByItemAndSucursal(List<Sucursal> sucursal, BodegaItem bodegaItem){
		
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("bodegaItemBean", bodegaItem)).add(Restrictions.eq("estado",  EstadoExistenciaEnum.DISPONIBLE)).add(Restrictions.in("sucursal", sucursal));
		
		return findByCriteria(criterion);
		
		
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao#findByIngreso(co.innovate.rentavoz.model.bodega.BodegaIngreso)
	 */
	@Override
	public List<BodegaExistencia> findByIngreso(BodegaIngreso bodegaIngreso) {
		Criterion criterion = Restrictions.eq("bodegaIngreso", bodegaIngreso);
		return findByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao#deleteFromBodegaIngreso(co.innovate.rentavoz.model.bodega.BodegaIngreso)
	 */
	@Override
	public void deleteFromBodegaIngreso(BodegaIngreso bodegaIngreso) {

		Query query = getEntityManager().createQuery(new StringBuilder("DELETE FROM BodegaExistencia b WHERE b.bodegaIngreso = :ingreso").toString());
		query.setParameter("ingreso", bodegaIngreso);
		query.executeUpdate();
	}

	

}
