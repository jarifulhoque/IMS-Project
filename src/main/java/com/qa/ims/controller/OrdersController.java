package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrdersDAO ordersDAO;
	private Utils utils;
	
	public OrdersController(OrdersDAO ordersDAO, Utils utils) {
		super();
		this.ordersDAO = ordersDAO;
		this.utils = utils;
	}
	
	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Orders> readAll() {
		List<Orders> order = ordersDAO.readAll();
		for (Orders orders : order) {
			LOGGER.info(orders);
		}
		return order;
	}
	
	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Orders create() {
		LOGGER.info("Please enter a customer ID");
		Long customerId = utils.getLong();
		Orders orders = ordersDAO.create(new Orders(customerId));
		LOGGER.info("Order created");
		return orders;
	}
	
	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Orders update() {
		LOGGER.info("Please enter the id of the order you wish to update");
		Long orderId = utils.getLong();
		LOGGER.info("Would you like to add to, or remove an item from an order?");
		String addOrDelete = utils.getString();
		if (addOrDelete.equals("add")) {
			LOGGER.info("Please enter the id of the item you wish to add to the order");
			Long itemId = utils.getLong();			
			Orders orders = ordersDAO.addItem(orderId, itemId);
			LOGGER.info("Order Updated");
			return orders;
		} else if (addOrDelete.equals("remove")) {
			LOGGER.info("Please enter the id of the item you wish to remove from the order");
			Long itemId = utils.getLong();
			Orders orders = ordersDAO.deleteItem(orderId, itemId);
			LOGGER.info("Order updated");
			return orders;
		} else {
			System.out.println("Please type either 'add' or 'remove'");
		}
		return ordersDAO.read(orderId);
	}
	
	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return ordersDAO.delete(id);
	}

}