package dream.servlet;

import dream.model.City;
import dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class CityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        Collection<City>  cities = PsqlStore.instOf().findAllCities();
        writer.println(buildJsonCities(cities));
        writer.flush();
    }

    private String buildJsonCities(Collection<City> cities) {
        StringBuilder json = new StringBuilder();
        json.append("[");

        var i = 1;
        for (City city : cities) {
            json.append(city.toString());
            if (i < cities.size()) {
                json.append(",");
            }
            i++;
        }

        json.append("]");
        return json.toString();
    }
}
