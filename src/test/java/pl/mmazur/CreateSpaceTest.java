package pl.mmazur;

import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
//import org.junit.jupiter.api.Assertions; --> jUnit
import org.junit.jupiter.api.Test;
import pl.mmazur.properites.ClickUpProperites;
import pl.mmazur.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

 class CreateSpaceTest {

    private static final String TOKEN = "pk_62571168_SR09PIGVCO2VD4UOJOWFYZ2P50NNW4QY";
    @Test
    void createSpaceTest() {
        JSONObject space = new JSONObject();
        space.put("name", "Test Space");

       final var response  = given()
                .header("Authorization", ClickUpProperites.getToken())
                .contentType(ContentType.JSON)
                .body(space.toString())
                .when()
                .post(ClickUpUrl.getBaseUrl() + "/team/9015332651/space")
                .then()
                .extract()
                .response();

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo("Test Space");

    }
}
