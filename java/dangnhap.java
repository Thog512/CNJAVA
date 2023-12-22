import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dangnhap")
public class dangnhap extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Giả sử tên đăng nhập là "admin" và mật khẩu là "password123"
        if("thog".equals(username) && "05122003".equals(password)) {
            response.sendRedirect("Index.html"); // Đăng nhập đúng, chuyển hướng đến Index.html
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<p>Tên đăng nhập hoặc mật khẩu không đúng!</p>");
            out.println("<a href='dangnhap.html'>Thử lại</a>");
            out.println("</body></html>");
        }
    }
}
