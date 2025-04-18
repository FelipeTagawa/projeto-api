package br.com.felipe.projetoApi.service;

import br.com.felipe.projetoApi.entity.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class UserDetailsimp implements UserDetails {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;

    public UserDetailsimp(Long id, String name, String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static UserDetailsimp build(UsuarioEntity usuario){
        return new UserDetailsimp(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getEmail(),
                new ArrayList<>());

    }

    private Collection<? extends GrantedAuthority> authorities;


    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
