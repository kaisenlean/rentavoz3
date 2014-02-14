/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.buscador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

/**
 * 
 * @author ejody
 */
public abstract class Buscador<T> {

	private boolean mostrarPopup;
	@SuppressWarnings("rawtypes")
	private List<BuscadorItem> items;

	private String criterio;
	private T item;
	private BuscadorListener listener;

	public String getCriterio() {
		return criterio.toUpperCase();
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	@SuppressWarnings("rawtypes")
	public List<BuscadorItem> getItems() {
		return items;
	}

	public void setItems(@SuppressWarnings("rawtypes") List<BuscadorItem> items) {
		this.items = items;
	}

	public boolean isMostrarPopup() {
		return mostrarPopup;
	}

	public void setMostrarPopup(boolean mostrarPopup) {
		this.mostrarPopup = mostrarPopup;
	}

	public void mostrar() {
		mostrar(null);
	}

	public void mostrar(ActionEvent evt) {
		buscar();
		mostrarPopup = true;
		setMostrarPopup(true);
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	@SuppressWarnings("rawtypes")
	public Buscador() {
		this.mostrarPopup = false;
		this.items = new ArrayList<BuscadorItem>();
		criterio = "";
		listener = new BuscadorListener() {

			@Override
			public void listener() {
				mostrar();
			}
		};
	}

	public abstract String buscar();

	public abstract void asignar(T t);

	public boolean getMostrarLimpiar() {
		return true;
	}

	public String limpiar() {
		asignar(null);

		cerrar();
		return null;
	}

	public void seleccionar(T t) {
		item = (T) t;
		System.out.println(t);
		asignar(item);

		cerrar();
		RequestContext.getCurrentInstance().execute("dlg.hide()");
	}

	public String cerrar() {
		getItems().clear();
		setCriterio("");

		setMostrarPopup(false);
		return null;
	}

	public void reiniciar() {
		setCriterio("");
		getItems().clear();
		setMostrarPopup(false);
	}

	public BuscadorListener getListener() {
		return listener;
	}

	public void setListener(BuscadorListener listener) {
		this.listener = listener;
	}

}
