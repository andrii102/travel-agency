package com.epam.finaltask.controller;

import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.dto.VoucherDTO;
import com.epam.finaltask.dto.VoucherSearchParams;
import com.epam.finaltask.model.VoucherStatus;
import com.epam.finaltask.service.UserServiceImpl;
import com.epam.finaltask.service.VoucherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
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
                           @ModelAttribute VoucherSearchParams voucherSearchParams,
                           Model model, HttpServletRequest request) {

        model.addAttribute("currentUri", request.getRequestURI());

        int pageSize = 6;
        Page<VoucherDTO> vouchersPage = voucherService.findFilteredVouchers(voucherSearchParams, PageRequest.of(page, pageSize));

        model.addAttribute("vouchers", vouchersPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vouchersPage.getTotalPages());
        model.addAttribute("voucherSearchParams", voucherSearchParams);

        return "vouchers";
    }

    @GetMapping("/{voucher_id}")
    public String voucherById(@PathVariable("voucher_id") String voucher_id, Model model, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("voucher", voucherService.findById(voucher_id));
        return "voucher";
    }

    @PostMapping("{voucher_id}")
    @PreAuthorize("hasRole('USER')")
    public String order(@PathVariable("voucher_id") String voucher_id, Model model,RedirectAttributes redirectAttributes, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = userService.getUserByUsername(auth.getName());
        voucherService.order(voucher_id, userDTO.getId());
        redirectAttributes.addFlashAttribute("orderSuccess", "Voucher ordered successfully!");
        return "redirect:/vouchers/" + voucher_id;
    }

    @PostMapping("{voucher_id}/hot")
    @PreAuthorize("hasRole('MANAGER')")
    public String setHot(@PathVariable String voucher_id, @RequestParam boolean hot, RedirectAttributes redirectAttributes){
        VoucherDTO voucherDTO = new VoucherDTO();
        voucherDTO.setIsHot(hot);
        voucherService.changeHotStatus(voucher_id,voucherDTO);
        redirectAttributes.addFlashAttribute("message", "Voucher hot is set to " + hot);
        return "redirect:/vouchers/" + voucher_id;
    }

    @PostMapping("{voucher_id}/status")
    @PreAuthorize("hasRole('MANAGER')")
    public String setVoucherStatus(@PathVariable String voucher_id,
                                   @RequestParam VoucherStatus status,
                                   RedirectAttributes redirectAttributes){
        voucherService.changeVoucherStatus(voucher_id, status);
        redirectAttributes.addFlashAttribute("message", "Voucher status is set to " + status);
        return "redirect:/vouchers/" + voucher_id;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteVoucher(@PathVariable String id) {
        voucherService.delete(id);
        return "redirect:/vouchers";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable String id, Model model) {
        VoucherDTO voucher = voucherService.findById(id);
        model.addAttribute("voucher", voucher);
        return "voucher_form";
    }

    @PostMapping("/save/{voucher_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveVoucher( @PathVariable String voucher_id ,@ModelAttribute VoucherDTO voucher) {
        voucherService.update(voucher_id ,voucher);
        return "redirect:/vouchers";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String showCreateForm(Model model) {
        model.addAttribute("voucher", new VoucherDTO());
        return "voucher_form";
    }



}
