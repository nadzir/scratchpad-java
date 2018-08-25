package scraper;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Routes can be defined in separated classes like shown in here
 */
// #user-routes-class
public class ScraperRoutes extends AllDirectives {
    // #user-routes-class
    final private LoggingAdapter log;
    private ActorRef scraperActor;

    public ScraperRoutes(ActorSystem system) {
        log = Logging.getLogger(system, this);
        scraperActor = system.actorOf(ScraperActor.props(), "scraper");
    }

    /**
     * This method creates one route (of possibly many more that will be part of
     * your Web App)
     */
    // #all-routes
    public Route routes() {
        return route(pathPrefix("scrape", () -> route(path("jobstreet", () -> {
            scraperActor.tell("test", ActorRef.noSender());
            return complete("hi");
        }))));
    }


    private void scrape() {
        // Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
        // log(doc.title());
        // Elements newsHeadlines = doc.select("#mp-itn b a");
        // for (Element headline : newsHeadlines) {
        //   log("%s\n\t%s", 
        //     headline.attr("title"), headline.absUrl("href"));
        // }
    }
}
