package com.epam.finaltask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Voucher {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)", insertable = false, updatable = false)
    private UUID id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price must be a positive number")
    private Double price;

    @NotNull(message = "Tour type is required")
    @Enumerated(EnumType.STRING)
    private TourType tourType;

    @NotNull(message = "Transfer type is required")
    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    @NotNull(message = "Hotel type is required")
    @Enumerated(EnumType.STRING)
    private HotelType hotelType;

    @NotNull(message = "Voucher status is required")
    @Enumerated(EnumType.STRING)
    private VoucherStatus status;

    @NotNull(message = "Arrival date is required")
    @FutureOrPresent(message = "Arrival date cannot be in the past")
    private LocalDate arrivalDate;

    @NotNull(message = "Eviction date is required")
    @Future(message = "Eviction date must be in the future")
    private LocalDate evictionDate;

    @ManyToOne
    @JoinColumn(name = "user_id") //id
    private User user;

    private boolean isHot;
}
