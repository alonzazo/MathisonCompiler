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

    @Override
    public boolean evaluarSemantica() throws SemanticError {
        setPadreDeMisHijos();

        if (!evaluarCondicion())
            throw new SemanticError("Se esperaba expresión booleana en estructura MIENTRAS:\nTipo esperado: " + Tipo.BOOLEANO + " Tipo dado: " + _condicion.evaluarTipo());

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
        if (_hijoMasIzq != null){
            if (_hermanoDerecho != null) return _hijoMasIzq.compilar() + _hermanoDerecho.compilar();
            else return _hijoMasIzq.compilar();
        } else {
            if (_hermanoDerecho != null) return _hermanoDerecho.compilar();
            else return "";
        }
    }
}
