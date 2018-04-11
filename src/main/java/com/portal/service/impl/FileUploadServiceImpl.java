package com.portal.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.LookupTableDao;
import com.portal.dao.OptionsDao;
import com.portal.dao.QuestionDao;
import com.portal.model.Question;
import com.portal.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	QuestionDao qestionDao;
	@Autowired
	LookupTableDao lookupTableDao;
	@Autowired
	OptionsDao optionsDao;

	@Override
	public int processExcelData(InputStream inputStream) {
		// TODO Auto-generated method stub
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet questionSheet = workbook.getSheetAt(0);
			Iterator<Row> sheetIterator = questionSheet.rowIterator();
			List<Object[]> parameterList = new ArrayList<Object[]>();
			while (sheetIterator.hasNext()) {
				Row row = sheetIterator.next();
				if(row.getRowNum()!=0 && row.getCell(0)!=null) {
					String questionDesc = row.getCell(1).toString();
					questionDesc=questionDesc.replaceAll("\n", "<br/>")
							                 .replaceAll("\"", "&quot;")
							                 .replaceAll("'", "&apos;");
//					questionDesc=StringEscapeUtils.escapeHtml(questionDesc);
					int id=qestionDao.isSameQuestion(new Object[]{questionDesc});
					System.out.println("id=>"+id);
					
					String answerType = lookupTableDao.getLookupTableDesc("tblanswertypelookup","answer_type_id","lcase(answer_type)=?",new Object[]{row.getCell(2).toString().toLowerCase()});
					String category= lookupTableDao.getLookupTableDesc("tblcategory","category_id","lcase(category_name)=?",new Object[]{row.getCell(3).toString().toLowerCase()});
					String comlexity= lookupTableDao.getLookupTableDesc("tblcomplexity","complexity","lcase(description)=?",new Object[]{row.getCell(4).toString().toLowerCase()});
					System.out.println(questionDesc +":"+answerType+":"+category+":"+comlexity);
					if (id!=0) {
						Question question =new Question();
						question.setQuestionId(id);
						question.setQuestionDesc(questionDesc);
						question.setAnswerType(Integer.valueOf(answerType));
						question.setCategoryId(Integer.valueOf(category));
						question.setComplexity(Integer.valueOf(comlexity));
						qestionDao.updateQuestion(question);
						System.out.println("Update successful");
					}
					else
					{
					parameterList.add(new Object[] { questionDesc, 
							answerType,
							category,
							comlexity });
					}
				}
				
				
			}
			if (parameterList.size()>0) {
				qestionDao.addBulkQuestion(parameterList);
			}
			
			
			XSSFSheet optionSheet = workbook.getSheetAt(1);
			sheetIterator = optionSheet.rowIterator();
			List<Object[]> optionParameterList = new ArrayList<Object[]>();
			while (sheetIterator.hasNext()) {
				Row row = sheetIterator.next();
				if(row.getRowNum()!=0 && row.getCell(0)!=null) {
					String questionDesc = row.getCell(0).toString();
					questionDesc=questionDesc.replaceAll("\n", "<br/>")
							                 .replaceAll("\"", "&quot;")
			                                 .replaceAll("'", "&apos;");;
//					questionDesc=StringEscapeUtils.escapeHtml(questionDesc);
					int id=qestionDao.isSameQuestion(new Object[]{questionDesc});
					System.out.println("id=>"+id);
					optionsDao.deleteOptionsByQuestionId(id);
					String optionDesc = row.getCell(1).toString();
					optionDesc=optionDesc.replaceAll("\n", "<br/>")
					                     .replaceAll("\"", "&quot;")
	                                     .replaceAll("'", "&apos;");
//					optionDesc=StringEscapeUtils.escapeHtml(optionDesc);
					String answer=row.getCell(2).toString();
					optionParameterList.add(new Object[] {id,optionDesc,answer});
				}
				
			}
			
			optionsDao.addBulkOptions(optionParameterList);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

}
