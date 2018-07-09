package ar.edu.undav.colaboreitor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.undav.colaboreitor.domain.Cuenta;

public interface CuentaRepo extends JpaRepository<Cuenta, Long> {
	Optional<Cuenta> findById(Long id);
	Optional<Cuenta> findByUsername(String username);

	Optional<Cuenta> deleteByUsername(String username);
	
}