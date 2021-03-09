package dream.servlet;

import dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCandidateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var canID = Integer.parseInt(req.getParameter("id"));
        PsqlStore.instOf().deleteCandidateByID(canID);

        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
