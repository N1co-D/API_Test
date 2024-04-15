package petstore.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import petstore.data.order.Order;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreApi extends Endpoints {
    private static final RequestSpecification requestSpecification = Specification.requestSpecification();
    private static final ResponseSpecification responseSpecification = Specification.responseSpecification();

    private boolean checkIfOrderExistById(int orderId) {
        Response response = given()
                .spec(requestSpecification)
                .pathParam("orderId", orderId)
                .param("orderId", orderId)
                .when()
                .get(ORDER_ID);
        response
                .then()
                .log()
                .status();
        return response.getStatusCode() == 200;
    }

    @Step("Отправка запроса на получение данных о количестве питомцев в инвентаре по статусам")
    public StoreApi getInventoryWithStatusFilter() {
        try {
            given()
                    .spec(requestSpecification)
                    .when()
                    .get(STORE_INVENTORY)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось открыть отфильтрованный по статусам инвентарь с питомцами");
        }
        return this;
    }

    @Step("Отправка запроса на получение заказа по id = {orderId}")
    public StoreApi findOrderById(int orderId) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("orderId", orderId)
                    .param("orderId", orderId)
                    .when()
                    .get(ORDER_ID)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти заказ по заданному id = " + orderId);
        }
        return this;
    }

    @Step("Отправка запроса на удаление заказа по id = {orderId}")
    public StoreApi deleteOrderById(int orderId) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("orderId", orderId)
                    .param("orderId", orderId)
                    .when()
                    .delete(ORDER_ID)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось удалить заказ по заданному id = " + orderId);
        }
        return this;
    }

    @Step("Отправка запроса на добавление нового заказа")
    public StoreApi placeNewOrder(int id, String petJson) {
        if (!checkIfOrderExistById(id)) {
            try {
                given()
                        .spec(requestSpecification)
                        .contentType(JSON)
                        .body(petJson)
                        .when()
                        .post(NEW_ORDER)
                        .then()
                        .spec(responseSpecification);
            } catch (AssertionError assertionError) {
                throw new RuntimeException("Не удалось разместить заказ");
            }
        } else {
            throw new RuntimeException("Заказ с id = " + id + " уже существует");
        }
        return this;
    }

    @Step("Проверка соответствия полей petId и status с ожидаемыми результатами = {expectedPetId} и {expectedStatus} соответственно")
    public StoreApi checkOrderParameters(int orderId, int expectedPetId, String expectedStatus) {
        try {
            Order response = given()
                    .spec(requestSpecification)
                    .pathParam("orderId", orderId)
                    .param("orderId", orderId)
                    .when()
                    .get(ORDER_ID)
                    .then()
                    .spec(responseSpecification)
                    .extract().body().as(Order.class);
            Assertions.assertAll(
                    () -> assertEquals(expectedPetId, response.getPetId(),
                            "Фактический petId заказа = " + response.getPetId() +
                                    " не соответствует ожидаемому = " + expectedPetId),
                    () -> assertEquals(expectedStatus, response.getStatus(),
                            "Фактический статус заказа = " + response.getStatus() +
                                    " не соответствует ожидаемому = " + expectedStatus)
            );
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти заказ по заданному id = " + orderId);
        }
        return this;
    }

    @Step("Проверка отсутствия данных о заказе с id = {orderId} по запросу")
    public StoreApi checkNoDataAboutOrder(int orderId) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("orderId", orderId)
                    .param("orderId", orderId)
                    .when()
                    .get(ORDER_ID)
                    .then()
                    .log().status()
                    .statusCode(404);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Заказ с заданным id = " + orderId + " не был удален");
        }
        return this;
    }
}