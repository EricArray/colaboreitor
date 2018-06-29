package ar.edu.undav.colaboreitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.undav.colaboreitor.domain.Reaccion;
import ar.edu.undav.colaboreitor.domain.ReaccionPk;

public interface ReaccionRepo extends JpaRepository<Reaccion, ReaccionPk> {

}