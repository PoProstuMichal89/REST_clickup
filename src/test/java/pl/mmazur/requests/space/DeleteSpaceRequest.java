package pl.mmazur.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.mmazur.properites.ClickUpProperites;
import pl.mmazur.requests.BaseRequest;
import pl.mmazur.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class DeleteSpaceRequest {
    public static Response deleteSpace(String spaceId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .when()
                .delete(ClickUpUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();

    }
}
