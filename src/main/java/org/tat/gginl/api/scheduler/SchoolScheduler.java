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
import org.tat.gginl.api.domains.services.SchoolService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class SchoolScheduler {

	@Autowired
	private SchoolService schoolService;

	@Value("${fileDir}")
	private String fileDir;

	@Scheduled(cron = "0 */5 * ? * *")
	public void createSchoolFolder() throws Exception {

		Date startDate = FileService.resetStartDate(new Date());
		startDate = FileService.minusDays(startDate, 2);
		Date endDate = FileService.resetEndDate(new Date());

		// List<Agent> agentList = agentService.findAll();

		List<Object> columnNameList = schoolService.findAllColumnName();
		List<Object[]> dataList = schoolService.findAllNativeObject();

		if (dataList.size() > 0) {

			List<String> columnString = columnNameList.stream().map(String::valueOf).collect(Collectors.toList());

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			String fileName="School_".concat(FileService.getDateToString(new Date())).concat(".csv");
			File schoolFile = new File(fileName);
			FileWriter writer = new FileWriter(schoolFile);

//			writesCsvFromBean(Paths.get(agentsFile.getPath()),agentList);
			columnString.add("[)~=_(]\r");
			CSVUtils.writeLine(writer, columnString, "[)!|;(]");

			for (Object[] object : dataList) {

				List<String> stringList = Stream.of(object).map(value -> value == null ? "" : value)
						.map(String::valueOf).collect(Collectors.toList());
				stringList.add("[)~=_(]\r");
				CSVUtils.writeLine(writer, stringList, "[)!|;(]");
			}

			writer.close();
//			String tempDir= fileDir.concat("\\AgentsInfo").concat(FileService.getDateToString(new Date()));
			String tempDir = fileDir.concat("\\School");
			Path filePath = Paths.get(tempDir.concat("\\"+fileName));
			Files.createDirectories(filePath.getParent());
			Files.move(Paths.get(schoolFile.getPath()), Paths.get(tempDir.concat("\\"+fileName)),
					StandardCopyOption.REPLACE_EXISTING);
			Files.deleteIfExists(Paths.get(schoolFile.getPath()));
			/*
			 * writer.close();
			 * 
			 * FileOutputStream fos = new FileOutputStream("Agents.zip"); ZipOutputStream zipOs = new
			 * ZipOutputStream(fos);
			 * 
			 * FileService.writeToZipFile(agentsFile, zipOs);
			 * 
			 * zipOs.close(); fos.close();
			 * 
			 * 
			 * File toCheckSumFile = new File("Agents.zip");
			 * 
			 * MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			 * 
			 * // Get the checksum String checksum = FileService.getFileChecksum(md5Digest, toCheckSumFile); File
			 * checksumFile = new File("AgentsInfoChecksum".concat(".md5"));
			 * 
			 * objectMapper.writeValue(checksumFile,checksum); String tempDir=
			 * fileDir.concat(":\\AceSharedFolder\\AgentInfo").concat(FileService.getDateToString(new Date()));
			 * 
			 * Path filePath = Paths.get(tempDir.concat("\\Agents.zip")); Files.createDirectories(filePath.getParent());
			 * 
			 * Files.move(Paths.get(toCheckSumFile.getPath()),Paths.get(tempDir.concat("\\Agents.zip")),
			 * StandardCopyOption.REPLACE_EXISTING);
			 * Files.move(Paths.get(checksumFile.getPath()),Paths.get(tempDir.concat("\\AgentsInfoChecksum.md5")),
			 * StandardCopyOption.REPLACE_EXISTING);
			 * 
			 * 
			 * 
			 * 
			 * Files.deleteIfExists(Paths.get("Agents.zip")); Files.deleteIfExists(Paths.get("AgentsInfochecksum.md5"));
			 * Files.deleteIfExists(Paths.get(agentsFile.getPath()));
			 * 
			 */
		}

	}

}
