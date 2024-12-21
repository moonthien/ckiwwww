package fit.iuh.se.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.se.entities.Albums;
import fit.iuh.se.repositories.AlbumRepository;
import fit.iuh.se.services.AlbumsService;

@Service
public class AlbumsServiceImpl implements AlbumsService{

	@Autowired
	private AlbumRepository albumRepository;
	
	@Override
	public List<Albums> findAll() {
		// TODO Auto-generated method stub
		return albumRepository.findAll();
	}

	@Override
	public Albums findByMaal(int maal) {
		// TODO Auto-generated method stub
		return albumRepository.findById(maal).get();
	}

	@Override
	public boolean save(Albums albums) {
		// TODO Auto-generated method stub
		albumRepository.save(albums);
		return true;
	}

	@Override
	public boolean delete(int maal) {
		// Kiểm tra xem album có tồn tại không
        if (albumRepository.existsById(maal)) {
            albumRepository.deleteById(maal);
            return true;
        }
        return false; // Nếu không tìm thấy album, trả về false
	}

	@Override
	public boolean updateTheLoai(Albums albums) {
		// TODO Auto-generated method stub
		albumRepository.saveAndFlush(albums);
		return true;
	}

	@Override
	public List<Albums> search(String keyword) {
		// TODO Auto-generated method stub
		return albumRepository.search(keyword);
	}

}
