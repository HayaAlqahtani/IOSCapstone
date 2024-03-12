package com.coded.gymSeeker.service.book;

import com.coded.gymSeeker.entity.BookEntity;
import com.coded.gymSeeker.entity.GymEntity;
import com.coded.gymSeeker.entity.UserEntity;
import com.coded.gymSeeker.reposatriy.BookRepository;
import com.coded.gymSeeker.reposatriy.GymRepository;
import com.coded.gymSeeker.reposatriy.UserRepository;
import com.coded.gymSeeker.service.auth.UserDetailUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepositoryRepository;
    private final UserRepository userRepository;
    private final GymRepository gymRepository;
    public BookServiceImpl(BookRepository bookRepositoryRepository, UserRepository userRepository, GymRepository gymRepository) {
        this.bookRepositoryRepository = bookRepositoryRepository;
        this.userRepository = userRepository;
        this.gymRepository = gymRepository;
    }

    @Override
    public void SaveBook(Long gymId) {
        BookEntity bookEntity=new BookEntity();
        UserEntity userEntity= userRepository.findById(UserDetailUtil.userDetails().getId())
                .orElseThrow();
        GymEntity gymEntity = gymRepository.findById(gymId)
                .orElseThrow();
        bookEntity.setUser(userEntity);
        bookEntity.setGym(gymEntity);
        bookEntity.setBookingDate(LocalDate.now());
        bookRepositoryRepository.save(bookEntity);
    }

    @Override
    public BookEntity getBookDetails(Long bookId) {
        return bookRepositoryRepository.findById(bookId).orElseThrow();
    }

    @Override
    public List<BookEntity> getAllBookDetails() {
        return bookRepositoryRepository.findAll();
    }

}
