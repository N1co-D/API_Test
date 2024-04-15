package petstore.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import petstore.data.pet.Pet;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetApi extends Endpoints {
    private static final RequestSpecification requestSpecification = Specification.requestSpecification();
    private static final ResponseSpecification responseSpecification = Specification.responseSpecification();

    private boolean checkIfPetExistById(int petId) {
        Response response = given()
                .spec(requestSpecification)
                .pathParam("petId", petId)
                .param("petId", petId)
                .when()
                .get(PET_ID);
        response
                .then()
                .log()
                .status();
        return response.getStatusCode() == 200;
    }

    @Step("Отправка запроса на получение питомца по id = {id}")
    public PetApi findPetById(int petId) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("petId", petId)
                    .param("petId", petId)
                    .when()
                    .get(PET_ID)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти питомца по заданному id = " + petId);
        }
        return this;
    }

    @Step("Отправка запроса на удаление питомца по id = {petId}")
    public PetApi deletePetById(int petId) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("petId", petId)
                    .param("petId", petId)
                    .when()
                    .delete(PET_ID)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось удалить питомца по заданному id = " + petId);
        }
        return this;
    }

    @Step("Отправка запроса на получение всех питомцев по статусу = {status}")
    public PetApi findPetByStatus(String status) {
        try {
            given()
                    .spec(requestSpecification)
                    .param("status", status)
                    .when()
                    .get(PET_STATUS)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти питомца по заданному статусу = " + status);
        }
        return this;
    }

    @Step("Отправка запроса на добавление нового питомца")
    public PetApi addNewPet(int id, String petJson) {
        if (!checkIfPetExistById(id)) {
            try {
                given()
                        .spec(requestSpecification)
                        .contentType(JSON)
                        .body(petJson)
                        .when()
                        .post(NEW_PET)
                        .then()
                        .spec(responseSpecification);
            } catch (AssertionError assertionError) {
                throw new RuntimeException("Не удалось добавить питомца");
            }
        } else {
            throw new RuntimeException("Питомец с id = " + id + " уже существует");
        }
        return this;
    }

    @Step("Отправка запроса на изменение имени на {name} и статуса питомца на {status} через id = {petId}")
    public PetApi partialUpdatePet(int petId, String name, String status) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("petId", petId)
                    .param("petId", petId)
                    .param("name", name)
                    .param("status", status)
                    .when()
                    .post(PET_ID)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось обновить имя и статус питомца");
        }
        return this;
    }

    @Step("Отправка запроса на полное изменение данных о питомце")
    public PetApi fullUpdatePet(String petJson) {
        try {
            given()
                    .spec(requestSpecification)
                    .contentType(JSON)
                    .body(petJson)
                    .when()
                    .put(NEW_PET)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось обновить данные о питомце");
        }
        return this;
    }

    @Step("Проверка соответствия полей name и status с ожидаемыми результатами = {expectedName} и {expectedStatus} соответственно")
    public PetApi checkPetParameters(int petId, String expectedName, String expectedStatus) {
        try {
            Pet response = given()
                    .spec(requestSpecification)
                    .pathParam("petId", petId)
                    .param("petId", petId)
                    .when()
                    .get(PET_ID)
                    .then()
                    .spec(responseSpecification)
                    .extract().body().as(Pet.class);
            Assertions.assertAll(
                    () -> assertEquals(expectedName, response.getName(),
                            "Фактическое имя питомца = " + response.getName() + " не соответствует ожидаемому = " + expectedName),
                    () -> assertEquals(expectedStatus, response.getStatus(),
                            "Фактический статус питомца = " + response.getStatus() + " не соответствует ожидаемому = " + expectedStatus)
            );
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти питомца по заданному id = " + petId);
        }
        return this;
    }

    @Step("Проверка отсутствия данных о питомце с id = {petId} по запросу")
    public PetApi checkNoDataAboutPet(int petId) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("petId", petId)
                    .param("petId", petId)
                    .when()
                    .get(PET_ID)
                    .then()
                    .log().status()
                    .statusCode(404);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Питомец с заданным id = " + petId + " не был удален");
        }
        return this;
    }
}