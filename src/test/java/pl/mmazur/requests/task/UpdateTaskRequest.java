package pl.mmazur.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.mmazur.dto.request.CreateTaskRequestDto;
import pl.mmazur.dto.request.UpdateTaskRequestDto;
import pl.mmazur.dto.response.CreateTaskResponseDto;
import pl.mmazur.requests.BaseRequest;
import pl.mmazur.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class UpdateTaskRequest {
    public static Response updateTask(JSONObject updateTask, String taskId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(updateTask.toString())
                .when()
                .put(ClickUpUrl.getTaskUrl(taskId))
                .then()
                .statusCode(200)
                .log().ifError()
                .extract()
                .response();
    }
}