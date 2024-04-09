package pages;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserPage extends BasePage {
    public static final String USER_USERNAME = "/user/{username}";
    public static final String USER_LOGIN = "/user/login";
    public static final String USER_LOGOUT = "/user/logout";

    public UserPage checkGetUserByUsername(String username) {
        Response response = given()
                .baseUri(BASE_URL)
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

    public UserPage checkLogin(String username, String password) {
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

    public UserPage checkReturnPetsByStatus() {
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
}