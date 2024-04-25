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
        return Stream.of(Arguments.of(101101L, orderData(101101L), 101101L, 0L, 0,
                "2024-04-13T16:20:28.459+0000", "placed", true));
    }

    /**
     * TC-ID9
     */
    public static Stream<Arguments> checkFindOrderByIdTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(101102L, orderData(101102L)));
    }

    /**
     * TC-ID10
     */
    public static Stream<Arguments> checkDeleteOrderByIdTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(101103L, orderData(101103L)));
    }

    /**
     * TC-ID11
     */
    public static Stream<Arguments> checkValidateJsonSchemeTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(101104L, orderData(101104L), 101104L, 101104L,
                "src/main/java/petstore/schemes/orderscheme.json"));
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