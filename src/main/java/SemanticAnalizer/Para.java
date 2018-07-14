package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Para extends Estructura
{
    private String _variable;
    private Declaracion _declaracion;
    private Expresion _desde;
    private Expresion _hasta;
    private Expresion _avance;
    

    public Para(){
    }

    public Para(String variable, Componente sentencias)
    {
        _variable = variable;
        _hijoMasIzq = sentencias;
    }

    public Para(String variable, Componente sentencias, Expresion desde, Expresion hasta, Expresion avance)
    {
        _variable = variable;
        _hijoMasIzq = sentencias;
        _desde = desde;
        _hasta = hasta;
        _avance = avance;
    }

    public Para(String variable, Declaracion declaracion, Componente sentencias, Expresion desde, Expresion hasta, Expresion avance)
    {
        _variable = variable;
        _declaracion = declaracion;
        _hijoMasIzq = sentencias;
        _desde = desde;
        _hasta = hasta;
        _avance = avance;
    }

    public String get_variable()
    {
        return _variable;
    }

    public void set_variable(int variable)
    {
        variable = variable;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        _desde.setPadre(this);
        _hasta.setPadre(this);
        _avance.setPadre(this);

        if (_desde.evaluarTipo() != Tipo.NUMERICO)
            throw new SemanticError("Se esperaba expresión numerica en segmento DESDE en PARA.");
        if (_hasta.evaluarTipo() != Tipo.NUMERICO)
            throw new SemanticError("Se esperaba expresión numerica en segmento HASTA en PARA.");
        if (_avance.evaluarTipo() != Tipo.NUMERICO)
            throw new SemanticError("Se esperaba expresión numerica en segmento AVANCE en PARA.");
        return true;
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError {
        setPadreDeMisHijos();

        _tblSimbolosLocales = new HashMap<>();
        if (_declaracion != null)
            getTblSimbolosLocales().put(_declaracion.get_nombre(), new Variable(_declaracion.get_nombre(),_declaracion.get_tipo(),_declaracion.get_expresionTamano(),_declaracion.is_arreglo()));

        evaluarCondicion();

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
