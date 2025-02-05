package repositorios.hendrio.java.filmes.services;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
