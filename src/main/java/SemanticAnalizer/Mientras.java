package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Mientras extends Estructura {

    public Mientras(){
        _sentencias = new LinkedList<Componente>();
    }

    public Mientras(List<Componente> sentencias){
        _sentencias = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
