package ar.edu.undav.colaboreitor;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws SQLException {
	    //Class.forName("org.postgresql.Driver");
	    DriverManager.registerDriver(new org.postgresql.Driver());
		SpringApplication.run(App.class, args);
	}

}
