package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ServiceResponse serviceResponse = usuarioService.encontrarPorEmail(email);

        if(!serviceResponse.getStatus()){
            throw new UsernameNotFoundException("Not found");
        }

        Usuario usuario = (Usuario) serviceResponse.getData();

        return new User(usuario.getEmail(), usuario.getPassword(), getGrantedAuthority(usuario));
    }

    private Collection<GrantedAuthority> getGrantedAuthority (Usuario usuario){
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));

        if(usuario.getRole().getNombre().equalsIgnoreCase("admin")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }
}
