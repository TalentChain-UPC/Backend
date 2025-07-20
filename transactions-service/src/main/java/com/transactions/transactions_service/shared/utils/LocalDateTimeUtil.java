package com.transactions.transactions_service.shared.utils;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class LocalDateTimeUtil {

    public LocalDateTime parseFlexibleDateTime(String input) {
        List<String> formats = List.of(
                "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd"
        );

        for (String pattern : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                if (pattern.contains("HH")) {
                    return LocalDateTime.parse(input, formatter);
                } else {
                    LocalDate date = LocalDate.parse(input, formatter);
                    return date.atStartOfDay();
                }
            } catch (Exception ignored) {}
        }

        throw new IllegalArgumentException("Formato inválido: " + input);
    }

}
