package repositorios.hendrio.java.filmes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "filmes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filmes{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Enumerated(EnumType.STRING)
        private EstaNoPlano estaNoPlano;
        private String nome;
        private String sinopse;
        private int duracaoMinutos;
        private String genero;
        private String lancado;
        private String diretor;
        private String atores;
        private String roteirista;
        private Long avaliacoes;

        public Filmes() {
        }

        public Filmes(DadosFilme dados) {
                this.id = getId();
                this.estaNoPlano = EstaNoPlano.verificarPlano(String.valueOf(getEstaNoPlano()));
                this.nome = getNome();
                this.sinopse = getSinopse();
                this.duracaoMinutos = getDuracaoMinutos();
                this.genero = getGenero();
                this.lancado = getLancado();
                this.diretor = getDiretor();
                this.atores = getAtores();
                this.roteirista = getRoteirista();
                this.avaliacoes = getAvaliacoes();
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public EstaNoPlano getEstaNoPlano() {
                return estaNoPlano;
        }

        public void setEstaNoPlano(EstaNoPlano estaNoPlano) {
                this.estaNoPlano = estaNoPlano;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getSinopse() {
                return sinopse;
        }

        public void setSinopse(String sinopse) {
                this.sinopse = sinopse;
        }

        public int getDuracaoMinutos() {
                return duracaoMinutos;
        }

        public void setDuracaoMinutos(int duracaoMinutos) {
                this.duracaoMinutos = duracaoMinutos;
        }

        public String getGenero() {
                return genero;
        }

        public void setGenero(String genero) {
                this.genero = genero;
        }

        public String getLancado() {
                return lancado;
        }

        public void setLancado(String lancado) {
                this.lancado = lancado;
        }

        public String getDiretor() {
                return diretor;
        }

        public void setDiretor(String diretor) {
                this.diretor = diretor;
        }

        public String getAtores() {
                return atores;
        }

        public void setAtores(String atores) {
                this.atores = atores;
        }

        public String getRoteirista() {
                return roteirista;
        }

        public void setRoteirista(String roteirista) {
                this.roteirista = roteirista;
        }

        public Long getAvaliacoes() {
                return avaliacoes;
        }

        public void setAvaliacoes(Long avaliacoes) {
                this.avaliacoes = avaliacoes;
        }

        @Override
        public String toString() {
                return "Filmes{" +
                        "id=" + id +
                        ", estaNoPlano=" + estaNoPlano +
                        ", nome='" + nome + '\'' +
                        ", sinopse='" + sinopse + '\'' +
                        ", duracaoMinutos=" + duracaoMinutos +
                        ", genero='" + genero + '\'' +
                        ", lancado='" + lancado + '\'' +
                        ", diretor='" + diretor + '\'' +
                        ", atores='" + atores + '\'' +
                        ", roteirista='" + roteirista + '\'' +
                        ", avaliacoes=" + avaliacoes +
                        '}';
        }
}