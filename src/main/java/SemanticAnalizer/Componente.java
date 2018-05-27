package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public interface Componente {

    public Componente getHermanoDerecho();
    public Componente getHijoMasIzq();
    public HashMap< String, Nombre > getTblSimbolosLocales();
    public LinkedList< HashMap > getTblSimbolosPadres();

}
