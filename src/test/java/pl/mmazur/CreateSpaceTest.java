package pl.mmazur;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.mmazur.requests.space.CreateSpaceRequest;
import pl.mmazur.requests.space.DeleteSpaceRequest;

class CreateSpaceTest {

    private static final String SPACE_NAME = "Test Space";

    @Test
    void createSpaceTest() {
        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final var response = CreateSpaceRequest.createSpace(space);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(SPACE_NAME);

        final var spaceId = response.jsonPath().getString("id");

        DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

    }
}
