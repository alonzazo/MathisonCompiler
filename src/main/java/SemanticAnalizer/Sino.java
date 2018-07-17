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

    @Override
    public String compilar() throws SemanticError {
        int numSi = (Programa.getInstance().getNumSi() - 1);
        String result = "";
        if (_hijoMasIzq != null){
            result = "\tj\t\tsino_retorno" + numSi + "\n" +
                    "si_retorno" + numSi +":\n" +
                    _hijoMasIzq.compilar() +
                    "sino_retorno" +numSi + ":\n";
            if (_hermanoDerecho != null) return result +_hermanoDerecho.compilar();
            else return result;
        } else {
            if (_hermanoDerecho != null) return "si_retorno" + numSi +":\n" + _hermanoDerecho.compilar();
            else return "";
        }
    }
}