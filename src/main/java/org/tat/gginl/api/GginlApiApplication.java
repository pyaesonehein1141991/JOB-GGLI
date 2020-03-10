package org.tat.gginl.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.tat.gginl.api.common.SecurityUser;
import org.tat.gginl.api.common.emumdata.Role;
import org.tat.gginl.api.domains.TimeToSave;
import org.tat.gginl.api.domains.services.RunTimeService;
import org.tat.gginl.api.domains.services.UserService;
import org.tat.gginl.api.scheduler.AgentScheduler;
import org.tat.gginl.api.scheduler.BankScheduler;
import org.tat.gginl.api.scheduler.BranchSchedular;
import org.tat.gginl.api.scheduler.CountryScheduler;
import org.tat.gginl.api.scheduler.CustomerScheduler;
import org.tat.gginl.api.scheduler.EntityScheduler;
import org.tat.gginl.api.scheduler.GradeInfoScheduler;
import org.tat.gginl.api.scheduler.HospitalScheduler;
import org.tat.gginl.api.scheduler.OccupationSchedular;
import org.tat.gginl.api.scheduler.OrganizationSchedular;
import org.tat.gginl.api.scheduler.PaymentTypeSchedular;
import org.tat.gginl.api.scheduler.ProductSchedular;
import org.tat.gginl.api.scheduler.ProvinceScheular;
import org.tat.gginl.api.scheduler.RelationShipSchedular;
import org.tat.gginl.api.scheduler.SaleManSchedular;
import org.tat.gginl.api.scheduler.SalePointScheduler;
import org.tat.gginl.api.scheduler.SchoolScheduler;
import org.tat.gginl.api.scheduler.StatCodeScheduler;
import org.tat.gginl.api.scheduler.TownShipCoeSchedular;
import org.tat.gginl.api.scheduler.TowshipSchedular;

@SpringBootApplication
public class GginlApiApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private UserService userService;

	@Autowired
	private AgentScheduler agent;

	@Autowired
	private BankScheduler bank;

	@Autowired
	private BranchSchedular branch;

	@Autowired
	private CountryScheduler country;

	@Autowired
	private CustomerScheduler customer;

	@Autowired
	private GradeInfoScheduler grade;

	@Autowired
	private HospitalScheduler hospital;

	@Autowired
	private OccupationSchedular occupaton;

	@Autowired
	private OrganizationSchedular organization;

	@Autowired
	private PaymentTypeSchedular paymentType;

	@Autowired
	private ProductSchedular product;

	@Autowired
	private ProvinceScheular province;

	@Autowired
	private SaleManSchedular saleman;

	@Autowired
	private SalePointScheduler salepoint;

	@Autowired
	private TownShipCoeSchedular townshipCode;

	@Autowired
	private TowshipSchedular township;

	@Autowired
	private RelationShipSchedular relationship;

	@Autowired
	private RunTimeService runtimeservice;

	@Autowired
	private StatCodeScheduler statCode;

	@Autowired
	private EntityScheduler entity;

	@Autowired
	private SchoolScheduler school;

	public static void main(String[] args) {
		SpringApplication.run(GginlApiApplication.class, args);

		// SpringApplication application = new SpringApplication(GginlApiApplication.class);
		// Properties properties = new Properties();
		// properties.setProperty("spring.main.banner-mode", "log");
		// properties.setProperty("logging.file", "C:/APILOG.log");
		// properties.setProperty("logging.pattern.console", "");
		// application.setDefaultProperties(properties);
		// application.run(args);

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public void shutdownApp() {
		int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);
		System.exit(exitCode);
	}

	@Override
	public void run(String... params) throws Exception {
		SecurityUser admin = new SecurityUser();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@email.com");
		admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

		// userService.signup(admin);

		SecurityUser client = new SecurityUser();
		client.setUsername("client");
		client.setPassword("client");
		client.setEmail("client@email.com");
		client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

		// userService.signup(client);

		agent.createAgentFolder();
		bank.createBankFolder();
		branch.createAgentFolder();
		country.createBankFolder();
		customer.createCustomerFolder();
		grade.createGradeInfoFolder();
		hospital.createHospitalFolder();
		occupaton.createOccupationSFolder();
		organization.createOrganizationFileFolder();
		paymentType.createPaymentTypeFolder();
		product.createProductFolder();
		province.createProvienceFolder();
		relationship.createRelationShipFolder();
		saleman.createSaleManFolder();
		salepoint.createSalePointFolder();
		townshipCode.createAgentFolder();
		township.createSalePointFolder();
		statCode.createStatCodeFolder();
		entity.createEntityFolder();
		school.createSchoolFolder();
		Optional<TimeToSave> runtimes = runtimeservice.findbyId("1");
		if (runtimes.isPresent()) {
			runtimes.get().setRuntime(new Date());
		}
		runtimeservice.updateTime(runtimes);

		// shutdownApp();

	}
}
