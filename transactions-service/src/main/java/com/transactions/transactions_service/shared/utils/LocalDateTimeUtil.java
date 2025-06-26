package com.transactions.transactions_service.shared.utils;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class LocalDateTimeUtil {

    private final ZoneId zoneLima = ZoneId.of("America/Lima");

    public String calculateContractStatus(LocalDateTime start, LocalDateTime end) {
        LocalDateTime now = ZonedDateTime.now(zoneLima).toLocalDateTime();

        if(now.isBefore(start)){
            return "INACTIVE";
        }else if(!now.isAfter(end)){
            return "ACTIVE";
        }else {
            return "FINISHED";
        }
    }

    public LocalDateTime parseFlexibleDateTime(String input) {
        List<DateTimeFormatter> formats = List.of(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("HH:mm:ss"),
                DateTimeFormatter.ofPattern("HH:mm")
        );

        for (DateTimeFormatter formatter : formats) {
            try {
                if(formatter.toString().contains("yyyy")){
                    return LocalDateTime.parse(input, formatter);
                }else {
                    LocalTime hour = LocalTime.parse(input, formatter);
                    return LocalDateTime.of(LocalDate.now(), hour);
                }
            }catch (Exception e){
            }
        }
        throw new IllegalArgumentException("Invalid date format" + input);
    }

}
