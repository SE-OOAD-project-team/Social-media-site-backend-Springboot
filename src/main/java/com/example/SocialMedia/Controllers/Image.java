package com.example.SocialMedia.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v2/image")
public class Image {
	
	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
	
	@GetMapping("{filename}")
	public ResponseEntity<Resource> getPhoto(@PathVariable String filename) throws IOException {
		Path path = Paths.get(UPLOAD_DIRECTORY, filename);
		File file = new File(path.toString());
		
		HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
	}
	
	private String generateUniqueFileName() {
	    String filename = "";
	    long millis = System.currentTimeMillis();
	    String datetime = new Date().toGMTString();
	    datetime = datetime.replace(" ", "");
	    datetime = datetime.replace(":", "");
	    String startchars = "file";
	    filename = startchars + "_" + datetime + "_" + millis;
	    return filename;
	}
	
	private String generateNewFileName(String original) {
		String [] split_filename = original.split("\\.");
		String ext = split_filename[split_filename.length - 1];
		String newFileName = generateUniqueFileName() + "." + ext;
		return newFileName;
	}
	
	@PostMapping("/upload")
	public HashMap<String, String> uploadPhoto(@RequestParam("photo") MultipartFile file) throws IOException {
//		System.out.println(file.getOriginalFilename());
		
		HashMap<String, String> ret = new HashMap<>();
		
		try {
			String newFileName = generateNewFileName(file.getOriginalFilename());
	//		System.out.println(newFileName);
			
			Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, newFileName);
			
	//		File createFile = new File(fileNameAndPath.toString());
			Files.write(fileNameAndPath, file.getBytes());
			System.out.println("File uploaded at: " + fileNameAndPath.toString());
			ret.put("filename", newFileName);
		}
		catch (Exception e) {
			ret.put("filename", "");
		}
		return ret;
	}
}