package pl.mmazur.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mmazur.dto.request.CreateTaskRequestDto;
import pl.mmazur.requests.list.CreateListRequest;
import pl.mmazur.requests.space.CreateSpaceRequest;
import pl.mmazur.requests.task.CreateTaskRequest;

public class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static String spaceName = "SPACE E2E";
    private String spaceId;
    private String listId;
    private String taskId;
    private String listName = "Zadania";
    private String taskName = "Przetestować clickup";

    @Test
    void updateTaskE2ETest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space id: {} ", spaceId);

        listId = createListStep();
        LOGGER.info("List id: {} ", listId);

        taskId = createTaskStep();
        LOGGER.info("Task id: {} ", taskId);
    }

    private String createSpaceStep() {
        JSONObject json = new JSONObject();
        json.put("name", spaceName);

        final var response = CreateSpaceRequest.createSpace(json);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(spaceName);
        //Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(spaceName); --> krótsza wersja z rzutowaniem responsa na jsonPath

        return jsonData.getString("id");
    }

    private String createListStep() {
        JSONObject json = new JSONObject();
        json.put("name", listName);

        final var response = CreateListRequest.createList(json, spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(listName);

        return jsonData.getString("id");
    }

    private String createTaskStep() {
//        JSONObject task = new JSONObject(); --> STARA WERSJA Z JSONEM
//        task.put("name", taskName);
//        task.put("description", "Jak to działa?");
//        task.put("status", "to do");
//        task.put("priority", JSONObject.NULL);
//        task.put("parent", JSONObject.NULL);
//        task.put("time_estimate", JSONObject.NULL);
//        task.put("assignees", JSONObject.NULL);
//        task.put("archived", false);

        CreateTaskRequestDto taskDto = new CreateTaskRequestDto(); // --> NOWA WERSJA Z DTO (Data Transfer Object)
        taskDto.setName(taskName);
        taskDto.setDescription("Jak to działa?");
        taskDto.setStatus("to do");

        final var response = CreateTaskRequest.createTask(taskDto, listId); //przekazanie dto do requesta i zamiana dto na jsona w requestcie to serializacja
//        LOGGER.info("Create task Response: {}", response);

        Assertions.assertThat(response.getName()).isEqualTo(taskName);
        Assertions.assertThat(response.getDescription()).isEqualTo("Jak to działa?");

        return response.getId();

    }

}
