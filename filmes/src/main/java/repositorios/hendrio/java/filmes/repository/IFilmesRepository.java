package repositorios.hendrio.java.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repositorios.hendrio.java.filmes.model.Filmes;

import java.util.List;

@Repository
public interface IFilmesRepository extends JpaRepository<Filmes, Long> {
    List<Filmes> findByDiretorIgnoreCase(String nomeDiretor);
    List<Filmes> findByAtoresContainingIgnoreCase(String nomeAtor);
    List<Filmes> findByRoteiristaContainingIgnoreCase(String nomeRoteirista);
//    List<Filmes> findTop5OrderByAvaliacoes();
//    List<Filmes> findTop10OrderByAvaliacoes();
}
