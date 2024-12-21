package fit.iuh.se.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.se.entities.TheLoai;
import fit.iuh.se.repositories.TheLoaiRepository;
import fit.iuh.se.services.TheLoaiService;

@Service
public class TheLoaiServiceImpl implements TheLoaiService{
	
	@Autowired
	private TheLoaiRepository theloaiRepository;

	@Override
	public List<TheLoai> findAll() {
		// TODO Auto-generated method stub
		return theloaiRepository.findAll();
	}
}
