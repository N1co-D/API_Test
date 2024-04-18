package petstore.service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import petstore.data.user.User;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class UserService {
    private static final RequestSpecification REQUEST_SPECIFICATION = Specification.requestSpecification();
    private static final ResponseSpecification RESPONSE_SPECIFICATION = Specification.responseSpecification();

    private boolean checkIfUserExistByUsername(String username) {
        log.info(String.format("Проверка наличия пользователя по логину = %s", username));
        Response response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("username", username)
                .when()
                .get(Constants.USER_BY_USERNAME);
        response
                .then()
                .log()
                .status();
        return response.getStatusCode() == 200;
    }

    @Step("Отправка запроса на получение пользователя по логину = {username}")
    public UserService getUserByUsername(String username) {
        log.info(String.format("Отправка запроса на получение данных о пользователе по логину = %s", username));
        given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("username", username)
                .when()
                .get(Constants.USER_BY_USERNAME)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info(String.format("Получение данных о пользователе по логину = %s прошла успешно",
                username));
        return this;
    }

    @Step("Отправка запроса на авторизацию пользователя с логином = {username}")
    public UserService login(String username, String password) {
        log.info(String.format("Отправка запроса на авторизацию пользователя с логином = %s", username));
        given()
                .spec(REQUEST_SPECIFICATION)
                .param("username", username)
                .param("password", password)
                .when()
                .get(Constants.USER_LOGIN)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info(String.format("Авторизация пользователя с логином = %s прошла успешно", username));
        return this;
    }

    @Step("Отправка запроса на выход пользователя из системы")
    public UserService logout() {
        log.info("Отправка запроса на выход из системы");
        given()
                .spec(REQUEST_SPECIFICATION)
                .when()
                .get(Constants.USER_LOGOUT)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info("Выход из системы произошел успешно");
        return this;
    }

    @Step("Отправка запроса на удаление пользователя по логину = {username}")
    public UserService deleteUserByUsername(String username) {
        log.info(String.format("Отправка запроса на удаление пользователя с логином = %s", username));
        given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("username", username)
                .when()
                .delete(Constants.USER_BY_USERNAME)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info(String.format("Удаление пользователя с логином = %s прошло успешно", username));
        return this;
    }

    @Step("Отправка запроса на добавление списка пользователей")
    public UserService createUserList(String userListJson) {
        log.info("Отправка запроса на добавление списка пользователей");
        given()
                .spec(REQUEST_SPECIFICATION)
                .contentType(JSON)
                .body(userListJson)
                .when()
                .post(Constants.USER_LIST)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info("Добавление списка пользователей прошло успешно");
        return this;
    }

    @Step("Отправка запроса на добавление нового пользователя")
    public UserService createUser(String username, String userJson) {
        log.info("Отправка запроса на добавление нового пользователя");
        if (!checkIfUserExistByUsername(username)) {
            log.info(String.format("В текущий момент пользователь с логином = %s в базе отсутствует", username));
            given()
                    .spec(REQUEST_SPECIFICATION)
                    .contentType(JSON)
                    .body(userJson)
                    .when()
                    .post(Constants.NEW_USER)
                    .then()
                    .spec(RESPONSE_SPECIFICATION);
        }
        log.info("Добавление пользователя прошло успешно");
        return this;
    }

    @Step("Отправка запроса на изменение данных пользователя с логином {username}")
    public UserService updateUser(String username, String userJson) {
        log.info(String.format("Отправка запроса на изменение данных пользователя с логином = %s", username));
        given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("username", username)
                .contentType(JSON)
                .body(userJson)
                .when()
                .put(Constants.USER_BY_USERNAME)
                .then()
                .spec(RESPONSE_SPECIFICATION);
        log.info(String.format("Изменение данных пользователя с логином = %s прошла успешно", username));
        return this;
    }

    @Step("Проверка соответствия полей firstName и email с ожидаемыми результатами = {expectedFirstName} и {expectedEmail} соответственно")
    public UserService checkUserParameters(String username, String expectedUsername,
                                           String expectedFirstName, String expectedLastName, String expectedEmail,
                                           String expectedPassword, String expectedPhone, int expectedUserStatus) {
        log.info("Проверка измененных данных пользователя");
        User response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("username", username)
                .when()
                .get(Constants.USER_BY_USERNAME)
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .extract().body().as(User.class);
        Assertions.assertAll(
                () -> assertEquals(expectedUsername, response.getUsername(),
                        "Фактический логин пользователя = " + response.getUsername() +
                                " не соответствует ожидаемому = " + expectedUsername),
                () -> assertEquals(expectedFirstName, response.getFirstName(),
                        "Фактическое имя пользователя = " + response.getFirstName() +
                                " не соответствует ожидаемому = " + expectedFirstName),
                () -> assertEquals(expectedLastName, response.getLastName(),
                        "Фактическая фамилия пользователя = " + response.getLastName() +
                                " не соответствует ожидаемой = " + expectedLastName),
                () -> assertEquals(expectedEmail, response.getEmail(),
                        "Фактическая электронная почта пользователя = " + response.getEmail() +
                                " не соответствует ожидаемой = " + expectedEmail),
                () -> assertEquals(expectedPassword, response.getPassword(),
                        "Фактический пароль пользователя = " + response.getPassword() +
                                " не соответствует ожидаемому = " + expectedPassword),
                () -> assertEquals(expectedPhone, response.getPhone(),
                        "Фактический телефон пользователя = " + response.getPhone() +
                                " не соответствует ожидаемому = " + expectedPhone),
                () -> assertEquals(expectedUserStatus, response.getUserStatus(),
                        "Фактический статус пользователя = " + response.getUserStatus() +
                                " не соответствует ожидаемому = " + expectedUserStatus)
        );
        log.info("Проверка измененных данных пользователя прошла успешно");
        return this;
    }

    @Step("Проверка отсутствия данных о пользователе с логином = {username} по запросу")
    public boolean checkNoDataAboutUser(String username) {
        log.info(String.format("Проверка отсутствия заказа по username = %s", username));
        Response response = given()
                .spec(REQUEST_SPECIFICATION)
                .pathParam("username", username)
                .when()
                .get(Constants.USER_BY_USERNAME);
        response
                .then()
                .log()
                .status();
        return response.getStatusCode() == 404;
    }
}