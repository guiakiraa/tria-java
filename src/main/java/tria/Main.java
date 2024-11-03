package tria;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jsonb.JsonBindingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import tria.filter.CorsFilter;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in tria package
        final ResourceConfig rc = new ResourceConfig().packages("tria");
        rc.register(JsonBindingFeature.class);
        rc.register(CorsFilter.class);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            logger.info("SISTEMA INICIANDO...");
            final HttpServer server = startServer();
            System.out.println(String.format("Jersey app started with endpoints available at "
                    + "%s%nHit Ctrl-C to stop it...", BASE_URI));
            System.in.read();
            server.stop();
            logger.info("SISTEMA ENCERRADO");
        }
        catch (Exception e) {
            logger.error("Erro inesperado: " + e);
        }
    }
}

