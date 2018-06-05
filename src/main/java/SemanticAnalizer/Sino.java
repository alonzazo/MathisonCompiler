package SemanticAnalizer;

import java.util.LinkedList;
import java.util.List;

public class Sino extends Estructura {

    public Sino(){

    }

    public Sino(Componente sentencias){
        _hijoMasIzq = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }

    @Override
    public String toString() {
        return "Sino{" +
                "_condicion=" + _condicion +
                '}';
    }
}