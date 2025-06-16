package com.epam.finaltask.dto;

import com.epam.finaltask.model.HotelType;
import com.epam.finaltask.model.TourType;
import com.epam.finaltask.model.TransferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherSearchParams {
    private Double minPrice;
    private Double maxPrice;
    private TourType tourType;
    private TransferType transferType;
    private HotelType hotelType;
}
