package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Author;
import model.Category;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.User;

public class DAOOrderDetail implements DAOInterface<OrderDetail>{
	
	@Override
	public ArrayList<OrderDetail> selectAll() {
		ArrayList<OrderDetail> result = new ArrayList<OrderDetail>();

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM orderdetail";
			PreparedStatement st = con.prepareStatement(sql);

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString(1);
				String ordersId  = rs.getString(2);
				String productId   = rs.getString(3);
				Double quantity = rs.getDouble(4);
				Double originalPrice = rs.getDouble(5);
				Double discount = rs.getDouble(6);
				Double sellPrice = rs.getDouble(7);
				Double vat = rs.getDouble(8);
				Double total  = rs.getDouble(9);

				Order order = new DAOOrder().selectById(new Order(ordersId, null, "", "", "", "", "", 0, 0, null, null));
				Product product = new DAOProduct().selectById(new Product("", "", null, 0, 0, 0, 0, 0, null, "", "", ""));

				OrderDetail orderDetail = new OrderDetail(id, order, product, quantity, originalPrice, discount, sellPrice, vat, total);

				result.add(orderDetail);

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
	public OrderDetail selectById(OrderDetail t) {
		OrderDetail result = null;

		try {
			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM orderdetail WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString(1);
				String ordersId  = rs.getString(2);
				String productId   = rs.getString(3);
				Double quantity = rs.getDouble(4);
				Double originalPrice = rs.getDouble(5);
				Double discount = rs.getDouble(6);
				Double sellPrice = rs.getDouble(7);
				Double vat = rs.getDouble(8);
				Double total  = rs.getDouble(9);

				Order order = new DAOOrder().selectById(new Order(ordersId, null, "", "", "", "", "", 0, 0, null, null));
				Product product = new DAOProduct().selectById(new Product("", "", null, 0, 0, 0, 0, 0, null, "", "", ""));
				
				OrderDetail orderDetail = new OrderDetail(id, order, product, quantity, originalPrice, discount, sellPrice, vat, total);
				
				result = orderDetail;
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
	public int insert(OrderDetail t) {
		int result = 0;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql =  "INSERT INTO orderdetail(id, ordersId , productId , quantity, originalPrice, discount, sellPrice, vat, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());
			st.setString(2, t.getOrder().getId());
			st.setString(3, t.getProduct().getId());
			st.setDouble(4, t.getQuantity());
			st.setDouble(5, t.getOriginalPrice());
			st.setDouble(6, t.getDiscount());
			st.setDouble(7, t.getSellPrice());
			st.setDouble(8, t.getVat());
			st.setDouble(9, t.getTotal());
			
			// B3 Thuc thi cau lenh SQL
			result = st.executeUpdate();

			// B4;
			System.out.println("Running: " + sql);
			System.out.println("There is " + result + " line add");

			// B5 Dong ket noi
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<OrderDetail> arr) {
		int count = 0;
		for (OrderDetail orderDetail : arr) {
			count += this.insert(orderDetail);
		}
		return count;
	}

	@Override
	public int delete(OrderDetail t) {
		int count = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "DELETE FROM orderdetail WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());
			
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
	public int deleteAll(ArrayList<OrderDetail> arr) {
		int count = 0;
		for(OrderDetail OrderDetail : arr) {
			count += this.delete(OrderDetail);
		}
		return count;
	}
	
	//Xóa chi tiết đơn hàng khi đơn hàng bị xóa
	public int deleteAll(Order order) {
		int count = 0;
		ArrayList<OrderDetail> data = this.selectAll();
		for(OrderDetail OrderDetail : data) {
			if (OrderDetail.getOrder().equals(order)) {
				this.delete(OrderDetail);
			}
		}
		return count;
	}
	

	@Override
	public int update(OrderDetail t) {
		int result = 0;

		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE orderdetail SET ordersId=? , productId=? , quantity=?, originalPrice=?, discount=?, sellPrice=?, vat=?, total=? WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(9, t.getId());
			st.setString(1, t.getOrder().getId());
			st.setString(2, t.getProduct().getId());
			st.setDouble(3, t.getQuantity());
			st.setDouble(4, t.getOriginalPrice());
			st.setDouble(5, t.getDiscount());
			st.setDouble(6, t.getSellPrice());
			st.setDouble(7, t.getVat());
			st.setDouble(8, t.getTotal());

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

}
