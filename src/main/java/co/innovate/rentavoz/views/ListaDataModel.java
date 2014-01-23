package co.innovate.rentavoz.views;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class ListaDataModel
 * @date 3/06/2013
 * 
 */
public abstract class ListaDataModel<T,PK extends Serializable> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	protected List<T> lista;

	public abstract GenericService<T, PK> getService();

	

	@Override
	public void setRowIndex(int rowIndex) {
		/*
		 * The following is in ancestor (LazyDataModel): this.rowIndex =
		 * rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
		 */
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else
			super.setRowIndex(rowIndex % getPageSize());
	}
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 22/01/2014
	*/
	public void customLoad(int startingAt, int maxPerPage){
		
	}

	@Override
	public List<T> load(int startingAt, int maxPerPage, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		try {
			try {

				// with datatable pagination limits
				lista = getService().findByCriteria(startingAt, maxPerPage);
				customLoad( startingAt,  maxPerPage);
				setWrappedData(lista);

			} finally {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// set the total of players
		if (getRowCount() <= 0) {
			setRowCount(getService().countAll());
		}

		// set the page dize
		setPageSize(maxPerPage);

		return lista;
	}

	@Override
	public Object getRowKey(T player) {
		return player;
	}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 22/01/2014
 * @param lista the lista to set
 */
public void setLista(List<T> lista) {
	this.lista = lista;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 22/01/2014
 * @return the lista
 */
public List<T> getLista() {
	return lista;
}
}
