package petstore;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import petstore.specs.Specification;

import static io.restassured.RestAssured.given;

public class PetStoreTestConfig extends PetStoreTestData {
    private static final RequestSpecification requestSpecification = Specification.requestSpecification();
    private static final String PET_ID = "/pet/{petId}";
    private static final String ORDER_ID = "/store/order/{orderId}";
    private static final String USER_USERNAME = "/user/{username}";
    static boolean CLEAN_PET_AFTER_TEST;
    static boolean CLEAN_ORDER_AFTER_TEST;
    static boolean CLEAN_USER_AFTER_TEST;

    @AfterEach
    public void clearPetTestData() {
        if (CLEAN_PET_AFTER_TEST) {
            try {
                given()
                        .spec(requestSpecification)
                        .queryParam("petId", petId)
                        .pathParam("petId", petId)
                        .when()
                        .delete(PET_ID)
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
                        .delete(ORDER_ID)
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
                        .delete(USER_USERNAME)
                        .then()
                        .log().status();
            } catch (AssertionError assertionError) {
                throw new RuntimeException("Не удалось удалить пользователя по заданному логину = " + username);
            }
            CLEAN_USER_AFTER_TEST = false;
        }
    }
}