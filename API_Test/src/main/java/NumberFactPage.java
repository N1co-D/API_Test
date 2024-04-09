import static io.restassured.RestAssured.given;

public class NumberFactPage {
    public static final String NUMBERS_URL = "http://numbersapi.com";
    public static final String ID_PATH = "/{id}";

    public static void main(String[] args) {
        given()
                .baseUri(NUMBERS_URL)
                .pathParam("id", 1)
                .when()
                .get(ID_PATH)
                .then()
                .log().all()
                .statusCode(200);
    }
}