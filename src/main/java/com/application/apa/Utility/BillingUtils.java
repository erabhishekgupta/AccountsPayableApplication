package com.application.apa.Utility;

import java.time.LocalDate;

import com.application.apa.Enum.BillingFrequency;

public class BillingUtils {

	public static LocalDate calculateNextbillingDate(LocalDate lastBillingDate, BillingFrequency billingFrequency) {
		switch (billingFrequency) {
		case WEEKLY:
			return lastBillingDate.plusWeeks(1);
		case MONTHLY:
			return lastBillingDate.plusMonths(1);
		case QUATERLY:
			return lastBillingDate.plusMonths(3);
		case YEARLY:
			return lastBillingDate.plusYears(1);

		default:
			throw new IllegalArgumentException("Unexpected value: " + billingFrequency);
		}
	}

	public static LocalDate calculateDueDate(LocalDate billingDate) {
		return billingDate.plusDays(5); 
	}

}
