package dream.servlet;

import dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.setAttribute("posts", PsqlStore.instOf().findAllPosts());
        req.setAttribute("cities", PsqlStore.instOf().findAllCities());

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
