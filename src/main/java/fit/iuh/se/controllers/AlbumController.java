package fit.iuh.se.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fit.iuh.se.entities.Albums;
import fit.iuh.se.entities.TheLoai;
import fit.iuh.se.services.AlbumsService;
import fit.iuh.se.services.TheLoaiService;

@Controller
public class AlbumController {
	@Autowired
	AlbumsService albumService;
	@Autowired
	TheLoaiService theloaiService;
	//convest date
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(dateFormat.parse(text));
                } catch (Exception e) {
                    setValue(null); // Tránh lỗi nếu không parse được
                }
            }
        });
    }
	
	@GetMapping("/albums")
    public String getAllAlbums(Model model) {
        model.addAttribute("alb", albumService.findAll());
        return "album-list"; // Tên file HTML
    }
	
	//Hiển thị form thêm
	@GetMapping("/albums/add-album")
	public String addAlbumForm(Model model) {
	    model.addAttribute("addNewAlbum", new Albums());
	    model.addAttribute("theloais", theloaiService.findAll()); // Thêm danh sách thể loại
	    return "AddAlbum"; // Tên file HTML
	}
	
	
	
	//Thêm mới này không có up file 
//	@PostMapping("/albums/save")
//	public String saveAlbum(@ModelAttribute("addNewAlbum") Albums album) {
//	    albumService.save(album); // Lưu album vào cơ sở dữ liệu
//	    return "redirect:/albums"; // Chuyển hướng về danh sách album
//	}
	
	//Thêm mới này có up file
	@PostMapping("/albums/save")
	public String saveAlbum(@ModelAttribute("addNewAlbum") Albums album,
	                        @RequestParam("image") MultipartFile imageFile) {
	    if (!imageFile.isEmpty()) {
	        try {
	            // Định nghĩa thư mục lưu trữ
	            String uploadDir = "uploads/";
	            Path uploadPath = Paths.get(uploadDir);

	            // Tạo thư mục nếu chưa tồn tại
	            if (!Files.exists(uploadPath)) {
	                Files.createDirectories(uploadPath);
	            }

	            // Tạo tên file duy nhất (thêm timestamp hoặc UUID)
	            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
	            Path filePath = uploadPath.resolve(fileName);

	            // Lưu file ảnh
	            Files.copy(imageFile.getInputStream(), filePath);

	            // Lưu đường dẫn ảnh vào database
	            album.setHinhAnh("/" + uploadDir + fileName);

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "redirect:/albums/add-album?error"; // Xử lý lỗi nếu lưu thất bại
	        }
	    }

	    albumService.save(album); // Lưu album vào cơ sở dữ liệu
	    return "redirect:/albums";
	}

	//Delete by maal 
	@GetMapping("/albums/delete/{maal}")
    public String deleteAlbum(@PathVariable("maal") int maal) {
        boolean isDeleted = albumService.delete(maal); // Gọi service để xóa album
        if (isDeleted) {
            return "redirect:/albums"; // Chuyển hướng về trang danh sách album
        } else {
            return "redirect:/albums"; // Nếu xóa thất bại (ví dụ album không tồn tại), vẫn chuyển hướng về trang danh sách
        }
    }
	
	//Hiển thị form chỉnh sửa albums
	@GetMapping("/albums/edit-album/{maal}")
	public String editAlbumForm(@PathVariable("maal") int maal, Model model) {
	    Albums album = albumService.findByMaal(maal); // Lấy thông tin album theo ID
	    model.addAttribute("updateAlbum", album); // Đẩy album vào model
	    model.addAttribute("theloais", theloaiService.findAll()); // Danh sách thể loại
	    return "Edit"; // Tên file HTML cho trang chỉnh sửa
	}
	
	//Update album không có uploads
//	@PostMapping("/albums/update/{maal}")
//	public String updateAlbum(@PathVariable("maal") int maal, @ModelAttribute("updateAlbum") Albums album) {
//	    album.setMaal(maal); // Đảm bảo ID không thay đổi
//	    albumService.save(album); // Lưu cập nhật vào cơ sở dữ liệu
//	    return "redirect:/albums"; // Chuyển hướng về danh sách album
//	}
	
	//Update album có uploads
	@PostMapping("/albums/update/{maal}")
	public String updateAlbum(@PathVariable("maal") int maal, 
	                          @ModelAttribute("updateAlbum") Albums updateAlbum,
	                          @RequestParam("image") MultipartFile file,
	                          RedirectAttributes redirectAttributes) {
	    try {
	        // Lấy album hiện tại từ database
	        Albums currentAlbum = albumService.findByMaal(maal);
	        
	        // Kiểm tra xem có file ảnh mới được tải lên không
	        if (!file.isEmpty()) {
	            // Lưu ảnh mới vào thư mục uploads
	            String uploadDir = "D:/oncuoikyWWW/FullCRUD/uploads/"; // Thư mục lưu ảnh";
	            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	            File uploadFile = new File(uploadDir + fileName);
	            file.transferTo(uploadFile);

	            // Cập nhật đường dẫn ảnh mới
	            updateAlbum.setHinhAnh("/uploads/" + fileName);
	        } else {
	            // Nếu không tải lên ảnh mới, giữ nguyên đường dẫn ảnh cũ
	            updateAlbum.setHinhAnh(currentAlbum.getHinhAnh());
	        }
	        
	        // Cập nhật các thông tin khác
	        updateAlbum.setMaal(maal); // Đảm bảo ID không thay đổi
	        albumService.save(updateAlbum); // Lưu cập nhật vào cơ sở dữ liệu
	        
	        // Thông báo thành công
	        redirectAttributes.addFlashAttribute("message", "Album updated successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("error", "Failed to update the album. Please try again!");
	    }

	    return "redirect:/albums";
	}
	
	@GetMapping("/albums/search")
    public String searchAlbums(@RequestParam("keyword") String keyword, Model model) {
        List<Albums> albums = albumService.search(keyword); // Tìm kiếm album theo từ khóa
        model.addAttribute("alb", albums); // Gửi kết quả tìm kiếm vào model
        model.addAttribute("keyword", keyword); // Đưa từ khóa tìm kiếm vào model để hiển thị trong ô tìm kiếm
        return "album-list"; // Trả về trang danh sách album với kết quả tìm kiếm
    }
}
