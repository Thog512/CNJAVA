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

@WebServlet("/taikhoancap3")
public class taikhoancap3 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public taikhoancap3() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8"); // Set charset to UTF-8 to support Unicode characters.
        try (PrintWriter out = res.getWriter();
             Connection con = getConnection(); // Use a separate method to get the connection.
             Statement stmt = con.createStatement()) {

            String sql = "SELECT * FROM taikhoancap3";
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
        out.println("<tr><th style=\"background-color: white;\">MaTaiKhoanSuDung</th><th style=\"background-color: white;\">MaTaiKhoanCap1</th>"
        		+ "<th style=\"background-color: white;\">MaTaiKhoanCap2</th><th style=\"background-color: white;\">MaTaiKhoanCap3</th>"
        		+ "<th style=\"background-color: white;\">TenTaiKhoanCap3</th><th style=\"background-color: white;\">SoDuDauKy</th></tr>");
       
        while (rs.next()) {  
	        String maTaiKhoanSuDung = rs.getString("MaTaiKhoanSuDung");  
	        String maTaiKhoanCap1 = rs.getString("MaTaiKhoanCap1");
	        String maTaiKhoanCap2 = rs.getString("MaTaiKhoanCap2");
	        String maTaiKhoanCap3 = rs.getString("MaTaiKhoanCap3");  
	        String tenTaiKhoanCap3 = rs.getString("TenTaiKhoanCap3");  
	        String soDuDauKy = rs.getString("SoDuDauKy"); 
	        
	        out.println("<tr><td>" + maTaiKhoanSuDung + "</td><td>" + maTaiKhoanCap1 + "</td><td>" + maTaiKhoanCap2 + "</td><td>" + maTaiKhoanCap3 + "</td><td>" + tenTaiKhoanCap3 + "</td><td>" + soDuDauKy + "</td></tr>");  
	    }
        out.println("</table>");
        out.println("<a href=\"quanly.html\" class=\"back-button\">Quay lại</a>");
        out.println("</body></html>");
    }
}
