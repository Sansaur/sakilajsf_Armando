package es.cifpcm.sakilajsf.web.bean.datos;

import es.cifpcm.sakilajsf.web.bean.modelo.Actor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImplementacionDao implements InterfazDao {

    public ImplementacionDao() {

    }

    @Override
    public List<Actor> selectAll() {
        List<Actor> actors = new ArrayList<>();
        try {
            /* Hacemos la query aquí */
            Connection conexion = DataSource.recibirDataSourcePropiedades().getConnection();
            String consulta = "SELECT * FROM actor";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Actor nuevo = new Actor();
                nuevo.setId(rs.getInt("actor_id"));
                nuevo.setNombre(rs.getString("first_name"));
                nuevo.setApellidos(rs.getString("last_name"));
                nuevo.setFecha(rs.getDate("last_update"));
                actors.add(nuevo);
            }
            rs.close();
            pstmt.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actors;
    }

    @Override
    public Actor insert(Actor nuevoActor) {
        //Actor actorRetorno = new Actor();
        try {
            Connection conn = DataSource.recibirDataSourcePropiedades().getConnection();
            System.err.println("Entrando en insert");
            System.err.println("Conexion en insert");
            // actor_id es AutoIncrement
            // last_update es DEFAULT CURRENT TIMESTAMP
            String consulta = "INSERT INTO actor(first_name, last_name) VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nuevoActor.getNombre());
            pstmt.setString(2, nuevoActor.getApellidos());
            pstmt.executeUpdate();
            System.err.println("Update en insert");
            // Lo dejo comentado para que se vea que sé de lo que va la idea.
            // Pero con generatedKeys solamente me permite coger first_name y last_name
            // Que son las dos cosas que vamos a insertar (actor_id es AutoIncrement y last_update se rellena automáticamente)
            // Rellenando actor retorno
            //actorRetorno.setId(pstmt.getGeneratedKeys().getInt("actor_id"));
            //actorRetorno.setNombre(pstmt.getGeneratedKeys().getString("first_name"));
            //actorRetorno.setApellidos(pstmt.getGeneratedKeys().getString("last_name"));
            //actorRetorno.setFecha(pstmt.getGeneratedKeys().getDate("last_update"));
            System.err.println("Retornando en insert");
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Con lo del generatedKeys solamente cogemos aquellas claves que hayamos insertado en la propia query
        //return actorRetorno;
        // Esto de aquí abajo fue mi idea inicial, devolver el último de "todos los actores" tras meter el nuevo, why not?
        return selectAll().get(selectAll().size()-1);
    }
}
