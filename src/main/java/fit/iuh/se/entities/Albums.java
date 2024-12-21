package fit.iuh.se.entities;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="album")
public class Albums {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maal")
	private int maal;
	
	@Column(name = "tenal", nullable = false, length = 255)
	@NotEmpty(message = "Ten album must not be empty")
	private String tenAl;
	
	//tanglen tangxuong
	@Column(name = "gia", nullable = false)
	private int gia;
	
	@Column(name = "namphathanh", nullable = false)
    @Temporal(TemporalType.DATE)
	private Date namPhatHanh;
	
	@Column(name = "hinhanh", length = 255)
	private String hinhAnh;
	
	//radio button
	@Column(name = "trangthai", nullable = false)
	private String status;
	
	//textarea description
	@Column(name = "description", nullable = false, length = 255)
	@NotEmpty(message = "Description must not be empty")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matheloai", nullable = false)
	private TheLoai theloai;

	public Albums() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Albums(int maal, @NotEmpty(message = "Ten album must not be empty") String tenAl, int gia, Date namPhatHanh,
			String hinhAnh, String status, @NotEmpty(message = "Description must not be empty") String description,
			TheLoai theloai) {
		super();
		this.maal = maal;
		this.tenAl = tenAl;
		this.gia = gia;
		this.namPhatHanh = namPhatHanh;
		this.hinhAnh = hinhAnh;
		this.status = status;
		this.description = description;
		this.theloai = theloai;
	}

	public int getMaal() {
		return maal;
	}

	public void setMaal(int maal) {
		this.maal = maal;
	}

	public String getTenAl() {
		return tenAl;
	}

	public void setTenAl(String tenAl) {
		this.tenAl = tenAl;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public Date getNamPhatHanh() {
		return namPhatHanh;
	}

	public void setNamPhatHanh(Date namPhatHanh) {
		this.namPhatHanh = namPhatHanh;
	}
	
	public String getNamPhatHanhFormatted() {
        if (namPhatHanh != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(namPhatHanh);
        }
        return null;
    }

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TheLoai getTheloai() {
		return theloai;
	}

	public void setTheloai(TheLoai theloai) {
		this.theloai = theloai;
	}

	@Override
	public String toString() {
		return "Albums [maal=" + maal + ", tenAl=" + tenAl + ", gia=" + gia + ", namPhatHanh=" + namPhatHanh
				+ ", hinhAnh=" + hinhAnh + ", status=" + status + ", description=" + description + ", theloai="
				+ theloai + "]";
	}
}
