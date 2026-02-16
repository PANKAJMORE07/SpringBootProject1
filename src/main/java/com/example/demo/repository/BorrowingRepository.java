package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
	
	List<Borrowing> findByMemberId(Long memberId);
}

