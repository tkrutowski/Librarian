package pl.sda.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.library.domain.model.Movie;
import pl.sda.library.domain.model.SuperHero;
import pl.sda.library.infrastructure.jpa.MovieRepository;
import pl.sda.library.infrastructure.jpa.SuperHeroRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@SpringBootApplication
//@EntityScan(basePackages = {"pl.sda.library.model"})
public class LibraryApplication {

    public static void main(String[] args) {
        // Create our entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuperHeroes");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        MovieRepository movieRepository = new MovieRepository(entityManager);
        SuperHeroRepository superHeroRepository = new SuperHeroRepository(entityManager);

        // Create some superheroes
        SuperHero ironman = new SuperHero("Iron Man");
        SuperHero thor = new SuperHero("Thor");

        // Create some movies
        Movie avengers = new Movie("The Avengers");
        avengers.addSuperHero(ironman);
        avengers.addSuperHero(thor);

        Movie infinityWar = new Movie("Avengers: Infinity War");
        infinityWar.addSuperHero(ironman);
        infinityWar.addSuperHero(thor);

        // Save the movies
        movieRepository.save(avengers);
        movieRepository.save(infinityWar);

        // Find all movies
        System.out.println("MOVIES:");
        movieRepository.findAll().forEach(movie -> {
            System.out.println("Movie: [" + movie.getId() + "] - " + movie.getTitle());
            movie.getSuperHeroes().forEach(System.out::println);
        });

        // Find all superheroes
        System.out.println("\nsuperheroes:");
        superHeroRepository.findAll().forEach(superHero -> {
            System.out.println(superHero);
            superHero.getMovies().forEach(System.out::println);
        });

        // Delete a movie and verify that its superheroes are not deleted
        movieRepository.deleteById(1);
        System.out.println("\nMOVIES (AFTER DELETE):");
        movieRepository.findAll().forEach(movie -> {
            System.out.println("Movie: [" + movie.getId() + "] - " + movie.getTitle());
            movie.getSuperHeroes().forEach(System.out::println);
        });
        System.out.println("\nsuperheroes (AFTER DELETE):");
        superHeroRepository.findAll().forEach(superHero -> {
            System.out.println(superHero);
            superHero.getMovies().forEach(System.out::println);
        });


//        // DEBUG, dump our tables
//        entityManager.unwrap(Session.class).doWork(connection ->
//                JdbcUtils.dumpTables(connection, "MOVIE", "SUPER_HERO", "SUPERHERO_MOVIES"));

        // Close the entity manager and associated factory
        entityManager.close();
        entityManagerFactory.close();
        SpringApplication.run(LibraryApplication.class, args);
    }

}
