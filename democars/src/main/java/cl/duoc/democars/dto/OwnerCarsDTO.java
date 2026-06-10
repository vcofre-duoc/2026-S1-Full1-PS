package cl.duoc.democars.dto;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
public class OwnerCarsDTO {
    private Long ownerId;
    private String ownerName;
    private List<carDTO> cars;  
}
