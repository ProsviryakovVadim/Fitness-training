package servlets;


import accounts.AccountService;
import accounts.ProfileFitnessUser;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionServlet extends HttpServlet{

    private final AccountService accountService;

    public SessionServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     *
     * Получение user и загрузка его профиля
     *
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sessionId = req.getSession().getId();
        ProfileFitnessUser profileFitnessUser = accountService.getLoginToProfile(sessionId);

        if (profileFitnessUser == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else {
            Gson gson = new Gson();
            String json = gson.toJson(profileFitnessUser);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }


    /**
     *
     *  Авторизация пользователя
     *
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        if (login == null || pass == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        ProfileFitnessUser profileFitnessUser = accountService.getLoginToProfile(login);
        if (profileFitnessUser == null || !profileFitnessUser.getPassFit().equals(pass)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.addSessionId(req.getSession().getId(), profileFitnessUser);
        Gson gson = new Gson();
        String json = gson.toJson(profileFitnessUser);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().print(json);
        resp.setStatus(HttpServletResponse.SC_OK);

    }

    /**
     *
     *  Выход пользователя
     *
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String session = req.getSession().getId();
        ProfileFitnessUser profileFitnessUser = accountService.getSessionIdToProfile(session);
        if (profileFitnessUser == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else {
            accountService.deleteSession(session);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Пока, железный человек!");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
