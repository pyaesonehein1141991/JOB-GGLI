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
import org.tat.gginl.api.domains.services.CustomerService;
import org.tat.gginl.api.domains.services.FileService;
import org.tat.gginl.api.domains.services.RunTimeService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class CustomerScheduler {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RunTimeService runTimeService;

	@Value("${fileDir}")
	private String fileDir;

	@Scheduled(cron = "0 */5 * ? * *")
	public void createCustomerFolder() throws Exception {

		/*
		 * Date startDate = FileService.resetStartDate(new Date()); startDate = FileService.minusDays(startDate, 2);
		 * Date endDate = FileService.resetEndDate(new Date());
		 */

		/*
		 * List<Object[]> dataList = customerService.findAllNativeObject(startDate, endDate);
		 */

		// RUNTIMEDATE
		Date query2 = runTimeService.findRuntime();
		Date nowDate = FileService.resetEndDate(new Date());
		List<Object> columnNameList = customerService.findAllColumnName();
		List<Object[]> dateList = customerService.findAllNativeObject(query2, nowDate);

		// List<Customer> customerList =
		// customerService.findByRecorderCreatedDateBetweenOrRecorderUpdatedDateBetween(startDate,
		// endDate);

		if (dateList.size() > 0) {

			List<String> columnString = columnNameList.stream().map(String::valueOf).collect(Collectors.toList());

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			File customerFile = new File("Customers.csv");
			FileWriter writer = new FileWriter(customerFile);

			// writesCsvFromBean(Paths.get(agentsFile.getPath()),agentList);
			columnString.add("[)~=_(]\r");
			CSVUtils.writeLine(writer, columnString, "[)!|;(]");

			for (Object[] object : dateList) {

				List<String> stringList = Stream.of(object).map(value -> value == null ? "" : value)
						.map(String::valueOf).collect(Collectors.toList());
				stringList.add("[)~=_(]\r");
				CSVUtils.writeLine(writer, stringList, "[)!|;(]");
			}

			writer.close();
			// String tempDir= fileDir.concat("\\CustomersInfo").concat(FileService.getDateToString(new
			// Date()));
			String tempDir = fileDir;
			Path filePath = Paths.get(tempDir.concat("\\Customers.csv"));
			Files.createDirectories(filePath.getParent());
			Files.move(Paths.get(customerFile.getPath()), Paths.get(tempDir.concat("\\Customers.csv")),
					StandardCopyOption.REPLACE_EXISTING);
			Files.deleteIfExists(Paths.get(customerFile.getPath()));
			/*
			 * 
			 * writer.close();
			 * 
			 * FileOutputStream fos = new FileOutputStream("Customer.zip"); ZipOutputStream zipOs = new
			 * ZipOutputStream(fos);
			 * 
			 * FileService.writeToZipFile(customerFile, zipOs);
			 * 
			 * zipOs.close(); fos.close();
			 * 
			 * 
			 * File toCheckSumFile = new File("Customer.zip");
			 * 
			 * MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			 * 
			 * // Get the checksum String checksum = FileService.getFileChecksum(md5Digest, toCheckSumFile); File
			 * checksumFile = new File("CustomerInfoChecksum".concat(".md5"));
			 * 
			 * objectMapper.writeValue(checksumFile,checksum); String tempDir=
			 * fileDir.concat(":\\AceSharedFolder\\CustomerInfo").concat(FileService.getDateToString(new Date()));
			 * 
			 * Path filePath = Paths.get(tempDir.concat("\\Customer.zip"));
			 * Files.createDirectories(filePath.getParent());
			 * 
			 * Files.move(Paths.get(toCheckSumFile.getPath()),Paths.get(tempDir.concat("\\Customer.zip")),
			 * StandardCopyOption.REPLACE_EXISTING);
			 * Files.move(Paths.get(checksumFile.getPath()),Paths.get(tempDir.concat(
			 * "\\CustomerInfoChecksum.md5")),StandardCopyOption.REPLACE_EXISTING);
			 * 
			 * 
			 * 
			 * Files.deleteIfExists(Paths.get("Customer.zip"));
			 * Files.deleteIfExists(Paths.get("CustomerInfochecksum.md5"));
			 * Files.deleteIfExists(Paths.get(customerFile.getPath()));
			 * 
			 */
		}

	}

}
