package com.reportingapp.services.data;

import java.util.List;
import javax.ejb.Local;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Interface (contract) for our Currency DAO.
 * @author Trevor
 *
 */
@Local
public interface IDAO<T> {
	/**
	 * Generic contract method for getting all objects from the database.
	 * @return List of generic type T
	 * @throws Exception throws Exception
	 */
	public List<T> getAll() throws Exception;
	/**
	 * Generic contract method for getting one object from the database using an id.
	 * @param id type int
	 * @return T generic type T
	 * @throws Exception throws Exception
	 */
	public T get(int id) throws Exception;
	/**
	 * Generic contract method for adding an object to the database.
	 * @param object generic type T
	 * @return boolean
	 * @throws Exception throws Exception
	 */
	public boolean add(T object) throws Exception;
	/**
	 * Generic contract method for updating an object in the database.
	 * @param object generic type T
	 * @return boolean
	 * @throws Exception throws Exception
	 */
	public boolean update(T object) throws Exception;
	/**
	 * Generic contract method for deleting an object from the database.
	 * @param object generic type T
	 * @return boolean
	 * @throws Exception throws Exception
	 */
	public boolean delete(T object) throws Exception;
}
