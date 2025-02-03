package repositorios.hendrio.java.filmes.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosVotacao(
        @JsonAlias("Source")
        String fonte,
        @JsonAlias("Value")
        String valor
) {
}
