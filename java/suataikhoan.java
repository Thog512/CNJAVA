import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/suataikhoan")
public class suataikhoan extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String maTaiKhoanCap1 = request.getParameter("MaTaiKhoanCap1");
        String tenTaiKhoan = request.getParameter("TenTaiKhoan");
        String loaiTaiSanNoCo = request.getParameter("LoaiTaiSanNoCo");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlytaikhoan", "root", "thog05122003");
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE taikhoancap1 SET TenTaiKhoan = ?, LoaiTaiSanNoCo = ? WHERE MaTaiKhoanCap1 = ?")) {

                statement.setString(1, tenTaiKhoan);
                statement.setString(2, loaiTaiSanNoCo);
                statement.setString(3, maTaiKhoanCap1);
                
                int rowsUpdated = statement.executeUpdate();
                
                if (rowsUpdated > 0) {
                    response.sendRedirect("taikhoancap1"); // Cập nhật thành công, chuyển hướng đến trang danh sách
                } else {
                    // Không tìm thấy bản ghi để cập nhật
                    response.getWriter().println("<script type='text/javascript'>");
                    response.getWriter().println("alert('Không tìm thấy tài khoản với mã cung cấp hoặc không có thông tin mới để cập nhật.');");
                    response.getWriter().println("location='suataikhoan.html';");
                    response.getWriter().println("</script>");
                }
            }
        } catch (Exception e) {
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('Lỗi khi cập nhật tài khoản: " + e.getMessage() + "');");
            response.getWriter().println("location='suataikhoan.html';");
            response.getWriter().println("</script>");
        }
    }
}
