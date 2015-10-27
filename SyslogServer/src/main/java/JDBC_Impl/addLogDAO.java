package JDBC_Impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by root on 23.10.15.
 */
public interface addLogDAO {
    ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    JDBC_Impl.addLogImpl DAO = (JDBC.addLog) appContext.getBean("addLog");
}
