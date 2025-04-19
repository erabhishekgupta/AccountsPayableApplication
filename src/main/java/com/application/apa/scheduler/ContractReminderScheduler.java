package com.application.apa.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.application.apa.service.ContractService;

@Component
@EnableScheduling
public class ContractReminderScheduler {

	@Autowired
	private ContractService contractService;

//	@Autowired
//	private ContractRepository contractRepsitory;

    //@Scheduled(cron = "0 */1 * * * *")
    //@Scheduled(cron = "0 0 10 * * ?")
    //schedular for sending email remainders for renewal of payments
	//@Scheduled(cron = "0 */1 * * * *")
	
	
	
	//@Scheduled(cron = "0 0 10 * * ?")
	@Scheduled(cron = "0 */1 * * * *")
	public void sendRemainders() {
		contractService.sendRenewalReminders();
	}
	
	// Runs every day at midnight
	// schedular for checking active status

//	 @Scheduled(cron = "0 0 0 * * ?") 
//	public void updateContractStatus() {
//
//		List<Contract> contracts = contractRepsitory.findAll();
//		LocalDate today = LocalDate.now();
//
//		for (Contract contract : contracts) {
//			if (today.isAfter(contract.getEndDate())) {
//				contract.setStatus(ContractStatus.EXPIRED);
//			} else {
//				contract.setStatus(ContractStatus.ACTIVE);
//			}
//		}
//		contractRepsitory.saveAll(contracts);
//	}

}



