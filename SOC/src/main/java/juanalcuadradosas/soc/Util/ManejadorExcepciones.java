package juanalcuadradosas.soc.Util;

import juanalcuadradosas.soc.Model.MensajeErrorItem;

public class ManejadorExcepciones {
    public static MensajeErrorItem AdministrarExcepcion(Exception ex){
        return new MensajeErrorItem(ex.getMessage());
    }
}
