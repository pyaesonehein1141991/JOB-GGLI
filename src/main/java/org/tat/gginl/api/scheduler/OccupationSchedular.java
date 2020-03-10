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
import org.tat.gginl.api.domains.Occupation;
import org.tat.gginl.api.domains.repository.OccupationRepository;
import org.tat.gginl.api.domains.services.FileService;
import org.tat.gginl.api.domains.services.OccupationService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
@Component
public class OccupationSchedular {

	@Autowired
	private OccupationService occupationService;
	
	@Value("${fileDir}")
	private String fileDir;
	
	@Scheduled(cron = "0 */5 * ? * *")
	 public void createOccupationSFolder() throws Exception {
			
			Date startDate =FileService.resetStartDate(new Date());
			startDate = FileService.minusDays(startDate, 2);
			Date endDate = FileService.resetEndDate(new Date());
			
//			List<Occupation> occupationList = occupationRepo.findAll();
			
			List<Object> columnNameList = occupationService.findAllColumnName();
			List<Object[]> dataList = occupationService.findAllNativeObject();
			
			if(dataList.size()>0) {
				
				List<String> columnString = columnNameList.stream().map(String::valueOf).collect(Collectors.toList()); 
				
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				
				File occupationFile = new File("Occupation.csv");
				FileWriter writer = new FileWriter(occupationFile);
				
//				writesCsvFromBean(Paths.get(agentsFile.getPath()),agentList);
				columnString.add("[)~=_(]\r");
				CSVUtils.writeLine(writer, columnString, "[)!|;(]");
				
				for(Object[] object : dataList) {
					
					List<String> stringList = Stream.of(object).map(value->value ==null?"":value).map(String::valueOf).collect(Collectors.toList());
					stringList.add("[)~=_(]\r");
					CSVUtils.writeLine(writer, stringList, "[)!|;(]");
				}
				

				writer.close();
//				String tempDir= fileDir.concat("\\OccupationInfo").concat(FileService.getDateToString(new Date()));
				String tempDir= fileDir;
				Path filePath = Paths.get(tempDir.concat("\\Occupation.csv"));
				Files.createDirectories(filePath.getParent());
				Files.move(Paths.get(occupationFile.getPath()),Paths.get(tempDir.concat("\\Occupation.csv")),StandardCopyOption.REPLACE_EXISTING);
				Files.deleteIfExists(Paths.get(occupationFile.getPath()));
				
				/*
				writer.close();
				
				FileOutputStream fos = new FileOutputStream("Occupation.zip");
				ZipOutputStream zipOs = new ZipOutputStream(fos);

				FileService.writeToZipFile(occupationFile, zipOs);

				zipOs.close();
				fos.close();
				
				
				File toCheckSumFile = new File("Occupation.zip");

				MessageDigest md5Digest = MessageDigest.getInstance("MD5");

				// Get the checksum
				String checksum =FileService.getFileChecksum(md5Digest, toCheckSumFile);
				File checksumFile = new File("OccupationInfoChecksum".concat(".md5"));
				
				objectMapper.writeValue(checksumFile,checksum);
				String tempDir= fileDir.concat(":\\AceSharedFolder\\OccupationInfo").concat(FileService.getDateToString(new Date()));
				
				Path filePath = Paths.get(tempDir.concat("\\Occupation.zip"));
				Files.createDirectories(filePath.getParent());
				
				Files.move(Paths.get(toCheckSumFile.getPath()),Paths.get(tempDir.concat("\\Occupation.zip")),StandardCopyOption.REPLACE_EXISTING);
				Files.move(Paths.get(checksumFile.getPath()),Paths.get(tempDir.concat("\\OccupationInfoChecksum.md5")),StandardCopyOption.REPLACE_EXISTING);
				
				
				Files.deleteIfExists(Paths.get("Occupation.zip"));
				Files.deleteIfExists(Paths.get("OccupationInfochecksum.md5"));
				Files.deleteIfExists(Paths.get(occupationFile.getPath()));
*/

			}
			
		}

}
