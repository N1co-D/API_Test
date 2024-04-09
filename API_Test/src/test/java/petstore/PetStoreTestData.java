package petstore;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class PetStoreTestData {
    /**
     * TC-ID1
     */
    static Stream<Arguments> checkAddNewPetTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    /**
     * TC-ID2
     */
    static Stream<Arguments> checkFindPetByIdTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        int id = 1;
        return Stream.of(Arguments.of(jsonFilePath, id));
    }

    /**
     * TC-ID3
     */
    static Stream<Arguments> checkPartialUpdatePetTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        int id = 1;
        String updatedName = "Kesha";
        String updatedStatus = "sold";
        return Stream.of(Arguments.of(jsonFilePath, id, updatedName, updatedStatus));
    }

    /**
     * TC-ID4
     */
    static Stream<Arguments> checkFullUpdatePetTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        String jsonFilePathToUpdate = "src/test/java/petstore/resources/updatepet.json";
        return Stream.of(Arguments.of(jsonFilePath, jsonFilePathToUpdate));
    }

    /**
     * TC-ID5
     */
    static Stream<Arguments> checkDeletePetByIdTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        int id = 1;
        return Stream.of(Arguments.of(jsonFilePath, id));
    }

    /**
     * TC-ID6
     */
    static Stream<Arguments> checkFindPetByStatusTestData() {
        String status = "sold";
        return Stream.of(Arguments.of(status));
    }

    /**
     * TC-ID8
     */
    static Stream<Arguments> checkAddNewOrderTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/order.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    /**
     * TC-ID9
     */
    static Stream<Arguments> checkFindOrderByIdTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/order.json";
        int id = 17031703;
        return Stream.of(Arguments.of(jsonFilePath, id));
    }

    /**
     * TC-ID10
     */
    static Stream<Arguments> checkDeleteOrderByIdTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/order.json";
        int id = 17031703;
        return Stream.of(Arguments.of(jsonFilePath, id));
    }

    /**
     * TC-ID11
     */
    static Stream<Arguments> checkCreateUserTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    /**
     * TC-ID12
     */
    static Stream<Arguments> checkCreateUserListTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/userlist.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    /**
     * TC-ID13
     */
    static Stream<Arguments> checkGetUserByUsernameTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        return Stream.of(Arguments.of(jsonFilePath, username));
    }

    /**
     * TC-ID14
     */
    static Stream<Arguments> checkLoginTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        String password = "string";
        return Stream.of(Arguments.of(jsonFilePath, username, password));
    }

    /**
     * TC-ID15
     */
    static Stream<Arguments> checkUpdateUserTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        String jsonFilePathToUpdate = "src/test/java/petstore/resources/updateduser.json";
        return Stream.of(Arguments.of(jsonFilePath, username, jsonFilePathToUpdate));
    }

    /**
     * TC-ID16
     */
    static Stream<Arguments> checkLogoutTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        String password = "string";
        return Stream.of(Arguments.of(jsonFilePath, username, password));
    }

    /**
     * TC-ID17
     */
    static Stream<Arguments> checkDeleteUserByUsernameTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/user.json";
        String username = "string";
        return Stream.of(Arguments.of(jsonFilePath, username));
    }
}