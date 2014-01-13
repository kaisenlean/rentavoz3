/**
 * 
 */
package co.innovate.rentavoz.seguridad.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.seguridad.EncoderManager;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project troca-co
 * @class EncoderManagerImpl
 * @date 5/11/2013
 * 
 */
@Service("encoderManager")
public class EncoderManagerImpl implements EncoderManager, Serializable {

	/**
	 * 29/12/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         SIZE_BUFFERING
	 */
	private static final int SIZE_BUFFERING = 16;
	/**
	 * 13/11/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Logger logEncoderManager = LoggerFactory
			.getLogger(EncoderManagerImpl.class);
	/**
	 * 6/11/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         MD52
	 */
	private static final String MD52 = "MD5";

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.seguridad.EncoderManager#encodeMd5Hash(java.lang.String)
	 */
	@Override
	public String encodeMd5Hash(String input) {
		String md5 = null;

		if (null == input) {
			return null;
		}

		try {

			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance(MD52);

			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(SIZE_BUFFERING);

		} catch (NoSuchAlgorithmException e) {

			logEncoderManager.error(e.toString());
		}
		return md5;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/12/2013
	 * @return the logEncoderManager
	 */
	public Logger getLogEncoderManager() {
		return logEncoderManager;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/12/2013
	 * @param logEncoderManager
	 *            the logEncoderManager to set
	 */
	public void setLogEncoderManager(Logger logEncoderManager) {
		this.logEncoderManager = logEncoderManager;
	}
}
