import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/timkiem")
public class timkiem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        response.setContentType("text/html; charset=UTF-8");

        try (PrintWriter out = response.getWriter();
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlytaikhoan", "root", "thog05122003");
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM taikhoancap1 WHERE MaTaiKhoanCap1 LIKE ? OR TenTaiKhoan LIKE ?")) {

            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            ResultSet rs = stmt.executeQuery();

            out.println("<table border='1'>");
            out.println("<tr><th>Mã Tài Khoản</th><th>Tên Tài Khoản</th><th>Loại Tài Sản Nợ Có</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("MaTaiKhoanCap1") + "</td>");
                out.println("<td>" + rs.getString("TenTaiKhoan") + "</td>");
                out.println("<td>" + rs.getString("LoaiTaiSanNoCo") + "</td></tr>");
            }
            out.println("</table>");

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
