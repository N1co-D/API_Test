package petstore.storeservice;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreServiceTest extends StoreServiceTestConfig {

    @DisplayName("TC-ID8")
    @Description("TC-ID8 Отправка запроса на добавление нового заказа")
    @ParameterizedTest
    @MethodSource("petstore.storeservice.StoreServiceTestData#checkAddNewOrderTestData")
    public void checkAddNewOrder(long orderId, String orderJson, long expectedId, long expectedPetId,
                                 Integer expectedQuantity, String expectedShipDate,
                                 String expectedStatus, boolean orderIsComplete) {
        storeService
                .placeNewOrder(orderId, orderJson)
                .checkOrderParameters(orderId, expectedId, expectedPetId, expectedQuantity,
                        expectedShipDate, expectedStatus, orderIsComplete);
        cleanData(orderId);
    }

    @DisplayName("TC-ID9")
    @Description("TC-ID9 Отправка запроса на получение заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.storeservice.StoreServiceTestData#checkFindOrderByIdTestData")
    public void checkFindOrderById(long orderId, String orderJson) {
        storeService
                .placeNewOrder(orderId, orderJson)
                .findOrderById(orderId);
        cleanData(orderId);
    }

    @DisplayName("TC-ID10")
    @Description("TC-ID10 Отправка запроса на удаление заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.storeservice.StoreServiceTestData#checkDeleteOrderByIdTestData")
    public void checkDeleteOrderById(long orderId, String orderJson) {
        storeService
                .placeNewOrder(orderId, orderJson)
                .deleteOrderById(orderId);
        assertTrue(storeService.checkNoDataAboutOrder(orderId),
                "Заказ с заданным id = " + orderId + " не был удален");
    }
}