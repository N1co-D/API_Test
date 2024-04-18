package petstore.service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import petstore.data.pet.Category;
import petstore.data.pet.Pet;
import petstore.data.pet.Tag;
import petstore.specs.Specification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class PetService {
    private static final RequestSpecification REQUEST_SPECIFICATION = Specification.requestSpecification();
    private static final ResponseSpecification RESPONSE_SPECIFICATION = Specification.responseSpecification();

    private boolean checkIfPetExistById(long petId) {
        log.info(String.format("Проверка наличия данных о питомце с id = %s", petId));
        Response response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("petId", petId)
                .when()
                .get(Constants.PET_BY_ID);
        response
                .then()
                .log()
                .status();
        return response.getStatusCode() == 200;
    }

    @Step("Отправка запроса на получение питомца по id = {id}")
    public PetService findPetById(long petId) {
        log.info(String.format("Отправка запроса на получение данных о питомце по id = %s", petId));
        given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("petId", petId)
                .when()
                .get(Constants.PET_BY_ID)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info(String.format("Получение данных о питомце по id = %s прошло успешно", petId));
        return this;
    }

    @Step("Отправка запроса на удаление питомца по id = {petId}")
    public PetService deletePetById(long petId) {
        log.info(String.format("Отправка запроса на удаление данных о питомце по id = %s", petId));
        given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("petId", petId)
                .when()
                .delete(Constants.PET_BY_ID)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info(String.format("Удаление данных о питомце по id = %s прошло успешно", petId));
        return this;
    }

    @Step("Отправка запроса на получение всех питомцев по статусу = {status}")
    public PetService findPetByStatus(String status) {
        log.info(String.format("Отправка запроса на поиск питомца по статусу = %s", status));
        given()
                .spec(REQUEST_SPECIFICATION)
                .param("status", status)
                .when()
                .get(Constants.PET_STATUS)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info(String.format("Получение данных о питомце по статусу = %s прошло успешно", status));
        return this;
    }

    @Step("Отправка запроса на добавление нового питомца")
    public PetService addNewPet(long id, String petJson) {
        log.info("Отправка запроса на добавление нового питомца");
        if (!checkIfPetExistById(id)) {
            log.info(String.format("В текущий момент питомец с id = %s в базе отсутствует", id));
            given()
                    .spec(REQUEST_SPECIFICATION)
                    .contentType(JSON)
                    .body(petJson)
                    .when()
                    .post(Constants.NEW_PET)
                    .then()
                    .spec(RESPONSE_SPECIFICATION);
        }
        log.info("Добавление питомца прошло успешно");
        return this;
    }

    @Step("Отправка запроса на изменение имени на {name} и статуса питомца на {status} через id = {petId}")
    public PetService partialUpdatePet(long petId, String name, String status) {
        log.info(String.format("Отправка запроса на изменение имени на %s и статуса питомца на %s через id = %s",
                name, status, petId));
        given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("petId", petId)
                .param("name", name)
                .param("status", status)
                .when()
                .post(Constants.PET_BY_ID)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info("Изменение данных питомца прошла успешно");
        return this;
    }

    @Step("Отправка запроса на полное изменение данных о питомце")
    public PetService fullUpdatePet(String petJson) {
        log.info("Отправка запроса на полное изменение данных о питомце");
        given()
                .spec(REQUEST_SPECIFICATION)
                .contentType(JSON)
                .body(petJson)
                .when()
                .put(Constants.NEW_PET)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info("Изменение данных питомца прошла успешно");
        return this;
    }

    @Step("Проверка соответствия полей name и status с ожидаемыми результатами = {expectedName} и {expectedStatus} соответственно")
    public PetService checkPetsNameAndStatus(long petId, String expectedName, String expectedStatus) {
        log.info("Проверка измененных имени и статуса питомца");
        Pet response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("petId", petId)
                .when()
                .get(Constants.PET_BY_ID)
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .extract().body().as(Pet.class);
        Assertions.assertAll(
                () -> assertEquals(expectedName, response.getName(),
                        "Фактическое имя питомца = " + response.getName() + " не соответствует ожидаемому = " + expectedName),
                () -> assertEquals(expectedStatus, response.getStatus(),
                        "Фактический статус питомца = " + response.getStatus() + " не соответствует ожидаемому = " + expectedStatus));
        log.info("Проверка измененных имени и статуса питомца прошла успешно");
        return this;
    }

    @Step("Проверка соответствия полей name и status с ожидаемыми результатами = {expectedName} и {expectedStatus} соответственно")
    public PetService checkPetParameters(long petId, long expectedId, Category expectedCategory, String expectedName,
                                         List<String> expectedPhotoUrls, List<Tag> expectedTags, String expectedStatus) {
        log.info("Проверка соответствия фактических измененных данных питомца с ожидаемыми");
        Pet response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("petId", petId)
                .when()
                .get(Constants.PET_BY_ID)
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .extract().body().as(Pet.class);
        Assertions.assertAll(
                () -> assertEquals(expectedId, response.getId(),
                        "Фактический идентификатор питомца = " + response.getId()
                                + " не соответствует ожидаемому = " + expectedId),
                () -> assertEquals(expectedCategory, response.getCategory(),
                        "Фактические категории питомца = " + response.getCategory() +
                                " не соответствует ожидаемым = " + expectedCategory),
                () -> assertEquals(expectedName, response.getName(),
                        "Фактическое имя питомца = " + response.getName() +
                                " не соответствует ожидаемому = " + expectedName),
                () -> assertEquals(expectedPhotoUrls, response.getPhotoUrls(),
                        "Фактические url фотографий питомца = " + response.getPhotoUrls() +
                                " не соответствуют ожидаемым = " + expectedPhotoUrls),
                () -> assertEquals(expectedTags, response.getTags(),
                        "Фактические теги питомца = " + response.getTags() +
                                " не соответствуют ожидаемым = " + expectedTags),
                () -> assertEquals(expectedStatus, response.getStatus(),
                        "Фактический статус питомца = " + response.getStatus() +
                                " не соответствует ожидаемому = " + expectedStatus));
        log.info("Проверка измененных данных питомца прошла успешно");
        return this;
    }

    @Step("Проверка отсутствия данных о питомце с id = {petId} по запросу")
    public boolean checkNoDataAboutPet(long petId) {
        log.info(String.format("Проверка отсутствия питомца по id = %s", petId));
        Response response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("petId", petId)
                .when()
                .get(Constants.PET_BY_ID);
        response
                .then()
                .log()
                .status();
        return response.getStatusCode() == 404;
    }
}