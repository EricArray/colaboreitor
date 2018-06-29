package ar.edu.undav.colaboreitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.undav.colaboreitor.domain.Incidente;

public interface IncidenteRepo extends JpaRepository<Incidente, Long> {

}