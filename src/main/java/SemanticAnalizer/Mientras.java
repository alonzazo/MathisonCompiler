package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Mientras extends Estructura {

    private Expresion _condicion;

    public Mientras(){

    }

    public Mientras(Componente sentencias){
        _hijoMasIzq = sentencias;
    }

    public Expresion get_condicion()
    {
        return _condicion;
    }

    public void set_condicion(Expresion condicion)
    {
        _condicion = condicion;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        setPadreExpresiones();
        return _condicion.evaluarTipo() == Tipo.BOOLEANO;
    }

    private void setPadreExpresiones(){
        if (_condicion instanceof Operacion)
            for (Componente i = ((Operacion) _condicion).get_primeraHoja(); i != null; i = i.getHermanoDerecho())
                i.setPadre(this);
        else
            _condicion.setPadre(this);
    }
}
