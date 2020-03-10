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
import org.tat.gginl.api.domains.RelationShip;
import org.tat.gginl.api.domains.repository.RelationshipRepository;
import org.tat.gginl.api.domains.services.FileService;
import org.tat.gginl.api.domains.services.RelationshipService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Component
public class RelationShipSchedular {
	

	@Autowired
	private RelationshipService relationshipService;

	@Value("${fileDir}")
	private String fileDir;
	
	@Scheduled(cron = "0 */5 * ? * *")
	 public void createRelationShipFolder() throws Exception {
			
			Date startDate =FileService.resetStartDate(new Date());
			startDate = FileService.minusDays(startDate, 2);
			Date endDate = FileService.resetEndDate(new Date());

			List<Object> columnNameList = relationshipService.findAllColumnName();
			List<Object[]> dataList = relationshipService.findAllNativeObject();
			
			
			if(dataList.size()>0) {
				
				List<String> columnString = columnNameList.stream().map(String::valueOf).collect(Collectors.toList()); 
				
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				
				File relationshipFile = new File("RelationShip.csv");
				FileWriter writer = new FileWriter(relationshipFile);
				
//				writesCsvFromBean(Paths.get(agentsFile.getPath()),agentList);
				columnString.add("[)~=_(]\r");
				CSVUtils.writeLine(writer, columnString, "[)!|;(]");
				
				for(Object[] object : dataList) {
					
					List<String> stringList = Stream.of(object).map(value->value ==null?"":value).map(String::valueOf).collect(Collectors.toList());
					stringList.add("[)~=_(]\r");
					CSVUtils.writeLine(writer, stringList, "[)!|;(]");
				}
				writer.close();
//				String tempDir= fileDir.concat("\\RelationShipInfo").concat(FileService.getDateToString(new Date()));
				String tempDir= fileDir;
				Path filePath = Paths.get(tempDir.concat("\\RelationShip.csv"));
				Files.createDirectories(filePath.getParent());
				Files.move(Paths.get(relationshipFile.getPath()),Paths.get(tempDir.concat("\\RelationShip.csv")),StandardCopyOption.REPLACE_EXISTING);
				Files.deleteIfExists(Paths.get(relationshipFile.getPath()));
				/*
				writer.close();
				FileOutputStream fos = new FileOutputStream("RelationShip.zip");
				ZipOutputStream zipOs = new ZipOutputStream(fos);

				FileService.writeToZipFile(relationshipFile, zipOs);

				zipOs.close();
				fos.close();
				
				
				File toCheckSumFile = new File("RelationShip.zip");

				MessageDigest md5Digest = MessageDigest.getInstance("MD5");

				// Get the checksum
				String checksum =FileService.getFileChecksum(md5Digest, toCheckSumFile);
				File checksumFile = new File("RelationShipInfoChecksum".concat(".md5"));
				
				objectMapper.writeValue(checksumFile,checksum);
				String tempDir= fileDir.concat(":\\AceSharedFolder\\RelationShipInfo").concat(FileService.getDateToString(new Date()));
				
				Path filePath = Paths.get(tempDir.concat("\\RelationShip.zip"));
				Files.createDirectories(filePath.getParent());
				
				Files.move(Paths.get(toCheckSumFile.getPath()),Paths.get(tempDir.concat("\\RelationShip.zip")),StandardCopyOption.REPLACE_EXISTING);
				Files.move(Paths.get(checksumFile.getPath()),Paths.get(tempDir.concat("\\RelationShipInfoChecksum.md5")),StandardCopyOption.REPLACE_EXISTING);
				
				
				Files.deleteIfExists(Paths.get("RelationShip.zip"));
				Files.deleteIfExists(Paths.get("RelationShipInfochecksum.md5"));
				Files.deleteIfExists(Paths.get(relationshipFile.getPath()));
*/

			}
			
		}
		


}
