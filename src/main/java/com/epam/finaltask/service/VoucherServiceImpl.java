package com.epam.finaltask.service;

import com.epam.finaltask.dto.VoucherDTO;
import com.epam.finaltask.exception.EntityNotFoundException;
import com.epam.finaltask.exception.StatusCodes;
import com.epam.finaltask.mapper.VoucherMapper;
import com.epam.finaltask.model.*;
import com.epam.finaltask.repository.UserRepository;
import com.epam.finaltask.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//import static com.epam.finaltask.exception.StatusCodes.ENTITY_NOT_FOUND;

@Service
public class VoucherServiceImpl implements VoucherService{
    @Autowired
    VoucherRepository voucherRepo;
    @Autowired
    VoucherMapper voucherMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public VoucherDTO create(VoucherDTO voucherDTO) {
        Voucher voucher = voucherMapper.toVoucher(voucherDTO);
        voucherRepo.save(voucher);
        return voucherMapper.toVoucherDTO(voucher);
    }

    @Override
    public VoucherDTO order(String id, String userId) {
        Optional<User> user =  userRepository.findById(UUID.fromString(userId));
        Optional<Voucher> voucher = voucherRepo.findById(UUID.fromString(id));
        if(user.isPresent() && voucher.isPresent()){
            user.get().addVoucher(voucher.get());
            return voucherMapper.toVoucherDTO(voucher.get());
        }
        throw new EntityNotFoundException(StatusCodes.ENTITY_NOT_FOUND.name(), "Error occured");
    }

    @Override
    public VoucherDTO update(String id, VoucherDTO voucherDTO) {
        Optional<Voucher> voucher = voucherRepo.findById(UUID.fromString(id));
        if(voucher.isPresent()){
            Voucher newVoucher = voucherMapper.toVoucher(voucherDTO);
            voucherRepo.save(newVoucher);
            return voucherMapper.toVoucherDTO(newVoucher);
        }
        throw new EntityNotFoundException(StatusCodes.ENTITY_NOT_FOUND.name(), "Not found");
    }

    @Override
    public void delete(String voucherId) {
        Optional<Voucher> voucher = voucherRepo.findById(UUID.fromString(voucherId));
        if(voucher.isPresent()){
            voucherRepo.deleteById(UUID.fromString(voucherId));
            return;
        }
        throw new EntityNotFoundException(StatusCodes.ENTITY_NOT_FOUND.name(), "Not found");
    }

    @Override
    public VoucherDTO changeHotStatus(String id, VoucherDTO voucherDTO) {
        Optional<Voucher> voucher = voucherRepo.findById(UUID.fromString(id));
        if(voucher.isPresent()){
            Voucher newVoucher = voucherMapper.toVoucher(voucherDTO);
            voucherRepo.save(newVoucher);
            return voucherMapper.toVoucherDTO(newVoucher);
        }
        throw new EntityNotFoundException(StatusCodes.ENTITY_NOT_FOUND.name(), "Not found");
    }

    @Override
    public List<VoucherDTO> findAllByUserId(String userId) {
        List<Voucher> vouchers = voucherRepo.findAllByUserId(UUID.fromString(userId));
        List<VoucherDTO> vouchersDTO = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            vouchersDTO.add(voucherMapper.toVoucherDTO(voucher));
        }
        return vouchersDTO;
    }

    @Override
    public List<VoucherDTO> findAllByTourType(TourType tourType) {
        List<Voucher> vouchers = voucherRepo.findAllByTourType(tourType);
        List<VoucherDTO> vouchersDTO = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            vouchersDTO.add(voucherMapper.toVoucherDTO(voucher));
        }
        return vouchersDTO;
    }

    @Override
    public List<VoucherDTO> findAllByPrice(Double price) {
        List<Voucher> vouchers = voucherRepo.findAllByPrice(price);
        List<VoucherDTO> vouchersDTO = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            vouchersDTO.add(voucherMapper.toVoucherDTO(voucher));
        }
        return vouchersDTO;
    }

    @Override
    public List<VoucherDTO> findAllByHotelType(HotelType hotelType) {
        List<Voucher> vouchers = voucherRepo.findAllByHotelType(hotelType);
        List<VoucherDTO> vouchersDTO = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            vouchersDTO.add(voucherMapper.toVoucherDTO(voucher));
        }
        return vouchersDTO;
    }

    @Override
    public List<VoucherDTO> findAll() {
        List<Voucher> vouchers = voucherRepo.findAll();
        List<VoucherDTO> vouchersDTO = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            vouchersDTO.add(voucherMapper.toVoucherDTO(voucher));
        }
        return vouchersDTO;
    }


}
