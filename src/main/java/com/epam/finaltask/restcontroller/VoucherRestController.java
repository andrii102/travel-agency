package com.epam.finaltask.restcontroller;

import com.epam.finaltask.dto.VoucherDTO;
import com.epam.finaltask.service.VoucherService;
import com.epam.finaltask.service.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherRestController {
    @Autowired
    VoucherService voucherService;

    @GetMapping
    public ResponseEntity<Map<String, List<VoucherDTO>>> getVouchers() {
        List<VoucherDTO> vouchersDTO = voucherService.findAll();
        Map<String, List<VoucherDTO>> response = new HashMap<>();
        response.put("results", vouchersDTO != null ? vouchersDTO : new ArrayList<>());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<Map<String, List<VoucherDTO>>> getVouchersByUser(@PathVariable String user_id) {
        List<VoucherDTO> vouchersDTO = voucherService.findAllByUserId(user_id);
        Map<String, List<VoucherDTO>> response = new HashMap<>();
        response.put("results", vouchersDTO != null ? vouchersDTO : new ArrayList<>());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createVoucher(@RequestBody VoucherDTO voucherDTO) {
        voucherService.create(voucherDTO);
        Map<String, String> response = new HashMap<>();
        response.put("statusCode", "OK");
        response.put("statusMessage", "Voucher is successfully created");
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/{voucher_id}")
    public ResponseEntity<Map<String, String>> updateVoucher(@PathVariable String voucher_id, @RequestBody VoucherDTO voucherDTO) {
        voucherService.update(voucher_id, voucherDTO);
        Map<String, String> response = new HashMap<>();
        response.put("statusCode", "OK");
        response.put("statusMessage", "Voucher is successfully updated");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{voucher_id}")
    public ResponseEntity<Map<String, String>> deleteVoucher(@PathVariable String voucher_id) {
        voucherService.delete(voucher_id);
        Map<String, String> response = new HashMap<>();
        response.put("statusCode", "OK");
        response.put("statusMessage",  String.format("Voucher with Id %s has been deleted", voucher_id));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{voucher_id}/status")
    public ResponseEntity<Map<String, String>>changeVoucher(@PathVariable String voucher_id, @RequestBody VoucherDTO voucherDTO){
        voucherService.changeHotStatus(voucher_id, voucherDTO);
        Map<String, String> response = new HashMap<>();
        response.put("statusCode", "OK");
        response.put("statusMessage", "Voucher status is successfully changed");
        return ResponseEntity.ok(response);
    }

}
