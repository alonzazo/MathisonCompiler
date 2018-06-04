package SemanticAnalizer;


import java.util.LinkedList;
import java.util.List;

public class Revisar extends Estructura {

    public Revisar(){

    }

    public Revisar(Componente sentencias){
        _hijoMasIzq = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
