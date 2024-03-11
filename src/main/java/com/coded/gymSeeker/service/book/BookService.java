package com.coded.gymSeeker.service.book;

import com.coded.gymSeeker.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    void SaveBook(Long gymId);

    BookEntity getBookDetails(Long bookId);

    List<BookEntity> getAllBookDetails();
}
