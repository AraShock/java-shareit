package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.practicum.shareit.booking.dto.BookingDto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static ru.practicum.shareit.common.Header.userIdHeader;

@RestController
@RequestMapping("/bookings")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingController {

    private final BookingClient bookingClient;

    @PostMapping
    public Mono<ResponseEntity<Object>> createBooking(@RequestHeader(userIdHeader) @Min(1) Long bookerId,
                                                      @Valid @RequestBody BookingDto bookingDto) {
        return bookingClient.createBooking(bookerId, bookingDto);
    }

    @PatchMapping("{bookingId}")
    public Mono<ResponseEntity<Object>> approveBooking(@RequestHeader(userIdHeader) @Min(1) Long ownerId,
                                                       @RequestParam String approved,
                                                       @PathVariable @Min(1) Long bookingId) {
        return bookingClient.approveBooking(ownerId, approved, bookingId);
    }

    @GetMapping("{bookingId}")
    public Mono<ResponseEntity<Object>> getBookingByIdForOwnerAndBooker(
            @PathVariable @Min(1) Long bookingId,
            @RequestHeader(userIdHeader) @Min(1) Long userId) {
        return bookingClient.getBookingByIdForOwnerAndBooker(bookingId, userId);
    }

    @GetMapping
    public Mono<ResponseEntity<Object>> getAllBookingsForUser(
            @RequestHeader(userIdHeader) @Min(1) Long userId,
            @RequestParam(defaultValue = "ALL") String state,
            @RequestParam(value = "from", defaultValue = "0") @Min(0) Integer from,
            @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(20) Integer size) {
        return bookingClient.getAllBookingsForUser(userId, state, from, size);
    }

    @GetMapping("owner")
    public Mono<ResponseEntity<Object>> getAllBookingsForItemsUser(
            @RequestHeader(userIdHeader) @Min(1) Long userId,
            @RequestParam(defaultValue = "ALL") String state,
            @RequestParam(value = "from", defaultValue = "0") @Min(0) Integer from,
            @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(20) Integer size) {
        return bookingClient.getAllBookingsForItemsUser(userId, state, from, size);
    }

    @GetMapping("/{bookingId}")
    public Mono<ResponseEntity<Object>> getBooking(@RequestHeader(userIdHeader) long userId,
                                                   @PathVariable Long bookingId) {
        return bookingClient.getBooking(userId, bookingId);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> setApprove(@RequestHeader(userIdHeader) Long ownerId,
                                             @RequestParam("approved") boolean approve,
                                             @PathVariable Long bookingId) {
        return bookingClient.setApprove(ownerId, approve, bookingId);
    }
}

