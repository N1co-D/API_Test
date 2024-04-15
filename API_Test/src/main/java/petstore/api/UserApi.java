package petstore.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import petstore.data.user.User;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserApi extends Endpoints {
    private static final RequestSpecification requestSpecification = Specification.requestSpecification();
    private static final ResponseSpecification responseSpecification = Specification.responseSpecification();

    private boolean checkIfUserExistByUsername(String username) {
        Response response = given()
                .spec(requestSpecification)
                .pathParam("username", username)
                .param("username", username)
                .when()
                .get(USER_USERNAME);
        response
                .then()
                .log()
                .status();
        return response.getStatusCode() == 200;
    }

    @Step("Отправка запроса на получение пользователя по логину = {username}")
    public UserApi getUserByUsername(String username) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("username", username)
                    .param("username", username)
                    .when()
                    .get(USER_USERNAME)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось получить пользователя по указанному имени = " + username);
        }
        return this;
    }

    @Step("Отправка запроса на авторизацию пользователя с логином = {username}")
    public UserApi login(String username, String password) {
        try {
            given()
                    .spec(requestSpecification)
                    .param("username", username)
                    .param("password", password)
                    .when()
                    .get(USER_LOGIN)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Авторизация пользователя с логином = " + username + " не удалась");
        }
        return this;
    }

    @Step("Отправка запроса на выход пользователя из системы")
    public UserApi logout() {
        try {
            given()
                    .spec(requestSpecification)
                    .when()
                    .get(USER_LOGOUT)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось выйти из системы");
        }
        return this;
    }

    @Step("Отправка запроса на удаление пользователя по логину = {username}")
    public UserApi deleteUserByUsername(String username) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("username", username)
                    .param("username", username)
                    .when()
                    .delete(USER_USERNAME)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось удалить пользователя по указанному имени = " + username);
        }
        return this;
    }

    @Step("Отправка запроса на добавление списка пользователей")
    public UserApi createUserList(String userListJson) {
        try {
            given()
                    .spec(requestSpecification)
                    .contentType(JSON)
                    .body(userListJson)
                    .when()
                    .post(USER_LIST)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось создать список пользователей");
        }
        return this;
    }

    @Step("Отправка запроса на добавление нового пользователя")
    public UserApi createUser(String username, String userJson) {
        if (!checkIfUserExistByUsername(username)) {
            try {
                given()
                        .spec(requestSpecification)
                        .contentType(JSON)
                        .body(userJson)
                        .when()
                        .post(NEW_USER)
                        .then()
                        .spec(responseSpecification);
            } catch (AssertionError assertionError) {
                throw new RuntimeException("Не удалось добавить нового пользователя");
            }
        } else {
            throw new RuntimeException("Пользователь с логином = " + username + " уже существует");
        }
        return this;
    }

    @Step("Отправка запроса на изменение данных пользователя с логином {username}")
    public UserApi updateUser(String username, String userJson) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("username", username)
                    .param("username", username)
                    .contentType(JSON)
                    .body(userJson)
                    .when()
                    .put(USER_USERNAME)
                    .then()
                    .spec(responseSpecification);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось обновить данные пользователя с логином = " + username);
        }
        return this;
    }

    @Step("Проверка соответствия полей firstName и email с ожидаемыми результатами = {expectedFirstName} и {expectedEmail} соответственно")
    public UserApi checkUserParameters(String username, String expectedFirstName, String expectedEmail) {
        try {
            User response = given()
                    .spec(requestSpecification)
                    .pathParam("username", username)
                    .param("username", username)
                    .when()
                    .get(USER_USERNAME)
                    .then()
                    .spec(responseSpecification)
                    .extract().body().as(User.class);
            Assertions.assertAll(
                    () -> assertEquals(expectedFirstName, response.getFirstName(),
                            "Фактическое имя пользователя = " + response.getFirstName() +
                                    " не соответствует ожидаемому = " + expectedFirstName),
                    () -> assertEquals(expectedEmail, response.getEmail(),
                            "Фактическая электронная почта пользователя = " + response.getEmail() +
                                    " не соответствует ожидаемой = " + expectedEmail)
            );
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось найти пользователя по заданному логину = " + username);
        }
        return this;
    }

    @Step("Проверка отсутствия данных о пользователе с логином = {username} по запросу")
    public UserApi checkNoDataAboutUser(String username) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("username", username)
                    .param("username", username)
                    .when()
                    .get(USER_USERNAME)
                    .then()
                    .log().status()
                    .statusCode(404);
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Пользователь с заданным логином = " + username + " не был удален");
        }
        return this;
    }
}