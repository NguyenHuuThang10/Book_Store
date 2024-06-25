package model;

import java.sql.Date;
import java.util.Objects;

public class Order {
	private String id;
	private User user;
	private String userAddress;
	private String deliveryAddress;
	private String status;
	private String paymentMethod;
	private String paymentStatus;
	private double amountPaid;
	private double amountDue;
	private Date orderDate;
	private Date deliveryDate;
	
	
	public Order() {
		
	}


	public Order(String id, User user, String userAddress, String deliveryAddress, String status, String paymentMethod,
			String paymentStatus, double amountPaid, double amountDue, Date orderDate, Date deliveryDate) {
		super();
		this.id = id;
		this.user = user;
		this.userAddress = userAddress;
		this.deliveryAddress = deliveryAddress;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.amountPaid = amountPaid;
		this.amountDue = amountDue;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public String getDeliveryAddress() {
		return deliveryAddress;
	}


	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public double getAmountPaid() {
		return amountPaid;
	}


	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}


	public double getAmountDue() {
		return amountDue;
	}


	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public Date getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amountDue, amountPaid, deliveryAddress, deliveryDate, id, orderDate, paymentMethod,
				paymentStatus, status, user, userAddress);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Double.doubleToLongBits(amountDue) == Double.doubleToLongBits(other.amountDue)
				&& Double.doubleToLongBits(amountPaid) == Double.doubleToLongBits(other.amountPaid)
				&& Objects.equals(deliveryAddress, other.deliveryAddress)
				&& Objects.equals(deliveryDate, other.deliveryDate) && Objects.equals(id, other.id)
				&& Objects.equals(orderDate, other.orderDate) && Objects.equals(paymentMethod, other.paymentMethod)
				&& Objects.equals(paymentStatus, other.paymentStatus) && Objects.equals(status, other.status)
				&& Objects.equals(user, other.user) && Objects.equals(userAddress, other.userAddress);
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", userAddress=" + userAddress + ", deliveryAddress="
				+ deliveryAddress + ", status=" + status + ", paymentMethod=" + paymentMethod + ", paymentStatus="
				+ paymentStatus + ", amountPaid=" + amountPaid + ", amountDue=" + amountDue + ", orderDate=" + orderDate
				+ ", deliveryDate=" + deliveryDate + "]";
	}
	
	
	
	
	
}
