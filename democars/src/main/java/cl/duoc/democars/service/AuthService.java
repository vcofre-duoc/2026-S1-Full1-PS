package cl.duoc.democars.service;

import cl.duoc.democars.dto.ApiResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.ParameterizedTypeReference;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient webClient;

    public ApiResponse<String> validateToken(String token) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/validate")
                        .queryParam("token", token)
                        .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {})
                    .block();
        } catch (Exception e) {
            return new ApiResponse<>(500, "Error al validar token: " + e.getMessage(), null);
        }
    }
}