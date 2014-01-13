/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.services.departamento.DepartamentoService;

/**
 * 
 * @author ejody
 */
public abstract class BuscadorDepartamento extends Buscador<Departamento> {

	public abstract DepartamentoService getService();

	public abstract void selCentrope(Departamento centrope);

	@Override
	public String buscar() {
		List<Departamento> result = getService().findByCriterio(getCriterio());

		getItems().clear();

		for (Departamento centrope : result) {
			getItems().add(
					new BuscadorItem<Departamento>(centrope.getIdDepartamento()
							+ "", centrope.getDepNombre(), centrope));
		}
		return null;
	}

	@Override
	public void asignar(Departamento t) {
		if (t == null) {
			selCentrope(new Departamento());
		} else {
			selCentrope(t);
		}
	}

	@Override
	public void mostrar(ActionEvent evt) {
		List<Departamento> result = getService().findByCriterio(getCriterio());

		getItems().clear();

		for (Departamento centrope : result) {
			getItems().add(
					new BuscadorItem<Departamento>(centrope.getIdDepartamento()
							+ "", centrope.getDepNombre(), centrope));
		}
		super.mostrar(evt);
	}

}
