package SemanticAnalizer;


import java.util.LinkedList;
import java.util.List;

public class Revisar extends Estructura {

    public Revisar(){
        _sentencias = new LinkedList<Componente>();
    }

    public Revisar(List<Componente> sentencias){
        _sentencias = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
