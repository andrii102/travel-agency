package com.epam.finaltask.controller;

import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.dto.VoucherDTO;
import com.epam.finaltask.model.HotelType;
import com.epam.finaltask.model.TourType;
import com.epam.finaltask.model.TransferType;
import com.epam.finaltask.model.VoucherStatus;
import com.epam.finaltask.service.UserServiceImpl;
import com.epam.finaltask.service.VoucherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VoucherControllerTest {

    @InjectMocks
    private VoucherController voucherController;

    @Mock
    private VoucherServiceImpl voucherService;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVouchers() {
        // Arrange
        VoucherDTO voucherDTO = new VoucherDTO();
        Page<VoucherDTO> page = new PageImpl<>(Collections.singletonList(voucherDTO));
        when(voucherService.findFilteredVouchers(any(), any(), any(), any(), any(), any())).thenReturn(page);
        when(request.getRequestURI()).thenReturn("/vouchers");

        // Act
        String viewName = voucherController.vouchers(0, null, null, null, null, null, model, request);

        // Assert
        assertEquals("vouchers", viewName);
        verify(model).addAttribute("currentUri", "/vouchers");
        verify(model).addAttribute("vouchers", page);
        verify(model).addAttribute("currentPage", 0);
        verify(model).addAttribute("totalPages", page.getTotalPages());
    }

    @Test
    public void testVoucherById() {
        // Arrange
        String voucherId = "1";
        VoucherDTO voucherDTO = new VoucherDTO();
        when(voucherService.findById(voucherId)).thenReturn(voucherDTO);
        when(request.getRequestURI()).thenReturn("/vouchers/1");

        // Act
        String viewName = voucherController.voucherById(voucherId, model, request);

        // Assert
        assertEquals("voucher", viewName);
        verify(model).addAttribute("currentUri", "/vouchers/1");
        verify(model).addAttribute("voucher", voucherDTO);
    }

    @Test
    void testOrder() {
        // Arrange
        String voucherId = "1";
        String username = "testUser ";
        String userId = "userId";
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);

        // Mock the authentication and user service
        when(request.getRequestURI()).thenReturn("/vouchers/1");
        when(userService.getUserByUsername(username)).thenReturn(userDTO);
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn(username);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Act
        String viewName = voucherController.order(voucherId, model, redirectAttributes, request);

        // Assert
        assertEquals("redirect:/vouchers/1", viewName);
        verify(model).addAttribute("currentUri", "/vouchers/1");
        verify(voucherService).order(voucherId, userId);
        verify(redirectAttributes).addFlashAttribute("orderSuccess", "Voucher ordered successfully!");
    }

    @Test
    void testSetHot() {
        // Arrange
        String voucherId = "1";
        boolean hot = true;
        VoucherDTO voucherDTO = new VoucherDTO();
        voucherDTO.setIsHot(hot);

        // Act
        String viewName = voucherController.setHot(voucherId, hot, redirectAttributes);

        // Assert
        assertEquals("redirect:/vouchers/1", viewName);
        verify(voucherService).changeHotStatus(voucherId, voucherDTO);
        verify(redirectAttributes).addFlashAttribute("message", "Voucher hot is set to " + hot);
    }

    @Test
    void testSetVoucherStatus() {
        // Arrange
        String voucherId = "1";
        VoucherStatus status = VoucherStatus.PAID; // Replace with actual status enum

        // Act
        String viewName = voucherController.setVoucherStatus(voucherId, status, redirectAttributes);

        // Assert
        assertEquals("redirect:/vouchers/1", viewName);
        verify(voucherService).changeVoucherStatus(voucherId, status);
        verify(redirectAttributes).addFlashAttribute("message", "Voucher status is set to " + status);
    }

    @Test
    void testDeleteVoucher() {
        // Arrange
        String voucherId = "1";

        // Act
        String viewName = voucherController.deleteVoucher(voucherId);

        // Assert
        assertEquals("redirect:/vouchers", viewName);
        verify(voucherService).delete(voucherId);
    }

    @Test
    void testShowEditForm() {
        // Arrange
        String voucherId = "1";
        VoucherDTO voucherDTO = new VoucherDTO(); // Create a sample VoucherDTO
        when(voucherService.findById(voucherId)).thenReturn(voucherDTO);

        // Act
        String viewName = voucherController.showEditForm(voucherId, model);

        // Assert
        assertEquals("voucher_form", viewName);
        verify(model).addAttribute("voucher", voucherDTO);
    }

    @Test
    void testSaveVoucher() {
        // Arrange
        String voucherId = "1";
        VoucherDTO voucherDTO = new VoucherDTO(); // Create a sample VoucherDTO

        // Act
        String viewName = voucherController.saveVoucher(voucherId, voucherDTO);

        // Assert
        assertEquals("redirect:/vouchers", viewName);
        verify(voucherService).update(voucherId, voucherDTO);
    }

    @Test
    void testShowCreateForm() {
        // Act
        String viewName = voucherController.showCreateForm(model);

        // Assert
        assertEquals("voucher_form", viewName);
        verify(model).addAttribute("voucher", new VoucherDTO());
    }



}