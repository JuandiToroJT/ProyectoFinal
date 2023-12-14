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

public interface AmenazaApi {
    @ApiOperation(value = "Obtener amenazas", nickname = "getAmenazas", notes = "Obtiene el listado de las amenazas, esta función es exclusiva del usuario", response = AmenazaItem.class, responseContainer = "List", tags = {"Amenaza"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Listado de amenazas", response = AmenazaItem.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/amenaza/listado", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<?> getAmenazas();

    @ApiOperation(value = "Reporta una amenaza", nickname = "reportarAmenaza", notes = "Reporta una amenaza, esta función es exclusiva del usuario", response = RespuestaTransaccion.class, tags = {"Amenaza"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Respuesta del proceso", response = RespuestaTransaccion.class), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/amenaza/{idUsuario}/reportar", produces = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<RespuestaTransaccion> reportarAmenaza(@ApiParam(value = "Id del usuario que reporta", required = true) @PathVariable("idUsuario") String idUsuario, @ApiParam(value = "Datos de la amenaza", required = true) @RequestBody DatosAmenaza body);

    @ApiOperation(value = "Analizar log", nickname = "analizarLog", notes = "Analiza el log de actividad para identificar posibles amenazas, y obtener una mitigación mediante IA", response = DatosAmenaza.class, responseContainer = "List", tags = {"Amenaza"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Listado de amenazas con su solución", response = DatosAmenaza.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/amenaza/analizarLog", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<?> analizarLog();
}
