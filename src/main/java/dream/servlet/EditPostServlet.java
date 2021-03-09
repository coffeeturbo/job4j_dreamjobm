package dream.servlet;


import dream.model.Post;
import dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post rslPost = new Post(0, "");
        if (req.getParameter("id") != null) {
            var id = Integer.parseInt(req.getParameter("id"));
            rslPost = PsqlStore.instOf().findPostById(id);
        }
        req.setAttribute("post", rslPost);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }
}
