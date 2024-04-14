package petstore;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import petstore.functionality.Endpoints;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;

public class PetStoreTestConfig extends PetStoreTestData {
    private static final RequestSpecification requestSpecification = Specification.requestSpecification();
    static boolean CLEAN_PET_AFTER_TEST;
    static boolean CLEAN_ORDER_AFTER_TEST;
    static boolean CLEAN_USER_AFTER_TEST;

    @BeforeEach
    public void setFilter(){
        RestAssured.filters(new AllureRestAssured());
    }

    @AfterEach
    public void clearPetTestData() {
        if (CLEAN_PET_AFTER_TEST) {
            try {
                given()
                        .spec(requestSpecification)
                        .queryParam("petId", petId)
                        .pathParam("petId", petId)
                        .when()
                        .delete(Endpoints.PET_ID)
                        .then()
                        .log().status();
            } catch (AssertionError assertionError) {
                throw new RuntimeException("Не удалось удалить питомца по заданному id = " + petId);
            }
            CLEAN_PET_AFTER_TEST = false;
        } else if (CLEAN_ORDER_AFTER_TEST) {
            try {
                given()
                        .spec(requestSpecification)
                        .queryParam("orderId", orderId)
                        .pathParam("orderId", orderId)
                        .when()
                        .delete(Endpoints.ORDER_ID)
                        .then()
                        .log().status();
            } catch (AssertionError assertionError) {
                throw new RuntimeException("Не удалось удалить заказ по заданному id = " + orderId);
            }
            CLEAN_ORDER_AFTER_TEST = false;
        } else if (CLEAN_USER_AFTER_TEST) {
            try {
                given()
                        .spec(requestSpecification)
                        .queryParam("username", username)
                        .pathParam("username", username)
                        .when()
                        .delete(Endpoints.USER_USERNAME)
                        .then()
                        .log().status();
            } catch (AssertionError assertionError) {
                throw new RuntimeException("Не удалось удалить пользователя по заданному логину = " + username);
            }
            CLEAN_USER_AFTER_TEST = false;
        }
    }
}