package dream.servlet;

import dream.model.Candidate;
import dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCandidateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Candidate candidate = new Candidate(0, "", 0);
        if (req.getParameter("id") != null) {
            var id = Integer.parseInt(req.getParameter("id"));
            candidate = PsqlStore.instOf().findCandidateById(id);
        }
        req.setAttribute("candidate", candidate);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }
}
