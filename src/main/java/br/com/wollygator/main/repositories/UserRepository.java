package br.com.wollygator.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wollygator.main.models.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Métodos adicionais:
    // Buscar por email, ignorando case sensitive
    List<User> findByEmailIgnoreCase(String email);
    // Buscar pela matrícula da carteira do usuário
    List<User> findByCarteiraMatricula(String matricula);
}
