package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Author;
import model.Category;
import model.Product;

public class DAOProduct implements DAOInterface<Product> {

	@Override
	public ArrayList<Product> selectAll() {
		ArrayList<Product> result = new ArrayList<Product>();

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM product";
			PreparedStatement st = con.prepareStatement(sql);

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String authorId  = rs.getString(3);
				int publishYear = rs.getInt(4);
				Double importPrice = rs.getDouble(5);
				Double originalPrice = rs.getDouble(6);
				Double sellPrice = rs.getDouble(7);
				int quantity = rs.getInt(8);
				String categoryId  = rs.getString(9);
				String language = rs.getString(10);
				String description = rs.getString(11);
				String img = rs.getString(12);

				Author author = new DAOAuthor().selectById(new Author(authorId, "", null, ""));
				Category category = new DAOCategory().selectById(new Category(categoryId, ""));

				Product product = new Product(id, name, author, publishYear, importPrice, originalPrice, sellPrice, quantity, category, language, description, img);

				result.add(product);

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
	public Product selectById(Product t) {
		Product result = null;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM product WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String authorId  = rs.getString(3);
				int publishYear = rs.getInt(4);
				Double importPrice = rs.getDouble(5);
				Double originalPrice = rs.getDouble(6);
				Double sellPrice = rs.getDouble(7);
				int quantity = rs.getInt(8);
				String categoryId  = rs.getString(9);
				String language = rs.getString(10);
				String description = rs.getString(11);
				String img = rs.getString(12);

				Author author = new DAOAuthor().selectById(new Author(authorId, "", null, ""));
				Category category = new DAOCategory().selectById(new Category(categoryId, ""));

				Product product = new Product(id, name, author, publishYear, importPrice, originalPrice, sellPrice, quantity, category, language, description, img);
				
				result = product;
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
	public int insert(Product t) {
		int result = 0;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql =  "INSERT INTO product(id, name, author, publishYear, importPrice, originalPrice, sellPrice, quantity, category, language, description, img) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());
			st.setString(2, t.getName());
			st.setString(3, t.getAuthor().getId());
			st.setInt(4, t.getPublishYear());
			st.setDouble(5, t.getImportPrice());
			st.setDouble(6, t.getOriginalPrice());
			st.setDouble(7, t.getSellPrice());
			st.setInt(8, t.getQuantity());
			st.setString(9, t.getCategogy().getId());
			st.setString(10, t.getLanguage());
			st.setString(11, t.getDescription());
			st.setString(12, t.getImg());

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
	public int insertAll(ArrayList<Product> arr) {
		int count = 0;
		for (Product product : arr) {
			count += this.insert(product);
		}
		return count;
	}

	@Override
	public int delete(Product t) {
		int count = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "DELETE FROM product WHERE id=?";
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
	public int deleteAll(ArrayList<Product> arr) {
		int count = 0;
		for (Product product : arr) {
			count += this.delete(product);
		}
		return count;
	}

	@Override
	public int update(Product t) {
		int result = 0;

		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE product SET name=?, author=?, publishYear=?, importPrice=?, originalPrice=?, sellPrice=?, quantity=?, category=?, language=?, description=?, img=? WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(12, t.getId());
			st.setString(1, t.getName());
			st.setString(2, t.getAuthor().getId());
			st.setInt(3, t.getPublishYear());
			st.setDouble(4, t.getImportPrice());
			st.setDouble(5, t.getOriginalPrice());
			st.setDouble(6, t.getSellPrice());
			st.setInt(7, t.getQuantity());
			st.setString(8, t.getCategogy().getId());
			st.setString(9, t.getLanguage());
			st.setString(10, t.getDescription());
			st.setString(11, t.getImg());

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
