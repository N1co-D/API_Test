package petstore;

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
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewPetTestData")
    public void checkAddNewPet(String jsonFilePath) throws IOException {
        petFunctionality.addNewPet(jsonFilePath);
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindPetByIdTestData")
    public void checkFindPetById(int id) {
        petFunctionality.findPetById(id);
    }

    @Order(3)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkPartialUpdatePetTestData")
    public void checkPartialUpdatePet(int currentId, int updatedId,
                                      String updatedName, String jsonFilePath) {
        petFunctionality.partialUpdatePet(currentId, updatedId, updatedName, jsonFilePath);
    }

    @Order(4)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFullUpdatePetTestData")
    public void checkFullUpdatePet(String jsonFilePath) throws IOException {
        petFunctionality.fullUpdatePet(jsonFilePath);
    }

    @Order(5)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeletePetByIdTestData")
    public void checkDeletePetById(int id) {
        petFunctionality.deletePetById(id);
    }

    @Order(6)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindPetByStatusTestData")
    public void checkFindPetByStatus(String status) {
        petFunctionality.findPetByStatus(status);
    }

    @Order(7)
    @Test
    public void checkGetInventoryByStatus() {
        storeFunctionality.getInventoryByStatus();
    }

    @Order(8)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewOrderTestData")
    public void checkAddNewOrder(String jsonFilePath) throws IOException {
        storeFunctionality.addNewOrder(jsonFilePath);
    }

    @Order(9)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindOrderByIdTestData")
    public void checkFindOrderById(int id) {
        storeFunctionality.findOrderById(id);
    }

    @Order(10)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteOrderByIdTestData")
    public void checkDeleteOrderById(int id) {
        storeFunctionality.deleteOrderById(id);
    }

    @Order(11)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserTestData")
    public void checkCreateUser(String jsonFilePath) throws IOException {
        userFunctionality.createUser(jsonFilePath);
    }

    @Order(12)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkCreateUserListTestData")
    public void checkCreateUserList(String jsonFilePath) throws IOException {
        userFunctionality.createUserList(jsonFilePath);
    }

    @Order(13)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkGetUserByUsernameTestData")
    public void checkGetUserByUsername(String username) {
        userFunctionality.getUserByUsername(username);
    }

    @Order(14)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkLoginTestData")
    public void checkLogin(String username, String password) {
        userFunctionality.login(username, password);
    }

    @Order(15)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkUpdateUserTestData")
    public void checkUpdateUser(String username, String jsonFilePath) throws IOException {
        userFunctionality.updateUser(username, jsonFilePath);
    }

    @Order(16)
    @Test
    public void checkLogout() {
        userFunctionality.logout();
    }

    @Order(17)
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeleteUserByUsernameTestData")
    public void checkDeleteUserByUsername(String username) {
        userFunctionality.deleteUserByUsername(username);
    }
}