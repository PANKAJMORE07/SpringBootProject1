package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.addBookDTO;
import com.example.demo.dto.responceBookDTO;
import com.example.demo.exception.BookNotFound;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;


@Service
public class BookService {
	
	@Autowired
	BookRepository BookRepo;
	
	
	//Logger
	private Logger logger = LoggerFactory.getLogger(BookService.class);
	
	//without DTO
//	public Book saveBook(Book book) {
//		return BookRepo.save(book);
//	}
	
	
	//with DTO
	public responceBookDTO saveBook(addBookDTO bookDTO) {
		
		//System.out.println("Book Is Added");
		logger.info("Book Is Added");
		 
		Book book = MapToEntity(bookDTO);
		
		Book savedBook = BookRepo.save(book);
		
		return MapToDTO(savedBook);
    }
	
	
	public List<responceBookDTO> getAllBooks(){
		
		List<Book> books =  BookRepo.findAll();
		if(books.isEmpty()) {
			throw new BookNotFound("Book Table Is Empty");
		}
		
		return books.stream().map(this :: MapToDTO).toList();
	}
	
	
	public responceBookDTO getBookbyId(Long id) {
		Book book =  BookRepo.findById(id)
				.orElseThrow(() -> new BookNotFound("Book Not Found with id: " + id));
		
		return MapToDTO(book);
	}
	
	
	
	public responceBookDTO updateBook(Long id, addBookDTO newBookDTO) {
		
		Book newBook = MapToEntity(newBookDTO);
		
		Book oldBook = BookRepo.findById(id)
					   .orElseThrow(() -> new BookNotFound("Book Not Found with id: " + id));
		
		oldBook.setAuthor(newBook.getAuthor());
		oldBook.setTitle(newBook.getTitle());
		oldBook.setAvailableCopies(newBook.getAvailableCopies());
		oldBook.setIsbn(newBook.getIsbn());
		
		Book savedBook = BookRepo.save(oldBook);
		
		return MapToDTO(savedBook);
	}
	
	
	public responceBookDTO deleteBook(Long id) {
		
		Book book = BookRepo.findById(id)
				.orElseThrow(() -> new BookNotFound("Book Not Found with id: " + id));
		
		BookRepo.delete(book);
		
		return MapToDTO(book);
	}
	
	
	public void deleteAll() {
		List<Book> books =  BookRepo.findAll();
		if(books.isEmpty()) {
			throw new BookNotFound("Book Table Is Empty");
		}
		
		BookRepo.deleteAll();
	}
	
	
	//DTO Mapping
	
	private Book MapToEntity(addBookDTO dto) {
		Book book = new Book();
		
		book.setTitle(dto.getTitle());
		book.setAuthor(dto.getAuthor());
		book.setIsbn(dto.getIsbn());
		book.setAvailableCopies(dto.getAvailableCopies());
		
		return book;
	}
	
	
	private responceBookDTO MapToDTO(Book book) {
		responceBookDTO dto = new responceBookDTO();
		
		dto.setId(book.getId());
		dto.setTitle(book.getTitle());
		dto.setAuthor(book.getAuthor());
		dto.setIsbn(book.getIsbn());
		dto.setAvailableCopies(book.getAvailableCopies());
		
		return dto;
	}
}
