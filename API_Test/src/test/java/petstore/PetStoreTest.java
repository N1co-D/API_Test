package petstore;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import functionality.PetFunctionality;
import functionality.StoreFunctionality;
import functionality.UserFunctionality;

import java.io.IOException;

public class PetStoreTest {
    private final PetFunctionality petFunctionality = new PetFunctionality();
    private final StoreFunctionality storeFunctionality = new StoreFunctionality();
    private final UserFunctionality userFunctionality = new UserFunctionality();

    @Description("TC-ID1 Отправка запроса на добавление нового питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewPetTestData")
    public void checkAddNewPet(String jsonFilePath) throws IOException {
        petFunctionality.addNewPet(jsonFilePath);
    }

    @Description("TC-ID2 Отправка запроса на получение питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindPetByIdTestData")
    public void checkFindPetById(String jsonFilePath, int id) throws IOException {
        petFunctionality
                .addNewPet(jsonFilePath)
                .findPetById(id);
    }

    @Description("TC-ID3 Отправка запроса на изменение имени и статуса питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkPartialUpdatePetTestData")
    public void checkPartialUpdatePet(String jsonFilePath, int id, String updatedName,
                                      String updatedStatus) throws IOException {
        petFunctionality
                .addNewPet(jsonFilePath)
                .partialUpdatePet(id, updatedName, updatedStatus);
    }

    @Description("TC-ID4 Отправка запроса на изменение данных о питомце")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFullUpdatePetTestData")
    public void checkFullUpdatePet(String jsonFilePath, String jsonFilePathToUpdate) throws IOException {
        petFunctionality
                .addNewPet(jsonFilePath)
                .fullUpdatePet(jsonFilePathToUpdate);
    }

    @Description("TC-ID5 Отправка запроса на удаление питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeletePetByIdTestData")
    public void checkDeletePetById(String jsonFilePath, int id) throws IOException {
        petFunctionality
                .addNewPet(jsonFilePath)
                .deletePetById(id);
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
        storeFunctionality.getInventoryByStatus();
    }

    @Description("TC-ID8 Отправка запроса на добавление нового заказа")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewOrderTestData")
    public void checkAddNewOrder(String jsonFilePath) throws IOException {
        storeFunctionality.addNewOrder(jsonFilePath);
    }

    @Description("TC-ID9 Отправка запроса на получение заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindOrderByIdTestData")
    public void checkFindOrderById(String jsonFilePath, int id) throws IOException {
        storeFunctionality
                .addNewOrder(jsonFilePath)
                .findOrderById(id);
    }

    @Description("TC-ID10 Отправка запроса на удаление заказа по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteOrderByIdTestData")
    public void checkDeleteOrderById(String jsonFilePath, int id) throws IOException {
        storeFunctionality
                .addNewOrder(jsonFilePath)
                .deleteOrderById(id);
    }

    @Description("TC-ID11 Отправка запроса на добавление нового пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserTestData")
    public void checkCreateUser(String jsonFilePath) throws IOException {
        userFunctionality.createUser(jsonFilePath);
    }

    @Description("TC-ID12 Отправка запроса на добавление списка пользователей")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserListTestData")
    public void checkCreateUserList(String jsonFilePath) throws IOException {
        userFunctionality.createUserList(jsonFilePath);
    }

    @Description("TC-ID13 Отправка запроса на получение пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkGetUserByUsernameTestData")
    public void checkGetUserByUsername(String jsonFilePath, String username) throws IOException {
        userFunctionality
                .createUser(jsonFilePath)
                .getUserByUsername(username);
    }

    @Description("TC-ID14 Отправка запроса на авторизацию пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLoginTestData")
    public void checkLogin(String jsonFilePath, String username, String password) throws IOException {
        userFunctionality
                .createUser(jsonFilePath)
                .login(username, password);
    }

    @Description("TC-ID15 Отправка запроса на изменение данных пользователя")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkUpdateUserTestData")
    public void checkUpdateUser(String jsonFilePath, String username, String jsonFilePathToUpdate) throws IOException {
        userFunctionality
                .createUser(jsonFilePath)
                .updateUser(username, jsonFilePathToUpdate);
    }

    @Description("TC-ID16 Отправка запроса на выход пользователя из системы")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLogoutTestData")
    public void checkLogout(String jsonFilePath, String username, String password) throws IOException {
        userFunctionality
                .createUser(jsonFilePath)
                .login(username, password)
                .logout();
    }

    @Description("TC-ID17 Отправка запроса на удаление пользователя по логину")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteUserByUsernameTestData")
    public void checkDeleteUserByUsername(String jsonFilePath, String username) throws IOException {
        userFunctionality
                .createUser(jsonFilePath)
                .deleteUserByUsername(username);
    }
}