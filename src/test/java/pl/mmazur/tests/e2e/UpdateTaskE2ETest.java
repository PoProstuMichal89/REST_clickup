package pl.mmazur.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.mmazur.requests.space.CreateSpaceRequest;

public class UpdateTaskE2ETest {

    private static String spaceName = "SPACE E2E";
    private String spaceId;

    @Test
    void updateTaskE2ETest() {
        spaceId = createSpaceStep();
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

}
