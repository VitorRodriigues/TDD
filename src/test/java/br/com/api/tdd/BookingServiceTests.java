package br.com.api.tdd;

import br.com.api.tdd.model.BookingModel;
import br.com.api.tdd.repository.BookingRepository;
import br.com.api.tdd.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BookingServiceTests {

    @TestConfiguration
    static class BookingServiceTestConfiguration {

        @Bean
        public BookingService bookingService() {
            return new BookingService();
        }
    }
    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @Test
    public void bookingTestServiceDaysCalculator() {
        String name = "Vitor";
        int days = bookingService.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(days, 10);
    }

    @Before
    public void setup() {
        LocalDate checkIn = LocalDate.parse("2022-09-10");
        LocalDate checkOut = LocalDate.parse("2022-09-20");
        BookingModel bookingModel = new BookingModel("1", "Vitor", checkIn, checkOut, 2);

        Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName()))
                .thenReturn(Optional.of(bookingModel));
    }
}
