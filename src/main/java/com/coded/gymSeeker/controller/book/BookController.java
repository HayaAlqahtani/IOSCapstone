package com.coded.gymSeeker.controller.book;


import com.coded.gymSeeker.entity.BookEntity;
import com.coded.gymSeeker.service.book.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> bookGym(@RequestParam Long gymId) {
        bookService.SaveBook(gymId);
        return ResponseEntity.ok("Booking create successfully");
    }

    @GetMapping("/get-book-details")
    public ResponseEntity<BookEntity> getBookDetail(@RequestParam Long bookId){
        return ResponseEntity.ok(bookService.getBookDetails(bookId));
    }

    @GetMapping("/get-all-book-details")
    public ResponseEntity<List<BookEntity>> getAllBookDetails(){
        return ResponseEntity.ok(bookService.getAllBookDetails());
    }
}