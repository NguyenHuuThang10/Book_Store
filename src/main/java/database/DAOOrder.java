package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Order;
import model.User;
import model.Order;

public class DAOOrder implements DAOInterface<Order> {

	@Override
	public ArrayList<Order> selectAll() {
		ArrayList<Order> result = new ArrayList<Order>();

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM orders";
			PreparedStatement st = con.prepareStatement(sql);

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString(1);
				String userId = rs.getString(2);
				String userAddress = rs.getString(3);
				String deliveryAddress = rs.getString(4);
				String status = rs.getString(5);
				String paymentMethod = rs.getString(6);
				String paymentStatus = rs.getString(7);
				Double amountPaid = rs.getDouble(8);
				Double amountDue = rs.getDouble(9);
				Date orderDate = rs.getDate(10);
				Date deliveryDate = rs.getDate(11);

				User user = new DAOUser().selectById(new User(userId, "", "", "", "", "", "", "", null, "", "", false));

				Order order = new Order(id, user, userAddress, deliveryAddress, status, paymentMethod, paymentStatus,
						amountPaid, amountDue, orderDate, deliveryDate);

				result.add(order);

			}

			// B5 Dong ket noi
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public Order selectById(Order t) {
		Order result = null;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM orders WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString(1);
				String userId = rs.getString(2);
				String userAddress = rs.getString(3);
				String deliveryAddress = rs.getString(4);
				String status = rs.getString(5);
				String paymentMethod = rs.getString(6);
				String paymentStatus = rs.getString(7);
				Double amountPaid = rs.getDouble(8);
				Double amountDue = rs.getDouble(9);
				Date orderDate = rs.getDate(10);
				Date deliveryDate = rs.getDate(11);

				User user = new DAOUser().selectById(new User(userId, "", "", "", "", "", "", "", null, "", "", false));

				Order order = new Order(id, user, userAddress, deliveryAddress, status, paymentMethod, paymentStatus,
						amountPaid, amountDue, orderDate, deliveryDate);
				
				result = order;
			}

			// B5 Dong ket noi
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(Order t) {
		int result = 0;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql =  "INSERT INTO orders(id, userId, userAddress,  deliveryAddress, status, paymentMethod, paymentStatus, amountPaid, amountDue, orderDated, eliveryDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());
			st.setString(2, t.getUser().getId());
			st.setString(3, t.getUserAddress());
			st.setString(4, t.getDeliveryAddress());
			st.setString(5, t.getStatus());
			st.setString(6, t.getPaymentMethod());
			st.setString(7, t.getPaymentStatus());
			st.setDouble(8, t.getAmountPaid());
			st.setDouble(9, t.getAmountDue());
			st.setDate(10, t.getOrderDate());
			st.setDate(11, t.getDeliveryDate());

			// B3 Thuc thi cau lenh SQL
			result = st.executeUpdate();

			// B4;
			System.out.println("Running: " + sql);
			System.out.println("There is " + result + " line changed");

			// B5 Dong ket noi
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<Order> arr) {
		int count = 0;

		for (Order order : arr) {
			count += this.insert(order);
		}
		return count;
	}

	@Override
	public int delete(Order t) {
		int count = 0;
		DAOOrderDetail orderDetailDao = new DAOOrderDetail();
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "DELETE FROM orders WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());
			
			orderDetailDao.deleteAll(t);
			
			count = st.executeUpdate();

			// B4;
			System.out.println("Running: " + sql);
			System.out.println("There is " + count + " line deleted");

			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteAll(ArrayList<Order> arr) {
		int count = 0;

		for (Order order : arr) {
			count += this.delete(order);
		}
		return count;
	}

	@Override
	public int update(Order t) {
		int result = 0;

		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE orders SET userId=?, userAddress=?,  deliveryAddress=?, status=?, paymentMethod=?, paymentStatus=?, amountPaid=?, amountDue=?, orderDated=?, eliveryDate=? WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(11, t.getId());
			st.setString(1, t.getUser().getId());
			st.setString(2, t.getUserAddress());
			st.setString(3, t.getDeliveryAddress());
			st.setString(4, t.getStatus());
			st.setString(5, t.getPaymentMethod());
			st.setString(6, t.getPaymentStatus());
			st.setDouble(7, t.getAmountPaid());
			st.setDouble(8, t.getAmountDue());
			st.setDate(9, t.getOrderDate());
			st.setDate(10, t.getDeliveryDate());

			System.out.println(sql);
			result = st.executeUpdate();

			System.out.println("Running: " + sql);
			System.out.println("There is " + result + " line chaged");

			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static void main(String[] args) {
		DAOOrder orderDao = new DAOOrder();
		
//		ArrayList<Order> rs = orderDao.selectAll();
//		
//		for(Order order: rs) {
//			System.out.println(order.toString());
//		}
		
	}

}
