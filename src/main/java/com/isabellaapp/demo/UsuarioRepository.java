package com.isabellaapp.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    List<Usuario> findByEdadGreaterThan(Integer edad);
    Usuario findByEmail(String email);
}
