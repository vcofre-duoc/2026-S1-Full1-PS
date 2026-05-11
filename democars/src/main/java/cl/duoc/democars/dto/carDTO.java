package cl.duoc.democars.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class carDTO {

    @NotBlank(message = "El username no puede estar vacío")
    @Size(min = 4, max = 20, message = "El username debe tener entre 4 y 20 caracteres")
    private String brand;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String model;

    @NotNull(message = "El año no puede ser nulo")
    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    @Max(value = 2030, message = "El año debe ser menor o igual a 2030")
    private Integer year;
}
