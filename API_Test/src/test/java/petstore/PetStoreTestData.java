package petstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.provider.Arguments;
import petstore.data.order.Order;
import petstore.data.pet.Category;
import petstore.data.pet.Pet;
import petstore.data.pet.Tag;
import petstore.data.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PetStoreTestData {
    public final static long PET_ID = 314314L;
    public final static long ORDER_ID = 17031703L;
    public final static String USERNAME = "QAtester4090";
    public final static String UPDATED_USERNAME = "QAtester1703";
    public final static List<String> PHOTO_URLS = new ArrayList<>(List.of("url1", "url2", "url3"));
    public final static List<Tag> TAGS = List.of(new Tag(0L, "black"), new Tag(1L, "small"));
    public final static Category CATEGORY = new Category(0L, "dog");

    /**
     * TC-ID1
     */
    public static Stream<Arguments> checkAddNewPetTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(petData(), PET_ID, CATEGORY, "doggie",
                PHOTO_URLS, TAGS, "available"));
    }

    /**
     * TC-ID2
     */
    public static Stream<Arguments> checkFindPetByIdTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(petData()));
    }

    /**
     * TC-ID3
     */
    public static Stream<Arguments> checkPartialUpdatePetTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(petData(), "Kesha", "sold"));
    }

    /**
     * TC-ID4
     */
    public static Stream<Arguments> checkFullUpdatePetTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(petData(), updatePetData(), PET_ID, CATEGORY,
                "boris", PHOTO_URLS, TAGS, "sold"));
    }

    /**
     * TC-ID5
     */
    public static Stream<Arguments> checkDeletePetByIdTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(petData()));
    }

    /**
     * TC-ID8
     */
    public static Stream<Arguments> checkAddNewOrderTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(orderData(), ORDER_ID, 0L, 0, "2024-04-13T16:20:28.459+0000",
                "placed", true));
    }

    /**
     * TC-ID9
     */
    public static Stream<Arguments> checkFindOrderByIdTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(orderData()));
    }

    /**
     * TC-ID10
     */
    public static Stream<Arguments> checkDeleteOrderByIdTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(orderData()));
    }

    /**
     * TC-ID11
     */
    public static Stream<Arguments> checkCreateUserTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(userData(), USERNAME, "Vladimir", "Ivanov", "java@mail.ru",
                "password", "string", 0));
    }

    /**
     * TC-ID12
     */
    public static Stream<Arguments> checkCreateUserListTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(userListData()));
    }

    /**
     * TC-ID13
     */
    public static Stream<Arguments> checkGetUserByUsernameTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(userData()));
    }

    /**
     * TC-ID14
     */
    public static Stream<Arguments> checkLoginTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(userData(), "password"));
    }

    /**
     * TC-ID15
     */
    public static Stream<Arguments> checkUpdateUserTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(userData(), "password", updateUserData(), "QAtester1703",
                "Daniil", "Markevich", "qa@test.com", "password", "string", 0));
    }

    /**
     * TC-ID16
     */
    public static Stream<Arguments> checkLogoutTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(userData(), "password"));
    }

    /**
     * TC-ID17
     */
    public static Stream<Arguments> checkDeleteUserByUsernameTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(userData()));
    }

    private static String petData() throws JsonProcessingException {
        Pet pet = new Pet(PET_ID, CATEGORY, "doggie", PHOTO_URLS, TAGS, "available");
        return turnObjectIntoJson(pet);
    }

    private static String updatePetData() throws JsonProcessingException {
        Pet pet = new Pet(PET_ID, CATEGORY, "boris", PHOTO_URLS, TAGS, "sold");
        return turnObjectIntoJson(pet);
    }

    private static String orderData() throws JsonProcessingException {
        Order order = new Order(ORDER_ID, 0L, 0, "2024-04-13T16:20:28.459Z",
                "placed", true);
        return turnObjectIntoJson(order);
    }

    private static String userData() throws JsonProcessingException {
        User user = new User(0L, USERNAME, "Vladimir", "Ivanov", "java@mail.ru",
                "password", "string", 0);
        return turnObjectIntoJson(user);
    }

    private static String updateUserData() throws JsonProcessingException {
        User user = new User(0L, UPDATED_USERNAME, "Daniil", "Markevich", "qa@test.com",
                "password", "string", 0);
        return turnObjectIntoJson(user);
    }

    private static String userListData() throws JsonProcessingException {
        List<User> users = new ArrayList<>();
        users.add(new User(101101L, "Alexx", "Alex", "Petrov", "email",
                "password", "string", 0));
        users.add(new User(202202L, "Maxx", "Max", "Ivanov", "email",
                "password", "string", 0));
        return turnObjectIntoJson(users);
    }

    private static String turnObjectIntoJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }
}