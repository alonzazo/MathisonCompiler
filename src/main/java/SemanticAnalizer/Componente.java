package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public interface Componente {


    public Componente getPadre();   // Está hecho para acceder únicamente a los padres y
                                    // sus tablas de símbolos, ser precavido con los recorridos.
    public Componente setPadre(Componente padre);
    public Componente getHermanoDerecho();
    public Componente getHijoMasIzq();
    public Componente setHermanoDerecho(Componente hermano);
    public Componente setHijoMasIzq(Componente hijo);
    public void setTblSimbolosLocales(HashMap<String, Nombre> tabla);
    public Componente getUltimoHijo();
    public Componente setUltimoHijo(Componente hijo);
    public HashMap< String, Nombre > getTblSimbolosLocales();

}
