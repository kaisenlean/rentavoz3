package co.innovate.rentavoz.mail.impl;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;

import co.innovate.rentavoz.mail.MailService;

/**
 * Servicio de envio de mails
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project troca-co
 * @class MailServiceImpl
 * @date 3/11/2013
 * 
 */
@Service("mailService")
public class MailServiceImpl implements MailService, Serializable {

	/**
	 * 23/11/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 5/11/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         VELOCITY_PATH
	 */
	private static final String VELOCITY_PATH = "velocity/";

	/**
	 * 3/11/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         VELOCITY_EXTENSION
	 */
	private static final String VELOCITY_EXTENSION = ".vm";

	/**
	 * 3/11/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         PATH_RELATIVE
	 */
	private static final String PATH_RELATIVE = "/src/main/resources/velocity/";

	/**
	 * 3/11/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         USER_DIR
	 */
	private static final String USER_DIR = "user.dir";

	private Log logMailService = LogFactory.getLog(MailServiceImpl.class);

	/** wrapper de Spring sobre javax.mail */
	private JavaMailSenderImpl mailSender;

	private String basePath = new StrBuilder(System.getProperty(USER_DIR)
			.toString()).append(PATH_RELATIVE).toString();

	@Autowired
	private VelocityEngine velocityEngine;

	/** correo electrónico del remitente */
	private String from;

	/** flag para indicar si está activo el servicio */
	private boolean active = true;

	private static final File[] NO_ATTACHMENTS = null;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/11/2013
	 * @return the basePath
	 */
	public String getBasePath() {
		return basePath;
	}

	public String getFrom() {
		return from;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/12/2013
	 * @return the logMailService
	 */
	public Log getLogMailService() {
		return logMailService;
	}

	public boolean isActive() {
		return active;
	}

	/**
	 * envío de email
	 * 
	 * @param to
	 *            correo electrónico del destinatario
	 * @param subject
	 *            asunto del mensaje
	 * @param text
	 *            cuerpo del mensaje
	 */
	@Override
	public void send(String to, String subject, String text) {
		send(to, subject, text, NO_ATTACHMENTS);
	}

	/**
	 * envío de email con attachments
	 * 
	 * @param to
	 *            correo electrónico del destinatario
	 * @param subject
	 *            asunto del mensaje
	 * @param text
	 *            cuerpo del mensaje
	 * @param attachments
	 *            ficheros que se anexarán al mensaje
	 */
	@Override
	public void send(String to, String subject, String text,
			File... attachments) {

		// chequeo de parámetros
		Assert.hasLength(to, "email 'to' needed");
		Assert.hasLength(subject, "email 'subject' needed");
		Assert.hasLength(text, "email 'text' needed");

		// asegurando la trazabilidad
		if (logMailService.isDebugEnabled()) {
			final boolean usingPassword = !"".equals(mailSender.getPassword());
			logMailService.debug("Sending email to: '" + to
					+ "' [through host: '" + mailSender.getHost() + ":"
					+ mailSender.getPort() + "', username: '"
					+ mailSender.getUsername() + "' usingPassword:"
					+ usingPassword + "].");
			logMailService.debug("isActive: " + active);
		}
		// el servicio esta activo?
		if (!active) {
			return;
		}
		// plantilla para el envío de email
		final MimeMessage message = mailSender.createMimeMessage();

		try {
			// el flag a true indica que va a ser multipart
			final MimeMessageHelper helper = new MimeMessageHelper(message,
					true);

			
			List<String> receptores=Arrays.asList(to.split(";"));
			String[] recipients= new String[receptores.size()];
			int cont=0;
			for (String string : receptores) {
				recipients[cont] = string;
				cont++;
			}
			// settings de los parámetros del envío
			helper.setTo(recipients);
			
			helper.setSubject(subject);
			helper.setFrom(getFrom());
			helper.setText(text);

			// adjuntando los ficheros
			if (attachments != null) {
				for (int i = 0; i < attachments.length; i++) {
					FileSystemResource file = new FileSystemResource(
							attachments[i]);
					helper.addAttachment(attachments[i].getName(), file);
					if (logMailService.isDebugEnabled()) {
						logMailService.debug("File '" + file + "' attached.");
					}
				}
			}

		} catch (MessagingException e) {
			new RuntimeException(e);
		}

		// el envío
		this.mailSender.send(message);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.mail.MailService#send(java.lang.String,
	 *      java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public void send(String to, String subject, String template,
			Map<String, Object> hTemplateVariables) {
		send(to, subject, template, hTemplateVariables, NO_ATTACHMENTS);

	}

	public void send(String to, String subject, String template,
			Map<String, Object> mapTemplate, File... attachments) {

		// chequeo de parámetros
		Assert.hasLength(to, "email 'to' needed");
		Assert.hasLength(subject, "email 'subject' needed");

		// asegurando la trazabilidad
		if (logMailService.isDebugEnabled()) {
			final boolean usingPassword = !"".equals(mailSender.getPassword());
			logMailService.debug("Sending email to: '" + to
					+ "' [through host: '" + mailSender.getHost() + ":"
					+ mailSender.getPort() + "', username: '"
					+ mailSender.getUsername() + "' usingPassword:"
					+ usingPassword + "].");
			logMailService.debug("isActive: " + active);
		}
		// el servicio esta activo?
		if (!active) {
			return;
		}
		// plantilla para el envío de email
		final MimeMessage message = mailSender.createMimeMessage();

		try {
			// el flag a true indica que va a ser multipart
			final MimeMessageHelper helper = new MimeMessageHelper(message,
					true);

			// settings de los parámetros del envío
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom(getFrom());

			// cargamos el path general en donde se encuentra el dir del archivo
			// *.vm

			StrBuilder templateDir = new StrBuilder(VELOCITY_PATH);
			templateDir.append(template);
			templateDir.append(VELOCITY_EXTENSION);

			String body = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, templateDir.toString(), mapTemplate);

			message.setContent(body, "text/html");

			// adjuntando los ficheros
			if (attachments != null) {
				for (int i = 0; i < attachments.length; i++) {
					FileSystemResource file = new FileSystemResource(
							attachments[i]);
					helper.addAttachment(attachments[i].getName(), file);
					if (logMailService.isDebugEnabled()) {
						logMailService.debug("File '" + file + "' attached.");
					}
				}
			}

		} catch (MessagingException e) {
			new RuntimeException(e);
		}

		// el envío
		this.mailSender.send(message);

	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/11/2013
	 * @param basePath
	 *            the basePath to set
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/12/2013
	 * @param logMailService
	 *            the logMailService to set
	 */
	public void setLogMailService(Log logMailService) {
		this.logMailService = logMailService;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

}