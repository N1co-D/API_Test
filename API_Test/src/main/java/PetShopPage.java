import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetShopPage {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final String ID_PET = "/pet/{id}";
    public static final String PET_STATUS = "/pet/findByStatus";
    public static final String STORE_INVENTORY = "/store/inventory";
    public static final String ORDER_ID = "/store/order/{id}";
    public static final String USER_USERNAME = "/user/{username}";
    public static final String USER_LOGIN = "/user/login";
    public static final String USER_LOGOUT = "/user/logout";

    public static void main(String[] args) {
//        given()
//                .baseUri(BASE_URL)
//                .pathParam("id", 2)
//                .when()
//                .get(ID_PET)
//                .then()
//                .log().all()
//                .statusCode(200);
//
//        System.out.println("----------------------------------------------------------------------");
//
//        Response response = given()
//                .baseUri(BASE_URL)
//                .pathParam("id", 2)
//                .when()
//                .get(ID_PET);
//        if (response.getStatusCode() == 200) {
//            response.then()
//                    .log()
//                    .all();
//        } else {
//            throw new RuntimeException("Не удалось найти питомца по указанному id. " +
//                    "Статус ответа: " + response.getStatusCode());
//        }
//
//        System.out.println("----------------------------------------------------------------------");
//
//        Response response2 = given()
//                .baseUri(BASE_URL)
//                .queryParam("status", "sold")
//                .when()
//                .get(PET_STATUS);
//
//        if (response2.getStatusCode() == 200) {
//            response2.then()
//                    .log()
//                    .all();
//        } else {
//            throw new RuntimeException("Не удалось найти питомца по указанному статусу. " +
//                    "Статус ответа: " + response2.getStatusCode());
//        }
//    }

//        System.out.println("----------------------------------------------------------------------");

//        Response response3 = given()
//                .baseUri(BASE_URL)
//                .when()
//                .get(STORE_INVENTORY);
//
//        if (response3.getStatusCode() == 200) {
//            response3.then()
//                    .log()
//                    .all();
//        } else {
//            throw new RuntimeException("Не удалось открыть инвентарь с питомцами. " +
//                    "Статус ответа: " + response3.getStatusCode());
//        }
//    }

//        System.out.println("----------------------------------------------------------------------");

//        Response response4 = given()
//                .baseUri(BASE_URL)
//                .pathParam("id", 2)
//                .when()
//                .get(ORDER_ID);
//
//        if (response4.getStatusCode() == 200) {
//            response4.then()
//                    .log()
//                    .all();
//        } else {
//            throw new RuntimeException("Не удалось найти заказ по указанному id. " +
//                    "Статус ответа: " + response4.getStatusCode());
//        }
//    }

        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get(USER_LOGOUT);

        if (response.getStatusCode() == 200) {
            response.then()
                    .log()
                    .all();
        } else {
            throw new RuntimeException("Не удалось выйти из системы. " +
                    "Статус ответа: " + response.getStatusCode());
        }
    }
}