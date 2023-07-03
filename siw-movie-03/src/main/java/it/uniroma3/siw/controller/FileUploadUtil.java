package it.uniroma3.siw.controller;

import java.io.*;
import java.nio.file.*;
 
import org.springframework.web.multipart.MultipartFile;
 
public class FileUploadUtil {
     
    public static void saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }      
    }
    
    
    public static void deleteDir(String title) {
    	String dir = "src/main/resources/static/images/";
    	
    	String name = dir +  title;
        Path path = Paths.get(name);
        File file = new File(name);
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                f.delete();
            }
        }
        
        try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

        
    }
    
    public static void deleteDirArt(String title) {
    	String dir = "src/main/resources/static/images/artists/";
    	
    	String name = dir +  title;
        Path path = Paths.get(name);
        File file = new File(name);
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                f.delete();
            }
        }
        
        try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

        
    }
    
}
