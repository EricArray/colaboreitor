package ar.edu.undav.colaboreitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.undav.colaboreitor.domain.Foto;

public interface FotoRepo extends JpaRepository<Foto, Long> {

}