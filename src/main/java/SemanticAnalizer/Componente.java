package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public interface Componente {

    public Componente getPadre();   // Está hecho para acceder únicamente a los padres y
                                    // sus tablas de símbolos, ser precavido con los recorridos.
    public Componente getHermanoDerecho();
    public Componente getHijoMasIzq();
    public HashMap< String, Nombre > getTblSimbolosLocales();

}
