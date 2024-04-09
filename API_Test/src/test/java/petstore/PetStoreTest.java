package petstore;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.PetFunctionality;
import pages.StoreFunctionality;
import pages.UserFunctionality;

import java.io.IOException;

public class PetStoreTest {
    private final PetFunctionality petFunctionality = new PetFunctionality();
    private final StoreFunctionality storeFunctionality = new StoreFunctionality();
    private final UserFunctionality userFunctionality = new UserFunctionality();

    @Order(1)
    @Description("Отправка запроса на добавление нового питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewPetTestData")
    public void checkAddNewPet(String jsonFilePath) throws IOException {
        petFunctionality.addNewPet(jsonFilePath);
    }

    @Order(2)
    @Description("Отправка запроса на получение питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindPetByIdTestData")
    public void checkFindPetById(int id) {
        petFunctionality.findPetById(id);
    }

    @Order(3)
    @Description("Отправка запроса на изменение имени и статуса питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkPartialUpdatePetTestData")
    public void checkPartialUpdatePet(int id, String updatedName,
                                      String updatedStatus) {
        petFunctionality.partialUpdatePet(id, updatedName, updatedStatus);
    }

    @Order(4)
    @Description("Отправка запроса на изменение данных о питомце")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFullUpdatePetTestData")
    public void checkFullUpdatePet(String jsonFilePath) throws IOException {
        petFunctionality.fullUpdatePet(jsonFilePath);
    }

    @Order(5)
    @Description("Отправка запроса на удаление питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeletePetByIdTestData")
    public void checkDeletePetById(int id) {
        petFunctionality.deletePetById(id);
    }

    @Order(6)
    @Description("Отправка запроса на получение всех питомцев по статусу")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindPetByStatusTestData")
    public void checkFindPetByStatus(String status) {
        petFunctionality.findPetByStatus(status);
    }

    @Order(7)
    @Description("Отправка запроса на получение данных о количестве питомцев в инвентаре по статусам")
    @Test
    public void checkGetInventoryByStatus() {
        storeFunctionality.getInventoryByStatus();
    }

    @Order(8)
    @Description("Отправка запроса на добавление нового заказа")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewOrderTestData")
    public void checkAddNewOrder(String jsonFilePath) throws IOException {
        storeFunctionality.addNewOrder(jsonFilePath);
    }

    @Order(9)
    @Description("Отправка запроса на получение заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindOrderByIdTestData")
    public void checkFindOrderById(int id) {
        storeFunctionality.findOrderById(id);
    }

    @Order(10)
    @Description("Отправка запроса на удаление заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteOrderByIdTestData")
    public void checkDeleteOrderById(int id) {
        storeFunctionality.deleteOrderById(id);
    }

    @Order(11)
    @Description("Отправка запроса на добавление нового пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserTestData")
    public void checkCreateUser(String jsonFilePath) throws IOException {
        userFunctionality.createUser(jsonFilePath);
    }

    @Order(12)
    @Description("Отправка запроса на добавление списка пользователей")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserListTestData")
    public void checkCreateUserList(String jsonFilePath) throws IOException {
        userFunctionality.createUserList(jsonFilePath);
    }

    @Order(13)
    @Description("Отправка запроса на получение пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkGetUserByUsernameTestData")
    public void checkGetUserByUsername(String username) {
        userFunctionality.getUserByUsername(username);
    }

    @Order(14)
    @Description("Отправка запроса на авторизацию пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLoginTestData")
    public void checkLogin(String username, String password) {
        userFunctionality.login(username, password);
    }

    @Order(15)
    @Description("Отправка запроса на изменение данных пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkUpdateUserTestData")
    public void checkUpdateUser(String username, String jsonFilePath) throws IOException {
        userFunctionality.updateUser(username, jsonFilePath);
    }

    @Order(16)
    @Description("Отправка запроса на выход пользователя из системы")
    @Test
    public void checkLogout() {
        userFunctionality.logout();
    }

    @Order(17)
    @Description("Отправка запроса на удаление пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteUserByUsernameTestData")
    public void checkDeleteUserByUsername(String username) {
        userFunctionality.deleteUserByUsername(username);
    }
}