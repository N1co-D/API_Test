package petstore.userservice;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest extends UserServiceTestConfig {

    @DisplayName("TC-ID11")
    @Description("TC-ID11 Отправка запроса на добавление нового пользователя")
    @ParameterizedTest
    @MethodSource("petstore.userservice.UserServiceTestData#checkCreateUserTestData")
    public void checkCreateUser(String username, String userJson, String expectedUsername,
                                String expectedFirstName, String expectedLastName, String expectedEmail,
                                String expectedPassword, String expectedPhone, int expectedUserStatus) {
        userService
                .createUser(username, userJson)
                .checkUserParameters(username, expectedUsername, expectedFirstName,
                        expectedLastName, expectedEmail, expectedPassword, expectedPhone, expectedUserStatus);
        cleanData(username);
    }

    @DisplayName("TC-ID12")
    @Description("TC-ID12 Отправка запроса на добавление списка пользователей")
    @ParameterizedTest
    @MethodSource("petstore.userservice.UserServiceTestData#checkCreateUserListTestData")
    public void checkCreateUserList(String userListJson) {
        userService
                .createUserList(userListJson);
    }

    @DisplayName("TC-ID13")
    @Description("TC-ID13 Отправка запроса на получение пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.userservice.UserServiceTestData#checkGetUserByUsernameTestData")
    public void checkGetUserByUsername(String username, String userJson) {
        userService
                .createUser(username, userJson)
                .getUserByUsername(username);
        cleanData(username);
    }

    @DisplayName("TC-ID14")
    @Description("TC-ID14 Отправка запроса на авторизацию пользователя")
    @ParameterizedTest
    @MethodSource("petstore.userservice.UserServiceTestData#checkLoginTestData")
    public void checkLogin(String username, String userJson, String password) {
        userService
                .createUser(username, userJson)
                .login(username, password);
        cleanData(username);
    }

    @DisplayName("TC-ID15")
    @Description("TC-ID15 Отправка запроса на изменение данных пользователя")
    @ParameterizedTest
    @MethodSource("petstore.userservice.UserServiceTestData#checkUpdateUserTestData")
    public void checkUpdateUser(String username, String userJson, String password, String updatedUsername,
                                String updateUserJson, String updatedUsernameToCheck, String expectedUsername,
                                String expectedFirstName, String expectedLastName, String expectedEmail,
                                String expectedPassword, String expectedPhone, int expectedUserStatus) {
        userService
                .createUser(username, userJson)
                .login(username, password)
                .updateUser(updatedUsername, updateUserJson)
                .checkUserParameters(updatedUsernameToCheck, expectedUsername, expectedFirstName,
                        expectedLastName, expectedEmail, expectedPassword, expectedPhone, expectedUserStatus);
        cleanData(username);
    }

    @DisplayName("TC-ID16")
    @Description("TC-ID16 Отправка запроса на выход пользователя из системы")
    @ParameterizedTest
    @MethodSource("petstore.userservice.UserServiceTestData#checkLogoutTestData")
    public void checkLogout(String username, String userJson, String password) {
        userService
                .createUser(username, userJson)
                .login(username, password)
                .logout();
        cleanData(username);
    }

    @DisplayName("TC-ID17")
    @Description("TC-ID17 Отправка запроса на удаление пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.userservice.UserServiceTestData#checkDeleteUserByUsernameTestData")
    public void checkDeleteUserByUsername(String username, String userJson) {
        userService
                .createUser(username, userJson)
                .deleteUserByUsername(username);
        assertTrue(userService.checkNoDataAboutUser(username),
                "Пользователь с заданным логином = " + username + " не был удален");
    }
}