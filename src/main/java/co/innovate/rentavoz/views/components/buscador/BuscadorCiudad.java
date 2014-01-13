/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.innovate.rentavoz.model.Ciudad;
import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.services.ciudad.CiudadService;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class BuscadorCiudad
* @date 13/01/2014
*
 */
public abstract class BuscadorCiudad extends Buscador<Ciudad> {

	public abstract CiudadService	 getService();

	public abstract Departamento getDepartamento();

	public abstract void selCentrope(Ciudad centrope);

	@Override
	public String buscar() {
		List<Ciudad> result = getService().findByCriterio(getCriterio(),
				getDepartamento());

		getItems().clear();

		for (Ciudad centrope : result) {
			getItems().add(
					new BuscadorItem<Ciudad>(centrope.getIdCiudad() + "",
							centrope.getCiuNombre(), centrope));
		}
		return null;
	}

	@Override
	public void asignar(Ciudad t) {
		if (t == null) {
			selCentrope(new Ciudad());
		} else {
			selCentrope(t);
		}
	}

	@Override
	public void mostrar(ActionEvent evt) {
		List<Ciudad> result = getService().findByCriterio(getCriterio(),
				getDepartamento());

		getItems().clear();

		for (Ciudad centrope : result) {
			getItems().add(
					new BuscadorItem<Ciudad>(centrope.getIdCiudad() + "",
							centrope.getCiuNombre(), centrope));
		}
		super.mostrar(evt);
	}

}
