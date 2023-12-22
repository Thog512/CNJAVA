

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
@WebServlet("/them3")
public class them3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public them3() {
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

            String maTaiKhoanSuDung = request.getParameter("MaTaiKhoanSuDung");  
            String maTaiKhoanCap1 = request.getParameter("MaTaiKhoanCap1");
            String maTaiKhoanCap2 = request.getParameter("MaTaiKhoanCap2");
            String maTaiKhoanCap3 = request.getParameter("MaTaiKhoanCap2");  
            String tenTaiKhoanCap3 = request.getParameter("TenTaiKhoanCap3");
            String soDuDauKy = request.getParameter("SoDuDauKy");	        

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlytaikhoan", "root", "thog05122003");
	            String sql = "INSERT INTO taikhoancap3 (MaTaiKhoanSuDung, MaTaiKhoanCap1, MaTaiKhoanCap2, MaTaiKhoanCap3, TenTaiKhoanCap3, SoDuDauKy) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = con.prepareStatement(sql);

	            statement.setString(1, maTaiKhoanSuDung);
	            statement.setString(2, maTaiKhoanCap1);
	            statement.setString(3, maTaiKhoanCap2);;
	            statement.setString(4, maTaiKhoanCap3);
	            statement.setString(5, tenTaiKhoanCap3);
	            statement.setString(6, soDuDauKy);;	            
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
