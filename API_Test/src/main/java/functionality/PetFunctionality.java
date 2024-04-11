package functionality;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class PetFunctionality extends BaseFunctionality {
    private static final String PET_ID = "/pet/{petId}";
    private static final String PET_STATUS = "/pet/findByStatus";
    private static final String NEW_PET = "/pet";

    @Step("Отправка запроса на получение питомца по id = {id}")
    public PetFunctionality findPetById(int id) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("petId", id)
                .pathParam("petId", id)
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

    @Step("Отправка запроса на удаление питомца по id = {id}")
    public PetFunctionality deletePetById(int id) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("petId", id)
                .pathParam("petId", id)
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

    @Step("Отправка запроса на получение всех питомцев по статусу = {status}")
    public PetFunctionality findPetByStatus(String status) {
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

    @Step("Отправка запроса на добавление нового питомца")
    public PetFunctionality addNewPet(String jsonFilePath) throws IOException {
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

    @Step("Отправка запроса на изменение имени на {name} и статуса питомца на {status} через id = {id}")
    public PetFunctionality partialUpdatePet(int id, String name, String status) {
        Response response = given()
                .baseUri(BASE_URL)
                .pathParam("petId", id)
                .queryParam("petId", id)
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

    @Step("Отправка запроса на полное изменение данных о питомце")
    public PetFunctionality fullUpdatePet(String jsonFilePath) throws IOException {
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