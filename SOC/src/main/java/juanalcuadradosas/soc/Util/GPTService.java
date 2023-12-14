package juanalcuadradosas.soc.Util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GPTService {
    private final String URL_API = "http://127.0.0.1:5000/gptAtaque?tipoAtaque={tipoAtaque}";
    private final RestTemplate restTemplate;

    public GPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtenerRespuestaGPT(String tipoAtaque) {
        String respuesta = restTemplate.getForObject(URL_API, String.class, tipoAtaque);
        return respuesta;
    }
}
