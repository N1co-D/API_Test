package petstore.storeservice;

import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import petstore.services.StoreService;

@Slf4j
public class StoreServiceTestConfig extends StoreServiceTestData {
    public final StoreService storeService = new StoreService();

    @BeforeEach
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Description("Очистка данных после тестирование")
    public void cleanData(long orderId) {
        storeService.deleteOrderById(orderId);
    }
}