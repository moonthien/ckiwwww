package fit.iuh.se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fit.iuh.se.entities.TheLoai;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai, Integer>{

}
