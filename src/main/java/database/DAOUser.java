package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Author;
import model.Category;
import model.Product;
import model.User;

public class DAOUser implements DAOInterface<User>{
	
	@Override
	public ArrayList<User> selectAll() {
		ArrayList<User> result = new ArrayList<User>();

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM user";
			PreparedStatement st = con.prepareStatement(sql);

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String password  = rs.getString(3);
				String fullname = rs.getString(4);
				String sex = rs.getString(5);
				String address = rs.getString(6);
				String deliveryAddress = rs.getString(7);
				String purchaseAddress = rs.getString(8);
				Date birthday  = rs.getDate(9);
				String phone = rs.getString(10);
				String email = rs.getString(11);
				Boolean emailNews = rs.getBoolean(12);

				User user = new User(id, name, password, fullname, sex, address, deliveryAddress, purchaseAddress, birthday, phone, email, emailNews);

				result.add(user);
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
	public User selectById(User t) {
		User result = null;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM user WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String password  = rs.getString(3);
				String fullname = rs.getString(4);
				String sex = rs.getString(5);
				String address = rs.getString(6);
				String deliveryAddress = rs.getString(7);
				String purchaseAddress = rs.getString(8);
				Date birthday  = rs.getDate(9);
				String phone = rs.getString(10);
				String email = rs.getString(11);
				Boolean emailNews = rs.getBoolean(12);

				User user = new User(id, name, password, fullname, sex, address, deliveryAddress, purchaseAddress, birthday, phone, email, emailNews);
				
				result = user;
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
	public int insert(User t) {
		int result = 0;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql =  "INSERT INTO user(id, name, password, fullname, sex, address, deliveryAddress, purchaseAddress, birthday, phone, email, emailNews) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());
			st.setString(2, t.getName());
			st.setString(3, t.getPassword());
			st.setString(4, t.getFullname());
			st.setString(5, t.getSex());
			st.setString(6, t.getAddress());
			st.setString(7, t.getDeliveryAddress());
			st.setString(8, t.getPurchaseAddress());
			st.setDate(9, t.getBirthday());
			st.setString(10, t.getPhone());
			st.setString(11, t.getEmail());
			st.setBoolean(12, t.isEmailNews());
			
			

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
	public int insertAll(ArrayList<User> arr) {
		int count = 0;
		for (User user : arr) {
			count += this.insert(user);
		}
		return count;
	}

	@Override
	public int delete(User t) {
		int count = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "DELETE FROM user WHERE id=?";
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
	public int deleteAll(ArrayList<User> arr) {
		int count = 0;
		for(User User : arr) {
			count += this.delete(User);
		}
		return count;
	}

	@Override
	public int update(User t) {
		int result = 0;

		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE user SET name=?, password=?, fullname=?, sex=?, address=?, deliveryAddress=?, purchaseAddress=?, birthday=?, phone=?, email=?, emailNews=? WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(12, t.getId());
			st.setString(1, t.getName());
			st.setString(2, t.getPassword());
			st.setString(3, t.getFullname());
			st.setString(4, t.getSex());
			st.setString(5, t.getAddress());
			st.setString(6, t.getDeliveryAddress());
			st.setString(7, t.getPurchaseAddress());
			st.setDate(8, t.getBirthday());
			st.setString(9, t.getPhone());
			st.setString(10, t.getEmail());
			st.setBoolean(11, t.isEmailNews());

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
	
	
	public boolean checkName(String name) {
		boolean result = false;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM user WHERE name=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				result = true;
				break;
			}

			// B5 Dong ket noi
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public User selectUserNameAndPassword(User t) {
		User result = null;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM user WHERE name=? AND password=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getName());
			st.setString(2, t.getPassword());
			

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String password  = rs.getString(3);
				String fullname = rs.getString(4);
				String sex = rs.getString(5);
				String address = rs.getString(6);
				String deliveryAddress = rs.getString(7);
				String purchaseAddress = rs.getString(8);
				Date birthday  = rs.getDate(9);
				String phone = rs.getString(10);
				String email = rs.getString(11);
				Boolean emailNews = rs.getBoolean(12);

				User user = new User(id, name, password, fullname, sex, address, deliveryAddress, purchaseAddress, birthday, phone, email, emailNews);
				
				result = user;
			}

			// B5 Dong ket noi
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	
	public boolean changePass(User t) {
		int result = 0;

		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE user SET password=? WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(2, t.getId());
			st.setString(1, t.getPassword());

			System.out.println(sql);
			result = st.executeUpdate();

			System.out.println("Running: " + sql);
			System.out.println("There is " + result + " line chaged");

			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result>0;
	}
	
	
	public int updateInfor(User t) {
		int result = 0;

		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE user SET fullname=?, sex=?, address=?, deliveryAddress=?, purchaseAddress=?, birthday=?, phone=?, email=?, emailNews=? WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(10, t.getId());
			st.setString(1, t.getFullname());
			st.setString(2, t.getSex());
			st.setString(3, t.getAddress());
			st.setString(4, t.getDeliveryAddress());
			st.setString(5, t.getPurchaseAddress());
			st.setDate(6, t.getBirthday());
			st.setString(7, t.getPhone());
			st.setString(8, t.getEmail());
			st.setBoolean(9, t.isEmailNews());

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
//		DAOUser dao = new DAOUser();
//		User user = new User();
//		user.setName("messi");
//		user.setPassword("Ni2BYVkjhxc72c/oLzUHwr2sJro=");
//		User rs = dao.selectUserNameAndPassword(user);
//		
//		System.out.println(rs.toString());
	}

}
