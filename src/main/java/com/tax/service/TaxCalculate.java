package com.tax.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TaxCalculate {

	public static Long getTotalSalary(Date djo, Long monthSalary, int lossOfPayInDays) {
		Double daySalary = (double) (monthSalary / 30);
		int totalDays = totWorkingDays(djo);
		double loassOfDay = monthSalary / 30;
		return (long) (daySalary * totalDays - lossOfPayInDays * loassOfDay);
	}

	public static int totWorkingDays(Date dateOfJoining) {
		LocalDate startDate = convertToLocalDate(dateOfJoining);
		int endYear = LocalDate.now().getYear();
		int joinedYear = startDate.getYear();
		int totalWorkingDays = 12 * 30;
		if (joinedYear >= endYear - 1 && startDate.getMonthValue()>=Month.APRIL.getValue()) 
		{
			startDate = convertToLocalDate(dateOfJoining).withDayOfMonth(1);
			LocalDate fyEndate = LocalDate.of(endYear, Month.MARCH, 31).withDayOfMonth(1);
			long months = ChronoUnit.MONTHS.between(fyEndate, startDate);
			
			int remaingDays = startDate.lengthOfMonth()-startDate.getDayOfMonth();
			totalWorkingDays = (int) (months*30 + remaingDays);
		}
		
		return totalWorkingDays;
	}

	public static LocalDate convertToLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Double getTax(Long salary) {
		Double tax = 0.0;
		if (salary <= 250000)
			tax = 0.0;
		else if (salary <= 500000)
			tax = 0.05 * (salary - 250000);
		else if (salary <= 1000000)
			tax = (0.1 * (salary - 500000)) + (0.05 * 250000);
		else
			tax = (0.2 * (salary - 1000000)) + (0.1 * 500000) + (0.05 * 250000);

		
		long factor = (long) Math.pow(10, 2);
		tax = tax * factor;
		long tmp = Math.round(tax);
		tax= (double) tmp / factor;
		return tax;
	}

	
	public static Double getCess(Long sal) {
		Double cess = 0.0;
		if (sal > 2500000) {
			sal = Math.round(sal / 1000000.0) * 1000000;
			cess = (double) ((2 * sal) / 100);
		}
		return cess;
	}
}
