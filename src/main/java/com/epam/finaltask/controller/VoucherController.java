package com.epam.finaltask.controller;

import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.dto.VoucherDTO;
import com.epam.finaltask.model.HotelType;
import com.epam.finaltask.model.TourType;
import com.epam.finaltask.model.TransferType;
import com.epam.finaltask.service.UserServiceImpl;
import com.epam.finaltask.service.VoucherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/vouchers")
public class VoucherController {

    @Autowired
    private VoucherServiceImpl voucherService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping()
    public String vouchers(@RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(required = false) TourType tourType,
                           @RequestParam(required = false) Double minPrice,
                           @RequestParam(required = false) Double maxPrice,
                           @RequestParam(required = false) TransferType transferType,
                           @RequestParam(required = false) HotelType hotelType,
                           Model model, HttpServletRequest request) {

        model.addAttribute("currentUri", request.getRequestURI());

        int pageSize = 6;
        Page<VoucherDTO> vouchersPage = voucherService.findFilteredVouchers(tourType, minPrice, maxPrice, transferType, hotelType, PageRequest.of(page, pageSize));

        model.addAttribute("vouchers", vouchersPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vouchersPage.getTotalPages());
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("transferType", transferType);
        model.addAttribute("hotelType", hotelType);

        return "vouchers";
    }

    @GetMapping("/{voucher_id}")
    public String voucherById(@PathVariable("voucher_id") String voucher_id, Model model, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("voucher", voucherService.findById(voucher_id));
        return "voucher";
    }

    @PostMapping("{voucher_id}")
    public String order(@PathVariable("voucher_id") String voucher_id, Model model,RedirectAttributes redirectAttributes, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = userService.getUserByUsername(auth.getName());
        voucherService.order(voucher_id, userDTO.getId());
        redirectAttributes.addFlashAttribute("orderSuccess", "Voucher ordered successfully!");
        return "redirect:/vouchers/" + voucher_id;
    }

}
