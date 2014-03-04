/**
 * 
 */
package co.innovate.rentavoz.repositories.linea.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.EstadoLinea;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.estadolinea.EstadoLineaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.linea.LineaDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LineaDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("lineaDao")
public class LineaDaoImpl extends GenericJpaRepository<Linea, Integer> implements Serializable,LineaDao {

	/**
	 * 4/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ESTADO_LINEA_SUSPENDIDA
	 */
	private static final int ESTADO_LINEA_SUSPENDIDA = 4;
	
	/**
	 * 4/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ESTADO_LINEA_SUSPENDIDA
	 */
	private static final int ESTADO_LINEA_INACTIVA = 5;
	/**
	 * 2/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * LIN_NUMERO
	 */
	private static final String LIN_NUMERO = "linNumero";
	/**
	 * 2/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * NO_ENCONTRADO_MSG
	 */
	private static final String NO_ENCONTRADO_MSG = "No se ha encontrado el estado de la linea con codigo : ";
	/**
	 * 2/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * SUCURSAL
	 */
	private static final String SUCURSAL = "sucursal";
	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * co.com.rentavoz.logica.jpa.fachadas
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final int ESTADO_LINEA_DISPONIBLE_CON_USO = 1;
	/**
	 * co.com.rentavoz.logica.jpa.fachadas
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final int ESTADO_LINEA_DISPONIBLE_USO = 4;
	public static final Integer ESTADO_LINEA_REPO = 2;
	public static final Integer ESTADO_LINEA_VENDIDA = 3;
	
	
	private Logger logger= Logger.getLogger(LineaDaoImpl.class);
	
	@Autowired
	private EstadoLineaDao estadoLineaDao;
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#nextCodigo()
	 */
	public Integer nextCodigo() {
		Query q = getEntityManager().createQuery(
				"SELECT MAX(l.idLinea) FROM Linea l ");
		q.setMaxResults(1);
		Integer resultado = Integer.valueOf("0");

		try {
			resultado = Integer.valueOf(q.getSingleResult().toString()) + 1;
		} catch (Exception e) {
			resultado = Integer.valueOf("1");
		}
		return resultado;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findBNumero(java.lang.String)
	 */
	public boolean findBNumero(String linNumero) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero");
		q.setParameter("numero", linNumero);
		q.setMaxResults(1);
		if (q.getResultList().isEmpty()) {
			return true;
		} else if (q.getSingleResult() == null) {
			return true;
		} else {
			return false;
		}

	}

	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findBNumeroObjeto(java.lang.String)
	 */
	public Linea findBNumeroObjeto(String linNumero) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero AND  l.estadoLineaidEstadoLinea.idEstadoLinea = :estado");
		q.setParameter("numero", linNumero);
		q.setParameter("estado", ESTADO_LINEA_REPO);
		q.setMaxResults(1);
		if (q.getResultList().isEmpty()) {
			return null;
		} else if (q.getSingleResult() != null) {
			return (Linea) q.getSingleResult();
		} else {
			return null;
		}

	}
	
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findByNumeroObjeto(java.lang.String)
	 */
	public Linea findByNumeroObjeto(String linNumero,List<Sucursal> sucursales,FechaFacturacion fechaFacturacion)throws BaseException{
		
		
		
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero AND l.sucursal IN (:sucursales)");
		q.setParameter("numero", linNumero);
		
		
		q.setParameter("sucursales", sucursales);
		q.setMaxResults(1);
		
		
		if (q.getResultList().isEmpty()) {
			return null;
		} else if (q.getSingleResult() != null) {
			
			Linea linea= (Linea) q.getSingleResult();
			
			if (linea==null) {
				return null;
			}
			
			if (linea.getEstadoLineaidEstadoLinea().getIdEstadoLinea()==ESTADO_LINEA_SUSPENDIDA) {
				throw new BaseException("Esta linea actualmente esta suspendida");
			}
			if (linea.getEstadoLineaidEstadoLinea().getIdEstadoLinea()==ESTADO_LINEA_INACTIVA) {
				throw new BaseException("Esta linea actualmente esta Inactiva en el sistema");
			}
			Query query= getEntityManager().createQuery("SELECT l FROM VentaLinea l WHERE l.ventaidVenta.fechaFacturacion = :periodo AND l.lineaidLinea = :linea");
			query.setParameter("periodo", fechaFacturacion);
			query.setParameter("linea", (Linea)q.getSingleResult());
			
			if (query.getResultList().isEmpty()) {
				
				return (Linea) q.getSingleResult();
			}else{
				
				throw new BaseException("Esta linea ya ha sido facturada en este periodo de facturación");
			}
		} else {
			return null;
		}

	}

	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findBNumero2(java.lang.String)
	 */
	public boolean findBNumero2(String linNumero) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero");
		q.setParameter("numero", linNumero);

		if (q.getResultList().size() == 1) {
			return true;
		} else
			return false;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findDisponibles()
	 */
	@SuppressWarnings("unchecked")
	public List<Linea> findDisponibles() {
		// buscar las lineas en estado disponibles
		Query q = getEntityManager()
				.createQuery(
						"SELECT l FROM Linea l WHERE l.estadoLineaidEstadoLinea.idEstadoLinea = :estado");
		q.setParameter("estado", ESTADO_LINEA_REPO);
		return q.getResultList();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findPlayers(int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Linea> findPlayers(int startingAt, int maxPerPage) {

		// regular query that will search for players in the db
		Query query = getEntityManager()
				.createQuery(
						"SELECT l FROM Linea l WHERE l.estadoLineaidEstadoLinea.idEstadoLinea = :estado");
		query.setParameter("estado", ESTADO_LINEA_REPO);
		
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);

		return query.getResultList();
	}
	
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findPlayers2(int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Linea> findPlayers2(int startingAt, int maxPerPage) {

		// regular query that will search for players in the db
		Query query = getEntityManager()
				.createQuery(
						"SELECT l FROM Linea l WHERE l.estadoLineaidEstadoLinea.idEstadoLinea = :estado  OR l.estadoLineaidEstadoLinea.idEstadoLinea = :estado2");
		query.setParameter("estado", ESTADO_LINEA_REPO);
		query.setParameter("estado2", ESTADO_LINEA_DISPONIBLE_CON_USO);
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);

		return query.getResultList();
	}


	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#countPlayersTotal()
	 */
	public int countPlayersTotal() {

		Query query = getEntityManager()
				.createQuery(
						"select COUNT(p) from Linea p WHERE p.estadoLineaidEstadoLinea.idEstadoLinea = :estado");

		query.setParameter("estado", ESTADO_LINEA_REPO);

		Number result = (Number) query.getSingleResult();

		return result.intValue();

	}


	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findByCriteria(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Linea> findByCriteria(String query) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero LIKE :query AND  l.estadoLineaidEstadoLinea.idEstadoLinea = :estado");
		q.setParameter("query", "%" + query + "%");
		q.setParameter("estado", ESTADO_LINEA_REPO);
		return q.getResultList();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findByCriteria(java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Linea> findByCriteria(String query, int idSucursal) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero LIKE :query AND ( l.estadoLineaidEstadoLinea.idEstadoLinea = :estado OR l.estadoLineaidEstadoLinea.idEstadoLinea = :estado2) AND l.sucursal.idSucursal = :sucursal");
		q.setParameter("query", "%" + query + "%");
		q.setParameter(SUCURSAL, idSucursal);
		q.setParameter("estado", ESTADO_LINEA_REPO);
		q.setParameter("estado2", ESTADO_LINEA_DISPONIBLE_USO);
		return q.getResultList();
	}

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findByCriteria(java.lang.String, int, int)
	 */
	@Override
	public List<Linea> findByCriteria(String query, int firstResult,
			int maxResults,Order order,List<Sucursal> sucursales) {
		EstadoLinea estadoLinea = estadoLineaDao.findById(ESTADO_LINEA_REPO);
		if (estadoLinea==null) {
			logger.error(new StrBuilder(NO_ENCONTRADO_MSG).append(ESTADO_LINEA_REPO).toString());
		}
		Criterion criterion = Restrictions.conjunction().add(Restrictions.like(LIN_NUMERO, query,MatchMode.ANYWHERE)).add(Restrictions.eq("estadoLineaidEstadoLinea", estadoLinea));
		
		Criterion criterion2= Restrictions.in(SUCURSAL, sucursales);
		
		return findByCriteria(firstResult, maxResults,order, criterion,criterion2);
	
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#countByCriteria(java.lang.String, int, int)
	 */
	@Override
	public int countByCriteria(String query,List<Sucursal> sucursales) {
		EstadoLinea estadoLinea = estadoLineaDao.findById(ESTADO_LINEA_REPO);
		if (estadoLinea==null) {
			logger.error(new StrBuilder(NO_ENCONTRADO_MSG).append(ESTADO_LINEA_REPO).toString());
		}
		Criterion criterion = Restrictions.conjunction().add(Restrictions.like(LIN_NUMERO, query,MatchMode.ANYWHERE)).add(Restrictions.eq("estadoLineaidEstadoLinea", estadoLinea));
		Criterion criterion2= Restrictions.in(SUCURSAL, sucursales);
		return countByCriteria(criterion,criterion2);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#getByNumeroLinea(java.lang.String)
	 */
	@Override
	public Linea getByNumeroLinea(String linNumero) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero ");
		q.setParameter("numero", linNumero);
		q.setMaxResults(BigInteger.ONE.intValue());
		if (q.getResultList().isEmpty()) {
			return null;
		} else if (q.getSingleResult() != null) {
			return (Linea) q.getSingleResult();
		} else {
			return null;
		}
	}
}
