package se.redmind.web;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;

/**
 * Created by victormattsson on 2015-10-22.
 */
public class SparkServer {

    public static void start() {

        port(9090);
        externalStaticFileLocation(System.getProperty("user.dir") + "/web");

        get("/RMDocs", (request, response) -> "Hello World");

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:9090/"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }

    }
}
