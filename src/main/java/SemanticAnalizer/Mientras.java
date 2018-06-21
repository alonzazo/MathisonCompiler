package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Mientras extends Estructura {

    private Componente _condicion;

    public Mientras(){

    }

    public Mientras(Componente sentencias){
        _hijoMasIzq = sentencias;
    }

    public Componente get_condicion()
    {
        return _condicion;
    }

    public void set_condicion(Componente condicion)
    {
        _condicion = condicion;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
