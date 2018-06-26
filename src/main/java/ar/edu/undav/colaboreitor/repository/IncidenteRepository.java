package ar.edu.undav.colaboreitor.repository;

import ar.edu.undav.colaboreitor.domain.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenteRepository extends JpaRepository<Incidente, Long> {

}