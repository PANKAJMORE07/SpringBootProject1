package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AddBookDTO;
import com.example.demo.dto.ResponceBookDTO;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService BookServ;
	
	@GetMapping("/all")
	public List<ResponceBookDTO> getAllBooks() {
		return BookServ.getAllBooks();
	}
	
	
	@GetMapping("/one/{id}")
	public ResponceBookDTO getBookById(@PathVariable Long id) {
		return BookServ.getBookbyId(id);
	}

	
	@PostMapping("/add")
	public ResponceBookDTO addBook(@Valid @RequestBody AddBookDTO book) {
		return BookServ.saveBook(book);
	}
	
	
	@PutMapping("/update")
	public ResponceBookDTO updateBook(@RequestParam Long id, @Valid @RequestBody AddBookDTO book) {
		return BookServ.updateBook(id, book);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponceBookDTO deleteBook(@PathVariable Long id) {
		return BookServ.deleteBook(id);
	}
	
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllbooks() {
		
		BookServ.deleteAll();
		
		return ResponseEntity
				.ok("Books deleted Successfully");
	}
	
	@GetMapping("/search/title")
	public List<ResponceBookDTO> searchByTitle(@RequestParam String title) {
		
	        return BookServ.searchByTitle(title);

	}
	
	@GetMapping("/search/author")
	public List<ResponceBookDTO> searchByAuthor(@RequestParam String author) {

	    return BookServ.searchByAuthor(author);

	}
	
}
