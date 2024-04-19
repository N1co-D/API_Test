package petstore;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreServiceTest extends PetStoreTestConfig {

    @DisplayName("TC-ID8")
    @Description("TC-ID8 Отправка запроса на добавление нового заказа")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewOrderTestData")
    public void checkAddNewOrder(String orderJson, long expectedId, long expectedPetId,
                                 Integer expectedQuantity, String expectedShipDate,
                                 String expectedStatus, boolean orderIsComplete) {
        storeService
                .placeNewOrder(ORDER_ID, orderJson)
                .checkOrderParameters(ORDER_ID, expectedId, expectedPetId, expectedQuantity,
                        expectedShipDate, expectedStatus, orderIsComplete);
        clearOrderDataAfterTest(ORDER_ID);
    }

    @DisplayName("TC-ID9")
    @Description("TC-ID9 Отправка запроса на получение заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindOrderByIdTestData")
    public void checkFindOrderById(String orderJson) {
        storeService
                .placeNewOrder(ORDER_ID, orderJson)
                .findOrderById(ORDER_ID);
        clearOrderDataAfterTest(ORDER_ID);
    }

    @DisplayName("TC-ID10")
    @Description("TC-ID10 Отправка запроса на удаление заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteOrderByIdTestData")
    public void checkDeleteOrderById(String orderJson) {
        storeService
                .placeNewOrder(ORDER_ID, orderJson)
                .deleteOrderById(ORDER_ID);
        assertTrue(storeService.checkNoDataAboutOrder(ORDER_ID),
                "Заказ с заданным id = " + ORDER_ID + " не был удален");
    }
}