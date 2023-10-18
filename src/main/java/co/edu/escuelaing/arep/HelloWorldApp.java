package co.edu.escuelaing.arep;

import java.net.URL;

import static spark.Spark.*;
import static spark.Spark.get;

/**
 *
 * @author Ricardo Olarte
 */
public class HelloWorldApp {

    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        secure("keystores/ecikeypair.p12", "ecistore", null, null);
        get("/hello", (req, res) -> "Hello World, this it's server1");

        get("/login", (request, response) -> URLReader.secureURL(getAWS(), getKeyStore(), "ecistore"));
    }
    private static int getPort(){
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

    /**
     * Get url aws
     * @return
     */
    private static String getAWS(){
        if(System.getenv("URL")!=null){
            return System.getenv("URL");
        }
        return "ec2-34-202-160-252.compute-1.amazonaws.com";
    }

    /**
     * Get keyStore
     * @return
     */
    static String getKeyStore(){
        if(System.getenv("keyStore")!=null){
            return System.getenv("keyStore");
        }
        return "keystores/myTrustStore";
    }

    /**
     * Get password
     * @return
     */
    private static String getKeyPWD(){
        if(System.getenv("keyPWD")!=null){
            return System.getenv("keyPWD");
        }
        return "ecistore";
    }
}
