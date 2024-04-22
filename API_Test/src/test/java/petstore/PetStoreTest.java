package petstore;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import petstore.data.pet.Category;
import petstore.data.pet.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class PetStoreTest extends PetStoreTestConfig {

    @Description("TC-ID1 Отправка запроса на добавление нового питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewPetTestData")
    public void checkAddNewPet(String petJson, long expectedId, Category expectedCategory, String expectedName,
                               List<String> expectedPhotoUrls, List<Tag> expectedTags, String expectedStatus) {
        petService
                .addNewPet(PET_ID, petJson)
                .checkPetParameters(PET_ID, expectedId, expectedCategory, expectedName,
                        expectedPhotoUrls, expectedTags, expectedStatus);
        clearPetDataAfterTest(PET_ID);
    }

    @Description("TC-ID2 Отправка запроса на получение питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindPetByIdTestData")
    public void checkFindPetById(String petJson) {
        petService
                .addNewPet(PET_ID, petJson)
                .findPetById(PET_ID);
        clearPetDataAfterTest(PET_ID);
    }

    @Description("TC-ID3 Отправка запроса на изменение имени и статуса питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkPartialUpdatePetTestData")
    public void checkPartialUpdatePet(String petJson, String updatedName, String updatedStatus) {
        petService
                .addNewPet(PET_ID, petJson)
                .partialUpdatePet(PET_ID, updatedName, updatedStatus)
                .checkPetsNameAndStatus(PET_ID, updatedName, updatedStatus);
        clearPetDataAfterTest(PET_ID);
    }

    @Description("TC-ID4 Отправка запроса на изменение данных о питомце")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFullUpdatePetTestData")
    public void checkFullUpdatePet(String petJson, String updatePetJson, long expectedId, Category expectedCategory,
                                   String expectedName, List<String> expectedPhotoUrls, List<Tag> expectedTags,
                                   String expectedStatus) {
        petService
                .addNewPet(PET_ID, petJson)
                .fullUpdatePet(updatePetJson)
                .checkPetParameters(PET_ID, expectedId, expectedCategory, expectedName,
                        expectedPhotoUrls, expectedTags, expectedStatus);
        clearPetDataAfterTest(PET_ID);
    }

    @Description("TC-ID5 Отправка запроса на удаление питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeletePetByIdTestData")
    public void checkDeletePetById(String petJson) {
        petService
                .addNewPet(PET_ID, petJson)
                .deletePetById(PET_ID);
        assertTrue(petService.checkNoDataAboutPet(PET_ID),
                "Питомец с заданным id = " + PET_ID + " не был удален");
    }

    @Description("TC-ID6 Отправка запроса на получение всех питомцев по статусу")
    @ParameterizedTest
    @ValueSource(strings = {"available", "pending", "sold"})
    public void checkFindPetByStatus(String status) {
        petService.findPetByStatus(status);
    }

    @Description("TC-ID7 Отправка запроса на получение данных о количестве питомцев в инвентаре по статусам")
    @Test
    public void checkGetInventoryByStatus() {
        storeService
                .getInventoryWithStatusFilter();
    }

    @Description("TC-ID8 Отправка запроса на добавление нового заказа")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewOrderTestData")
    public void checkAddNewOrder(String orderJson, long expectedId, long expectedPetId,
                                 Integer expectedQuantity, String expectedShipDate,
                                 String expectedStatus, boolean orderIsComplete) {
        storeService
                .placeNewOrder(ORDER_ID, orderJson)
                .checkOrderParameters(ORDER_ID, expectedId, expectedPetId, expectedQuantity,
                        expectedShipDate, expectedStatus, orderIsComplete);
        clearOrderDataAfterTest(ORDER_ID);
    }

    @Description("TC-ID9 Отправка запроса на получение заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindOrderByIdTestData")
    public void checkFindOrderById(String orderJson) {
        storeService
                .placeNewOrder(ORDER_ID, orderJson)
                .findOrderById(ORDER_ID);
        clearOrderDataAfterTest(ORDER_ID);
    }

    @Description("TC-ID10 Отправка запроса на удаление заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteOrderByIdTestData")
    public void checkDeleteOrderById(String orderJson) {
        storeService
                .placeNewOrder(ORDER_ID, orderJson)
                .deleteOrderById(ORDER_ID);
        assertTrue(storeService.checkNoDataAboutOrder(ORDER_ID),
                "Заказ с заданным id = " + ORDER_ID + " не был удален");
    }

    @Description("TC-ID11 Отправка запроса на добавление нового пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserTestData")
    public void checkCreateUser(String userJson, String expectedUsername,
                                String expectedFirstName, String expectedLastName, String expectedEmail,
                                String expectedPassword, String expectedPhone, int expectedUserStatus) {
        userService
                .createUser(USERNAME, userJson)
                .checkUserParameters(USERNAME, expectedUsername, expectedFirstName,
                        expectedLastName, expectedEmail, expectedPassword, expectedPhone, expectedUserStatus);
        clearUserDataAfterTest(USERNAME);
    }

    @Description("TC-ID12 Отправка запроса на добавление списка пользователей")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserListTestData")
    public void checkCreateUserList(String userListJson) {
        userService
                .createUserList(userListJson);
    }

    @Description("TC-ID13 Отправка запроса на получение пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkGetUserByUsernameTestData")
    public void checkGetUserByUsername(String userJson) {
        userService
                .createUser(USERNAME, userJson)
                .getUserByUsername(USERNAME);
        clearUserDataAfterTest(USERNAME);
    }

    @Description("TC-ID14 Отправка запроса на авторизацию пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLoginTestData")
    public void checkLogin(String userJson, String password) {
        userService
                .createUser(USERNAME, userJson)
                .login(USERNAME, password);
        clearUserDataAfterTest(USERNAME);
    }

    @Description("TC-ID15 Отправка запроса на изменение данных пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkUpdateUserTestData")
    public void checkUpdateUser(String userJson, String password, String updateUserJson,
                                String expectedUsername, String expectedFirstName, String expectedLastName,
                                String expectedEmail, String expectedPassword, String expectedPhone,
                                int expectedUserStatus) {
        userService
                .createUser(USERNAME, userJson)
                .login(USERNAME, password)
                .updateUser(UPDATED_USERNAME, updateUserJson)
                .checkUserParameters(UPDATED_USERNAME, expectedUsername, expectedFirstName,
                        expectedLastName, expectedEmail, expectedPassword, expectedPhone, expectedUserStatus);
        clearUserDataAfterTest(USERNAME);
    }

    @Description("TC-ID16 Отправка запроса на выход пользователя из системы")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLogoutTestData")
    public void checkLogout(String userJson, String password) {
        userService
                .createUser(USERNAME, userJson)
                .login(USERNAME, password)
                .logout();
        clearUserDataAfterTest(USERNAME);
    }

    @Description("TC-ID17 Отправка запроса на удаление пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteUserByUsernameTestData")
    public void checkDeleteUserByUsername(String userJson) {
        userService
                .createUser(USERNAME, userJson)
                .deleteUserByUsername(USERNAME);
        assertTrue(userService.checkNoDataAboutUser(USERNAME),
                "Пользователь с заданным логином = " + USERNAME + " не был удален");
    }
}