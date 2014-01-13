/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.innovate.rentavoz.model.Operador;
import co.innovate.rentavoz.services.operador.OperadorService;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class BuscadorOperador
* @date 13/01/2014
*
 */
public abstract class BuscadorOperador extends Buscador<Operador> {

	public abstract OperadorService getService();

	public abstract void selCentrope(Operador centrope);

	@Override
	public String buscar() {
		List<Operador> result = getService().findByCriterio(getCriterio());

		getItems().clear();

		for (Operador centrope : result) {
			getItems().add(
					new BuscadorItem<Operador>(centrope.getIdOperador() + "",
							centrope.getOpeNombre(), centrope));
		}
		return null;
	}

	@Override
	public void asignar(Operador t) {
		if (t == null) {
			selCentrope(new Operador());
		} else {
			selCentrope(t);
		}
	}

	@Override
	public void mostrar(ActionEvent evt) {
		List<Operador> result = getService().findByCriterio(getCriterio());

		getItems().clear();

		for (Operador centrope : result) {
			getItems().add(
					new BuscadorItem<Operador>(centrope.getIdOperador() + "",
							centrope.getOpeNombre(), centrope));
		}
		super.mostrar(evt);
	}

}
