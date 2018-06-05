package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Mientras extends Estructura {

    public Mientras(){

    }

    public Mientras(Componente sentencias){
        _hijoMasIzq = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }

    @Override
    public String toString() {
        return "Mientras{" +
                "_condicion=" + _condicion +
                '}';
    }
}
