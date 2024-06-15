/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 *
 * @author Vinicius
 */
public class DateFormatterFactory {
    public static DateFormat dateFormatddMMyyy() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public static DateFormat dateFormatyyyyMMdd() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public static DateTimeFormatter dateTime(){
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
    
    public static DateTimeFormatter date(){
        return new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy[ [HH][:mm][:ss][.SSS]]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();
    }
    
}
