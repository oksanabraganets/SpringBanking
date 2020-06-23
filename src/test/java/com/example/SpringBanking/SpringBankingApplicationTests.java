package com.example.SpringBanking;

import com.example.SpringBanking.entity.BillDAO;
import com.example.SpringBanking.entity.Role;
import com.example.SpringBanking.repository.BillRepository;
import com.example.SpringBanking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SpringBankingApplicationTests {

	private final BillRepository billRepository;
	private final UserRepository userRepository;

	@Autowired
	public SpringBankingApplicationTests(BillRepository billRepository, UserRepository userRepository) {
		this.billRepository = billRepository;
		this.userRepository = userRepository;
	}

	@Test
	void createBills() {
		List<Long> users = userRepository.findAll().stream()
				.filter(x -> x.getRole() == Role.ROLE_USER)
				.map(x -> x.getId())
				.collect(Collectors.toList());
		for (Long userId : users){
			BillDAO bill = BillDAO.builder()
					.user(userId)
					.amount(120L)
					.recipient("VOLIA")
					.build();
			billRepository.save(bill);
		}
	}

}
