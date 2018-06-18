package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Para extends Estructura
{
    private String _variable;
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
        return false;
    }
}
