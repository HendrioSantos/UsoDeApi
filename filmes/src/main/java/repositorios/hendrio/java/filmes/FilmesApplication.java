package repositorios.hendrio.java.filmes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repositorios.hendrio.java.filmes.main.Principal;
import repositorios.hendrio.java.filmes.repository.IFilmesRepository;

@SpringBootApplication
public class FilmesApplication implements CommandLineRunner {

	@Autowired
	private IFilmesRepository IFilmesRepository;

	public static void main(String[] args) {
		SpringApplication.run(FilmesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(IFilmesRepository);
		principal.exibeMenu();
	}
}
