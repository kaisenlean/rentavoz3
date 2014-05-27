/**
 * 
 */
package co.innovate.rentavoz.repositories.linea;

import java.util.List;

import org.hibernate.criterion.Order;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LineaDao
 * @date 13/01/2014
 *
 */
public interface LineaDao extends GenericRepository<Linea, Integer> {
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @return
	*/
	Integer nextCodigo();
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param linNumero
	* @return
	*/
	boolean findBNumero(String linNumero);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param linNumero
	* @return
	*/
	Linea findBNumeroObjeto(String linNumero);
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/03/2014
	* @param linNumero
	* @return
	*/
	Linea getByNumeroLinea(String linNumero);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param linNumero
	* @return
	*/
	Linea findByNumeroObjeto(String linNumero,List<Sucursal> sucursales,FechaFacturacion fechaFacturacion)throws BaseException;
	
	
	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param linNumero
	* @return
	*/
	boolean findBNumero2(String linNumero) ;
	 
	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @return
	*/
	List<Linea> findDisponibles() ;
	 
	 
	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param startingAt
	* @param maxPerPage
	* @return
	*/
	List<Linea> findPlayers(int startingAt, int maxPerPage);
	 
	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param startingAt
	* @param maxPerPage
	* @return
	*/
	List<Linea> findPlayers2(int startingAt, int maxPerPage);
	 
	 
	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @return
	*/
	int countPlayersTotal() ;
	 
	 
	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param query
	* @return
	*/
	List<Linea> findByCriteria(String query);
	
	 /**
		* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
		* @date 13/01/2014
		* @param query
		* @return
		*/
		List<Linea> findByCriteria(String query,int firstResult , int maxResults,Order order,List<Sucursal> sucursales);
	 
	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param query
	* @param idSucursal
	* @return
	*/
	List<Linea> findByCriteria(String query, int idSucursal) ;
	
	
	
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 24/01/2014
	* @param query
	* @param firstResult
	* @param maxResults
	* @return
	*/
	int countByCriteria(String query,List<Sucursal> sucursales);
	
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/05/2014
	* @param firstResult
	* @param maxResults
	* @param corte
	* @param order
	* @return
	*/
	List<Linea> findByCorte(int firstResult,int maxResults,int corte ,Order order);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/05/2014
	* @param corte
	* @return
	*/
	int countByCorte(int corte);
	
	

	
	
}
