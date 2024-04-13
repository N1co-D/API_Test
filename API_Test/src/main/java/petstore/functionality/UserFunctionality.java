package petstore.functionality;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class UserFunctionality {
    private static final String USER_USERNAME = "/user/{username}";
    private static final String USER_LOGIN = "/user/login";
    private static final String USER_LOGOUT = "/user/logout";
    private static final String USER_LIST = "/user/createWithList";
    private static final String NEW_USER = "/user";
    private static final RequestSpecification requestSpecification = Specification.requestSpecification();
    private static final ResponseSpecification responseSpecification = Specification.responseSpecification();

    @Step("Отправка запроса на получение пользователя по логину = {username}")
    public UserFunctionality getUserByUsername(String username) {
        try {
            given()
                    .spec(requestSpecification)
                    .queryParam("username", username)
                    .pathParam("username", username)
                    .when()
                    .get(USER_USERNAME)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось пользователя по указанному имени = " + username);
        }
    }

    @Step("Отправка запроса на авторизацию пользователя с логином = {username}")
    public UserFunctionality login(String username, String password) {
        try {
            given()
                    .spec(requestSpecification)
                    .queryParam("username", username)
                    .queryParam("password", password)
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
    public UserFunctionality logout() {
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
    public UserFunctionality deleteUserByUsername(String username) {
        try {
            given()
                    .spec(requestSpecification)
                    .queryParam("username", username)
                    .pathParam("username", username)
                    .when()
                    .delete(USER_USERNAME)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось пользователя по указанному имени = " + username);
        }
    }

    @Step("Отправка запроса на добавление списка пользователей")
    public UserFunctionality createUserList(String userListJson) {
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
    public UserFunctionality createUser(String userJson) {
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
    }

    @Step("Отправка запроса на изменение данных пользователя с логином {username}")
    public UserFunctionality updateUser(String username, String userJson) {
        try {
            given()
                    .spec(requestSpecification)
                    .pathParam("username", username)
                    .queryParam("username", username)
                    .contentType(JSON)
                    .body(userJson)
                    .when()
                    .put(USER_USERNAME)
                    .then()
                    .spec(responseSpecification);
            return this;
        } catch (AssertionError assertionError) {
            throw new RuntimeException("Не удалось обновить данные пользователя с логином = " + username);
        }
    }
}