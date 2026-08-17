package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peneiras_app.dto.UserCreateDTO;
import peneiras_app.entity.User;
import peneiras_app.service.UserService;

@RestController
@RequestMapping("auth/register")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserCreateDTO dto) {
        User user = userService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
