/**
 * 
 */
package co.innovate.rentavoz.repositories.linea.impl;

import java.io.Serializable;
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

import co.innovate.rentavoz.model.EstadoLinea;
import co.innovate.rentavoz.model.almacen.Linea;
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
	private static final Integer ESTADO_LINEA_REPO = 2;
	
	
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
	public Linea findByNumeroObjeto(String linNumero) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero ");
		q.setParameter("numero", linNumero);
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
		q.setParameter("sucursal", idSucursal);
		q.setParameter("estado", ESTADO_LINEA_REPO);
		q.setParameter("estado2", ESTADO_LINEA_DISPONIBLE_USO);
		return q.getResultList();
	}

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#findByCriteria(java.lang.String, int, int)
	 */
	@Override
	public List<Linea> findByCriteria(String query, int firstResult,
			int maxResults,Order order) {
		EstadoLinea estadoLinea = estadoLineaDao.findById(ESTADO_LINEA_REPO);
		if (estadoLinea==null) {
			logger.error(new StrBuilder("No se ha encontrado el estado de la linea con codigo : ").append(ESTADO_LINEA_REPO).toString());
		}
		Criterion criterion = Restrictions.conjunction().add(Restrictions.like("linNumero", query,MatchMode.ANYWHERE)).add(Restrictions.eq("estadoLineaidEstadoLinea", estadoLinea));
		
		
		return findByCriteria(firstResult, maxResults,order, criterion);
	
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.linea.LineaDao#countByCriteria(java.lang.String, int, int)
	 */
	@Override
	public int countByCriteria(String query) {
		EstadoLinea estadoLinea = estadoLineaDao.findById(ESTADO_LINEA_REPO);
		if (estadoLinea==null) {
			logger.error(new StrBuilder("No se ha encontrado el estado de la linea con codigo : ").append(ESTADO_LINEA_REPO).toString());
		}
		Criterion criterion = Restrictions.conjunction().add(Restrictions.like("linNumero", query,MatchMode.ANYWHERE)).add(Restrictions.eq("estadoLineaidEstadoLinea", estadoLinea));
		
		return countByCriteria(criterion);
	}
}
