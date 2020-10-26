package com.myclass.service;

import org.springframework.web.multipart.MultipartFile;


public interface UploadPathService {
	String doUpload(MultipartFile file, String urlFile);
	String EditFile(MultipartFile file, String urlFile);
	Boolean DeleteFile(String oldImage, String urlFile);
}
