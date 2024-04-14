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
    static int petId;
    static int orderId;
    static String username;

    /**
     * TC-ID1
     */
    static Stream<Arguments> checkAddNewPetTestData() throws JsonProcessingException {
        petId = 314314;
        String petJson = petDataInJson(petId);
        return Stream.of(Arguments.of(petJson));
    }

    /**
     * TC-ID2
     */
    static Stream<Arguments> checkFindPetByIdTestData() throws JsonProcessingException {
        petId = 314314;
        String petJson = petDataInJson(petId);
        return Stream.of(Arguments.of(petJson));
    }

    /**
     * TC-ID3
     */
    static Stream<Arguments> checkPartialUpdatePetTestData() throws JsonProcessingException {
        petId = 314314;
        String petJson = petDataInJson(petId);
        String updatedName = "Kesha";
        String updatedStatus = "sold";
        return Stream.of(Arguments.of(petJson, updatedName, updatedStatus));
    }

    /**
     * TC-ID4
     */
    static Stream<Arguments> checkFullUpdatePetTestData() throws JsonProcessingException {
        petId = 314314;
        String petJson = petDataInJson(petId);
        String updatePetJson = updatePetDataInJson(petId);
        return Stream.of(Arguments.of(petJson, updatePetJson));
    }

    /**
     * TC-ID5
     */
    static Stream<Arguments> checkDeletePetByIdTestData() throws JsonProcessingException {
        petId = 314314;
        String petJson = petDataInJson(petId);
        return Stream.of(Arguments.of(petJson));
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
        orderId = 17031703;
        String orderJson = orderDataInJson(orderId);
        return Stream.of(Arguments.of(orderJson));
    }

    /**
     * TC-ID9
     */
    static Stream<Arguments> checkFindOrderByIdTestData() throws JsonProcessingException {
        orderId = 17031703;
        String orderJson = orderDataInJson(orderId);
        return Stream.of(Arguments.of(orderJson));
    }

    /**
     * TC-ID10
     */
    static Stream<Arguments> checkDeleteOrderByIdTestData() throws JsonProcessingException {
        orderId = 18121812;
        String orderJson = orderDataInJson(orderId);
        return Stream.of(Arguments.of(orderJson));
    }

    /**
     * TC-ID11
     */
    static Stream<Arguments> checkCreateUserTestData() throws JsonProcessingException {
        username = "MarkevichD";
        String userJson = userDataInJson(username);
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
        username = "MarkevichD";
        String userJson = userDataInJson(username);
        return Stream.of(Arguments.of(userJson));
    }

    /**
     * TC-ID14
     */
    static Stream<Arguments> checkLoginTestData() throws JsonProcessingException {
        username = "MarkevichD";
        String userJson = userDataInJson(username);
        String password = "password";
        return Stream.of(Arguments.of(userJson, password));
    }

    /**
     * TC-ID15
     */
    static Stream<Arguments> checkUpdateUserTestData() throws JsonProcessingException {
        username = "MarkevichD";
        String userJson = userDataInJson(username);
        String password = "password";
        String updateUserJson = updateUserDataInJson(username);
        return Stream.of(Arguments.of(userJson, password, updateUserJson));
    }

    /**
     * TC-ID16
     */
    static Stream<Arguments> checkLogoutTestData() throws JsonProcessingException {
        username = "MarkevichD";
        String userJson = userDataInJson(username);
        String password = "password";
        return Stream.of(Arguments.of(userJson, password));
    }

    /**
     * TC-ID17
     */
    static Stream<Arguments> checkDeleteUserByUsernameTestData() throws JsonProcessingException {
        username = "QATester4090";
        String userJson = userDataInJson(username);
        return Stream.of(Arguments.of(userJson));
    }

    private static String petDataInJson(int petId) throws JsonProcessingException {
        List<String> photoUrls = new ArrayList<>(List.of("string"));
        List<Tags> tags = List.of(new Tags(0, "string"));
        Category category = new Category(0, "string");
        Pet pet = new Pet(petId, category, "doggie", photoUrls, tags, "available");
        return turnObjectIntoJson(pet);
    }

    private static String updatePetDataInJson(int petId) throws JsonProcessingException {
        List<String> photoUrls = new ArrayList<>(List.of("string"));
        List<Tags> tags = List.of(new Tags(0, "string"));
        Category category = new Category(0, "string");
        Pet pet = new Pet(petId, category, "boris", photoUrls, tags, "sold");
        return turnObjectIntoJson(pet);
    }

    private static String orderDataInJson(int orderId) throws JsonProcessingException {
        Order order = new Order(orderId, 0, 0, "2024-04-13T16:20:28.459Z", "placed", true);
        return turnObjectIntoJson(order);
    }

    private static String userDataInJson(String username) throws JsonProcessingException {
        User user = new User(0, username, "string", "string", "email", "password", "string", 0);
        return turnObjectIntoJson(user);
    }

    private static String updateUserDataInJson(String username) throws JsonProcessingException {
        User user = new User(0, username, "Daniil", "Markevich", "email", "password", "string", 0);
        return turnObjectIntoJson(user);
    }

    private static String userListDataInJson() throws JsonProcessingException {
        List<User> users = new ArrayList<>();
        users.add(new User(101101, "Alex", "Alex", "Petrov", "email", "password", "string", 0));
        users.add(new User(202202, "Max", "Max", "Ivanov", "email", "password", "string", 0));
        return turnObjectIntoJson(users);
    }

    private static String turnObjectIntoJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }
}