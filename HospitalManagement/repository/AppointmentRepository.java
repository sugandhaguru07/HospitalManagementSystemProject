package com.hospital.management.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hospital.management.entityClasses.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment,String>{
	  List<Appointment> findByDoctorIdAndStartLessThanAndEndGreaterThan(
		      String doctorId, LocalDateTime newEnd, LocalDateTime newStart);
}
