package com.codemind.tourgenius.controller;

import com.codemind.tourgenius.dto.request.BookingCreateRequest;
import com.codemind.tourgenius.dto.response.BookingSearchResponse;
import com.codemind.tourgenius.model.Booking;
import com.codemind.tourgenius.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("${app.endpoint.bookingCreate}")
    public String saveBooking(@RequestBody BookingCreateRequest request){
        Booking booking =  new Booking();
        booking.setId(request.getId());
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckInTime(request.getCheckInTime());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setCheckOutTime(request.getCheckOutTime());
        booking.setUserId(request.getUserId());
        bookingService.create(booking);
        return "booking Added";
    }


    @PutMapping("${app.endpoint.bookingUpdate}")
    public String updateRoom(@PathVariable("id") String bookingId, @RequestBody BookingCreateRequest request){
        Booking booking= bookingService.findById(Long.valueOf(bookingId));
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckInTime(request.getCheckInTime());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setCheckOutTime(request.getCheckOutTime());
        booking.setUserId(request.getUserId());
        bookingService.update(booking);
        return "booking data Updated";
    }

    @GetMapping("${app.endpoint.bookingSearch}")
    public List<BookingSearchResponse> search() {
        List<BookingSearchResponse> bookingSearchResponseList =  new ArrayList<>();
        List<Booking> bookings = bookingService.findAll();
        for(Booking booking :bookings){
            BookingSearchResponse response = new BookingSearchResponse();
            response.setId(booking.getId());
            response.setCheckInDate(booking.getCheckInDate());
            response.setCheckInTime(booking.getCheckInTime());
            response.setCheckOutDate(booking.getCheckOutDate());
            response.setCheckOutTime(booking.getCheckOutTime());
            response.setUserId(booking.getUserId());
            bookingSearchResponseList.add(response);
        }

        return bookingSearchResponseList;
    }


    @GetMapping ("${app.endpoint.bookingView}")
    public BookingSearchResponse updateBooking(@PathVariable("id") String bookingId) {
        Booking booking =bookingService.findById(Long.valueOf(bookingId));
        BookingSearchResponse response = new BookingSearchResponse();
        response.setId(booking.getId());
        response.setCheckInDate(booking.getCheckInDate());
        response.setCheckInTime(booking.getCheckInTime());
        response.setCheckOutDate(booking.getCheckOutDate());
        response.setCheckOutTime(booking.getCheckOutTime());
        response.setUserId(booking.getUserId());

        return response;
    }

    @DeleteMapping ("${app.endpoint.bookingelete}")
    public String deleteRoom(@PathVariable("id") String bookingId) {
        Booking booking= bookingService.findById(Long.valueOf(bookingId));
        if(booking!=null){
            bookingService.delete(booking);

            return "booking data deleted ";
        }

        return "booking data not deleted";

    }
}