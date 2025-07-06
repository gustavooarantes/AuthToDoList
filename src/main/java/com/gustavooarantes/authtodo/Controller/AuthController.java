package com.gustavooarantes.authtodo.Controller;

import com.gustavooarantes.authtodo.DTO.UserRequestDTO;
import com.gustavooarantes.authtodo.Model.AuthDTO;
import com.gustavooarantes.authtodo.Model.LoginResponseDTO;
import com.gustavooarantes.authtodo.Model.UserModel;
import com.gustavooarantes.authtodo.Service.AuthService;
import com.gustavooarantes.authtodo.Service.TokenService;
import com.gustavooarantes.authtodo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.username(), authDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserModel> signUp(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserModel user = userService.signUp(userRequestDTO);
        return ResponseEntity.ok().body(user);
    }
}
