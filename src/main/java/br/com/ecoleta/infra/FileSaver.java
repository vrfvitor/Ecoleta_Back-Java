package br.com.ecoleta.infra;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	public String saveUploadedFile(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				String fileName = generateHashName(file.getOriginalFilename());
				Path path = Paths.get("src/main/resources/static/uploads/" + fileName);
				Files.write(path, file.getBytes());
				return fileName;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	private String generateHashName(String originalFilename) {
		String hash = UUID.randomUUID().toString().substring(0,5);
        return hash + "-" + originalFilename;
	}
}
