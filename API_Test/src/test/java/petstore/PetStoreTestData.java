package petstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.provider.Arguments;
import petstore.data.order.Order;
import petstore.data.pet.Category;
import petstore.data.pet.Pet;
import petstore.data.pet.Tags;
import petstore.data.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PetStoreTestData {
    /**
     * TC-ID1
     */
    static Stream<Arguments> checkAddNewPetTestData() throws JsonProcessingException {
        String petJson = petDataInJson();
        return Stream.of(Arguments.of(petJson));
    }

    /**
     * TC-ID2
     */
    static Stream<Arguments> checkFindPetByIdTestData() throws JsonProcessingException {
        String petJson = petDataInJson();
        int id = 1;
        return Stream.of(Arguments.of(petJson, id));
    }

    /**
     * TC-ID3
     */
    static Stream<Arguments> checkPartialUpdatePetTestData() throws JsonProcessingException {
        String petJson = petDataInJson();
        int id = 1;
        String updatedName = "Kesha";
        String updatedStatus = "sold";
        return Stream.of(Arguments.of(petJson, id, updatedName, updatedStatus));
    }

    /**
     * TC-ID4
     */
    static Stream<Arguments> checkFullUpdatePetTestData() throws JsonProcessingException {
        String petJson = petDataInJson();
        String updatePetJson = updatePetDataInJson();
        return Stream.of(Arguments.of(petJson, updatePetJson));
    }

    /**
     * TC-ID5
     */
    static Stream<Arguments> checkDeletePetByIdTestData() throws JsonProcessingException {
        String petJson = petDataInJson();
        int id = 1;
        return Stream.of(Arguments.of(petJson, id));
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
    static Stream<Arguments> checkAddNewOrderTestData() throws JsonProcessingException {
        String orderJson = orderDataInJson();
        return Stream.of(Arguments.of(orderJson));
    }

    /**
     * TC-ID9
     */
    static Stream<Arguments> checkFindOrderByIdTestData() throws JsonProcessingException {
        String orderJson = orderDataInJson();
        int id = 17031703;
        return Stream.of(Arguments.of(orderJson, id));
    }

    /**
     * TC-ID10
     */
    static Stream<Arguments> checkDeleteOrderByIdTestData() throws JsonProcessingException {
        String orderJson = orderDataInJson();
        int id = 17031703;
        return Stream.of(Arguments.of(orderJson, id));
    }

    /**
     * TC-ID11
     */
    static Stream<Arguments> checkCreateUserTestData() throws JsonProcessingException {
        String userJson = userDataInJson();
        return Stream.of(Arguments.of(userJson));
    }

    /**
     * TC-ID12
     */
    static Stream<Arguments> checkCreateUserListTestData() throws JsonProcessingException {
        String userListJson = userListDataInJson();
        return Stream.of(Arguments.of(userListJson));
    }

    /**
     * TC-ID13
     */
    static Stream<Arguments> checkGetUserByUsernameTestData() throws JsonProcessingException {
        String userJson = userDataInJson();
        String username = "string";
        return Stream.of(Arguments.of(userJson, username));
    }

    /**
     * TC-ID14
     */
    static Stream<Arguments> checkLoginTestData() throws JsonProcessingException {
        String userJson = userDataInJson();
        String username = "string";
        String password = "string";
        return Stream.of(Arguments.of(userJson, username, password));
    }

    /**
     * TC-ID15
     */
    static Stream<Arguments> checkUpdateUserTestData() throws JsonProcessingException {
        String userJson = userDataInJson();
        String username = "string";
        String updateUserJson = updateUserDataInJson();
        return Stream.of(Arguments.of(userJson, username, updateUserJson));
    }

    /**
     * TC-ID16
     */
    static Stream<Arguments> checkLogoutTestData() throws JsonProcessingException {
        String userJson = userDataInJson();
        String username = "string";
        String password = "string";
        return Stream.of(Arguments.of(userJson, username, password));
    }

    /**
     * TC-ID17
     */
    static Stream<Arguments> checkDeleteUserByUsernameTestData() throws JsonProcessingException {
        String userJson = userDataInJson();
        String username = "string";
        return Stream.of(Arguments.of(userJson, username));
    }

    private static String petDataInJson() throws JsonProcessingException {
        List<String> photoUrls = new ArrayList<>(List.of("string"));
        List<Tags> tags = List.of(new Tags(0, "string"));
        Category category = new Category(0, "string");
        Pet pet = new Pet(1, category, "doggie", photoUrls, tags, "available");
        return turnObjectIntoJson(pet);
    }

    private static String updatePetDataInJson() throws JsonProcessingException {
        List<String> photoUrls = new ArrayList<>(List.of("string"));
        List<Tags> tags = List.of(new Tags(0, "string"));
        Category category = new Category(0, "string");
        Pet pet = new Pet(1, category, "boris", photoUrls, tags, "sold");
        return turnObjectIntoJson(pet);
    }

    private static String orderDataInJson() throws JsonProcessingException {
        Order order = new Order(17031703, 0, 0, "2024-04-13T16:20:28.459Z", "placed", true);
        return turnObjectIntoJson(order);
    }

    private static String userDataInJson() throws JsonProcessingException {
        User user = new User(0, "string", "string", "string", "string", "string", "string", 0);
        return turnObjectIntoJson(user);
    }

    private static String userListDataInJson() throws JsonProcessingException {
        List<User> users = new ArrayList<>();
        users.add(new User(0, "Alex", "Alex", "Petrov", "string", "string", "string", 0));
        users.add(new User(1, "Max", "Max", "Ivanov", "string", "string", "string", 0));
        return turnObjectIntoJson(users);
    }

    private static String updateUserDataInJson() throws JsonProcessingException {
        User user = new User(0, "Daniil", "Daniil", "Markevich", "string", "string", "string", 0);
        return turnObjectIntoJson(user);
    }

    private static String turnObjectIntoJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }
}