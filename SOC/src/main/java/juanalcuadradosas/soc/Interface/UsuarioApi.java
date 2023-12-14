package juanalcuadradosas.soc.Interface;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import juanalcuadradosas.soc.Model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface UsuarioApi {
    @ApiOperation(value = "Crea o edita una persona", nickname = "crearEditarPersona", notes = "Crea una persona o la edita en caso de que ya exista, ya sea usuario normal o administrador. ", response = RespuestaTransaccion.class, tags = {"Usuario"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Respuesta del proceso", response = RespuestaTransaccion.class), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/usuario/persona/registrar", produces = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<RespuestaTransaccion> crearEditarPersona(@ApiParam(value = "Datos de la persona", required = true) @RequestBody DatosPersona body);

    @ApiOperation(value = "Login", nickname = "loginPersona", notes = "Loguea una persona, ya sea usuario normal o administrador. ", response = RespuestaTransaccion.class, tags = {"Usuario"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Respuesta del proceso", response = RespuestaTransaccion.class), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/usuario/persona/{id}/{password}/login", produces = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<RespuestaTransaccion> loginPersona(@ApiParam(value = "Id de la persona", required = true) @PathVariable("id") String id, @ApiParam(value = "Clave encriptada", required = true) @PathVariable("password") String clave);

    @ApiOperation(value = "Obtener listado usuarios", nickname = "getUsuarios", notes = "Obtiene el listado de usuarios registrados, esta función es exclusiva del administrador", response = PersonaItem.class, responseContainer = "List", tags = {"Usuario"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Listado de usuarios", response = PersonaItem.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/usuario/{idAdministrador}/listado", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<?> getUsuarios(@ApiParam(value = "Id del administrador", required = true) @PathVariable("idAdministrador") String idAdministrador);

    @ApiOperation(value = "Obtener areas", nickname = "getAreas", notes = "Obtiene el listado de areas", response = ParametroItem.class, responseContainer = "List", tags = {"Usuario"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Listado de areas", response = ParametroItem.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/usuario/areas", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<?> getAreas();
}
