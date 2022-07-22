package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {
	
	@Mock
	private Utils utils;
	
	@Mock
	private OrdersDAO dao;
	
	@InjectMocks
	private OrdersController controller;
	
	@Test
	public void testCreate() {
		final Long O_ID = (long) 1;
		final Orders created = new Orders(O_ID);
		
		Mockito.when(utils.getLong()).thenReturn(O_ID);
		Mockito.when(dao.create(created)).thenReturn(created);
		
		assertEquals(created, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testReadAll() {
		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders(1L, 1L));
		
		Mockito.when(dao.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());
		
		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	
	// Adding Item To Order Test
	@Test
	public void testUpdateAdd() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Charger", 20.00));
		Orders updated = new Orders(1L, 1L, items);		
		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getLong()).thenReturn(updated.getOrderId());
		Mockito.when(this.utils.getString()).thenReturn("add");
		Mockito.when(this.utils.getLong()).thenReturn(1L);
//		Mockito.when(this.dao.getItems(1L)).thenReturn(updated.getOrderItems());
		Mockito.when(this.dao.addItem(1L, 1L)).thenReturn(updated);
		
		assertEquals(updated, this.controller.update());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(dao, Mockito.times(1)).addItem(1L, 1L);
	}
	
	// Removing Item From Order Test
	@Test
	public void testUpdateDelete() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Charger", 20.00));
		final Orders updated = new Orders(1L, 1L, items);
			
		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getLong()).thenReturn(updated.getOrderId());
		Mockito.when(this.utils.getString()).thenReturn("remove");
		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.dao.deleteItem(1L, 1L)).thenReturn(updated);
			
		assertEquals(updated, this.controller.update());
			
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).deleteItem(1L, 1L);
	}
	
	@Test
	public void testDelete() {
		final long ID = 1L;
		
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);
		
		assertEquals(1L, this.controller.delete());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}