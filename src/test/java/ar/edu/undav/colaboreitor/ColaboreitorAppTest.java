package ar.edu.undav.colaboreitor;

import ar.edu.undav.colaboreitor.domain.Incidente;
import ar.edu.undav.colaboreitor.domain.Reaccion;
import ar.edu.undav.colaboreitor.domain.ReaccionPk;
import ar.edu.undav.colaboreitor.domain.User;
import ar.edu.undav.colaboreitor.repository.IncidenteRepository;
import ar.edu.undav.colaboreitor.repository.ReaccionRepository;
import ar.edu.undav.colaboreitor.repository.UserRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColaboreitorAppTest {
    
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IncidenteRepository incidenteRepository;

	@Autowired
	private ReaccionRepository reaccionRepository;
	
	
	@Test
	public void saveUser() {
		User user = new User(
				"testSaveUser", "password", "Eric", "a1234bcd",
				new BigDecimal("0.0"), new BigDecimal("1.0"),
				88888888, Timestamp.valueOf(LocalDateTime.now()));
		userRepository.save(user);
		
		userRepository.delete(user);
	}
	
	@Test
	public void saveIncidente() {
		User user = new User(
				"testSaveIncidente", "password", "Eric", "a1234bcd",
				new BigDecimal("0.0"), new BigDecimal("1.0"),
				88888888, Timestamp.valueOf(LocalDateTime.now()));
		userRepository.save(user);

		Incidente incidente = new Incidente(
				userRepository.findByUsername("testSaveIncidente").get(),
				new BigDecimal("0.0"), new BigDecimal("2.0"),
				Timestamp.valueOf(LocalDateTime.now()));
		incidenteRepository.save(incidente);
		
		userRepository.deleteById(user.getId());
	}
	
	@Test
	public void saveReaccion() {
		User user = new User(
				"testSaveReaccion", "password", "Eric", "a1234bcd",
				new BigDecimal("0.0"), new BigDecimal("1.0"),
				88888888, Timestamp.valueOf(LocalDateTime.now()));
		userRepository.save(user);
		
		Incidente incidente = new Incidente(
				user,
				new BigDecimal("0.0"), new BigDecimal("2.0"),
				Timestamp.valueOf(LocalDateTime.now()));
		incidenteRepository.save(incidente);
		
		Reaccion reaccion = new Reaccion(incidente, user, 1);
		reaccionRepository.save(reaccion);

		userRepository.deleteById(user.getId());
	}
}