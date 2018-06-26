package ar.edu.undav.colaboreitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ar.edu.undav.colaboreitor.repository.IncidenteRepository;
import ar.edu.undav.colaboreitor.repository.ReaccionRepository;
import ar.edu.undav.colaboreitor.repository.UserRepository;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
