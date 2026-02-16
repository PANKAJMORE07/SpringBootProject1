package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BookNotFound;
import com.example.demo.model.Book;
import com.example.demo.model.Borrowing;
import com.example.demo.model.Member;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowingRepository;
import com.example.demo.repository.MemberRepository;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private MemberRepository memberRepo;

    // Borrow book
    public Borrowing borrowBook(Long bookId, Long memberId) {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));


        if(book.getAvailableCopies() <= 0)
            throw new RuntimeException("No copies available");


        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        Borrowing borrowing = new Borrowing();

        borrowing.setBook(book);
        borrowing.setMember(member);
        borrowing.setBorrowDate(LocalDate.now());

        return borrowingRepo.save(borrowing);
    }

    
    
    // Return book
    public Borrowing returnBook(Long borrowingId) {

        Borrowing borrowing = borrowingRepo.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));

        borrowing.setReturnDate(LocalDate.now());
        
        Book book = borrowing.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);
        
        return borrowingRepo.save(borrowing);
    }

    // Get all borrowings
    public List<Borrowing> getAllBorrowings() {

        List<Borrowing> list = borrowingRepo.findAll();

        if(list.isEmpty())
            throw new RuntimeException("No borrowings found");

        return list;
    }

    // Get by id
    public Borrowing getBorrowingById(Long id) {

        return borrowingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));
    }

    // Delete
    public Borrowing deleteBorrowing(Long id) {

        Borrowing borrowing = borrowingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));

        borrowingRepo.delete(borrowing);
        return borrowing;
    }
    
    // Delete all
    public void deleteAllBorrowing(Long id) {
        borrowingRepo.deleteAll();
       
    }
    
    public List<Borrowing> getBorrowedByMember(Long memberId) {

        List<Borrowing> list = borrowingRepo.findByMemberId(memberId);

        if(list.isEmpty())
            throw new RuntimeException("No borrowings found for member");

        return list;
    }
}
