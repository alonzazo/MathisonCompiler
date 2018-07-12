package SemanticAnalizer;

import java.util.HashMap;
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
    public boolean evaluarSemantica() throws SemanticError {
        setPadreDeMisHijos();

        _tblSimbolosLocales = new HashMap<>();

        //Evaluamos las demás sentencias, en este caso hacemos profundidad primero.
        if (this.getHijoMasIzq() != null)
            getHijoMasIzq().evaluarSemantica();
        else
            System.out.println("ADVERTENCIA: Estructura PARA sin sentencias -> Se considerarán los segmentos DESDE, HASTA, AVANCE.");

        if (this.getHermanoDerecho() != null)
            getHermanoDerecho().evaluarSemantica();
        return true;
    }
}