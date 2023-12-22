

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
@WebServlet("/them2")
public class them2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public them2() {
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
            String maTaiKhoanCap2 = request.getParameter("MaTaiKhoanCap2");
            String tenTaiKhoanCap2 = request.getParameter("TenTaiKhoanCap2");
	        

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlytaikhoan", "root", "thog05122003");
	            String sql = "INSERT INTO taikhoancap2 (MaTaiKhoanCap1, MaTaiKhoanCap2, TenTaiKhoanCap2) VALUES (?, ?, ?)";
	            PreparedStatement statement = con.prepareStatement(sql);

	            statement.setString(1, maTaiKhoanCap1);
	            statement.setString(2, maTaiKhoanCap2);
	            statement.setString(3, tenTaiKhoanCap2);;
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
