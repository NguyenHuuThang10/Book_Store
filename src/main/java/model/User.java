package model;

import java.sql.Date;
import java.util.Objects;

public class User {
	private String id;
	private String name;
	private String password;
	private String fullname;
	private String sex;
	private String address;
	private String deliveryAddress;
	private String purchaseAddress;
	private Date birthday;
	private String phone;
	private String email;
	private boolean emailNews;
	
	public User() {
		
	}

	public User(String id, String name, String password, String fullname, String sex, String address,
			String deliveryAddress, String purchaseAddress, Date birthday, String phone, String email,
			boolean emailNews) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.fullname = fullname;
		this.sex = sex;
		this.address = address;
		this.deliveryAddress = deliveryAddress;
		this.purchaseAddress = purchaseAddress;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.emailNews = emailNews;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getPurchaseAddress() {
		return purchaseAddress;
	}

	public void setPurchaseAddress(String purchaseAddress) {
		this.purchaseAddress = purchaseAddress;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailNews() {
		return emailNews;
	}

	public void setEmailNews(boolean emailNews) {
		this.emailNews = emailNews;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, birthday, deliveryAddress, email, emailNews, fullname, id, name, password, phone,
				purchaseAddress, sex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return  Objects.equals(id, other.id) ;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", fullname=" + fullname + ", sex="
				+ sex + ", address=" + address + ", deliveryAddress=" + deliveryAddress + ", purchaseAddress="
				+ purchaseAddress + ", birthday=" + birthday + ", phone=" + phone + ", email=" + email + ", emailNews="
				+ emailNews + "]";
	}
	
	
	
	
	
	
}
