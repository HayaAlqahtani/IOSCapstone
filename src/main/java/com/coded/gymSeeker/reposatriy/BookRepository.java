package com.coded.gymSeeker.reposatriy;

import com.coded.gymSeeker.entity.BookEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface BookRepository  extends JpaRepository<BookEntity,Long> {

//void setGym(GymInformation gym);
//
//List<Booking> findByUserId(Long userId);
//
// void setBookingDate(Date date);
//
// void setUser(User user);
//
// String getId();
}
