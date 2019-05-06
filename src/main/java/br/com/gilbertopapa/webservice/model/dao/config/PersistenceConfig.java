package br.com.gilbertopapa.webservice.model.dao.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class PersistenceConfig {

    private static final String DATABASE_URL = System.getenv("DATABASE_URL");
    private static final String DATABASE_USERNAME = System.getenv("DATABASE_USERNAME");
    private static final String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");
    private static final String URL_CONNECTION_PARAMS = "?useSSL=false&autoReconnect=true";
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    @SuppressWarnings("unchecked")
    public EntityManagerFactory createEntityManagerFactory() {
        @SuppressWarnings("rawtypes")
        Map properties = new HashMap();
        properties.put("connection.driver_class", DRIVER);
        properties.put("hibernate.connection.url", DATABASE_URL + URL_CONNECTION_PARAMS);
        properties.put("hibernate.connection.username", DATABASE_USERNAME);
        properties.put("hibernate.connection.password", DATABASE_PASSWORD);
        properties.put("hibernate.dialect", HIBERNATE_DIALECT);
        properties.put("hibernate.enable_lazy_load_no_trans", true);
        properties.put("hibernate.connection.pool_size", 100);
        properties.put("hibernate.show_sql", false);

        try {
            return Persistence.createEntityManagerFactory("jpa", properties);
        } catch (Exception e) {
           // log.messageError("Failed connecting to database. Message: " + e.getMessage() + "; Details: " + e.getCause().toString());
            throw e;
        }
    }
}
