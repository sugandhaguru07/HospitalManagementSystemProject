package com.hospital.management.serviceClasses;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hospital.management.entityClasses.Bill;
import com.hospital.management.repository.BillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillService {
  private final BillRepository billRepo;

  public Page<Bill> getAllBills(Pageable pageable) { return billRepo.findAll(pageable); }
  public Optional<Bill> getBillById(String id) { return billRepo.findById(id); }
  public Bill createBill(Bill bill) { return billRepo.save(bill); }
  public void deleteBill(String id) { billRepo.deleteById(id); }

  public Bill updateBill(String id, Bill bill) {
    return billRepo.findById(id).map(b -> {
      b.setAmount(bill.getAmount());
      b.setDate(bill.getDate());
      b.setPatientId(bill.getPatientId());
      b.setStatus(bill.getStatus());
      return billRepo.save(b);
    }).orElseThrow(() -> new RuntimeException("Bill not found"));
  }
}
