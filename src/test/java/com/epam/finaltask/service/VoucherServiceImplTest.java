package com.epam.finaltask.service;

import com.epam.finaltask.dto.VoucherDTO;
import com.epam.finaltask.exception.EntityNotFoundException;
import com.epam.finaltask.mapper.VoucherMapper;
import com.epam.finaltask.model.User;
import com.epam.finaltask.model.Voucher;
import com.epam.finaltask.repository.UserRepository;
import com.epam.finaltask.repository.VoucherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VoucherServiceImplTest {

    @InjectMocks
    private VoucherServiceImpl voucherService;

    @Mock
    private VoucherRepository voucherRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private VoucherMapper voucherMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldSaveVoucher() {
        VoucherDTO dto = new VoucherDTO();
        Voucher voucher = new Voucher();
        when(voucherMapper.toVoucher(dto)).thenReturn(voucher);
        when(voucherMapper.toVoucherDTO(voucher)).thenReturn(dto);

        VoucherDTO result = voucherService.create(dto);

        verify(voucherRepo).save(voucher);
        assertEquals(dto, result);
    }

    @Test
    void order_shouldAssignVoucherToUser() {
        UUID userId = UUID.randomUUID();
        UUID voucherId = UUID.randomUUID();
        User user = new User();
        Voucher voucher = new Voucher();

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(voucherRepo.findById(voucherId)).thenReturn(Optional.of(voucher));
        when(voucherMapper.toVoucherDTO(voucher)).thenReturn(new VoucherDTO());

        VoucherDTO result = voucherService.order(voucherId.toString(), userId.toString());

        assertEquals(user, voucher.getUser());
        verify(voucherRepo).save(voucher);
        assertNotNull(result);
    }

    @Test
    void order_shouldThrowIfNotFound() {
        assertThrows(EntityNotFoundException.class, () ->
                voucherService.order(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }

    @Test
    void update_shouldUpdateVoucher() {
        UUID id = UUID.randomUUID();
        VoucherDTO dto = new VoucherDTO();
        Voucher voucher = new Voucher();

        when(voucherRepo.findById(id)).thenReturn(Optional.of(new Voucher()));
        when(voucherMapper.toVoucher(dto)).thenReturn(voucher);
        when(voucherMapper.toVoucherDTO(voucher)).thenReturn(dto);

        VoucherDTO result = voucherService.update(id.toString(), dto);

        verify(voucherRepo).save(voucher);
        assertEquals(dto, result);
    }

    @Test
    void delete_shouldRemoveVoucher() {
        UUID id = UUID.randomUUID();
        when(voucherRepo.findById(id)).thenReturn(Optional.of(new Voucher()));

        voucherService.delete(id.toString());

        verify(voucherRepo).deleteById(id);
    }

    @Test
    void findById_shouldReturnVoucher() {
        UUID id = UUID.randomUUID();
        Voucher voucher = new Voucher();
        VoucherDTO dto = new VoucherDTO();

        when(voucherRepo.findById(id)).thenReturn(Optional.of(voucher));
        when(voucherMapper.toVoucherDTO(voucher)).thenReturn(dto);

        VoucherDTO result = voucherService.findById(id.toString());
        assertEquals(dto, result);
    }

    @Test
    void findAll_shouldReturnMappedList() {
        Voucher voucher = new Voucher();
        VoucherDTO dto = new VoucherDTO();

        when(voucherRepo.findAll()).thenReturn(List.of(voucher));
        when(voucherMapper.toVoucherDTO(voucher)).thenReturn(dto);

        List<VoucherDTO> result = voucherService.findAll();
        assertEquals(1, result.size());
    }
}
