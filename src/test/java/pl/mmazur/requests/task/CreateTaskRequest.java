package pl.mmazur.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.mmazur.dto.CreateTaskRequestDto;
import pl.mmazur.properites.ClickUpProperites;
import pl.mmazur.requests.BaseRequest;
import pl.mmazur.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {
    public static Response createTask(JSONObject task, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(task.toString())
                .when()
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .log().ifError()
                .extract()
                .response();

    }

    public static Response createTask(CreateTaskRequestDto taskDto, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(taskDto) //dla DTO nie trzeba robić toString
                .when()
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .log().ifError()
                .extract()
                .response();

    }
}
