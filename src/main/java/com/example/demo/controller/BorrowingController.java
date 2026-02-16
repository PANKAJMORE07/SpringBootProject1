package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Borrowing;
import com.example.demo.service.BorrowingService;

@RestController
@RequestMapping("/borrowing")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingServ;

    @PostMapping("/borrow")
    public Borrowing borrowBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        return borrowingServ.borrowBook(bookId, memberId);

    }

    @PutMapping("/return/{borrowingId}")
    public Borrowing returnBook(@PathVariable Long borrowingId) {
       return borrowingServ.returnBook(borrowingId);

    }


    @GetMapping("/all")
    public List<Borrowing> getAllBorrowings() {

        return borrowingServ.getAllBorrowings();

    }

    @GetMapping("/{id}")
    public Borrowing getBorrowingById(@PathVariable Long id) {
        return borrowingServ.getBorrowingById(id);
    }

    @GetMapping("/member/{memberId}")
    public List<Borrowing>getBorrowingsByMember(@PathVariable Long memberId) {
        	return borrowingServ.getBorrowedByMember(memberId);
    }


   
    @DeleteMapping("/delete/{id}")
    public Borrowing deleteBorrowing(@PathVariable Long id) {

    	return borrowingServ.deleteBorrowing(id);
    }

}
