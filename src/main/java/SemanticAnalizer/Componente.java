package SemanticAnalizer;

import java.util.HashMap;

public interface Componente {

    public enum Tipo{   CADENA,
        NUMERICO,
        BOOLEANO,
        NO_PRIMITIVO
    };

    public  Componente getHermanoDerecho();
    public  Componente getHijoMasIzq();
    public HashMap< String, Nombre > getVariablesLocales();

}
