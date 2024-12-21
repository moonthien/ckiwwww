package fit.iuh.se.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="theloai")
public class TheLoai {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="matheloai")
	private int maTheLoai;
	
	@Column(name = "tentheloai", nullable = false, length = 255)
	private String tenTheLoai;
	
	@Column(name = "mota", length = 255)
	private String moTa;
	
	@OneToMany(mappedBy = "theloai", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Albums> albums;

	public TheLoai() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TheLoai(int maTheLoai, String tenTheLoai, String moTa, List<Albums> albums) {
		super();
		this.maTheLoai = maTheLoai;
		this.tenTheLoai = tenTheLoai;
		this.moTa = moTa;
		this.albums = albums;
	}

	public int getMaTheLoai() {
		return maTheLoai;
	}

	public void setMaTheLoai(int maTheLoai) {
		this.maTheLoai = maTheLoai;
	}

	public String getTenTheLoai() {
		return tenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public List<Albums> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Albums> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "TheLoai [maTheLoai=" + maTheLoai + ", tenTheLoai=" + tenTheLoai + ", moTa=" + moTa + ", albums="
				+ albums + "]";
	}
}
