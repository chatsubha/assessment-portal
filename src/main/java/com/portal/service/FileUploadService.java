package com.portal.service;

import java.io.InputStream;

public interface FileUploadService {
	
	public String processExcelData(InputStream inputStream);
	
	public String processExcelDataForEx2003(InputStream inputStream);

}
