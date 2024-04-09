package pages;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class UserFunctionality extends BasePage {
    private static final String USER_USERNAME = "/user/{username}";
    private static final String USER_LOGIN = "/user/login";
    private static final String USER_LOGOUT = "/user/logout";
    private static final String USER_LIST = "/user/createWithList";
    private static final String USER = "/user";

    @Step("Отправка запроса на получение пользователя по логину = {username}")
    public UserFunctionality getUserByUsername(String username) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("username", username)
                .pathParam("username", username)
                .when()
                .get(USER_USERNAME);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось пользователя по указанному имени. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    @Step("Отправка запроса на авторизацию пользователя с логином = {username}")
    public UserFunctionality login(String username, String password) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get(USER_LOGIN);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Авторизация не удалась. Проверьте вводимые данные " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    @Step("Отправка запроса на выход пользователя из системы")
    public UserFunctionality logout() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get(USER_LOGOUT);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось выйти из системы. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    @Step("Отправка запроса на удаление пользователя по логину = {username}")
    public UserFunctionality deleteUserByUsername(String username) {
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("username", username)
                .pathParam("username", username)
                .when()
                .delete(USER_USERNAME);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось пользователя по указанному имени. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    @Step("Отправка запроса на добавление списка пользователей")
    public UserFunctionality createUserList(String jsonFilePath) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Json json = new Json();
        Object jsonObject = json.toType(jsonString, Object.class);
        String jsonBody = json.toJson(jsonObject);

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(USER_LIST);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось создать список пользователей. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    @Step("Отправка запроса на добавление нового пользователя")
    public UserFunctionality createUser(String jsonFilePath) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Json json = new Json();
        Object jsonObject = json.toType(jsonString, Object.class);
        String jsonBody = json.toJson(jsonObject);

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(USER);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось создать нового пользователя. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }

    @Step("Отправка запроса на изменение данных пользователя с логином {username}")
    public UserFunctionality updateUser(String username, String jsonFilePath) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Json json = new Json();
        Object jsonObject = json.toType(jsonString, Object.class);
        String jsonBody = json.toJson(jsonObject);

        Response response = given()
                .baseUri(BASE_URL)
                .pathParam("username", username)
                .header("Content-Type", "application/json")
                .queryParam("username", username)
                .body(jsonBody)
                .when()
                .put(USER_USERNAME);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
            return this;
        } else {
            throw new RuntimeException("Не удалось обновить данные пользователя. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }
}