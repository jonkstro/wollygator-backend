package br.com.wollygator.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wollygator.main.models.Carteira;
import java.util.List;


@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    List<Carteira> findByMatricula(String matricula);
}
