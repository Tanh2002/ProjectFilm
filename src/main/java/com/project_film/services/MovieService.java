package services;

import entities.Movie;
import models.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.IMovieRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    final IMovieRepository repository;

    @Autowired
    public MovieService(IMovieRepository repository) {
        this.repository = repository;
    }

    public Movie insert(Movie movie) {
        movie.setCreatedDate(LocalDateTime.now());
        return repository.save(movie);
    }

    public Movie update(Movie movie) {
        Optional<Movie> optionalMovie = repository.findById(movie.getId());

        if (optionalMovie.isPresent()) {
            return repository.save(movie);
        }
        return null;
    }

    public Movie delete(Long id) {
        Optional<Movie> optionalMovie = repository.findById(id);

        if (optionalMovie.isPresent()) {
            Movie foundMovie = optionalMovie.get();

            if (foundMovie.getStatus() == BookingStatus.BOOKING_PENDING) {
                foundMovie.setStatus(BookingStatus.BOOKING_CANCEL);
                return repository.save(foundMovie);
            }
        }
        return null;
    }

    public List<Movie>  findAll() {
        return repository.findAll();
    }

    // GOING OR PENDING
    public List<Movie> findAllMovieActive() {
        return repository.findAll().stream().filter(movie ->
                movie.isActive() == true).collect(Collectors.toList());
    }

    public List<Movie> findAllMovieCancel() {
        return repository.findAll().stream().filter(movie ->
                movie.isCancel() == true).collect(Collectors.toList());
    }

    public List<Movie> findAllMovieFinish() {
        return repository.findAll().stream().filter(movie ->
                movie.isFinish() == true).collect(Collectors.toList());
    }

    public Optional<Movie> getMovieById(Long id) {
        return repository.findById(id);
    }
}
