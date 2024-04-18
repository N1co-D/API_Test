package petstore.data.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;
    private long petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private boolean complete;
}