package com.company.graphqldemor1;

import com.company.graphqldemor1.entity.User;
import com.company.graphqldemor1.security.FullAccessRole;
import com.google.common.base.Strings;
import io.jmix.core.SaveContext;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.core.security.SystemAuthenticator;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.List;

@ConfigurationPropertiesScan
@SpringBootApplication
public class GraphqlDemoR1Application {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoR1Application.class, args);
	}

	@Bean
	@Primary
	@ConfigurationProperties("main.datasource")
	DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("main.datasource.hikari")
	DataSource dataSource(DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	@EventListener
	public void printApplicationUrl(ApplicationStartedEvent event) {
		LoggerFactory.getLogger(GraphqlDemoR1Application.class).info("Application started at "
				+ "http://localhost:"
				+ environment.getProperty("local.server.port")
				+ Strings.nullToEmpty(environment.getProperty("server.servlet.context-path")));
	}

	@EventListener(ApplicationStartedEvent.class)
	private void createDemoUser(ApplicationStartedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();

		SystemAuthenticator authenticator = applicationContext.getBean(SystemAuthenticator.class);
		UnconstrainedDataManager dataManager = applicationContext.getBean(UnconstrainedDataManager.class);
		GraphQLDemoProperties properties = applicationContext.getBean(GraphQLDemoProperties.class);

		authenticator.withSystem(() -> {
			List<User> users = dataManager.load(User.class)
					.all()
					.list();
			SaveContext saveContext = new SaveContext();
			for (User user : users) {
				user.setPassword(properties.getPassword());
				saveContext.saving(user);
			}
			dataManager.save(saveContext);

			User adminUser = dataManager.load(User.class)
					.condition(PropertyCondition.equal("username", properties.getAdmin()))
					.optional()
					.orElse(null);
			if (adminUser == null) {
				User newUser = dataManager.create(User.class);
				newUser.setUsername(properties.getAdmin());
				newUser.setPassword(properties.getPassword());
				newUser.setActive(true);

				RoleAssignmentEntity assignment = dataManager.create(RoleAssignmentEntity.class);
				assignment.setUsername(properties.getAdmin());
				assignment.setRoleCode(FullAccessRole.CODE);
				assignment.setRoleType(RoleAssignmentRoleType.RESOURCE);

				dataManager.save(newUser, assignment);
			}
			return null;
		});
	}
}
