/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views;

import javax.annotation.PostConstruct;

/**
 * 
 * @author ejody
 */
public interface StandardInterface<T,PK> {

	@PostConstruct
	void init();

	void renderizarItem(T objeto, boolean showForm);

	void eliminarItem(T objeto);

	void verForm();

	void verFormNuevo(boolean showForm);

	String aceptar();

	void buscarrPorCriterio();

}
