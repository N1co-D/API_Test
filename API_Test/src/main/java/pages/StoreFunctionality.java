package pages;

import io.restassured.response.Response;
import org.openqa.selenium.json.Json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StoreFunctionality extends BasePage {
    private static final String STORE_INVENTORY = "/store/inventory";
    private static final String ORDER_ID = "/store/order/{id}";
    private static final String NEW_ORDER = "/store/order";

    public StoreFunctionality checkReturnPetsByStatus() {
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

    public StoreFunctionality checkFindOrderById(int id) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("orderId", id)
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

    public StoreFunctionality checkDeleteOrderById(int id) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("orderId", id)
                .pathParam("id", id)
                .when()
                .get(ORDER_ID);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось удалить заказ по указанному id. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    public StoreFunctionality checkAddNewOrder(String jsonFilePath) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Json json = new Json();
        Object jsonObject = json.toType(jsonString, Object.class);
        String jsonBody = json.toJson(jsonObject);

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(NEW_ORDER);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось разместить заказ. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }
}