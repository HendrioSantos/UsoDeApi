package repositorios.hendrio.java.filmes.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosPontuacao (
        @JsonAlias("Ratings")
        List<DadosVotacao> votacao,
        @JsonAlias("imdbRating")
        int pontuacaoIMDB,
        @JsonAlias("imdbVotes")
        int quantidadePontuacaoIMDB,
        @JsonAlias("Metascore")
        int pontuacaoMetascore
) {
}
