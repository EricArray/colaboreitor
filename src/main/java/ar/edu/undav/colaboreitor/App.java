package ar.edu.undav.colaboreitor;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import ar.edu.undav.colaboreitor.web.LocalidadController;
import ar.edu.undav.colaboreitor.web.Respuesta;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

	public static void main(String[] args) throws SQLException {
	    //Class.forName("org.postgresql.Driver");
	    //DriverManager.registerDriver(new org.postgresql.Driver());
		SpringApplication.run(App.class, args);
	}


	@Bean
	LocalidadController localidadController() {
		return new LocalidadController();
	}

	@Bean
	Respuesta respuesta() {
		return new Respuesta();
	}
}
