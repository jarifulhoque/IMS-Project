package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}
	
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		if(items.size() < 1) {
			LOGGER.info("There are no items stored in the database.");
			return items;
		}
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}
	
	@Override
	public Item create() {
		LOGGER.info("Please enter the item name");
		String name = utils.getString();
		LOGGER.info("Please enter the value");
		Double value = utils.getDouble();
		Item item = itemDAO.create(new Item(name, value));
		LOGGER.info("Item created");
		return item;
	}
	
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the item name");
		String name = utils.getString();
		LOGGER.info("Please enter the new value");
		Double value = utils.getDouble();
		Item item = itemDAO.update(new Item(id, name, value));
		LOGGER.info("Item Updated");
		return item;
	}
	
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		Item item = itemDAO.read(id);
		int result = itemDAO.delete(id);
		if(result > 0) {
			LOGGER.info("Item Deleted");			
		}
		return result;
	}
	

}