package petstore.functionality;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class StoreFunctionality extends Endpoints {
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
    public StoreFunctionality getInventoryWithStatusFilter() {
        try {
            given()
                    .spec(requestSpecification)
                    .when()
                    .get(STORE_INVENTORY)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось открыть отфильтрованный по статусам инвентарь с питомцами");
        }
    }

    @Step("Отправка запроса на получение заказа по id = {orderId}")
    public StoreFunctionality findOrderById(int orderId) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("orderId", orderId)
                    .param("orderId", orderId)
                    .when()
                    .get(ORDER_ID)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти заказ по заданному id = " + orderId);
        }
    }

    @Step("Отправка запроса на удаление заказа по id = {orderId}")
    public StoreFunctionality deleteOrderById(int orderId) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("orderId", orderId)
                    .param("orderId", orderId)
                    .when()
                    .delete(ORDER_ID)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось удалить заказ по заданному id = " + orderId);
        }
    }

    @Step("Отправка запроса на добавление нового заказа")
    public StoreFunctionality placeNewOrder(int id, String petJson) {
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
                return this;
            } catch (AssertionError assertionError) {
                throw new RuntimeException("Не удалось разместить заказ");
            }
        } else {
            throw new RuntimeException("Заказ с id = " + id + " уже существует");
        }
    }

    @Step("Проверка отсутствия данных о заказе с id = {orderId} по запросу")
    public StoreFunctionality checkNoDataAboutOrder(int orderId) {
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
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Заказ с заданным id = " + orderId + " не был удален");
        }
    }
}