package petstore.userservice;

import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import petstore.services.UserService;

@Slf4j
public class UserServiceTestConfig extends UserServiceTestData {
    public final UserService userService = new UserService();

    @BeforeEach
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Description("Очистка данных после тестирование")
    public void cleanData(String username) {
        userService.deleteUserByUsername(username);
    }
}