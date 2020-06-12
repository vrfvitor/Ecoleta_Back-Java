package br.com.ecoleta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ecoleta.model.Point;

public interface PointRepository extends JpaRepository<Point, Integer> {

	@Query("SELECT DISTINCT p FROM Point p JOIN p.items items "
			+ "WHERE p.uf = :uf AND p.city = :city AND items.id IN :items")
	List<Point> findBy(@Param("uf") String uf, @Param("city") String city, @Param("items") List<Integer> items);

}
