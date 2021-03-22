package dream.servlet;

import dream.model.Candidate;
import dream.model.City;
import dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CandidateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Candidate candidate = new Candidate(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("photoId")),
                Integer.parseInt(req.getParameter("cityId"))
        );
        PsqlStore.instOf().save(candidate);
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<Candidate> candidates = PsqlStore.instOf().findAllCandidates();
        Collection<City> cities = PsqlStore.instOf().findAllCities();

        req.setAttribute("candidates", candidates);
        req.setAttribute("cities", cities);

        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }
}
