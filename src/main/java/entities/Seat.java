package entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import models.BookingStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    private String seatStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    private int status = BookingStatus.BOOKING_PENDING;
    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
