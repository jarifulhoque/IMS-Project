package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	private CustomerDAO customerDAO;
	private ItemDAO itemDAO;
	
	public OrdersDAO(CustomerDAO customerDAO, ItemDAO itemDAO) {
		super();
		this.customerDAO = customerDAO;
		this.itemDAO = itemDAO;
	}

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("order_id");
		Long customerId = resultSet.getLong("fk_customer_id");
		List<Item> orderItems = getItems(orderId);
		return new Orders(orderId, customerId, orderItems);
	}
	
	/**
	 * Showing item prices when read order is called
	 * 
	 * @param orderId
	 * 
	 * @return
	 */
	
	public List<Item> getItems(Long orderId) {
		List<Long> itemIds = new ArrayList<>();
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items WHERE fk_order_id = " + orderId);) {
			while (resultSet.next()) {
				itemIds.add(resultSet.getLong("fk_item_id"));
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		List<Item> itemList = new ArrayList<>();
		for (Long i : itemIds) {
			itemList.add(itemDAO.read(i));
		}
		return itemList;
	}
	
	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Orders> order = new ArrayList<>();
			while (resultSet.next()) {
				order.add(modelFromResultSet(resultSet));
			}
			return order;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Creates an order in the database
	 * 
	 * @param orders - takes in an order object. id will be ignored
	 */
	@Override
	public Orders create(Orders orders) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(fk_customer_id) VALUES (?)");) {
			statement.setLong(1, orders.getCustomerId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Orders read(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");) {
			statement.setLong(1, orderId);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Updates an order in the database
	 * 
	 * @param orders - takes in an order object, the id field will be used to
	 * 					update that order in the database
	 * @return
	 */
	
	public Orders addItem(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items (fk_order_id, fk_item_id) VALUES (?, ?)")) {
			statement.setLong(1, orderId);
			statement.setLong(2, itemId);
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return read(orderId);
	}
	
	/**
	 * Removes an item from the database
	 * 
	 * @param itemId - id of the item to be removed
	 */
	
	public Orders deleteItem(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM order_items WHERE fk_order_id = ? AND fk_item_id = ? LIMIT 1")) {
			statement.setLong(1, orderId);
			statement.setLong(2, itemId);
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Deletes an order from the database
	 * 
	 * @param orderId - id of the order
	 */
	@Override
	public int delete(long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, orderId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Orders update(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}

}