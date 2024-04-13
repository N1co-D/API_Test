package petstore.functionality;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PetFunctionality {
    private static final String PET_ID = "/pet/{petId}";
    private static final String PET_STATUS = "/pet/findByStatus";
    private static final String NEW_PET = "/pet";
    private static final RequestSpecification requestSpecification = Specification.requestSpecification();
    private static final ResponseSpecification responseSpecification = Specification.responseSpecification();

    @Step("Отправка запроса на получение питомца по id = {id}")
    public PetFunctionality findPetById(int id) {
        try {
            given()
                    .spec(requestSpecification)
                    .queryParam("petId", id)
                    .pathParam("petId", id)
                    .when()
                    .get(PET_ID)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти питомца по заданному id = " + id);
        }
    }

    @Step("Отправка запроса на удаление питомца по id = {id}")
    public PetFunctionality deletePetById(int id) {
        try {
            given()
                    .spec(requestSpecification)
                    .queryParam("petId", id)
                    .pathParam("petId", id)
                    .when()
                    .delete(PET_ID)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось удалить питомца по заданному id = " + id);
        }
    }

    @Step("Отправка запроса на получение всех питомцев по статусу = {status}")
    public PetFunctionality findPetByStatus(String status) {
        try {
            given()
                    .spec(requestSpecification)
                    .queryParam("status", status)
                    .when()
                    .get(PET_STATUS)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти питомца по заданному статусу = " + status);
        }
    }

    @Step("Отправка запроса на добавление нового питомца")
    public PetFunctionality addNewPet(String petJson) {
        try {
            given()
                    .spec(requestSpecification)
                    .contentType(JSON)
                    .body(petJson)
                    .when()
                    .post(NEW_PET)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось добавить питомца");
        }
    }

    @Step("Отправка запроса на изменение имени на {name} и статуса питомца на {status} через id = {id}")
    public PetFunctionality partialUpdatePet(int id, String name, String status) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("petId", id)
                    .queryParam("petId", id)
                    .queryParam("name", name)
                    .queryParam("status", status)
                    .when()
                    .post(PET_ID)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось обновить имя и статус питомца");
        }
    }

    @Step("Отправка запроса на полное изменение данных о питомце")
    public PetFunctionality fullUpdatePet(String petJson) {
        try {
            given()
                    .spec(requestSpecification)
                    .contentType(JSON)
                    .body(petJson)
                    .when()
                    .put(NEW_PET)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось обновить данные о питомце");
        }
    }
}