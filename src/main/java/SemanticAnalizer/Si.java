package SemanticAnalizer;

import java.util.LinkedList;
import java.util.List;

public class Si extends Estructura {

    public Si(){
    }

    public Si(Componente sentencias){
        _hijoMasIzq = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
