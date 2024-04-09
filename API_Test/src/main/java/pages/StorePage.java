package pages;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StorePage extends BasePage {
    public static final String STORE_INVENTORY = "/store/inventory";
    public static final String ORDER_ID = "/store/order/{id}";

    public StorePage checkReturnPetsByStatus() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get(STORE_INVENTORY);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось открыть инвентарь с питомцами. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    public StorePage checkFindOrderById(int id) {
        Response response = given()
                .baseUri(BASE_URL)
                .pathParam("id", id)
                .when()
                .get(ORDER_ID);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось найти заказ по указанному id. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }
}