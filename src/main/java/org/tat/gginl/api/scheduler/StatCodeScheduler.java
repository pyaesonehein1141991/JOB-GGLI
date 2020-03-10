package org.tat.gginl.api.scheduler;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tat.gginl.api.common.CSVUtils;
import org.tat.gginl.api.domains.services.FileService;
import org.tat.gginl.api.domains.services.SalePointService;
import org.tat.gginl.api.domains.services.StatCodeService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class StatCodeScheduler {

	@Autowired
	private StatCodeService statCodeService;
	
	@Value("${fileDir}")
	private String fileDir;
	
	@Scheduled(cron = "0 */5 * ? * *")
	 public void createStatCodeFolder() throws Exception {
			
			Date startDate =FileService.resetStartDate(new Date());
			startDate = FileService.minusDays(startDate, 2);
			Date endDate = FileService.resetEndDate(new Date());
			
		//	List<SalePoint> salePointList = salePointService.findAll();
			
			List<Object> columnNameList = statCodeService.findAllColumnName();
			List<Object[]> dataList = statCodeService.findAllNativeObject();
			
			if(dataList.size()>0) {
				
				List<String> columnString = columnNameList.stream().map(String::valueOf).collect(Collectors.toList()); 
				
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				
				File salePointsFile = new File("StatCode.csv");
				
				
				FileWriter writer = new FileWriter("StatCode.csv");
				
//				writesCsvFromBean(Paths.get(agentsFile.getPath()),agentList);
				columnString.add("[)~=_(]\r");
				CSVUtils.writeLine(writer, columnString, "[)!|;(]");
				
				for(Object[] object : dataList) {
					
					List<String> stringList = Stream.of(object).map(value->value ==null?"":value).map(String::valueOf).collect(Collectors.toList());
					stringList.add("[)~=_(]\r");
					CSVUtils.writeLine(writer, stringList, "[)!|;(]");
				}
				
			
				
				writer.close();
//				String tempDir= fileDir.concat("\\SalePointInfo").concat(FileService.getDateToString(new Date()));
				String tempDir= fileDir;
				Path filePath = Paths.get(tempDir.concat("\\StatCode.csv"));
				Files.createDirectories(filePath.getParent());
				Files.move(Paths.get(salePointsFile.getPath()),Paths.get(tempDir.concat("\\StatCode.csv")),StandardCopyOption.REPLACE_EXISTING);
				
				Files.deleteIfExists(Paths.get(salePointsFile.getPath()));

//				FileOutputStream fos = new FileOutputStream("SalePoints.zip");
//				ZipOutputStream zipOs = new ZipOutputStream(fos);
//				
//                
//				FileService.writeToZipFile(salePointsFile, zipOs);
//
//				zipOs.close();
//				fos.close();
//				
//				
//				File toCheckSumFile = new File("SalePoints.zip");
//
//				MessageDigest md5Digest = MessageDigest.getInstance("MD5");
//
//				// Get the checksum
//				String checksum =FileService.getFileChecksum(md5Digest, toCheckSumFile);
//				File checksumFile = new File("SalePointsInfoChecksum".concat(".md5"));
//				
//				objectMapper.writeValue(checksumFile,checksum);
//				String tempDir= fileDir.concat(":\\AceSharedFolder\\SalePointInfo").concat(FileService.getDateToString(new Date()));
//				
//				Path filePath = Paths.get(tempDir.concat("\\SalePoints.zip"));
//				Files.createDirectories(filePath.getParent());
//				
//				Files.move(Paths.get(toCheckSumFile.getPath()),Paths.get(tempDir.concat("\\SalePoints.zip")),StandardCopyOption.REPLACE_EXISTING);
//				Files.move(Paths.get(checksumFile.getPath()),Paths.get(tempDir.concat("\\SalePointsInfoChecksum.md5")),StandardCopyOption.REPLACE_EXISTING);
//				
//				
  //    			Files.deleteIfExists(Paths.get("SalePoints.zip"));
//				Files.deleteIfExists(Paths.get("SalePointsInfochecksum.md5"));
		//		Files.deleteIfExists(Paths.get(salePointsFile.getPath()));


			}
			
		}

}
