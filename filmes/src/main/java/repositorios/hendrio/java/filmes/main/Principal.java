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
        while (opcao != 0){
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
                    buscarFilmePorDiretor();
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("...Saindo Da Aplicação...");
                    break;
                default:
                    System.out.println("Opção Inválida. Tente Alguma Das Opções Do Menu");
            }
        }
    }

    public void mostrarMenu(){
        String menu =
                """
                1 - Verificar Se o Filme Existe no Banco de Dados
                2 - Buscar Filmes
                2 - Buscar Filmes por Diretor
                3 - Buscar FIlmes por Roteirista
                4 - Listar Filmes já Buscadas por Você
                5 - Buscar Filmes por Titulo
                6 - Buscar Filmes por Ator
                7 - Top 5 Seus Filmes Buscados
                8 - Top 10 Seus Filmes Buscados
                """;
        System.out.println(menu);
    }

    public DadosFilme verificarFilme(){
        System.out.println("Digite um filme para a busca: ");
        var filmeBuscado = input.nextLine().replace(" ", "+");
        System.out.println(filmeBuscado);
        var json = DOMINIO + API_KEY + filmeBuscado;
        System.out.println(json);
        var dadosFilme = consumoApi.obterDados(json, Filmes.class);
        DadosFilme dadosJsonFilme = converteDados.obterDados(dadosFilme, DadosFilme.class);
        System.out.println(dadosFilme);
        return dadosJsonFilme;
    }

    public void buscarFilme(){
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
                    .forEach(f -> System.out.println(f.getNome() + f.getAvaliacoes()+ f.getDuracaoMinutos() + f.getGenero()));
        }
    }

    private void buscarFilmePorAtores(){
        System.out.println("Qual ator deseja? ");
        var nomeAtor = input.nextLine();
        List<Filmes> atores = iFilmesRepository.findByAtoresIgnoreCase(nomeAtor);

    }

}
