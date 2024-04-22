package petstore.userservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.provider.Arguments;
import petstore.data.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserServiceTestData {

    /**
     * TC-ID11
     */
    public static Stream<Arguments> checkCreateUserTestData() throws JsonProcessingException {
        String username = "QAtester101";
        return Stream.of(Arguments.of(username, userData(username), username, "Vladimir", "Ivanov",
                "java@mail.ru", "password", "string", 0));
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
        String username = "QAtester102";
        return Stream.of(Arguments.of(username, userData(username)));
    }

    /**
     * TC-ID14
     */
    public static Stream<Arguments> checkLoginTestData() throws JsonProcessingException {
        String username = "QAtester103";
        return Stream.of(Arguments.of(username, userData(username), "password"));
    }

    /**
     * TC-ID15
     */
    public static Stream<Arguments> checkUpdateUserTestData() throws JsonProcessingException {
        String username = "QAtester104";
        String updatedUsername = "QAtester105";
        return Stream.of(Arguments.of(username, userData(username), "password", updatedUsername,
                updateUserData(updatedUsername), updatedUsername, "QAtester105", "Daniil", "Markevich", "qa@test.com",
                "password", "string", 0));
    }

    /**
     * TC-ID16
     */
    public static Stream<Arguments> checkLogoutTestData() throws JsonProcessingException {
        String username = "QAtester106";
        return Stream.of(Arguments.of(username, userData(username), "password"));
    }

    /**
     * TC-ID17
     */
    public static Stream<Arguments> checkDeleteUserByUsernameTestData() throws JsonProcessingException {
        String username = "QAtester107";
        return Stream.of(Arguments.of(username, userData(username)));
    }

    private static String userData(String username) throws JsonProcessingException {
        User user = new User(0L, username, "Vladimir", "Ivanov", "java@mail.ru",
                "password", "string", 0);
        return turnObjectIntoJson(user);
    }

    private static String updateUserData(String updatedUsername) throws JsonProcessingException {
        User user = new User(0L, updatedUsername, "Daniil", "Markevich", "qa@test.com",
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