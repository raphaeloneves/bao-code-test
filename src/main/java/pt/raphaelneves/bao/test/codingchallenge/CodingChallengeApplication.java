package pt.raphaelneves.bao.test.codingchallenge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import pt.raphaelneves.bao.test.codingchallenge.integrations.importers.DataImporter;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@Slf4j
public class CodingChallengeApplication implements CommandLineRunner {

	private final DataImporter elementImporter;
	private final DataImporter groupImporter;

	@Autowired
	public CodingChallengeApplication(@Qualifier("elementImporter") DataImporter elementImporter,
									  @Qualifier("groupImporter") DataImporter groupImporter) {
		this.elementImporter = elementImporter;
		this.groupImporter = groupImporter;
	}

	public static void main(String[] args) {
		SpringApplication.run(CodingChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loadDataFromIntegrations();
	}

	private void loadDataFromIntegrations() {
		log.info("IMPORTING ELEMENTS DATA FROM INTEGRATIONS");
		elementImporter.importData();
		log.info("IMPORTING ELEMENTS GROUP DATA FROM INTEGRATIONS");
		groupImporter.importData();
	}
}
