package services;

import entities.Movie;
import entities.Seat;
import models.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.IMovieRepository;
import repositories.ISeatRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService {
    final ISeatRepository repository;

    @Autowired
    public SeatService(ISeatRepository repository) {
        this.repository = repository;
    }

    public Seat insert(Seat seat) {
        seat.setCreatedDate(LocalDateTime.now());
        return repository.save(seat);
    }

    public Seat update(Seat seat) {
        Optional<Seat> optionalSeat = repository.findById(seat.getId());

        if (optionalSeat.isPresent()) {
            return repository.save(seat);
        }
        return null;
    }

    public Seat delete(Long id) {
        Optional<Seat> optionalSeat = repository.findById(id);

        if (optionalSeat.isPresent()) {
            Seat foundSeat = optionalSeat.get();

            if (foundSeat.getStatus() == BookingStatus.BOOKING_PENDING) {
                foundSeat.setStatus(BookingStatus.BOOKING_CANCEL);
                return repository.save(foundSeat);
            }
        }
        return null;
    }

    public List<Seat>  findAll() {
        return repository.findAll();
    }

    // GOING OR PENDING
    public List<Seat> findAllSeatActive() {
        return repository.findAll().stream().filter(seat ->
                seat.isActive() == true).collect(Collectors.toList());
    }

    public List<Seat> findAllSeatCancel() {
        return repository.findAll().stream().filter(seat ->
                seat.isCancel() == true).collect(Collectors.toList());
    }

    public List<Seat> findAllSeatFinish() {
        return repository.findAll().stream().filter(seat ->
                seat.isFinish() == true).collect(Collectors.toList());
    }

    public Optional<Seat> getSeatById(Long id) {
        return repository.findById(id);
    }
}
