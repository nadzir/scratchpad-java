package scraper;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

//#main-class
public class Main extends AllDirectives {

    // set up ActorSystem and other dependencies here
    private final ScraperRoutes scraperRoutes;

    public Main(ActorSystem system) {
        scraperRoutes = new ScraperRoutes(system);
    }
    //#main-class

    public static void main(String[] args) throws Exception {
        //#server-bootstrapping
        // boot up server using the route as defined below
        ActorSystem system = ActorSystem.create("helloAkkaHttpServer");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        //#server-bootstrapping

        //#http-server
        //In order to access all directives we need an instance where the routes are define.
        Main app = new Main(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        http.bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), materializer);

        System.out.println("Server online at http://localhost:8080/");
        //#http-server
    }

    //#main-class
    /**
     * Here you can define all the different routes you want to have served by this web server
     * Note that routes might be defined in separated classes like the current case
     */
    protected Route createRoute() {
        return scraperRoutes.routes();
    }
}
//#main-class


