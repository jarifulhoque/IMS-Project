package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Orders {
	
	private Long orderId;
	private Long customerId;
	public List<Item> orderItems = new ArrayList<>();
	
	public Orders(Long orderId, Long customerId, List<Item> orderItems) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.setOrderItems(orderItems);
	}

	public Orders(Long orderId, Long customerId) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
	}

	public Orders(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		double orderPrice = 0;
		for (Item i : orderItems)
			orderPrice += i.getItemPrice();
		return "Orders [orderId=" + orderId + ", customerId=" + customerId + ", orderItems=" + orderItems + "]\nOrder Total: �" + orderPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, orderId, orderItems);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(orderItems, other.orderItems);
	}
			
}