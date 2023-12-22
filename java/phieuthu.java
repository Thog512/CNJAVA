import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/phieuthu")
public class phieuthu extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public phieuthu() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8"); // Set charset to UTF-8 to support Unicode characters.
        try (PrintWriter out = res.getWriter();
             Connection con = getConnection(); // Use a separate method to get the connection.
             Statement stmt = con.createStatement()) {

            String sql = "SELECT * FROM phieuthu";
            ResultSet rs = stmt.executeQuery(sql);
            printTable(out, rs);
        } catch (Exception e) {
            e.printStackTrace(res.getWriter()); // Print the stack trace to the web page for debugging.
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlytaikhoan", "root", "thog05122003");
    }

    private void printTable(PrintWriter out, ResultSet rs) throws SQLException {
        out.println("<html><body>");
        out.println("<style> table { width: 100%; border-collapse: collapse; background-color: #E6E6FA; }");
        out.println("th, td { padding: 8px; text-align: left; border: 1px solid #000; } tr:hover {background-color: #FFFFFF;} </style>");        
        out.println("<table>");  
        out.println("<tr><th style=\"background-color: white;\">SoPhieuThu</th><th style=\"background-color: white;\">MaTaiKhoanDoiUngGhiNo</th>"
        		+ "<th style=\"background-color: white;\">MaTaiKhoanDoiUngGhiCo</th><th style=\"background-color: white;\">LyDoThuTien</th>"
        		+ "<th style=\"background-color: white;\">TieuKhoanGhiCo</th><th style=\"background-color: white;\">SoTien</th>"
        		+ "<th style=\"background-color: white;\">TenNguoiNopTien</th><th style=\"background-color: white;\">NgayLapPhieu</th></tr>");
        
	    while (rs.next()) {  
	        String soPhieuThu = rs.getString("SoPhieuThu");  
	        String maTaiKhoanDoiUngGhiNo = rs.getString("MaTaiKhoanDoiUngGhiNo");
	        String maTaiKhoanDoiUngGhiCo = rs.getString("MaTaiKhoanDoiUngGhiCo");
	        String lyDoThuTien = rs.getString("LyDoThuTien");
	        String tieuKhoanGhiCo = rs.getString("TieuKhoanGhiCo");  
	        String soTien = rs.getString("SoTien");  
	        String tenNguoiNopTien = rs.getString("TenNguoiNopTien");  
	        String ngayLapPhieu = rs.getString("NgayLapPhieu");  
	
	        out.println("<tr><td>" + soPhieuThu + "</td><td>" + maTaiKhoanDoiUngGhiNo + "</td><td>" + maTaiKhoanDoiUngGhiCo + "</td><td>" + lyDoThuTien + "</td><td>" + tieuKhoanGhiCo + "</td><td>" + soTien + "</td><td>" + tenNguoiNopTien + "</td><td>" + ngayLapPhieu + "</td></tr>");  
	    }
        out.println("</table>");
        out.println("<a href=\"quanly.html\" class=\"back-button\">Quay lại</a>");
        out.println("</body></html>");
    }
}
