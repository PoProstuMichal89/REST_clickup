package pl.mmazur.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.mmazur.dto.request.CreateTaskRequestDto;
import pl.mmazur.dto.response.CreateTaskResponseDto;
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

    public static CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto, String listId) { //druga metoda, która przyjmuje DTO
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(taskDto) //dla DTO nie trzeba robić toString
                .when()
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .statusCode(200)
                .log().ifError()
                .extract()
                .response()
                .as(CreateTaskResponseDto.class); //as - konwersja na obiekt (DTO) --> to jest deserializacja (nie chcemy całego responsa tylko obket javowy, który zawiera tylko to co nas interesuje<określone pola z jsona>)

    }
}
