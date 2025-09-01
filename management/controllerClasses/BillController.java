package com.hospital.management.controllerClasses;

import com.hospital.management.entityClasses.Bill;
import com.hospital.management.serviceClasses.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
public class BillController {
  private final BillService billService;

  @GetMapping
  public Page<Bill> getAllBills(@PageableDefault(size=10) Pageable pageable) {
    return billService.getAllBills(pageable);
  }

  @PostMapping
  public ResponseEntity<Bill> createBill(@Valid @RequestBody Bill bill) {
    return new ResponseEntity<>(billService.createBill(bill), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public Optional<Bill> getByID(@PathVariable String id) {
    return billService.getBillById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePatientDetails(@PathVariable String id) {
    billService.deleteBill(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Bill> updatePatientDetails(@PathVariable String id, @Valid @RequestBody Bill bill) {
    return ResponseEntity.ok(billService.updateBill(id, bill));
  }
}
