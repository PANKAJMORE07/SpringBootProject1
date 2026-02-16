package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class Borrowing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;
	
	
	private LocalDate borrowDate;
	
	private LocalDate returnDate;

	public long getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}

	public Member getMember() {
		return member;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
}
