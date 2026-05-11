package cl.duoc.login.service;

import cl.duoc.login.dto.UserDTO;
import cl.duoc.login.model.User;
import cl.duoc.login.repository.UserRepository;
import cl.duoc.login.security.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Lombok genera constructor con los campos final
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //Agregamos ESTO
    private final JwtUtil jwtUtil;

    // Registrar usuario con contraseña encriptada
    public User registerUser(String username, String rawPassword) {
        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("El usuario '" + username + "' ya existe");
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(null, username, encodedPassword);
        return userRepository.save(user);
    }

    // Validar login
    public String login(String username, String rawPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && passwordEncoder.matches(rawPassword, userOpt.get().getPassword())) {
            return jwtUtil.generateToken(username);
        }
        return null;
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    // Listar todos los usuarios
    public List<UserDTO> getAllUsersDTO() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername()))
                .toList();
    }
}
