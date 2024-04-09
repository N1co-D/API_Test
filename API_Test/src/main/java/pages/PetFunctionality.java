package pages;

import io.restassured.response.Response;
import org.openqa.selenium.json.Json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class PetFunctionality extends BasePage {
    private static final String PET_ID = "/pet/{id}";
    private static final String PET_STATUS = "/pet/findByStatus";
    private static final String NEW_PET = "/pet";

    public PetFunctionality checkFindPetById(int id) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("petId", id)
                .pathParam("id", id)
                .when()
                .get(PET_ID);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось найти питомца по указанному id. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    public PetFunctionality checkDeletePetById(int id) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("petId", id)
                .pathParam("id", id)
                .when()
                .delete(PET_ID);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось найти питомца по указанному id. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    public PetFunctionality checkFindPetByStatus(String status) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("status", status)
                .when()
                .get(PET_STATUS);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось найти питомца по указанному статусу. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    public PetFunctionality checkAddNewPet(String jsonFilePath) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Json json = new Json();
        Object jsonObject = json.toType(jsonString, Object.class);
        String jsonBody = json.toJson(jsonObject);

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(NEW_PET);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось добавить питомца. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    public PetFunctionality checkUpdatePet(int currentId, int updatedId, String name, String status) {
        Response response = given()
                .baseUri(BASE_URL)
                .pathParam("id", currentId)
                .queryParam("petId", updatedId)
                .queryParam("name", name)
                .queryParam("status", status)
                .when()
                .post(PET_ID);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось обновить данные о питомце. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    public PetFunctionality checkUpdatePet(String jsonFilePath) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Json json = new Json();
        Object jsonObject = json.toType(jsonString, Object.class);
        String jsonBody = json.toJson(jsonObject);

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .put(NEW_PET);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось обновить данные о питомце. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }
}