package petstore;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest extends PetStoreTestConfig {

    @DisplayName("TC-ID11 Отправка запроса на добавление нового пользователя")
    @Description("TC-ID11 Отправка запроса на добавление нового пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserTestData")
    public void checkCreateUser(String userJson, String expectedUsername,
                                String expectedFirstName, String expectedLastName, String expectedEmail,
                                String expectedPassword, String expectedPhone, int expectedUserStatus) {
        userService
                .createUser(USERNAME, userJson)
                .checkUserParameters(USERNAME, expectedUsername, expectedFirstName,
                        expectedLastName, expectedEmail, expectedPassword, expectedPhone, expectedUserStatus);
        clearUserDataAfterTest(USERNAME);
    }

    @DisplayName("TC-ID12 Отправка запроса на добавление списка пользователей")
    @Description("TC-ID12 Отправка запроса на добавление списка пользователей")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserListTestData")
    public void checkCreateUserList(String userListJson) {
        userService
                .createUserList(userListJson);
    }

    @DisplayName("TC-ID13 Отправка запроса на получение пользователя по логину")
    @Description("TC-ID13 Отправка запроса на получение пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkGetUserByUsernameTestData")
    public void checkGetUserByUsername(String userJson) {
        userService
                .createUser(USERNAME, userJson)
                .getUserByUsername(USERNAME);
        clearUserDataAfterTest(USERNAME);
    }

    @DisplayName("TC-ID14 Отправка запроса на авторизацию пользователя")
    @Description("TC-ID14 Отправка запроса на авторизацию пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLoginTestData")
    public void checkLogin(String userJson, String password) {
        userService
                .createUser(USERNAME, userJson)
                .login(USERNAME, password);
        clearUserDataAfterTest(USERNAME);
    }

    @DisplayName("TC-ID15 Отправка запроса на изменение данных пользователя")
    @Description("TC-ID15 Отправка запроса на изменение данных пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkUpdateUserTestData")
    public void checkUpdateUser(String userJson, String password, String updateUserJson,
                                String expectedUsername, String expectedFirstName, String expectedLastName,
                                String expectedEmail, String expectedPassword, String expectedPhone,
                                int expectedUserStatus) {
        userService
                .createUser(USERNAME, userJson)
                .login(USERNAME, password)
                .updateUser(UPDATED_USERNAME, updateUserJson)
                .checkUserParameters(UPDATED_USERNAME, expectedUsername, expectedFirstName,
                        expectedLastName, expectedEmail, expectedPassword, expectedPhone, expectedUserStatus);
        clearUserDataAfterTest(USERNAME);
    }

    @DisplayName("TC-ID16 Отправка запроса на выход пользователя из системы")
    @Description("TC-ID16 Отправка запроса на выход пользователя из системы")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLogoutTestData")
    public void checkLogout(String userJson, String password) {
        userService
                .createUser(USERNAME, userJson)
                .login(USERNAME, password)
                .logout();
        clearUserDataAfterTest(USERNAME);
    }

    @DisplayName("TC-ID17 Отправка запроса на удаление пользователя по логину")
    @Description("TC-ID17 Отправка запроса на удаление пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteUserByUsernameTestData")
    public void checkDeleteUserByUsername(String userJson) {
        userService
                .createUser(USERNAME, userJson)
                .deleteUserByUsername(USERNAME);
        assertTrue(userService.checkNoDataAboutUser(USERNAME),
                "Пользователь с заданным логином = " + USERNAME + " не был удален");
    }
}