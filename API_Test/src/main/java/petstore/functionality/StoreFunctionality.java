package petstore.functionality;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class StoreFunctionality {
    private static final String STORE_INVENTORY = "/store/inventory";
    private static final String ORDER_ID = "/store/order/{orderId}";
    private static final String NEW_ORDER = "/store/order";
    private static final RequestSpecification requestSpecification = Specification.requestSpecification();
    private static final ResponseSpecification responseSpecification = Specification.responseSpecification();

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

    @Step("Отправка запроса на получение заказа по id = {id}")
    public StoreFunctionality findOrderById(int id) {
        try {
            given()
                    .spec(requestSpecification)
                    .queryParam("orderId", id)
                    .pathParam("orderId", id)
                    .when()
                    .get(ORDER_ID)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти заказ по заданному id = " + id);
        }
    }

    @Step("Отправка запроса на удаление заказа по id = {id}")
    public StoreFunctionality deleteOrderById(int id) {
        try {
            given()
                    .spec(requestSpecification)
                    .queryParam("orderId", id)
                    .pathParam("orderId", id)
                    .when()
                    .delete(ORDER_ID)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось удалить заказ по заданному id = " + id);
        }
    }

    @Step("Отправка запроса на добавление нового заказа")
    public StoreFunctionality placeNewOrder(String petJson) {
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
    }
}