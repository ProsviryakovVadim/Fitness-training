package server;

import accounts.AccountService;
import accounts.ProfileFitnessUser;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.FitManServlet;
import servlets.SessionServlet;


public class ServerStart {

    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

        accountService.addNewUser(new ProfileFitnessUser("admin"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new FitManServlet(accountService)),  "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionServlet(accountService)), "/api/v1/sessions");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("fitnes_html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8085);
        server.setHandler(handlerList);

        server.start();
        // Вывод в консоль сообщение
        java.util.logging.Logger.getGlobal().info("Server started");

        server.join();

    }
}