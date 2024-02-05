package pl.mmazur.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.mmazur.properites.ClickUpProperites;
import pl.mmazur.requests.BaseRequest;
import pl.mmazur.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateSpaceRequest {
    public static Response createSpace(JSONObject space) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(space.toString())
                .when()
                .post(ClickUpUrl.getSpacesUrl(ClickUpProperites.getTeamId()))
                .then()
                .log().ifError()
                .extract()
                .response();

    }
}
