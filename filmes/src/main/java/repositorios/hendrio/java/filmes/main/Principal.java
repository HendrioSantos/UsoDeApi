package repositorios.hendrio.java.filmes.main;

import org.springframework.beans.factory.annotation.Autowired;
import repositorios.hendrio.java.filmes.model.DadosFilme;
import repositorios.hendrio.java.filmes.model.Filmes;
import repositorios.hendrio.java.filmes.repository.IFilmesRepository;
import repositorios.hendrio.java.filmes.services.ConsumoApi;
import repositorios.hendrio.java.filmes.services.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados converteDados = new ConverteDados();
    @Autowired
    private final IFilmesRepository iFilmesRepository;

    private final String DOMINIO = "http://www.omdbapi.com/";
    private final String API_KEY = "?apikey=6585022c&t=";
    private final Scanner input = new Scanner(System.in);

    public Principal(IFilmesRepository iFilmesRepository) {
        this.iFilmesRepository = iFilmesRepository;
    }

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            mostrarMenu();
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao){
                case 1:
                    verificarFilme();
                    break;
                case 2:
                    buscarFilme();
                    break;
                case 3:
                    buscarFilmePorAtores();
                    break;
                case 4:
                    buscarFilmePorDiretor();
                    break;
                case 5:
                    buscarFilmePorRoteirista();
                    break;
                case 6:
                    listaTodosOsFilmesBuscados();
                    break;
                case 7:
                    buscarFilmePorTipoPlano();
                    break;
//                case 8:
//                    top5SeusFilmes();
//                    break;
//                case 9:
//                    top10SeusFilmes();
//                    break;
                case 0:
                    System.out.println("...Saindo Da Aplicação...");
                    break;
                default:
                    System.out.println("Opção Inválida. Tente Alguma Das Opções Do Menu");
            }
        }
    }

    public void mostrarMenu() {
        String menu =
                """
                \n1 - Verificar Se o Filme Existe no Banco de Dados
                2 - Buscar Filmes
                3 - Buscar Filmes por Ator
                4 - Buscar Filmes por Diretor
                5 - Buscar Filmes por Roteirista
                6 - Listar Filmes já Buscadas por Você
                7 - Buscar Filmes por Ator
                8 - Top 5 Seus Filmes Buscados
                9 - Top 10 Seus Filmes Buscados
                
                0 - Para Sair da Aplicação;
                """;
        System.out.println(menu);
    }

    public DadosFilme verificarFilme() {
        System.out.println("Digite um filme para a busca: ");
        var filmeBuscado = input.nextLine().replace(" ", "+");
        System.out.println(filmeBuscado);
//        var tipoDePlano = input.nextLine();
//        EstaNoPlano estaNoPlano = EstaNoPlano.verificarPlano(tipoDePlano);
        var json = DOMINIO + API_KEY + filmeBuscado;
        System.out.println(json);
        var dadosFilme = consumoApi.obterDados(json);
        DadosFilme dadosJsonFilme = converteDados.obterDados(dadosFilme, DadosFilme.class);
        System.out.println(dadosFilme);
        return dadosJsonFilme;
    }

    public void buscarFilme() {
        DadosFilme dados = verificarFilme();
        Filmes filmes = new Filmes(dados);
        iFilmesRepository.save(filmes);
        System.out.println(dados);
    }

    private void buscarFilmePorDiretor() {
        System.out.println("Digite o nome do Diretor: ");
        var nomeDiretor = input.nextLine();
        List<Filmes> filmes = iFilmesRepository.findByDiretorIgnoreCase(nomeDiretor);
        if (filmes.isEmpty()){
            System.out.println("Nenhum filme encontrado para o diretor digitado: " + nomeDiretor);
        } else {
            System.out.println("Filmes encontrados no seu catalogo com o seguinte diretor: " + nomeDiretor);
            filmes.stream()
                    .sorted(Comparator.comparing(Filmes::getAvaliacoes).
                            thenComparing(Filmes::getDuracaoMinutos).
                            thenComparing(Filmes::getGenero))
                    .forEach(f -> System.out.println( nomeDiretor +
                            " trabalhou em: " + f.getNome()
                            + " Quantidade de Avaliações: " +
                            f.getAvaliacoes() + " Com duração de: " +
                            f.getDuracaoMinutos() + " Genêro do Filme: " +
                            f.getGenero()));}
    }

    private void buscarFilmePorAtores(){
        System.out.println("Qual ator deseja? ");
        var nomeAtor = input.nextLine();
        List<Filmes> atores = iFilmesRepository.findByAtoresContainingIgnoreCase(nomeAtor);
        System.out.println("Filmes encontrados: " + atores.size());
        if (atores.isEmpty()) {
            System.out.println("Não há um filme com o ator que tu desejas...");
        } else {
            atores.stream()
                    .sorted(Comparator.comparing(Filmes::getNome).
                            thenComparing(Filmes::getAvaliacoes).
                            thenComparing(Filmes::getDuracaoMinutos).
                            thenComparing(Filmes::getGenero))
                    .forEach(f -> System.out.println( nomeAtor +
                            " trabalhou em: " + f.getNome()
                            + " Quantidade de Avaliações: " +
                            f.getAvaliacoes() + " Com duração de: " +
                            f.getDuracaoMinutos() + " Genêro do Filme: " +
                            f.getGenero()));
        }
    }

    private void buscarFilmePorRoteirista() {
        System.out.println("Nome do Roteirista para busca: ");
        var nomeRoteirista = input.nextLine();
        List<Filmes> roteirista = iFilmesRepository.findByRoteiristaContainingIgnoreCase(nomeRoteirista);
        System.out.println("Roteiristas encontrados: " + roteirista.size());
        roteirista.stream()
                .sorted(Comparator.comparing(Filmes::getNome).
                        thenComparing(Filmes::getAvaliacoes)
                        .thenComparing(Filmes::getDuracaoMinutos)
                        .thenComparing(Filmes::getGenero))
                .forEach(f -> System.out.println( nomeRoteirista + " trabalhou em: " + f.getNome()
                        + " Quantidade de Avaliações: " +
                        f.getAvaliacoes() + " Com duração de: " +
                        f.getDuracaoMinutos() + " Genêro do Filme: " +
                        f.getGenero()));
    }

    private void listaTodosOsFilmesBuscados() {
        List<Filmes> todosOsFilmes = iFilmesRepository.findAll();
        todosOsFilmes.forEach(System.out::println);
    }

    private void buscarFilmePorTipoPlano() {

    }

//    private void top5SeusFilmes() {
//        Principal principal = new Principal();
//        List<Filmes> top5Filmes = iFilmesRepository.findTop5OrderByAvaliacoes(principal.listaTodosOsFilmesBuscados());
//        top5Filmes.stream()
//                .forEach(top -> System.out.println(top.getNome() + " avaliação: " + top.getAvaliacoes()));
//    }
//
//    private void top10SeusFilmes() {
//        List<Filmes> top10Filmes = iFilmesRepository.findTop10OrderByAvaliacoes();
//        top10Filmes.stream()
//                .forEach(top -> System.out.println(top.getNome() + " avaliação: " + top.getAvaliacoes()));
//    }
}
