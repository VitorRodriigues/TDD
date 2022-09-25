package br.com.api.tdd.service;

import br.com.api.tdd.model.BookingModel;
import br.com.api.tdd.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public int daysCalculatorWithDatabase(String name) {
        Optional<BookingModel> bookingModel = bookingRepository.findByReserveName(name);

        return Period.between(bookingModel.get().getCheckIn(), bookingModel.get().getCheckOut()).getDays();
    }
}
