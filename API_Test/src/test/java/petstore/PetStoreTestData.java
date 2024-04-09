package petstore;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class PetStoreTestData {
    static Stream<Arguments> checkAddNewPetTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    static Stream<Arguments> checkFindPetByIdTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        int id = 1;
        return Stream.of(Arguments.of(jsonFilePath, id));
    }

    static Stream<Arguments> checkPartialUpdatePetTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        int id = 1;
        String updatedName = "Kesha";
        String updatedStatus = "sold";
        return Stream.of(Arguments.of(jsonFilePath, id, updatedName, updatedStatus));
    }

    static Stream<Arguments> checkFullUpdatePetTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        String jsonFilePathToUpdate = "src/test/java/petstore/resources/updatepet.json";
        return Stream.of(Arguments.of(jsonFilePath, jsonFilePathToUpdate));
    }

    static Stream<Arguments> checkDeletePetByIdTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        int id = 1;
        return Stream.of(Arguments.of(jsonFilePath, id));
    }

    static Stream<Arguments> checkFindPetByStatusTestData() {
        String status = "sold";
        return Stream.of(Arguments.of(status));
    }

    static Stream<Arguments> checkAddNewOrderTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/order.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    static Stream<Arguments> checkFindOrderByIdTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/order.json";
        int id = 17031703;
        return Stream.of(Arguments.of(jsonFilePath, id));
    }

    static Stream<Arguments> checkDeleteOrderByIdTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/order.json";
        int id = 17031703;
        return Stream.of(Arguments.of(jsonFilePath, id));
    }

    static Stream<Arguments> checkCreateUserTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    static Stream<Arguments> checkCreateUserListTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/userlist.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    static Stream<Arguments> checkGetUserByUsernameTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        return Stream.of(Arguments.of(jsonFilePath, username));
    }

    static Stream<Arguments> checkLoginTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        String password = "string";
        return Stream.of(Arguments.of(jsonFilePath, username, password));
    }

    static Stream<Arguments> checkUpdateUserTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        String jsonFilePathToUpdate = "src/test/java/petstore/resources/updateduser.json";
        return Stream.of(Arguments.of(jsonFilePath, username, jsonFilePathToUpdate));
    }

    static Stream<Arguments> checkLogoutTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        String password = "string";
        return Stream.of(Arguments.of(jsonFilePath, username, password));
    }

    static Stream<Arguments> checkDeleteUserByUsernameTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        return Stream.of(Arguments.of(jsonFilePath, username));
    }
}