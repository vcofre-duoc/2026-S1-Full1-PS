package cl.duoc.democars.controller;

import cl.duoc.democars.dto.ApiResponse;
import cl.duoc.democars.dto.OwnerCarsDTO;
import cl.duoc.democars.service.OwnerService;
import cl.duoc.democars.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Owners Controller", description = "Endpoints para gestión de dueños y sus autos.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final AuthService authService;

    @GetMapping("/{id}/cars")
    @Operation(summary = "Listar autos de un dueño", description = "Permite obtener todos los autos asociados a un dueño por su ID.")
    public ResponseEntity<ApiResponse<OwnerCarsDTO>> getOwnerCars(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {

        // Validar token
        String token = authHeader.replace("Bearer ", "");
        ApiResponse<String> validationResponse = authService.validateToken(token);

        if (validationResponse == null || validationResponse.getCode() != 200) {
            ApiResponse<OwnerCarsDTO> errorResponse =
                    new ApiResponse<>(401, "Token inválido", null);
            return ResponseEntity.status(401).body(errorResponse);
        }

        // Obtener autos del dueño
        OwnerCarsDTO ownerCars = ownerService.getOwnerCars(id);
        ApiResponse<OwnerCarsDTO> response =
                new ApiResponse<>(200, "Autos del dueño", ownerCars);

        return ResponseEntity.ok(response);
    }
}
