package com.neoflex.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class VacationPayCalculatorService {
    private static final Set<LocalDate> holidays = new HashSet<>();
    static {
        holidays.add(LocalDate.of(2023, 1, 2));
        holidays.add(LocalDate.of(2023, 1, 3));
        holidays.add(LocalDate.of(2023, 1, 4));
        holidays.add(LocalDate.of(2023, 1, 5));
        holidays.add(LocalDate.of(2023, 1, 6));
        holidays.add(LocalDate.of(2023, 2, 23));
        holidays.add(LocalDate.of(2023, 2, 24));
        holidays.add(LocalDate.of(2023, 3, 8));
        holidays.add(LocalDate.of(2023, 5, 1));
        holidays.add(LocalDate.of(2023, 5, 8));
        holidays.add(LocalDate.of(2023, 5, 9));
        holidays.add(LocalDate.of(2023, 6, 12));
        holidays.add(LocalDate.of(2023, 11, 6));
    }
    public long calculateWeekends(LocalDate startDate, LocalDate endDate) {
        long weekends = 0;
        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            if (current.getDayOfWeek().getValue() > 5) {
                weekends++;
            }
            current = current.plusDays(1);
        }
        return weekends;
    }

    public long calculateHolidays(LocalDate startDate, LocalDate endDate) {
        long holidaysInVacation = 0;
        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            if (holidays.contains(current)) {
                holidaysInVacation++;
            }
            current = current.plusDays(1);
        }
        return holidaysInVacation;
    }

}
