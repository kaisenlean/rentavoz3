/**
 * 
 */
package co.innovate.rentavoz.seguridad;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project troca-co
 * @class EncoderManager
 * @date 5/11/2013
 * 
 */
public interface EncoderManager {

	/**
	 * Convertir un String en MD5 Hash
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 5/11/2013
	 * @param input
	 * @return
	 */
	String encodeMd5Hash(String input);

}
