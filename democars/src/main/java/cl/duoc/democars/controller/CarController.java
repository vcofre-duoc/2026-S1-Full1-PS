package cl.duoc.democars.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.democars.dto.ApiResponse;
import cl.duoc.democars.dto.carDTO;
import cl.duoc.democars.service.AuthService;
import cl.duoc.democars.service.CarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;
    private final AuthService authService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<carDTO>>> getAllCars(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        ApiResponse<String> validationResponse = authService.validateToken(token);

        if (validationResponse == null || validationResponse.getCode() != 200) {
            ApiResponse<List<carDTO>> errorResponse =
                    new ApiResponse<>(401, "Token inválido", null);
            return ResponseEntity.status(401).body(errorResponse);
        }

        List<carDTO> cars = carService.getAllCarsDTO();
        ApiResponse<List<carDTO>> response =
                new ApiResponse<>(200, "Listado de autos", cars);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list2")
        public ResponseEntity<ApiResponse<String>> getAllCars2(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        //ApiResponse<String> validationResponse = authService.validateToken(token);

        ApiResponse<String> response =
                new ApiResponse<>(200, "Listado de autos", token);
        return ResponseEntity.ok(response);
    }
}
