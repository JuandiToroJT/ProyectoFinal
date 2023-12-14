package juanalcuadradosas.soc.Repository;

import juanalcuadradosas.soc.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    @Query("SELECT u FROM Usuario u WHERE u.isAdmin <> 1")
    List<Usuario> findAllUsuariosNoAdmin();
}
