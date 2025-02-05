package repositorios.hendrio.java.filmes.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVotacao (
        @JsonAlias("Source")
        String fonte,
        @JsonAlias("Value")
        String valor
) {
}
