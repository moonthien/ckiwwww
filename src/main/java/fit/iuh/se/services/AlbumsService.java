package fit.iuh.se.services;

import java.util.List;

import fit.iuh.se.entities.Albums;

public interface AlbumsService {
	List<Albums> findAll();
	Albums findByMaal(int maal);
	boolean save(Albums albums);
	boolean delete(int maal);
	boolean updateTheLoai(Albums albums);
	List<Albums> search(String keyword);
}
