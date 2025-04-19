package br.com.felipe.projetoApi.controller;

import br.com.felipe.projetoApi.dto.AuthenticationDTO;
import br.com.felipe.projetoApi.dto.UsuarioDTO;
import br.com.felipe.projetoApi.service.AuthService;
import br.com.felipe.projetoApi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDTO){
        return ResponseEntity.ok(authService.login(authDTO));
    }

    @PostMapping(value = "/novoUsuario")
    public void inserirNovoUsuario(@RequestBody UsuarioDTO novoUsuario){
        usuarioService.inserirNovoUsuario(novoUsuario);
    }

    @GetMapping(value = "/verificarCadastro/{uuid}")
    public String verificarCadastro(@PathVariable("uuid") String uuid){
        return usuarioService.verificarCadastro(uuid);
    }
}
