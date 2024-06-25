package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Author;

public class DAOAuthor implements DAOInterface<Author> {

	@Override
	public ArrayList<Author> selectAll() {
		ArrayList<Author> result = new ArrayList<Author>();

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM author";
			PreparedStatement st = con.prepareStatement(sql);

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String story = rs.getString("story");

				Author author = new Author(id, name, birthday, story);
				result.add(author);

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
	public Author selectById(Author t) {
		Author result = null;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "SELECT * FROM author WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());

			// B3 Thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// B4;
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String story = rs.getString("story");

				result = new Author(id, name, birthday, story);

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
	public int insert(Author t) {
		int result = 0;

		try {

			// B1: Ket noi database
			Connection con = JDBCUtil.getConnection();

			// B2: Tao ra doi tuong statment
			String sql = "INSERT INTO author(id, name, birthday, story) VALUES (?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getId());
			st.setString(2, t.getName());
			st.setDate(3, t.getBirthday());
			st.setString(4, t.getStory());

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
	public int insertAll(ArrayList<Author> arr) {
		int count = 0;
		
		for (Author author : arr) {
			count += this.insert(author);
		}
		return count;
	}

	@Override
	public int delete(Author t) {
		int count = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "DELETE FROM author WHERE id=?";
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
	public int deleteAll(ArrayList<Author> arr) {
		int count = 0;
		
		for (Author author : arr) {
			count += this.delete(author);
		}
		return count;
	}

	@Override
	public int update(Author t) {
		int result = 0;
		
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "UPDATE author SET name=?, birthday=?, story=? WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(4, t.getId());
			st.setString(1, t.getName());
			st.setDate(2, t.getBirthday());
			st.setString(3, t.getStory());
			
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
		DAOAuthor authorDao = new DAOAuthor();
//		ArrayList<Author> rs = authorDao.selectAll();
//		
//		for(Author author : rs) {
//			System.out.println(author.toString());
//		}

//		Author rs = authorDao.selectById(new Author("ag01", "", null, ""));
//
//		System.out.println(rs.toString());
		
//		Author author = new Author("BT01", "Tran Van B", new Date(2002-1901, 20, 10), "Mo ta");
//		authorDao.insert(author);
		
//		authorDao.delete(new Author("BT01", "", null, ""));
		
		Author author = authorDao.selectById(new Author("AG01", "", null, ""));
		System.out.println(author.toString());
		
		author.setStory("Thay đổi");
		
		authorDao.update(author);

	}

}
