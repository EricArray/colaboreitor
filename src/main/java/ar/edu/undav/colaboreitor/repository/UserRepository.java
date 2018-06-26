package ar.edu.undav.colaboreitor.repository;

import ar.edu.undav.colaboreitor.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);
	Optional<User> findByUsername(String username);

	Optional<User> deleteByUsername(String username);
	
}