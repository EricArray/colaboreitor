package ar.edu.undav.colaboreitor.repository;

import ar.edu.undav.colaboreitor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}