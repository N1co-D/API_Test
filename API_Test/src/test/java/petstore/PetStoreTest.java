package petstore;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petstore.api.PetApi;
import petstore.api.StoreApi;
import petstore.api.UserAPI;

public class PetStoreTest extends PetStoreTestConfig {
    private final PetApi petFunctionality = new PetApi();
    private final StoreApi storeFunctionality = new StoreApi();
    private final UserAPI userFunctionality = new UserAPI();

    @Description("TC-ID1 Отправка запроса на добавление нового питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewPetTestData")
    public void checkAddNewPet(String petJson) {
        CLEAN_PET_AFTER_TEST = true;
        petFunctionality.addNewPet(petId, petJson)
                .findPetById(petId);
    }

    @Description("TC-ID2 Отправка запроса на получение питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindPetByIdTestData")
    public void checkFindPetById(String petJson) {
        CLEAN_PET_AFTER_TEST = true;
        petFunctionality.addNewPet(petId, petJson)
                .findPetById(petId);
    }

    @Description("TC-ID3 Отправка запроса на изменение имени и статуса питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkPartialUpdatePetTestData")
    public void checkPartialUpdatePet(String petJson, String updatedName, String updatedStatus) {
        CLEAN_PET_AFTER_TEST = true;
        petFunctionality
                .addNewPet(petId, petJson)
                .partialUpdatePet(petId, updatedName, updatedStatus)
                .findPetById(petId);
    }

    @Description("TC-ID4 Отправка запроса на изменение данных о питомце")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFullUpdatePetTestData")
    public void checkFullUpdatePet(String petJson, String updatePetJson) {
        CLEAN_PET_AFTER_TEST = true;
        petFunctionality
                .addNewPet(petId, petJson)
                .fullUpdatePet(updatePetJson)
                .findPetById(petId);
    }

    @Description("TC-ID5 Отправка запроса на удаление питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeletePetByIdTestData")
    public void checkDeletePetById(String petJson) {
        petFunctionality
                .addNewPet(petId, petJson)
                .deletePetById(petId)
                .checkNoDataAboutPet(petId);
    }

    @Description("TC-ID6 Отправка запроса на получение всех питомцев по статусу")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindPetByStatusTestData")
    public void checkFindPetByStatus(String status) {
        petFunctionality.findPetByStatus(status);
    }

    @Description("TC-ID7 Отправка запроса на получение данных о количестве питомцев в инвентаре по статусам")
    @Test
    public void checkGetInventoryByStatus() {
        storeFunctionality.getInventoryWithStatusFilter();
    }

    @Description("TC-ID8 Отправка запроса на добавление нового заказа")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewOrderTestData")
    public void checkAddNewOrder(String orderJson) {
        CLEAN_ORDER_AFTER_TEST = true;
        storeFunctionality.placeNewOrder(orderId, orderJson)
                .findOrderById(orderId);
    }

    @Description("TC-ID9 Отправка запроса на получение заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindOrderByIdTestData")
    public void checkFindOrderById(String orderJson) {
        CLEAN_ORDER_AFTER_TEST = true;
        storeFunctionality.placeNewOrder(orderId, orderJson)
                .findOrderById(orderId);
    }

    @Description("TC-ID10 Отправка запроса на удаление заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteOrderByIdTestData")
    public void checkDeleteOrderById(String orderJson) {
        storeFunctionality
                .placeNewOrder(orderId, orderJson)
                .deleteOrderById(orderId)
                .checkNoDataAboutOrder(orderId);
    }

    @Description("TC-ID11 Отправка запроса на добавление нового пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserTestData")
    public void checkCreateUser(String userJson) {
        CLEAN_USER_AFTER_TEST = true;
        userFunctionality.createUser(username, userJson)
                .getUserByUsername(username);
    }

    @Description("TC-ID12 Отправка запроса на добавление списка пользователей")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserListTestData")
    public void checkCreateUserList(String userListJson) {
        userFunctionality.createUserList(userListJson);
    }

    @Description("TC-ID13 Отправка запроса на получение пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkGetUserByUsernameTestData")
    public void checkGetUserByUsername(String userJson) {
        CLEAN_USER_AFTER_TEST = true;
        userFunctionality.createUser(username, userJson)
                .getUserByUsername(username);
    }

    @Description("TC-ID14 Отправка запроса на авторизацию пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLoginTestData")
    public void checkLogin(String userJson, String password) {
        CLEAN_USER_AFTER_TEST = true;
        userFunctionality
                .createUser(username, userJson)
                .login(username, password);
    }

    @Description("TC-ID15 Отправка запроса на изменение данных пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkUpdateUserTestData")
    public void checkUpdateUser(String userJson, String password, String updateUserJson) {
        CLEAN_USER_AFTER_TEST = true;
        userFunctionality
                .createUser(username, userJson)
                .login(username, password)
                .updateUser(username, updateUserJson)
                .getUserByUsername(username);
    }

    @Description("TC-ID16 Отправка запроса на выход пользователя из системы")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLogoutTestData")
    public void checkLogout(String userJson, String password) {
        CLEAN_USER_AFTER_TEST = true;
        userFunctionality
                .createUser(username, userJson)
                .login(username, password)
                .logout();
    }

    @Description("TC-ID17 Отправка запроса на удаление пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteUserByUsernameTestData")
    public void checkDeleteUserByUsername(String userJson) {
        userFunctionality
                .createUser(username, userJson)
                .deleteUserByUsername(username)
                .checkNoDataAboutUser(username);
    }
}