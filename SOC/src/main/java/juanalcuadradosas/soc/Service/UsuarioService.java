package juanalcuadradosas.soc.Service;

import juanalcuadradosas.soc.Entity.Area;
import juanalcuadradosas.soc.Entity.Usuario;
import juanalcuadradosas.soc.Model.DatosPersona;
import juanalcuadradosas.soc.Model.ParametroItem;
import juanalcuadradosas.soc.Model.PersonaItem;
import juanalcuadradosas.soc.Model.RespuestaTransaccion;
import juanalcuadradosas.soc.Repository.AreaRepository;
import juanalcuadradosas.soc.Repository.UsuarioRepository;
import juanalcuadradosas.soc.Util.ReglasExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<ParametroItem> getAreas(){
        List<Area> listadoAreas = areaRepository.findAll();

        return listadoAreas.stream()
                .map(area -> new ParametroItem(area.getCodigo(), area.getDescripcion()))
                .collect(Collectors.toList());
    }

    public RespuestaTransaccion crearEditarPersona(DatosPersona body) {
        body.validarIntegridad();

        Optional<Area> area = areaRepository.findById(body.getArea());
        if (area.isEmpty())
            throw new ReglasExcepcion("El área no existe");

        Usuario usuario = new Usuario(body.getId(), body.getIdentificacion(), body.getNombre(), body.getCorreo(), area.get(),
                body.getAdministrador() ? 1 : 0, body.getClave());

        usuarioRepository.save(usuario);

        return new RespuestaTransaccion((long)1, true);
    }

    public RespuestaTransaccion loginPersona(String id, String clave) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty())
            throw new ReglasExcepcion("El usuario no existe");

        if (!Objects.equals(usuario.get().getClave(), clave))
            throw new ReglasExcepcion("La clave es incorrecta");

        long numTra = usuario.get().getIsAdmin();

        return new RespuestaTransaccion(numTra, true);
    }

    public List<PersonaItem> getUsuarios(String idAdministrador) {
        Optional<Usuario> admin = usuarioRepository.findById(idAdministrador);
        if (admin.isEmpty())
            throw new ReglasExcepcion("La persona no existe");

        if (admin.get().getIsAdmin() != 1)
            throw new ReglasExcepcion("El id no pertenece a un administrador");

        List<Usuario> listadoUsuarios = usuarioRepository.findAllUsuariosNoAdmin();

        return listadoUsuarios.stream()
                .map(user -> new PersonaItem(user.getIdUsuario(), user.getCedula(), user.getNombre(), user.getCorreo(), user.getArea().getCodigo(), user.getArea().getDescripcion()))
                .collect(Collectors.toList());
    }
}
