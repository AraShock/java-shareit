package ru.practicum.shareit.booking.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.shareit.booking.Status;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class BookingDto {
    @NotNull(message = "Поле start обязательно")
    @Future(message = "Начало бронирования не может быть в прошлом")
    private LocalDateTime start;
    @Future(message = "Конец бронирования не может быть в прошлом")
    @NotNull(message = "Поле end обязательно")
    private LocalDateTime end;
    @NotNull(message = "Поле itemId обязательо")
    @Min(value = 1, message = "Некорректный itemId")
    private Long itemId;
    @Builder.Default
    private final Status status = Status.WAITING;
}
