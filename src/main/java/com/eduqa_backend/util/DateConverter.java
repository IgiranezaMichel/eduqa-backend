package com.eduqa_backend.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {
public static String LocalDateConverter(LocalDate localDate,String pattern){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return formatter.format(localDate);
}
public static String LocalDateTimeConverter(LocalDateTime localDate,String pattern){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return formatter.format(localDate);
}
}
