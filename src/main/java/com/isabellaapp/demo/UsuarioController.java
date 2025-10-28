package com.isabellaapp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // ✅ ENDPOINT 1: Página de inicio
    @GetMapping("/")
    public String home() {
        return """
            <div style='text-align: center; font-family: Arial; margin-top: 50px;'>
                <h1>🚀 ¡Bienvenida Isabella y Heitan!</h1>
                <p>Tu API REST con Spring Boot está funcionando correctamente</p>
                <div style='margin-top: 30px;'>
                    <a href='/api/usuarios' style='margin: 10px; padding: 10px; background: #007bff; color: white; text-decoration: none; border-radius: 5px;'>Ver Usuarios</a>
                    <a href='/h2-console' style='margin: 10px; padding: 10px; background: #28a745; color: white; text-decoration: none; border-radius: 5px;'>Base de Datos H2</a>
                </div>
            </div>
            """;
    }
    
    // ✅ ENDPOINT 2: Obtener TODOS los usuarios
    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }
    
    // ✅ ENDPOINT 3: Obtener usuario por ID
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    // ✅ ENDPOINT 4: Crear nuevo usuario
    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    // ✅ ENDPOINT 5: Buscar usuarios por nombre
    @GetMapping("/usuarios/buscar")
    public List<Usuario> buscarUsuariosPorNombre(@RequestParam String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    // ✅ ENDPOINT 6: Health check
    @GetMapping("/health")
    public String healthCheck() {
        return """
            {
                "status": "UP",
                "application": "DemoAppIsabella",
                "timestamp": "%s"
            }
            """.formatted(java.time.LocalDateTime.now());
    }
    
    // ✅ ENDPOINT 7: Cargar datos de prueba - CORREGIDO
    @GetMapping("/cargar-datos-prueba")
    public String cargarDatosPrueba() {
        // Limpiar datos existentes primero
        usuarioRepository.deleteAll();
        
        // Crear usuarios de prueba
        usuarioRepository.save(new Usuario("Isabella", "isabella@demo.com", 25));
        usuarioRepository.save(new Usuario("Carlos", "carlos@demo.com", 30));
        usuarioRepository.save(new Usuario("Ana", "ana@demo.com", 28));
        usuarioRepository.save(new Usuario("María", "maria@demo.com", 22));
        usuarioRepository.save(new Usuario("Juan", "juan@demo.com", 35));
        
        return "¡Datos de prueba cargados correctamente! Se crearon 5 usuarios.";
    }
    
    // ✅ ENDPOINT 8: Eliminar todos los usuarios (para limpiar)
    @DeleteMapping("/usuarios")
    public String eliminarTodosUsuarios() {
        long count = usuarioRepository.count();
        usuarioRepository.deleteAll();
        return "Se eliminaron " + count + " usuarios de la base de datos";
    }
    
    // ✅ ENDPOINT 9: Contar usuarios
    @GetMapping("/usuarios/count")
    public String contarUsuarios() {
        long count = usuarioRepository.count();
        return "Total de usuarios en la base de datos: " + count;
    }
}