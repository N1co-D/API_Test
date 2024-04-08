import static io.restassured.RestAssured.given;

public class PetShopPage {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final String ID_PET = "/pet/{id}";

    public static void main(String[] args) {
        given()
                .baseUri(BASE_URL)
                .pathParam("id", 1)
                .when()
                .get(ID_PET)
                .then()
                .log().all()
                .statusCode(200);
    }
}
