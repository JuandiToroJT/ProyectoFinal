package juanalcuadradosas.soc.Controller;

import io.swagger.annotations.Api;
import juanalcuadradosas.soc.Interface.UsuarioApi;
import juanalcuadradosas.soc.Model.*;
import juanalcuadradosas.soc.Service.UsuarioService;
import juanalcuadradosas.soc.Util.ManejadorExcepciones;
import juanalcuadradosas.soc.Util.ReglasExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Usuario", tags = "Usuario")
public class UsuarioApiController implements UsuarioApi {
    @Autowired
    private UsuarioService usuarioService;

    public ResponseEntity<RespuestaTransaccion> crearEditarPersona(DatosPersona body) {
        try {
            RespuestaTransaccion respuesta = usuarioService.crearEditarPersona(body);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<RespuestaTransaccion> loginPersona(String id, String clave) {
        try {
            RespuestaTransaccion respuesta = usuarioService.loginPersona(id, clave);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getUsuarios(String idAdministrador) {
        try {
            List<PersonaItem> listadoUsuarios = usuarioService.getUsuarios(idAdministrador);
            return new ResponseEntity<>(listadoUsuarios, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getAreas() {
        try {
            List<ParametroItem> listadoAreas = usuarioService.getAreas();
            return new ResponseEntity<>(listadoAreas, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }
}
