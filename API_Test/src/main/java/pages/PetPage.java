package pages;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetPage extends BasePage {
    public static final String PET_ID = "/pet/{id}";
    public static final String PET_STATUS = "/pet/findByStatus";

    public PetPage checkFindPetById(int id) {
        Response response = given()
                .baseUri(BASE_URL)
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

    public PetPage checkDeletePetById(int id) {
        Response response = given()
                .baseUri(BASE_URL)
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

    public PetPage checkFindPetByStatus(String status) {
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
}