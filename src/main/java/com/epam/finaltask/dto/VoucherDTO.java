package com.epam.finaltask.dto;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.*;

public class VoucherDTO {

	private String id;

	@NotBlank(message = "Title is required")
	@Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
	private String title;

	@NotBlank(message = "Description is required")
	@Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
	private String description;

	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.0", message = "Price cannot be negative")
	private Double price;

	@NotBlank(message = "Tour type is required")
	private String tourType;

	@NotBlank(message = "Transfer type is required")
	private String transferType;

	@NotBlank(message = "Hotel type is required")
	private String hotelType;

	@NotBlank(message = "Status is required")
	private String status;

	@NotNull(message = "Arrival date is required")
	private LocalDate arrivalDate;

	@NotNull(message = "Eviction date is required")
	private LocalDate evictionDate;

	private UUID userId;

	private Boolean isHot;
}
