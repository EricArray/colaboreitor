package ar.edu.undav.colaboreitor.repository;

import ar.edu.undav.colaboreitor.domain.Reaccion;
import ar.edu.undav.colaboreitor.domain.ReaccionPk;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaccionRepository extends JpaRepository<Reaccion, ReaccionPk> {

}