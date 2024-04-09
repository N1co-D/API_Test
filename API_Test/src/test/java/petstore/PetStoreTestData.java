package petstore;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class PetStoreTestData {
    static Stream<Arguments> checkAddNewPetTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    static Stream<Arguments> checkFindPetByIdTestData() {
        int id = 1;
        return Stream.of(Arguments.of(id));
    }

    static Stream<Arguments> checkPartialUpdatePetTestData() {
        int id = 1;
        String updatedName = "Kesha";
        String updatedStatus = "sold";
        return Stream.of(Arguments.of(id, updatedName, updatedStatus));
    }

    static Stream<Arguments> checkFullUpdatePetTestData() {
        String jsonFilePath = "src/test/java/petstore/resources/pet.json";
        return Stream.of(Arguments.of(jsonFilePath));
    }

    static Stream<Arguments> checkDeletePetByIdTestData() {
        int id = 1;
        return Stream.of(Arguments.of(id));
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
        int id = 18121812;
        return Stream.of(Arguments.of(id));
    }

    static Stream<Arguments> checkDeleteOrderByIdTestData() {
        int id = 17031703;
        return Stream.of(Arguments.of(id));
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
        String username = "string";
        return Stream.of(Arguments.of(username));
    }

    static Stream<Arguments> checkLoginTestData() {
        String username = "string";
        String password = "string";
        return Stream.of(Arguments.of(username, password));
    }

    static Stream<Arguments> checkUpdateUserTestData() {
        String username = "string";
        String jsonFilePath = "src/test/java/petstore/resources/updateduser.json";
        return Stream.of(Arguments.of(username, jsonFilePath));
    }

    static Stream<Arguments> checkDeleteUserByUsernameTestData() {
        String username = "string";
        return Stream.of(Arguments.of(username));
    }
}