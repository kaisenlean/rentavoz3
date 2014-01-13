/**
 * 
 */
package co.innovate.rentavoz.repositories.linea;

import java.util.List;

import co.innovate.rentavoz.model.almacen.Linea;
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
	* @date 13/01/2014
	* @param linNumero
	* @return
	*/
	Linea findByNumeroObjeto(String linNumero);
	
	
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
	* @param idSucursal
	* @return
	*/
	List<Linea> findByCriteria(String query, int idSucursal) ;
}
