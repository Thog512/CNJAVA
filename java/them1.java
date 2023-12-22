

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThemAlbums
 */
@WebServlet("/them1")
public class them1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public them1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html; charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<html><meta charset='UTF-8'><body>");

            String maTaiKhoanCap1 = request.getParameter("MaTaiKhoanCap1");  
            String tenTaiKhoan = request.getParameter("TenTaiKhoan");
            String loaiTaiSanNoCo = request.getParameter("LoaiTaiSanNoCo");
	        

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlytaikhoan", "root", "thog05122003");
	            String sql = "INSERT INTO taikhoancap1 (MaTaiKhoanCap1, TenTaiKhoan, LoaiTaiSanNoCo) VALUES (?, ?, ?)";
	            PreparedStatement statement = con.prepareStatement(sql);

	            statement.setString(1, maTaiKhoanCap1);
	            statement.setString(2, tenTaiKhoan);
	            statement.setString(3, loaiTaiSanNoCo);;
	            statement.executeUpdate();
	            response.sendRedirect("quanly.html");

	            statement.close();
	            con.close();
	        } catch (Exception e) {
	            out.println("<p>Error: " + e.getMessage() + "</p>");
	        }
	        out.println("</body></html>");
	    }
}
