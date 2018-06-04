package SemanticAnalizer;

import java.util.LinkedList;
import java.util.List;

public class Si extends Estructura {

    public Si(){
        _sentencias = new LinkedList<Componente>();
    }

    public Si(List<Componente> sentencias){
        _sentencias = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
