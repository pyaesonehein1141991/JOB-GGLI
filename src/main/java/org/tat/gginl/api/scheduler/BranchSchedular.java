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
import org.tat.gginl.api.domains.Branch;
import org.tat.gginl.api.domains.repository.BranchRepository;
import org.tat.gginl.api.domains.services.BranchService;
import org.tat.gginl.api.domains.services.FileService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class BranchSchedular {
	

	@Autowired
	private BranchService branchService;
	
	@Value("${fileDir}")
	private String fileDir;
	
	@Scheduled(cron = "0 */5 * ? * *")
	public void createAgentFolder() throws Exception {
		
		Date startDate =FileService.resetStartDate(new Date());
		startDate =FileService.minusDays(startDate, 2);
		Date endDate =FileService.resetEndDate(new Date());
		
		List<Branch> branchList = branchService.findAll();
		

		List<Object> columnNameList = branchService.findAllColumnName();
		List<Object[]> dataList = branchService.findAllNativeObject();
		
		if(branchList.size()>0) {
			
			List<String> columnString = columnNameList.stream().map(String::valueOf).collect(Collectors.toList()); 
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			File agentsFile = new File("Branchs.csv");
			FileWriter writer = new FileWriter(agentsFile);
//			writesCsvFromBean(Paths.get(agentsFile.getPath()),agentList);
			columnString.add("[)~=_(]\r");
			CSVUtils.writeLine(writer, columnString, "[)!|;(]");
			for(Object[] object : dataList) {
				List<String> stringList = Stream.of(object).map(value->value ==null?"":value).map(String::valueOf).collect(Collectors.toList());
				stringList.add("[)~=_(]\r");
				CSVUtils.writeLine(writer, stringList, "[)!|;(]");
			}
			writer.close();
//			String tempDir= fileDir.concat("\\BranchsInfo").concat(FileService.getDateToString(new Date()));
			String tempDir= fileDir;
			Path filePath = Paths.get(tempDir.concat("\\Branchs.csv"));
			Files.createDirectories(filePath.getParent());
			Files.move(Paths.get(agentsFile.getPath()),Paths.get(tempDir.concat("\\Branchs.csv")),StandardCopyOption.REPLACE_EXISTING);
			Files.deleteIfExists(Paths.get(agentsFile.getPath()));
		/*	
			writer.close();
			
			FileOutputStream fos = new FileOutputStream("Branch.zip");
			ZipOutputStream zipOs = new ZipOutputStream(fos);

			FileService.writeToZipFile(agentsFile, zipOs);

			zipOs.close();
			fos.close();
			
			
			File toCheckSumFile = new File("Branch.zip");

			MessageDigest md5Digest = MessageDigest.getInstance("MD5");

			// Get the checksum
			String checksum = FileService.getFileChecksum(md5Digest, toCheckSumFile);
			File checksumFile = new File("BranchInfoChecksum".concat(".md5"));
			
			objectMapper.writeValue(checksumFile,checksum);
			String tempDir= fileDir.concat(":\\AceSharedFolder\\BranchInfo").concat(FileService.getDateToString(new Date()));
			
			Path filePath = Paths.get(tempDir.concat("\\Branch.zip"));
			Files.createDirectories(filePath.getParent());
			
			Files.move(Paths.get(toCheckSumFile.getPath()),Paths.get(tempDir.concat("\\Branch.zip")),StandardCopyOption.REPLACE_EXISTING);
			Files.move(Paths.get(checksumFile.getPath()),Paths.get(tempDir.concat("\\BranchInfoChecksum.md5")),StandardCopyOption.REPLACE_EXISTING);
			
			
			
			Files.deleteIfExists(Paths.get("Branch.zip"));
			Files.deleteIfExists(Paths.get("BranchInfochecksum.md5"));
			Files.deleteIfExists(Paths.get(agentsFile.getPath()));
*/

		}
		
	}
    

}
