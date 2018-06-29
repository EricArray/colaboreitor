package ar.edu.undav.colaboreitor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.undav.colaboreitor.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);
	Optional<User> findByUsername(String username);

	Optional<User> deleteByUsername(String username);
	
}