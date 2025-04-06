package com.epam.finaltask.mapper;

import com.epam.finaltask.dto.VoucherDTO;
import com.epam.finaltask.model.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoucherMapper {
    Voucher toVoucher(VoucherDTO voucherDTO);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "hot", target = "isHot")
    VoucherDTO toVoucherDTO(Voucher voucher);

}
