package juanalcuadradosas.soc.Controller;

import io.swagger.annotations.Api;
import juanalcuadradosas.soc.Interface.AmenazaApi;
import juanalcuadradosas.soc.Interface.UsuarioApi;
import juanalcuadradosas.soc.Model.*;
import juanalcuadradosas.soc.Service.AmenazaService;
import juanalcuadradosas.soc.Service.UsuarioService;
import juanalcuadradosas.soc.Util.ManejadorExcepciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Amenaza", tags = "Amenaza")
public class AmenazaApiController implements AmenazaApi {
    @Autowired
    private AmenazaService amenazaService;

    public ResponseEntity<?> getAmenazas() {
        try {
            List<AmenazaItem> listadoAmenazas = amenazaService.getAmenazas();
            return new ResponseEntity<>(listadoAmenazas, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<RespuestaTransaccion> reportarAmenaza(String idUsuario, DatosAmenaza body) {
        try {
            RespuestaTransaccion respuesta = amenazaService.reportarAmenaza(idUsuario, body);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> analizarLog() {
        try {
            List<DatosAmenaza> listadoAmenazas = amenazaService.analizarLog();
            return new ResponseEntity<>(listadoAmenazas, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }
}
