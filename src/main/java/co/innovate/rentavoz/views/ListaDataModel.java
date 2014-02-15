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
public abstract class ListaDataModel<T, PK extends Serializable> extends
		LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	protected List<T> lista;
	
	protected SortOrder sortOrder;
	
	protected String sortField;

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
	public abstract void customLoad(int startingAt, int maxPerPage,
			String globalFilter, String sortField,
			SortOrder sortOrder);

	@Override
	public List<T> load(int startingAt, int maxPerPage, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		String globalFilter = null;
		this.sortField=sortField;
		this.sortOrder=sortOrder;
		if (sortOrder==null) {
			sortOrder=SortOrder.UNSORTED;
		}
		try {
			try {

				lista = null;
				globalFilter = filters.get("globalFilter");
				if (globalFilter == null) {
					globalFilter = String.valueOf("");
				}
				customLoad(startingAt, maxPerPage, globalFilter,  sortField,
						 sortOrder);
				if (lista == null) {
					lista = getService().findByCriteria(startingAt, maxPerPage);

				}
				setWrappedData(lista);

			} finally {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		setRowCount(-1);
		// set the total of players
		if (getRowCount() <= 0) {
			Integer count = customCount(globalFilter);

			if (count == null) {

				setRowCount(getService().countAll());
			} else {

				setRowCount(count);
			}
		}

		// set the page dize
		setPageSize(maxPerPage);

		return lista;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/01/2014
	 * @param globalFilter
	 * @return
	 */
	public abstract Integer customCount(String globalFilter);

	@Override
	public Object getRowKey(T player) {
		return player;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/01/2014
	 * @param lista
	 *            the lista to set
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
