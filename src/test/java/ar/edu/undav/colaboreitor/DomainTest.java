package ar.edu.undav.colaboreitor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.edu.undav.colaboreitor.domain.Cp;
import ar.edu.undav.colaboreitor.domain.Foto;
import ar.edu.undav.colaboreitor.domain.Incidente;
import ar.edu.undav.colaboreitor.domain.Localidad;
import ar.edu.undav.colaboreitor.domain.Reaccion;
import ar.edu.undav.colaboreitor.domain.User;
import ar.edu.undav.colaboreitor.repository.CpRepo;
import ar.edu.undav.colaboreitor.repository.FotoRepo;
import ar.edu.undav.colaboreitor.repository.IncidenteRepo;
import ar.edu.undav.colaboreitor.repository.LocalidadRepo;
import ar.edu.undav.colaboreitor.repository.ReaccionRepo;
import ar.edu.undav.colaboreitor.repository.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainTest {

	@Autowired
	private LocalidadRepo localidadRepo;
	
	@Autowired
	private CpRepo cpRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private IncidenteRepo incidenteRepo;

	@Autowired
	private ReaccionRepo reaccionRepo;

	@Autowired
	private FotoRepo fotoRepo;
	
	
	@Test
	public void testDomain() {
		// localidad
		Localidad localidad = new Localidad(
				"Lanús", new BigDecimal("0.0"), new BigDecimal("0.0"));
		localidadRepo.save(localidad);
		
		// CP
		Cp cp = new Cp(
				"a123bcd", localidad, new BigDecimal("1.0"), new BigDecimal("1.0"));
		cpRepo.save(cp);
		
		// user
		User user = new User(
				"testUser", "password", "Eric", cp,
				new BigDecimal("0.0"), new BigDecimal("1.0"),
				88888888, Timestamp.valueOf(LocalDateTime.now()));
		userRepo.save(user);
		
		// incidente
		Incidente incidente = new Incidente(
				user, cp,
				new BigDecimal("0.0"), new BigDecimal("2.0"),
				Timestamp.valueOf(LocalDateTime.now()));
		incidenteRepo.save(incidente);
		
		// foto
		Foto foto = new Foto(incidente, "http://i.imgur.com/d2fSe.jpg");
		fotoRepo.save(foto);
		
		// reaccion
		Reaccion reaccion = new Reaccion(incidente, user, 1);
		reaccionRepo.save(reaccion);

		// eliminar todo
		userRepo.deleteById(user.getId());
	}
}