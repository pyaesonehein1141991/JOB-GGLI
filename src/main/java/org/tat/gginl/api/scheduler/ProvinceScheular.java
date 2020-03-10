package org.tat.gginl.api.scheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tat.gginl.api.common.CSVUtils;
import org.tat.gginl.api.domains.Province;
import org.tat.gginl.api.domains.services.FileService;
import org.tat.gginl.api.domains.services.ProvienceServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class ProvinceScheular {
	
	@Autowired
	private ProvienceServices provienceService;

	@Value("${fileDir}")
	private String fileDir;
	
	@Scheduled(cron = "0 */5 * ? * *")
	public void createProvienceFolder() throws Exception {
		
		Date startDate =FileService.resetStartDate(new Date());
		startDate =FileService.minusDays(startDate, 2);
		Date endDate =FileService.resetEndDate(new Date());
		
	//	List<Bank> agentList = bankService.findAll();
		

		List<Object> columnNameList = provienceService.findAllColumnName();
		List<Object[]> dataList = provienceService.findAllNativeObject();
		
		
		if(dataList.size()>0) {
			
			List<String> columnString = columnNameList.stream().map(String::valueOf).collect(Collectors.toList()); 
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			
			File provinceFile = new File("Provience.csv");
			FileWriter writer = new FileWriter(provinceFile);
			
//			writesCsvFromBean(Paths.get(agentsFile.getPath()),agentList);
			columnString.add("[)~=_(]\r");
			CSVUtils.writeLine(writer, columnString, "[)!|;(]");
			
			
			for(Object[] object : dataList) {
				
				List<String> stringList = Stream.of(object).map(value->value ==null?"":value).map(String::valueOf).collect(Collectors.toList());
				stringList.add("[)~=_(]\r");
				CSVUtils.writeLine(writer, stringList, "[)!|;(]");
			}
			
			writer.close();
//			String tempDir= fileDir.concat("\\ProvienceInfo").concat(FileService.getDateToString(new Date()));
			String tempDir= fileDir;
			Path filePath = Paths.get(tempDir.concat("\\Provience.csv"));
			Files.createDirectories(filePath.getParent());
			Files.move(Paths.get(provinceFile.getPath()),Paths.get(tempDir.concat("\\Provience.csv")),StandardCopyOption.REPLACE_EXISTING);
			Files.deleteIfExists(Paths.get(provinceFile.getPath()));
			/*
			writer.close();
			
			FileOutputStream fos = new FileOutputStream("Provience.zip");
			ZipOutputStream zipOs = new ZipOutputStream(fos);

			FileService.writeToZipFile(agentsFile, zipOs);

			zipOs.close();			
			fos.close();
			
			File toCheckSumFile = new File("Provience.zip");

			MessageDigest md5Digest = MessageDigest.getInstance("MD5");

			// Get the checksum
			String checksum = FileService.getFileChecksum(md5Digest, toCheckSumFile);
			File checksumFile = new File("ProvienceInfoChecksum".concat(".md5"));
			
			objectMapper.writeValue(checksumFile,checksum);
			String tempDir= fileDir.concat(":\\AceSharedFolder\\Provience").concat(FileService.getDateToString(new Date()));
			
			Path filePath = Paths.get(tempDir.concat("\\Provience.zip"));
			Files.createDirectories(filePath.getParent());
			
			Files.move(Paths.get(toCheckSumFile.getPath()),Paths.get(tempDir.concat("\\Provience.zip")),StandardCopyOption.REPLACE_EXISTING);
			Files.move(Paths.get(checksumFile.getPath()),Paths.get(tempDir.concat("\\ProvienceInfoChecksum.md5")),StandardCopyOption.REPLACE_EXISTING);
			
			Files.deleteIfExists(Paths.get("Provience.zip"));
			Files.deleteIfExists(Paths.get("ProvienceInfoChecksum.md5"));
			Files.deleteIfExists(Paths.get(agentsFile.getPath()));
*/

		}
		
	}


}
