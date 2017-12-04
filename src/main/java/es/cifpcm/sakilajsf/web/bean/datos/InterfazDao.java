package es.cifpcm.sakilajsf.web.bean.datos;

import es.cifpcm.sakilajsf.web.bean.modelo.Actor;
import java.util.List;

public interface InterfazDao {
    public List<Actor> selectAll();
    public Actor insert(Actor nuevoActor);
}
