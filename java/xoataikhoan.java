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

@WebServlet("/xoataikhoan")
public class xoataikhoan extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public xoataikhoan() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String maTaiKhoanCap1 = request.getParameter("MaTaiKhoanCap1");

        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlytaikhoan", "root", "thog05122003")) {
                String sql = "DELETE FROM taikhoancap1 WHERE MaTaiKhoanCap1 = ?";
                try (PreparedStatement statement = con.prepareStatement(sql)) {
                    statement.setString(1, maTaiKhoanCap1);
                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        out.println("<p>Đã xóa thành công tài khoản có mã: " + maTaiKhoanCap1 + "</p>");
                    } else {
                        out.println("<p>Tài khoản không tồn tại hoặc đã được xóa trước đó.</p>");
                    }
                }
                response.sendRedirect("quanly.html");
            }
            // Redirect or display a message
        } catch (Exception e) {
            e.printStackTrace(response.getWriter()); // For debugging, print the stack trace to the response.
        }
    }
}
