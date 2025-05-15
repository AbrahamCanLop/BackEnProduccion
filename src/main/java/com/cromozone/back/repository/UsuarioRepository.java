package com.cromozone.back.repository;

import com.cromozone.back.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

       // Buscar por email (útil para login o verificación de registro)
       Optional<Usuario> findByEmail(String email);

       // Buscar por nombre de usuario (útil para búsquedas en la app)
       Optional<Usuario> findByNombreUsuario(String nombreUsuario);
   
       // Verificar existencia por email
       boolean existsByEmail(String email);
   
       // Verificar existencia por nombre de usuario
       boolean existsByNombreUsuario(String nombreUsuario);
}