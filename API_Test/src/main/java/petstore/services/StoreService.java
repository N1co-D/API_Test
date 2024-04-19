package petstore.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import petstore.data.order.Order;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class StoreService {
    private static final RequestSpecification REQUEST_SPECIFICATION = Specification.requestSpecification();
    private static final ResponseSpecification RESPONSE_SPECIFICATION = Specification.responseSpecification();

    private boolean checkIfOrderExistById(long orderId) {
        log.info(String.format("Проверка наличия о заказе с id = %s", orderId));

        Response response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("orderId", orderId)
                .when()
                .get(Constants.ORDER_ID);

        response
                .then()
                .log()
                .status();

        return response.getStatusCode() == 200;
    }

    @Step("Отправка запроса на получение данных о количестве питомцев в инвентаре по статусам")
    public StoreService getInventoryWithStatusFilter() {
        log.info("Отправка запроса на получение данных о количестве питомцев в инвентаре по статусам");

        given()
                .spec(REQUEST_SPECIFICATION)
                .when()
                .get(Constants.STORE_INVENTORY)
                .then()
                .spec(RESPONSE_SPECIFICATION);

        log.info("Получение данных о количестве питомцев в инвентаре по статусам прошло успешно");
        return this;
    }

    @Step("Отправка запроса на получение заказа по id = {orderId}")
    public StoreService findOrderById(long orderId) {
        log.info(String.format("Отправка запроса на получение данных о заказе по id = %s", orderId));

        given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("orderId", orderId)
                .when()
                .get(Constants.ORDER_ID)
                .then()
                .spec(RESPONSE_SPECIFICATION);

        log.info(String.format("Получение данных о заказе по id = %s прошло успешно", orderId));
        return this;
    }

    @Step("Отправка запроса на удаление заказа по id = {orderId}")
    public StoreService deleteOrderById(long orderId) {
        log.info(String.format("Отправка запроса на удаление данных о заказе по id = %s", orderId));

        given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("orderId", orderId)
                .when()
                .delete(Constants.ORDER_ID)
                .then()
                .spec(RESPONSE_SPECIFICATION);

        log.info(String.format("Удаление данных о заказе по id = %s прошло успешно", orderId));
        return this;
    }

    @Step("Отправка запроса на добавление нового заказа")
    public StoreService placeNewOrder(long orderId, String petJson) {
        log.info("Отправка запроса на добавление нового заказа");

        if (!checkIfOrderExistById(orderId)) {
            log.info(String.format("В текущий момент заказ с id = %s в базе отсутствует", orderId));
            given()
                    .spec(REQUEST_SPECIFICATION)
                    .contentType(JSON)
                    .body(petJson)
                    .when()
                    .post(Constants.NEW_ORDER)
                    .then()
                    .spec(RESPONSE_SPECIFICATION);
        }

        log.info("Добавление заказа прошло успешно");
        return this;
    }

    @Step("Проверка соответствия полей с ожидаемыми результатами")
    public StoreService checkOrderParameters(long orderId, long expectedId, long expectedPetId,
                                             Integer expectedQuantity, String expectedShipDate,
                                             String expectedStatus, boolean orderIsComplete) {
        log.info("Отправка запроса на получение фактических данных заказа");

        Order response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("orderId", orderId)
                .when()
                .get(Constants.ORDER_ID)
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .extract().body().as(Order.class);

        log.info("Проверка соответствия фактических измененных данных заказа с ожидаемыми");

        Assertions.assertAll(
                () -> assertEquals(expectedId, response.getId(),
                        "Фактический идентификатор заказа = " + response.getId() +
                                " не соответствует ожидаемому = " + expectedId),
                () -> assertEquals(expectedPetId, response.getPetId(),
                        "Фактический petId заказа = " + response.getPetId() +
                                " не соответствует ожидаемому = " + expectedPetId),
                () -> assertEquals(expectedQuantity, response.getQuantity(),
                        "Фактический объем заказа = " + response.getQuantity() +
                                " не соответствует ожидаемому = " + expectedQuantity),
                () -> assertEquals(expectedShipDate, response.getShipDate(),
                        "Фактическая дата отправки = " + response.getShipDate() +
                                " не соответствует ожидаемой = " + expectedShipDate),
                () -> assertEquals(expectedStatus, response.getStatus(),
                        "Фактический статус заказа = " + response.getStatus() +
                                " не соответствует ожидаемому = " + expectedStatus),
                () -> assertEquals(orderIsComplete, response.isComplete(),
                        "Результат выполнения заказа = " + response.isComplete() +
                                " не соответствует ожидаемому = " + orderIsComplete));

        log.info("Проверка измененных данных заказа прошла успешно");
        return this;
    }

    @Step("Проверка отсутствия данных о заказе с id = {orderId} по запросу")
    public boolean checkNoDataAboutOrder(long orderId) {
        log.info(String.format("Проверка отсутствия заказа по id = %s", orderId));

        Response response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("orderId", orderId)
                .when()
                .get(Constants.ORDER_ID);

        response
                .then()
                .log()
                .status();

        return response.getStatusCode() == 404;
    }
}