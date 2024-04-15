package petstore.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

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
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось получить пользователя по указанному имени = " + username);
        }
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
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Авторизация пользователя с логином = " + username + " не удалась");
        }
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
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось выйти из системы");
        }
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
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось удалить пользователя по указанному имени = " + username);
        }
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
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось создать список пользователей");
        }
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
                return this;
            } catch (AssertionError assertionError) {
                throw new RuntimeException("Не удалось добавить нового пользователя");
            }
        } else {
            throw new RuntimeException("Пользователь с логином = " + username + " уже существует");
        }
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
                    .log().all()
                    .when()
                    .put(USER_USERNAME)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось обновить данные пользователя с логином = " + username);
        }
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
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Пользователь с заданным логином = " + username + " не был удален");
        }
    }
}