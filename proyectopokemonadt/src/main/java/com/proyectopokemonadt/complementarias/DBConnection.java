package com.proyectopokemonadt.complementarias;

import java.io.FileInputStream;
import java.util.Properties;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection {
    
    private static DBConnection instance;

    private DBConnection (DataSource dataSource) {
    
    }

    public static DBConnection getInstance(DataSource dataSource) {
        if(instance == null){
            return new DBConnection(dataSource);
        }
        return instance;
    }

     public static DataSource getMySQLDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mysqlDS = null;

        try {
            fis = new FileInputStream("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios\\db.properties");
            props.load(fis);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setUrl(props.getProperty("URL"));
            mysqlDS.setUser(props.getProperty("USUARIO"));
            mysqlDS.setPassword(props.getProperty("PASSWORD"));
        } catch (Exception e) {
            e.getMessage();
        }
        return mysqlDS;
    }
}
