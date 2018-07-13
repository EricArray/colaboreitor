package ar.edu.undav.colaboreitor.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.undav.colaboreitor.domain.Incidente;
import ar.edu.undav.colaboreitor.domain.Localidad;

public interface IncidenteRepo extends JpaRepository<Incidente, Long> {

	List<Incidente> findByLocalidad(Localidad localidad);

	@Query("select i from incidente i where nombre like '%?1%'")
	List<Incidente> findLikeNombre(String nombre);

	@Query(
		"select i " +
	    "from incidente i " +
	    "where ST_Distance_Sphere(" +
	    	"ST_GeomFromText(" +
	    		"'POINT(' || CAST(lng AS VARCHAR(12)) || ', ' || CAST(lat AS VARCHAR(12)) || ')'" +
	    	"), ST_GeomFromText('POINT(?1, ?2))) < ?3"
    )
	List<Incidente> findNear(BigDecimal lng, BigDecimal lat, double maxDist);
}