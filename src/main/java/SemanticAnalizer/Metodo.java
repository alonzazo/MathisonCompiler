package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Metodo extends ComponenteConcreto implements Nombre {

    private String _nombre;
    private Tipo _tipo;

    public Metodo(){
        _nombre = "";
        _tipo = Tipo.NUMERICO;
    }

    public Metodo( String nombre, Tipo tipo ){
        _nombre = nombre;
        _tipo = tipo;
    }

    public Metodo(Componente padre){
        _padre = padre;
    }

    @Override
    public String get_nombre() {
        return null;
    }

    @Override
    public String toString() {
        return "Metodo{" +
                "_nombre='" + _nombre + '\'' +
                ", _tipo=" + _tipo +
                '}';
    }

    @Override
    public Tipo get_tipo() {
        return null;
    }

    public List<Variable> getParametros() {
        return new LinkedList<Variable>();
    }
}
