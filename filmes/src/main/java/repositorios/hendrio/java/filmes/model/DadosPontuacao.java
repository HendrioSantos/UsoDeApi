package repositorios.hendrio.java.filmes.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DadosPontuacao(
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
