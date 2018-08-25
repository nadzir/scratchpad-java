package scraper;

import akka.actor.Props;
import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ScraperActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    static Props props() {
      return Props.create(ScraperActor.class);
    }

    @Override
    public Receive createReceive() {
      return receiveBuilder()
        .match(String.class, s -> {
          log.info("Received String message: {}", s);
          //#my-actor
          //#reply
          // getSender().tell(s, getSelf());
          //#reply
          //#my-actor
        })
        .matchAny(o -> log.info("received unknown message"))
        .build();
    }
  }