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
import org.tat.gginl.api.domains.SaleMan;
import org.tat.gginl.api.domains.repository.SaleManRepository;
import org.tat.gginl.api.domains.services.FileService;
import org.tat.gginl.api.domains.services.SaleManService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class SaleManSchedular {
	@Autowired
	private SaleManService saleManService;
	

	@Value("${fileDir}")
	private String fileDir;
	
	@Scheduled(cron = "0 */5 * ? * *")
	 public void createSaleManFolder() throws Exception {
			
			Date startDate =FileService.resetStartDate(new Date());
			startDate = FileService.minusDays(startDate, 2);
			Date endDate = FileService.resetEndDate(new Date());
			
		//	List<SaleMan> saleManList = saleManRepo.findAll();
			List<Object> columnNameList = saleManService.findAllColumnName();
			List<Object[]> dataList = saleManService.findAllNativeObject();
			
			if(dataList.size()>0) {
				
				List<String> columnString = columnNameList.stream().map(String::valueOf).collect(Collectors.toList()); 
				
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				
				File saleManFile = new File("SaleMan.csv");
				FileWriter writer = new FileWriter(saleManFile);
				
//				writesCsvFromBean(Paths.get(agentsFile.getPath()),agentList);
				columnString.add("[)~=_(]\r");
				CSVUtils.writeLine(writer, columnString, "[)!|;(]");
				
				for(Object[] object : dataList) {
					
					List<String> stringList = Stream.of(object).map(value->value ==null?"":value).map(String::valueOf).collect(Collectors.toList());
					stringList.add("[)~=_(]\r");
					CSVUtils.writeLine(writer, stringList, "[)!|;(]");
				}
				
				writer.close();
//				String tempDir= fileDir.concat("\\SaleManInfo").concat(FileService.getDateToString(new Date()));
				String tempDir= fileDir;
				Path filePath = Paths.get(tempDir.concat("\\SaleMan.csv"));
				Files.createDirectories(filePath.getParent());
				Files.move(Paths.get(saleManFile.getPath()),Paths.get(tempDir.concat("\\SaleMan.csv")),StandardCopyOption.REPLACE_EXISTING);
				Files.deleteIfExists(Paths.get(saleManFile.getPath()));
				/*
				
				/*
				
				writer.close();
				
				FileOutputStream fos = new FileOutputStream("SaleMan.zip");
				ZipOutputStream zipOs = new ZipOutputStream(fos);

				FileService.writeToZipFile(saleManFile, zipOs);

				zipOs.close();
				fos.close();
				
				
				File toCheckSumFile = new File("SaleMan.zip");

				MessageDigest md5Digest = MessageDigest.getInstance("MD5");

				// Get the checksum
				String checksum =FileService.getFileChecksum(md5Digest, toCheckSumFile);
				File checksumFile = new File("SaleManInfoChecksum".concat(".md5"));
				
				objectMapper.writeValue(checksumFile,checksum);
				String tempDir= fileDir.concat(":\\AceSharedFolder\\SaleManInfo").concat(FileService.getDateToString(new Date()));
				
				Path filePath = Paths.get(tempDir.concat("\\SaleMan.zip"));
				Files.createDirectories(filePath.getParent());
				
				Files.move(Paths.get(toCheckSumFile.getPath()),Paths.get(tempDir.concat("\\SaleMan.zip")),StandardCopyOption.REPLACE_EXISTING);
				Files.move(Paths.get(checksumFile.getPath()),Paths.get(tempDir.concat("\\SaleManInfoChecksum.md5")),StandardCopyOption.REPLACE_EXISTING);
				
				
				Files.deleteIfExists(Paths.get("SaleMan.zip"));
				Files.deleteIfExists(Paths.get("SaleManInfochecksum.md5"));
				Files.deleteIfExists(Paths.get(saleManFile.getPath()));
*/

			}
			
		}
		


}
