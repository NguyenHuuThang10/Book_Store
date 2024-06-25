package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DAOUser;
import model.User;
import util.Encode;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		if (action.equals("login")) {
			login(request, response);
		}else if (action.equals("logout")) {
			logout(request, response);
		}else if (action.equals("register")) {
			register(request, response);
		}else if (action.equals("reset")) {
			reset(request, response);
		}else if (action.equals("changeInfor")) {
			changeInfor(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		try {
			
			String url = "";
			String errors = "";
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			password = Encode.encode(password);
			
			User user = new User();
			
			user.setName(name);
			user.setPassword(password);
			
			DAOUser daoUser = new DAOUser();
			User u = daoUser.selectUserNameAndPassword(user);

			if(u != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", u);
				url = "/index.jsp";
				
			}else {
				errors += "Sai thông tin đăng nhập, vui lòng kiểm tra lại";
				url = "/users/login.jsp";
			}
			
			request.setAttribute("errors", errors);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			
			session.invalidate();
			response.sendRedirect("./index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String passwordConfirm = request.getParameter("passwordConfirm");
			String fullname = request.getParameter("fullname");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String address = request.getParameter("address");
			String deliveryAddress = request.getParameter("deliveryAddress");
			String purchaseAddress = request.getParameter("purchaseAddress");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String emailNews = request.getParameter("emailNews");
			
			request.setAttribute("name", name);
			request.setAttribute("password", password);
			request.setAttribute("passwordConfirm", passwordConfirm);
			request.setAttribute("fullname", fullname);
			request.setAttribute("birthday", birthday);
			request.setAttribute("address", address);
			request.setAttribute("deliveryAddress", deliveryAddress);
			request.setAttribute("purchaseAddress", purchaseAddress);
			request.setAttribute("phone", phone);
			request.setAttribute("email", email);
			request.setAttribute("name", name);
			request.setAttribute("mailNews", emailNews);
			
			Date dateBirthday = null;
			String dateError = "";
			if (birthday != null && !birthday.isEmpty()) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					sdf.setLenient(false);
					dateBirthday = new Date(sdf.parse(birthday).getTime());
				} catch (ParseException e) {
					dateError = "Định dạng ngày tháng không hợp lệ. Vui lòng chọn theo định dạng yyyy-MM-dd. </br>";
				}
			}
			
			String url = "";
			String errors = dateError;
			
			DAOUser daoUser = new DAOUser();
			
			if(daoUser.checkName(name)) {
				errors += "Tên đăng nhập đã tồn tại. Vui lòng nhập tên khác. </br>";
			}
			
			if(!password.equals(passwordConfirm)) {
				errors += "Nhập lại mật khẩu không trùng khớp. </br>";
			}else {
				password = Encode.encode(password);
			}
			
			request.setAttribute("errors", errors);
			
			if(errors.length() > 0) {
				url = "/users/register.jsp";
			}else {
				Random rd = new Random();
				String id = System.currentTimeMillis() + rd.nextInt(1000) + "";
				
				User user = new User(id, name, password, fullname, sex, address, deliveryAddress, purchaseAddress, dateBirthday, phone, email, emailNews!=null);
				daoUser.insert(user);
				url = "/users/login.jsp";
				request.setAttribute("success", "Đăng ký tài khoản thành công. Bạn có thể đăng nhập ngay bây giờ");
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String confirmNewPassword = request.getParameter("confirmNewPassword");
			
			oldPassword = Encode.encode(oldPassword);
			
			String url = "";
			String errors = "";
			
			
			
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("user");
			User user = null;
			
			if(obj != null) {
				user = (User)obj;
			}
			
			if(user == null) {
				errors += "Bạn chưa đăng nhập vào hệ thống! </br>";
				url = "/users/login.jsp";
			}else {
				url = "/users/reset.jsp";
				if(!oldPassword.equals(user.getPassword())) {
					errors += "Mật khẩu cũ không trùng khớp! </br>";
				}else {
					if(!newPassword.equals(confirmNewPassword)) {
						errors += "Nhập lại mật khẩu không trùng khớp! </br>";
					}else {
						newPassword = Encode.encode(newPassword);
						user.setPassword(newPassword);
						DAOUser daoUser = new DAOUser();
						
						if(daoUser.changePass(user)) {
							request.setAttribute("success", "Đổi mật khẩu thành công!");
						}else {
							errors += "Lỗi hệ thống, vui lòng thử lại sau! </br>";
						}
					}
					
				}
			}
			
			
			
			
			
			request.setAttribute("errors", errors);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private void changeInfor(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		try {

			String fullname = request.getParameter("fullname");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String address = request.getParameter("address");
			String deliveryAddress = request.getParameter("deliveryAddress");
			String purchaseAddress = request.getParameter("purchaseAddress");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String emailNews = request.getParameter("emailNews");

			Date dateBirthday = null;
			String dateError = "";
			if (birthday != null && !birthday.isEmpty()) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					sdf.setLenient(false);
					dateBirthday = new Date(sdf.parse(birthday).getTime());
				} catch (ParseException e) {
					dateError = "Định dạng ngày tháng không hợp lệ. Vui lòng chọn theo định dạng yyyy-MM-dd. </br>";
				}
			}

			String url = "";
			String errors = dateError;

			DAOUser daoUser = new DAOUser();

			Object obj = request.getSession().getAttribute("user");
			User user = null;

			if (obj != null) {
				user = (User) obj;
			}

			if (user != null) {
				String id = user.getId();

				User us = new User(id, "", "", fullname, sex, address, deliveryAddress, purchaseAddress, dateBirthday,
						phone, email, emailNews != null);

				daoUser.updateInfor(us);
				
				User user2 = daoUser.selectById(us);
				request.getSession().setAttribute("user", user2);
				
				request.setAttribute("success", "Thay đổi thông tin cá nhân thành công!");

			}
			
			request.setAttribute("errors", errors);
			url = "/users/changeInfor.jsp";

			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
