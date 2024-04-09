package pages;

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

    public UserFunctionality checkGetUserByUsername(String username) {
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

    public UserFunctionality checkLogin(String username, String password) {
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

    public UserFunctionality checkReturnPetsByStatus() {
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

    public UserFunctionality checkDeleteUserByUsername(String username) {
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

    public UserFunctionality checkCreateUserList(String jsonFilePath) throws IOException {
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

    public UserFunctionality checkCreateUser(String jsonFilePath) throws IOException {
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

    public UserFunctionality checkUpdateUser(String username, String jsonFilePath) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Json json = new Json();
        Object jsonObject = json.toType(jsonString, Object.class);
        String jsonBody = json.toJson(jsonObject);

        Response response = given()
                .baseUri(BASE_URL)
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