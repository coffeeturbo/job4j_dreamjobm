package dream.servlet;

import dream.model.User;
import dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("password"));

        PsqlStore.instOf().save(user);
        HttpSession ses = req.getSession();
        ses.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}
