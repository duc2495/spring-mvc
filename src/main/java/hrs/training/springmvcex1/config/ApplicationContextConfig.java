package hrs.training.springmvcex1.config;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import hrs.training.springmvcex1.dao.CustomerDAO;
import hrs.training.springmvcex1.dao.impl.CustomerDAOImpl;

@Configuration
@ComponentScan("hrs.training.springmvcex1.*")
@EnableTransactionManagement

// Load to Environment.
@PropertySources({ @PropertySource("classpath:datasource-config.properties") })

public class ApplicationContextConfig {

	// Lưu trữ các giá thuộc tính load bởi @PropertySource.
	@Autowired
	private Environment env;

	@Bean(name = "viewResolver")
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();

		// TilesView 3
		viewResolver.setViewClass(TilesView.class);

		return viewResolver;
	}

	@Bean(name = "tilesConfigurer")
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();

		// TilesView 3
		tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");

		return tilesConfigurer;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		// See: datasource-config.properties
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		System.out.println("## getDataSource: " + dataSource);

		return dataSource;
	}
	
    @Bean
    public CustomerDAO getCustomerDAO() {
        return new CustomerDAOImpl(getDataSource());
    }
}