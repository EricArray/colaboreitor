package ar.edu.undav.colaboreitor;

import ar.edu.undav.colaboreitor.domain.User;
import ar.edu.undav.colaboreitor.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColaboreitorAppTest {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void saveUser() {
		User user = new User(1l, "admin", "password", "Eric", "a1234bcd", 0.234, 0.123, 87654321, Date.valueOf(LocalDate.now()));
		userRepository.save(user);
	}
}