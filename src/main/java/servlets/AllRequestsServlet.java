package servlets;

import template.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AllRequestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> createPage = createPage(req);
        createPage.put("message", "");

        resp.getWriter().println(PageGenerator.instance().getPage("page.html", createPage));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> createPage = createPage(req);
        String message = req.getParameter("message");


        if (message == null || message.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        createPage.put("message", message == null ? "" : message);

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(PageGenerator.instance().getPage("page.html", createPage));


    }

    public static Map<String, Object> createPage(HttpServletRequest request) {
        Map<String, Object> pageSend = new HashMap<>();
        pageSend.put("method", request.getMethod());
        pageSend.put("URL", request.getRequestURL().toString());
        pageSend.put("pathInfo", request.getPathInfo());
        pageSend.put("sessionId", request.getSession().getId());
        pageSend.put("parameters", request.getParameterMap().toString());
        return pageSend;
    }
}
