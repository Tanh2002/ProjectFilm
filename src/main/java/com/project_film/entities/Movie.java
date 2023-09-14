package entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import models.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String description;
    private String director;
    private String actors;
    private String duration;
    private String imageUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    private int status = BookingStatus.BOOKING_PENDING;
    @OneToMany(mappedBy = "movie")
    private List<Showtime> showtimes;

    public boolean isActive() {
        return status == BookingStatus.BOOKING_PENDING || status == BookingStatus.BOOKING_GOING;
    }

    public boolean isCancel() {
        return status == BookingStatus.BOOKING_CANCEL;
    }

    public boolean isFinish() {
        return status == BookingStatus.BOOKING_FINISH;
    }
}
