/**
 * 
 */
package co.innovate.rentavoz.exception;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project troca-co
 * @class BaseEXCEPTION
 * @date 8/12/2013
 * 
 */
public class BaseException extends Exception {

	/**
	 * 8/12/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/12/2013
	 * @param text
	 */
	public BaseException(String text) {
		super(text);
	}

}
