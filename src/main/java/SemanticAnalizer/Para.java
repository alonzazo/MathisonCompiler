package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Para extends Estructura
{
    private String _variable;

    public Para(){
    }

    public Para(String variable, Componente sentencias)
    {
        _variable = variable;
        _hijoMasIzq = sentencias;
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
