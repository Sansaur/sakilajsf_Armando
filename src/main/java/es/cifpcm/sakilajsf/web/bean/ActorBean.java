package es.cifpcm.sakilajsf.web.bean;

import es.cifpcm.sakilajsf.web.bean.datos.ImplementacionDao;
import es.cifpcm.sakilajsf.web.bean.modelo.Actor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

/**
 *
 * He tenido que cambiar esto a actorBean2 porque me estaba tomando del pelo por
 * culpa de un refactorizado Explico en la clase Básicamente, al cambiar de
 * paquetes, parece que aún los nombres de las beans se mantienen. Sacando este
 * error:
 *
 *
 * Bean name is ambiguous. Name actorBean resolves to beans: [Managed Bean
 * [class es.cifpcm.sakilajsf.web.bean.controlador.ActorBean] with qualifiers
 * [@Default @Named @Any], Managed Bean [class
 * es.cifpcm.sakilajsf.web.bean.ActorBean] with qualifiers [@Default @Named
 *
 * @Any]]
 *
 *
 * @author alumno
 */
@Named(value = "actorBean2")
@RequestScoped
public class ActorBean extends Actor {

    public ActorBean() {
    }

    @NotNull(message = "Nombre no puede estar vacío")
    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @NotNull(message = "Apellidos no puede estar vacío")
    @Override
    public String getApellidos() {
        return super.getApellidos();
    }

    public List<Actor> getActorList() {
        ImplementacionDao Impl = new ImplementacionDao();
        return Impl.selectAll();
    }

    public String save() {
        try {
            ImplementacionDao Impl = new ImplementacionDao();
            Actor nuevo = Impl.insert(this);
            // Dándole al bean las características.
            setId(nuevo.getId());
            setNombre(nuevo.getNombre());
            setApellidos(nuevo.getApellidos());
            setFecha(nuevo.getFecha());
            if (nuevo == null) {
                throw new Exception();
            } else {
                return "actorDetail";
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de inserción", "Error de inserción"));
            return "/error";
        }
    }
}
