package juanalcuadradosas.soc.Util;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {
    private List<String> descripcionesAtaques = new ArrayList<>();
    private Map<String, List<String>> diccionario = new HashMap<>();

    public LogAnalyzer() {
        // Ransomware
        diccionario.put("Ransomware", List.of("WannaCry", "Petya", "Ryuk", "cifrado", "rescate", "malware"));
        diccionario.put("Phishing", List.of("Spear phishing", "vishing", "correo electrónico", "engaño", "información confidencial"));
        diccionario.put("Ataque de Denegación de Servicio (DDoS)", List.of("Ataque a Dyn", "Mirai botnet", "tráfico falso", "inaccesibilidad", "servicio en línea"));
        diccionario.put("Violación de Datos", List.of("Equifax", "Yahoo", "LinkedIn", "acceso no autorizado", "información personal", "credenciales"));
        diccionario.put("Inyección de SQL", List.of("Ataque de inyección de SQL", "código SQL malicioso", "aplicación web", "acceso no autorizado"));
        diccionario.put("Malware", List.of("Virus", "gusanos", "troyanos", "software malicioso", "daño", "explotación"));
        diccionario.put("Zero-Day Exploits", List.of("Stuxnet", "vulnerabilidades", "seguridad", "explotación", "parche"));
        diccionario.put("Ataques a Infraestructuras Críticas", List.of("Energía", "agua", "transporte", "infraestructuras esenciales", "compromiso"));
        diccionario.put("Ataques a Redes Sociales", List.of("Breaches en Facebook", "Twitter", "acceso no autorizado", "difusión de información falsa"));
        diccionario.put("Fugas de Información Confidencial", List.of("WikiLeaks", "divulgación no autorizada", "información confidencial", "clasificada"));
        diccionario.put("Hacking de Dispositivos IoT", List.of("Ataques a cámaras de seguridad", "dispositivos domésticos inteligentes", "acceso no autorizado"));
        diccionario.put("Espionaje Cibernético", List.of("APT", "Amenazas Persistentes Avanzadas", "gobiernos", "organizaciones", "información sensible"));
    }

    public void cargarDescripcionesLogs() throws IOException {
        ClassPathResource resource = new ClassPathResource("actividad.log");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

        StringBuilder descripcionActual = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Descripcion del Ataque:")) {
                descripcionActual = new StringBuilder();
                descripcionActual.append(line.replace("Descripcion del Ataque: ", "").trim());
            } else if (line.startsWith("Usuarios Afectados:")) {
                String cleanedDescription = descripcionActual.toString().replaceAll("\\s+", " ").replaceAll("\\.$", "");
                descripcionesAtaques.add(cleanedDescription);
            } else {
                descripcionActual.append(line.trim()).append(" ");
            }
        }

        reader.close();
    }

    public List<String> getDescripcionesAtaques() {
        return descripcionesAtaques;
    }

    public Map<String, List<String>> getDiccionario() {
        return diccionario;
    }
}
