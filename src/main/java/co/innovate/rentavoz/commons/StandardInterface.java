package co.innovate.rentavoz.commons;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.annotation.PostConstruct;

/**
 * 
 * @author ejody
 */
public interface StandardInterface<T> {

	String aceptar();

	void buscarrPorCriterio();

	void eliminarItem(T objeto);

	@PostConstruct
	void init();

	void renderizarItem(T objeto, boolean showForm);

	void verForm();

	void verFormNuevo(boolean showForm);

}
