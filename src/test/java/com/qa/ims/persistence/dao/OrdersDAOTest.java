package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAOTest {
	
	private final CustomerDAO customerDAO = new CustomerDAO();
	private final ItemDAO itemDAO = new ItemDAO();
	private final OrdersDAO DAO = new OrdersDAO(customerDAO, itemDAO);
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Orders created = new Orders(2L, 1L);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testGetItems() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "Adapter", 10.00));
		assertEquals(expected, DAO.getItems(1L));
	}
	
	@Test
	public void testReadAll() {
		List<Orders> expected = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Adapter", 10.00));
		expected.add(new Orders(1L, 1L, items));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		List<Item> items = new ArrayList<>();
		final Orders expected = new Orders(1L, 1L, items);
		items.add(new Item(1L, "Adapter", 10.00));
		assertEquals(expected, DAO.read(ID));
	}
	
	@Test
	public void testAddItem() {
		List<Item> items = new ArrayList<>();
		final Orders updated = new Orders(1L, 1L, items);
		items.add(new Item(1L, "Adapter", 10.00));
		items.add(new Item(1L, "Adapter", 10.00));
		assertEquals(updated, DAO.addItem(1L, 1L));
	}
	
	@Test
	public void testDeleteItem() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Adapter", 10.00));
		final Orders updated = null;
		assertEquals(updated, DAO.deleteItem(1L, 1L));
	}
	


}