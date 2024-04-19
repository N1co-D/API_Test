package petstore;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import petstore.services.PetService;
import petstore.services.StoreService;
import petstore.services.UserService;

@Slf4j
public class PetStoreTestConfig extends PetStoreTestData {
    public final PetService petService = new PetService();
    public final StoreService storeService = new StoreService();
    public final UserService userService = new UserService();

    @BeforeEach
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }

    public void clearPetDataAfterTest(long petId) {
        log.info(String.format("Проверка наличия питомца с id = %s в базе", petId));

        if (!petService.checkNoDataAboutPet(petId)) {
            log.info(String.format("Питомец с id = %s есть в базе", petId));
            petService.deletePetById(petId);
        }

        log.info(String.format("Питомец с id = %s отсутствует в базе", petId));
    }

    public void clearOrderDataAfterTest(long orderId) {
        log.info(String.format("Проверка наличия заказа с id = %s в базе", orderId));

        if (!storeService.checkNoDataAboutOrder(orderId)) {
            log.info(String.format("Заказ с id = %s есть в базе", orderId));
            storeService.deleteOrderById(orderId);
        }

        log.info(String.format("Заказ с id = %s отсутствует в базе", orderId));
    }

    public void clearUserDataAfterTest(String username) {
        log.info(String.format("Проверка наличия пользователя с логином = %s в базе", username));

        if (!userService.checkNoDataAboutUser(username)) {
            log.info(String.format("Пользователь с логином = %s есть в базе", username));
            userService.deleteUserByUsername(username);
        }

        log.info(String.format("Пользователь с логином = %s отсутствует в базе", username));
    }
}