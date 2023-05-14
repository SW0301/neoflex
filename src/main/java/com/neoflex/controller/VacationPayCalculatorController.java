package com.neoflex.controller;

import com.neoflex.service.VacationPayCalculatorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@RestController
public class VacationPayCalculatorController {

    private VacationPayCalculatorService vacationPayCalculatorService = new VacationPayCalculatorService();

    @GetMapping("/calculate")
    public double calculateVacationAllowance(
            @RequestParam double salary,
            @RequestParam int vacationDays,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationStartDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationEndDate
    ) {
        double vacationAllowance = salary / 365 * vacationDays; // если указано только количество дней отпуска,
        // то по упрощенной схеме делим зарплату за 365 дней на количество дней и умножаем на число дней отпуска
        if (vacationStartDate != null && vacationEndDate != null) {
            long vacationDuration = ChronoUnit.DAYS.between(vacationStartDate, vacationEndDate) + 1;
            long weekends = vacationPayCalculatorService.calculateWeekends(vacationStartDate, vacationEndDate);
            long holidays = vacationPayCalculatorService.calculateHolidays(vacationStartDate, vacationEndDate);
            vacationDuration -= weekends + holidays;
            vacationAllowance = salary / 247 * vacationDuration; // зарплата делится на количество рабочих дней в году
        }
        return vacationAllowance;
    }

}