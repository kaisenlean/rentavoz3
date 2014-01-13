/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.services.tercero.TerceroService;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class BuscadorTercero
* @date 13/01/2014
*
 */
public abstract class BuscadorTercero extends Buscador<Tercero> {

	public abstract TerceroService getService();

	public abstract void selCentrope(Tercero centrope);

	@Override
	public String buscar() {
		List<Tercero> result = getService().findByCriterio(getCriterio());

		getItems().clear();

		for (Tercero centrope : result) {
			getItems().add(
					new BuscadorItem<Tercero>(centrope.getIdTecero() + "",
							centrope.toString(), centrope));
		}
		return null;
	}

	@Override
	public void asignar(Tercero t) {
		if (t == null) {
			selCentrope(new Tercero());
		} else {
			selCentrope(t);
		}
	}

	/**
	 * 
	 * @param evt
	 */
	@Override
	public void mostrar(ActionEvent evt) {
		List<Tercero> result = getService().findByCriterio(getCriterio());

		getItems().clear();

		for (Tercero centrope : result) {
			getItems().add(
					new BuscadorItem<Tercero>(centrope.getIdTecero() + "",
							centrope.toString(), centrope));
		}
		super.mostrar(evt);
	}

}
