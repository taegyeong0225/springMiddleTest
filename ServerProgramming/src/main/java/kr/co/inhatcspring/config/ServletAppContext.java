package kr.co.inhatcspring.config;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.inhatcspring.mapper.MapperInterface;

@Configuration
// Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다.
@EnableWebMvc
// 스캔할 패키지를 지정한다.
@ComponentScan ("kr.co.inhatcspring.controller")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {
	@Value("${db.classname}")
    private String db_classname;

    @Value("${db.url}")
    private String db_url;

    @Value("${db.username}")
    private String db_username;

    @Value("${db.password}")
    private String db_password;
	
	// Controller의 메서드가 반환하는 23p의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.
	public void configureViewResolvers (ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers (registry);
		registry.jsp ("/WEB-INF/views/", ".jsp");
	}
	// 정적 파일의 경로를 매핑한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler ("/**").addResourceLocations ("/resources/") ;
	}
	// 데이터베이스 접속 정보 관리
	@Bean
	public BasicDataSource dataSource() {
	    BasicDataSource source = new BasicDataSource();
	    source.setDriverClassName(db_classname);
	    source.setUrl(db_url);
	    source.setUsername(db_username);
	    source.setPassword(db_password);
	    
	    return source;
	}

	// 쿼리문과 접속 관리하는 객체
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    factoryBean.setDataSource(source);
	    SqlSessionFactory factory = factoryBean.getObject();
	    
	    return factory;
	}

	// 쿼리문 실행을 위한 객체
	@Bean
	public MapperFactoryBean<MapperInterface> test_mapper(SqlSessionFactory factory) throws Exception {
	    MapperFactoryBean<MapperInterface> factoryBean = new MapperFactoryBean<MapperInterface>(MapperInterface.class);
	    factoryBean.setSqlSessionFactory(factory);
	    
	    return factoryBean;
	}
	

}