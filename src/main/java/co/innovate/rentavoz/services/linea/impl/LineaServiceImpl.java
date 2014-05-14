/**
 * 
 */
package co.innovate.rentavoz.services.linea.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.linea.LineaDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.linea.LineaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LineaServiceImpl
 * @date 13/01/2014
 *
 */
@Service("lineaService")
public class LineaServiceImpl extends GenericServiceImpl<Linea, Integer> implements Serializable ,LineaService {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private LineaDao lineaDao;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#nextCodigo()
	 */
	@Override
	public Integer nextCodigo() {
		return lineaDao.nextCodigo();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findBNumero(java.lang.String)
	 */
	@Override
	public boolean findBNumero(String linNumero) {
		return lineaDao.findBNumero(linNumero);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findBNumeroObjeto(java.lang.String)
	 */
	@Override
	public Linea findBNumeroObjeto(String linNumero) {
		return lineaDao.findBNumeroObjeto(linNumero);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findByNumeroObjeto(java.lang.String)
	 */
	@Override
	public Linea findByNumeroObjeto(String linNumero,List<Sucursal> sucursales,FechaFacturacion fechaFacturacion)throws BaseException{
		return lineaDao.findByNumeroObjeto(linNumero,sucursales,fechaFacturacion);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findBNumero2(java.lang.String)
	 */
	@Override
	public boolean findBNumero2(String linNumero) {
		return lineaDao.findBNumero2(linNumero);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findDisponibles()
	 */
	@Override
	public List<Linea> findDisponibles() {
		return lineaDao.findDisponibles();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findPlayers(int, int)
	 */
	@Override
	public List<Linea> findPlayers(int startingAt, int maxPerPage) {
		return lineaDao.findPlayers(startingAt, maxPerPage);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findPlayers2(int, int)
	 */
	@Override
	public List<Linea> findPlayers2(int startingAt, int maxPerPage) {
		return lineaDao.findPlayers2(startingAt, maxPerPage);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#countPlayersTotal()
	 */
	@Override
	public int countPlayersTotal() {
		return lineaDao.countPlayersTotal();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findByCriteria(java.lang.String)
	 */
	@Override
	public List<Linea> findByCriteria(String query) {
		return lineaDao.findByCriteria(query);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findByCriteria(java.lang.String, int)
	 */
	@Override
	public List<Linea> findByCriteria(String query, int idSucursal) {
		return lineaDao.findByCriteria(query, idSucursal);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Linea, Integer> getDao() {
		return lineaDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findByCriteria(java.lang.String, int, int)
	 */
	@Override
	public List<Linea> findByCriteria(String query, int firstResult,
			int maxResults,Order order,List<Sucursal> sucursales) {
		
		return lineaDao.findByCriteria(query, firstResult, maxResults,order,sucursales);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#countByCriteria(java.lang.String)
	 */
	@Override
	public int countByCriteria(String query,List<Sucursal> sucursales) {
		return lineaDao.countByCriteria(query,sucursales);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#getByNumeroLinea(java.lang.String)
	 */
	@Override
	public Linea getByNumeroLinea(String linNumero) {
		return lineaDao.getByNumeroLinea(linNumero);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#findByCorte(int, int, int, org.hibernate.criterion.Order)
	 */
	@Override
	public List<Linea> findByCorte(int firstResult, int maxResults, int corte,
			Order order) {
		return lineaDao.findByCorte(firstResult, maxResults, corte, order);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.linea.LineaService#countByCorte(int)
	 */
	@Override
	public int countByCorte(int corte) {
		return lineaDao.countByCorte(corte);
	}

	


}
