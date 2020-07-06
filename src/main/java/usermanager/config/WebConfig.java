package usermanager.config;
import usermanager.dao.UserDao;
import usermanager.dao.UserDaoImpl;
import usermanager.model.User;
import usermanager.service.UserService;
import usermanager.service.UserServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = "usermanager")
public class WebConfig {

    @Value("${db.url}")
    String url;

    @Value("${db.username}")
    String username;

    @Value("${db.password}")
    String password;

    @Value("${db.parameters}")
    String parameters;


    @Value("${db.driverClassName}")
    String driverClassName;


    @Autowired
    private Environment env;

    @Bean
    ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    @Bean
    BasicDataSource dataSource () {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setConnectionProperties(parameters);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        //?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC"
        return basicDataSource;
    }


    @Bean
    LocalSessionFactoryBean AnnotatedSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        Properties props=new Properties();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class);


        return factoryBean;
    }

    @Bean
    UserDao userDao () {
        return new UserDaoImpl();
    }

    @Bean
    UserService userService (){
        return new UserServiceImpl();
    }

    @Bean
    HibernateTransactionManager transactionManager (){
        return new HibernateTransactionManager(AnnotatedSessionFactory().getObject());
    }



}
