package ar.edu.undav.colaboreitor.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.undav.colaboreitor.domain.Incidente;

public interface IncidenteRepo extends JpaRepository<Incidente, Long> {

	@Query(
			value = "select i from cp c, incidente i where c.localidad = ?1 and i.cp = c.cp",
			nativeQuery = true)
	List<Incidente> findByLocalidad(long localidad);

	@Query(
			value = "select i from incidente i where nombre like '%?1%'",
			nativeQuery = true)
	List<Incidente> findLikeNombre(String nombre);

	@Query(
		value =
		"select i " +
	    "from incidente i " +
	    "where ST_Distance_Sphere(" +
	    	"ST_GeomFromText(" +
	    		"'POINT(' || CAST(lng AS VARCHAR(12)) || ', ' || CAST(lat AS VARCHAR(12)) || ')'" +
	    	"), ST_GeomFromText('POINT(?1, ?2)')) < ?3",
    	nativeQuery = true
    )
	List<Incidente> findNear(BigDecimal lng, BigDecimal lat, double maxDist);
}