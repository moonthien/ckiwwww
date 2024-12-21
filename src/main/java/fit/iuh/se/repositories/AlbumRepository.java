package fit.iuh.se.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fit.iuh.se.entities.Albums;

@Repository
public interface AlbumRepository extends JpaRepository<Albums, Integer> {
    @Query(value = "SELECT * FROM album a WHERE a.tenal LIKE %:keyword%", nativeQuery = true)
    List<Albums> search(@Param("keyword") String keyword);
}
