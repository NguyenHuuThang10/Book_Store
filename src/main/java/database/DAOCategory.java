package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Category;
import model.Category;

public class DAOCategory implements DAOInterface<Category> {

	@Override
	public ArrayList<Category> selectAll() {
		ArrayList<Category> result = new ArrayList<Category>();

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM Category";
			PreparedStatement st = con.prepareStatement(sql);

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");

				Category Category = new Category(id, name);
				result.add(Category);

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
	public Category selectById(Category t) {
		Category result = null;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM Category WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");

				result = new Category(id, name);

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
	public int insert(Category t) {
		int result = 0;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "INSERT INTO Category(id, name) VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());
			st.setString(2, t.getName());

			// B3 Thuc thi cau lenh SQL
			result = st.executeUpdate();

			// B4;
			System.out.println("Running: " + sql);
			System.out.println("There is "+ result +" line changed");

			// B5 Dong ket noi
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<Category> arr) {
		int count = 0;
		
		for (Category category : arr) {
			count += this.insert(category);
		}
		return count;
	}

	@Override
	public int delete(Category t) {
		int count = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "DELETE FROM category WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());
			
			count = st.executeUpdate();
			
			// B4;
			System.out.println("Running: " + sql);
			System.out.println("There is "+ count +" line deleted");
			
			
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteAll(ArrayList<Category> arr) {
		int count = 0;
		
		for (Category category : arr) {
			count += this.delete(category);
		}
		return count;
	}

	@Override
	public int update(Category t) {
		int result = 0;
		
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "UPDATE category SET name=? WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getName());
			st.setString(2, t.getId());
			
			System.out.println(sql);
			result = st.executeUpdate();
			
			System.out.println("Running: " + sql);
			System.out.println("There is "+ result +" line chaged");
			
			JDBCUtil.closeConnection(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		DAOCategory category = new DAOCategory();
		
//		ArrayList<Category> rs = category.selectAll();
//		
//		for(Category cate : rs) {
//			System.out.println(cate.toString());
//		}
		
//		Category rs = category.selectById(new Category("TT", ""));
//		System.out.println(rs.toString());
		
//		Category data = new Category("TC", "Tinh cam");
//		
//		category.insert(data);
		
		
//		category.delete(new Category("TT", ""));
		
		Category data = category.selectById(new Category("TC", ""));
		
		data.setName("Kich tinh");
		
		category.update(data);
		
	}
}
