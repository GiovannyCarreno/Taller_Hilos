package Control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Control {
    public boolean validarNombre(String nombre) {
        Pattern patron = Pattern.compile("\\S+(\\s+\\S+)*");
        Matcher matcher = patron.matcher(nombre);
        return matcher.matches();
    }

    public boolean validarDocumento(String documento) {
        String patron = "^[0-9]+$";
        return Pattern.matches(patron, documento);
    }
}
