package com.myclass.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.service.UploadPathService;

@Service
public class UploadPathServiceImpl implements UploadPathService {

	@Autowired
	private ServletContext servletContext;
	@Override
	public String doUpload(MultipartFile file,String urlFile) {
		if (file.getSize() > 0) {
	        String uploadRootPath = System.getProperty("user.dir");
			uploadRootPath += "\\src\\main\\resources" + urlFile;
	        File uploadRootDir = new File(uploadRootPath);
	        if (!uploadRootDir.exists()) {
	            uploadRootDir.mkdirs();
	        }
	        MultipartFile fileData = file;
	        String originalFileName = file.getOriginalFilename();
            try {            	
            	originalFileName = file.getOriginalFilename();
    		    int index = originalFileName.lastIndexOf(".");
    			String ext = originalFileName.substring(index + 1);
    			originalFileName = System.currentTimeMillis() + "." + ext;
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + originalFileName);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData.getBytes());
                stream.close();
                serverFile.canWrite();
                System.out.println("Write file: " + serverFile);
            } catch (Exception e) {
                System.out.println("Error Write file: " + originalFileName);
            }
            return originalFileName;
		}		
		return null;
	}
	
	@Override
	public String EditFile(MultipartFile file, String urlFile) {
		if (file.getSize() > 0) {
		    // Thư mục gốc upload file.
	        String uploadRootPath = servletContext.getRealPath(urlFile);
	        File uploadRootDir = new File(uploadRootPath);
	        // Tạo thư mục gốc upload nếu nó không tồn tại.
	        if (!uploadRootDir.exists()) {
	            uploadRootDir.mkdirs();
	        }
	        
	        MultipartFile fileData = file;
	        String originalFileName = file.getOriginalFilename();
            try {            	
            	originalFileName = file.getOriginalFilename();
    		    int index = originalFileName.lastIndexOf(".");
    			String ext = originalFileName.substring(index + 1);
    			originalFileName = System.currentTimeMillis() + "." + ext;
                // Tạo file tại Server.
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + originalFileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData.getBytes());
                stream.close();
                serverFile.canWrite();
                System.out.println("Write file: " + serverFile);
            } catch (Exception e) {
                System.out.println("Error Write file: " + originalFileName);
            }
            return originalFileName;
		}		
		return null;
	}
	
	@Override
	public Boolean DeleteFile(String oldImage, String urlFile) {
		if (!oldImage.isEmpty()) {
			String uploadRootPath = System.getProperty("user.dir");
			uploadRootPath += "\\src\\main\\resources" + urlFile;
			File uploadRootDir = new File(uploadRootPath + oldImage);
	        Boolean deleteFile = uploadRootDir.delete();
	        return deleteFile;
		}
		return false;
	}




}
