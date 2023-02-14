package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import dao.BoardDao;
import dao.MemberDao;
import db.DBInfo;
import service.ChangePasswordService;
import service.LoginService;
import service.RegisterService;

@Configuration
@EnableTransactionManagement
public class MemberConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName(DBInfo.driverClassName);
		ds.setUrl(DBInfo.url);
		ds.setUsername(DBInfo.username);
		ds.setPassword(DBInfo.password);
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setMaxIdle(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public RegisterService registerService() {
		return new RegisterService(memberDao());
	}
	
	@Bean
	public LoginService loginService() {
		return new LoginService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePasswordService() {
		return new ChangePasswordService(memberDao());
	}
	
	@Bean
	public BoardDao boardDao() {
		return new BoardDao(dataSource());
	}
	
}
