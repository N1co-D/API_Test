package petstore.storeservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.provider.Arguments;
import petstore.data.order.Order;

import java.util.stream.Stream;

public class StoreServiceTestData {

    /**
     * TC-ID8
     */
    public static Stream<Arguments> checkAddNewOrderTestData() throws JsonProcessingException {
        long orderId = 101101L;
        return Stream.of(Arguments.of(orderId, orderData(orderId), orderId, 0L, 0,
                "2024-04-13T16:20:28.459+0000", "placed", true));
    }

    /**
     * TC-ID9
     */
    public static Stream<Arguments> checkFindOrderByIdTestData() throws JsonProcessingException {
        long orderId = 101102L;
        return Stream.of(Arguments.of(orderId, orderData(orderId)));
    }

    /**
     * TC-ID10
     */
    public static Stream<Arguments> checkDeleteOrderByIdTestData() throws JsonProcessingException {
        long orderId = 101103L;
        return Stream.of(Arguments.of(orderId, orderData(orderId)));
    }

    private static String orderData(long orderId) throws JsonProcessingException {
        Order order = new Order(orderId, 0L, 0, "2024-04-13T16:20:28.459Z",
                "placed", true);
        return turnObjectIntoJson(order);
    }

    private static String turnObjectIntoJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }
}