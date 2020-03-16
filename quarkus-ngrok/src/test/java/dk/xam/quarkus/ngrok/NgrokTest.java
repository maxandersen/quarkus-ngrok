package dk.xam.quarkus.ngrok;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class NgrokTest {


    @Test
    public void testStartup() throws IOException, InterruptedException {
        Ngrok ng = new Ngrok();

        ng.start(null);

        Thread.sleep(5000);
        ng.stop(null);
    }
}