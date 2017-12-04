package es.cifpcm.sakilajsf.web.bean.datos;

import java.util.ResourceBundle;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sansaur
 */
public class DataSource {

    public static MysqlDataSource recibirDataSourcePropiedades() {
        MysqlDataSource mysqlDS = null;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config.conexion");
            Class.forName(rb.getString("database.driver"));
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(rb.getString("database.url"));
            mysqlDS.setUser(rb.getString("database.user"));
            mysqlDS.setPassword(rb.getString("database.password"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mysqlDS;
    }
}
