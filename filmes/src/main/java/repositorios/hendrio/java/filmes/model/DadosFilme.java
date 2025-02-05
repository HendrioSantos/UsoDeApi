package repositorios.hendrio.java.filmes.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFilme(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
//        @Enumerated(EnumType.STRING)
//        EstaNoPlano estaNoPlano,
        @JsonAlias("Title")
        String nome,
        @JsonAlias("Plot")
        String sinopse,
        @JsonAlias("Runtime")
        String duracaoMinutos,
        @JsonAlias("Genre")
        String genero,
        @JsonAlias("Released")
        String lancado,
        @JsonAlias("Director")
        String diretor,
        @JsonAlias("Actors")
        String atores,
        @JsonAlias("Writer")
        String roteirista,
        @JsonAlias("imdbRating")
        String avaliacoes
) {
}
