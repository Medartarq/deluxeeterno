package com.deluxeterno.repository;

import com.deluxeterno.domain.Inquiry;
import com.deluxeterno.domain.InquiryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findAllByOrderByCreatedAtDesc();

    long countByStatus(InquiryStatus status);
}
