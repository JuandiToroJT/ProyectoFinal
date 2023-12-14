package juanalcuadradosas.soc.Service;

import juanalcuadradosas.soc.Entity.Amenaza;
import juanalcuadradosas.soc.Entity.Area;
import juanalcuadradosas.soc.Entity.Usuario;
import juanalcuadradosas.soc.Model.AmenazaItem;
import juanalcuadradosas.soc.Model.DatosAmenaza;
import juanalcuadradosas.soc.Model.ParametroItem;
import juanalcuadradosas.soc.Model.RespuestaTransaccion;
import juanalcuadradosas.soc.Repository.AmenazaRepository;
import juanalcuadradosas.soc.Repository.UsuarioRepository;
import juanalcuadradosas.soc.Util.GPTService;
import juanalcuadradosas.soc.Util.LogAnalyzer;
import juanalcuadradosas.soc.Util.ReglasExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AmenazaService {
    @Autowired
    private AmenazaRepository amenazaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<AmenazaItem> getAmenazas() {
        List<Amenaza> listadoAmenazas = amenazaRepository.findAll();

        return listadoAmenazas.stream().map(ame -> new AmenazaItem(ame.getNombre(), ame.getDescripcion(), ame.getId(), ame.getIdUsuario().getIdUsuario())).collect(Collectors.toList());
    }

    public RespuestaTransaccion reportarAmenaza(String idUsuario, DatosAmenaza body) {
        body.validarIntegridad();

        Optional<Usuario> user = usuarioRepository.findById(idUsuario);
        if (user.isEmpty()) throw new ReglasExcepcion("La persona no existe");

        if (user.get().getIsAdmin() == 1) throw new ReglasExcepcion("El id no pertenece a un usuario");

        Amenaza amenaza = new Amenaza(body.getNombre(), body.getDescripcion(), user.get());
        Amenaza resp = amenazaRepository.save(amenaza);

        return new RespuestaTransaccion((long) resp.getId(), true);
    }

    public List<DatosAmenaza> analizarLog() throws IOException {
        List<DatosAmenaza> datosAmenazas = new ArrayList<>();

        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.cargarDescripcionesLogs();

        for (String descripcion : logAnalyzer.getDescripcionesAtaques()){
            String descLimpia = descripcion.replaceAll("\\s+", " ").replaceAll("\\.$", "").toLowerCase();

            for (Map.Entry<String, List<String>> dic : logAnalyzer.getDiccionario().entrySet()) {
                String tipoAtaque = null;

                String categoria = dic.getKey();
                List<String> palabrasClave = dic.getValue();

                for (String palabraClave : palabrasClave) {
                    if (descLimpia.contains(palabraClave.toLowerCase())) {
                        tipoAtaque = categoria;
                        break;
                    }
                }

                if (tipoAtaque != null){
                    String tipoAtqLamb = tipoAtaque;
                    if (datosAmenazas.stream().noneMatch(x -> Objects.equals(x.getNombre(), tipoAtqLamb))){
                        GPTService service = new GPTService(new RestTemplate());
                        datosAmenazas.add(new DatosAmenaza(tipoAtaque, service.obtenerRespuestaGPT(tipoAtaque)));
                    }
                }
            }


        }

        return datosAmenazas;
    }
}
