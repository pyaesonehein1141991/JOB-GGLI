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
import org.tat.gginl.api.domains.services.FileService;
import org.tat.gginl.api.domains.services.HospitalService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class HospitalScheduler {

	@Autowired
	private HospitalService hospitalService;

	@Value("${fileDir}")
	private String fileDir;

	@Scheduled(cron = "0 */5 * ? * *")
	public void createHospitalFolder() throws Exception {

		Date startDate = FileService.resetStartDate(new Date());
		startDate = FileService.minusDays(startDate, 2);
		Date endDate = FileService.resetEndDate(new Date());

		// List<Bank> agentList = bankService.findAll();

		List<Object> columnNameList = hospitalService.findAllColumnName();
		List<Object[]> dataList = hospitalService.findAllNativeObject();

		if (dataList.size() > 0) {

			List<String> columnString = columnNameList.stream().map(String::valueOf).collect(Collectors.toList());

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			String fileName="Hospital_".concat(FileService.getDateToString(new Date())).concat(".csv");
			File hospitalFile = new File(fileName);
			FileWriter writer = new FileWriter(hospitalFile);

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
//			String tempDir= fileDir.concat("\\HospitalInfo").concat(FileService.getDateToString(new Date()));
			String tempDir =fileDir.concat("\\Hospital");
			Path filePath = Paths.get(tempDir.concat("\\"+fileName));
			Files.createDirectories(filePath.getParent());
			Files.move(Paths.get(hospitalFile.getPath()), Paths.get(tempDir.concat("\\"+fileName)),
					StandardCopyOption.REPLACE_EXISTING);
			Files.deleteIfExists(Paths.get(hospitalFile.getPath()));

			/*
			 * 
			 * writer.close();
			 * 
			 * FileOutputStream fos = new FileOutputStream("Hospital.zip"); ZipOutputStream
			 * zipOs = new ZipOutputStream(fos);
			 * 
			 * FileService.writeToZipFile(agentsFile, zipOs);
			 * 
			 * zipOs.close(); fos.close();
			 * 
			 * 
			 * File toCheckSumFile = new File("Hospital.zip");
			 * 
			 * MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			 * 
			 * // Get the checksum String checksum = FileService.getFileChecksum(md5Digest,
			 * toCheckSumFile); File checksumFile = new
			 * File("HospitalInfoChecksum".concat(".md5"));
			 * 
			 * objectMapper.writeValue(checksumFile,checksum); String tempDir=
			 * fileDir.concat(":\\AceSharedFolder\\HospitalInfo").concat(FileService.
			 * getDateToString(new Date()));
			 * 
			 * Path filePath = Paths.get(tempDir.concat("\\Hospital.zip"));
			 * Files.createDirectories(filePath.getParent());
			 * 
			 * Files.move(Paths.get(toCheckSumFile.getPath()),Paths.get(tempDir.concat(
			 * "\\Hospital.zip")),StandardCopyOption.REPLACE_EXISTING);
			 * Files.move(Paths.get(checksumFile.getPath()),Paths.get(tempDir.concat(
			 * "\\HospitalInfoChecksum.md5")),StandardCopyOption.REPLACE_EXISTING);
			 * 
			 * 
			 * 
			 * Files.deleteIfExists(Paths.get("Hospital.zip"));
			 * Files.deleteIfExists(Paths.get("HospitalInfoChecksum.md5"));
			 * Files.deleteIfExists(Paths.get(agentsFile.getPath()));
			 * 
			 */
		}

	}
}
