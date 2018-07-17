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
        String result = "\t#para\n";
        int numPara = Programa.getInstance().getNumPara();
        String etiqueta = "para" + numPara;
        Programa.getInstance().setNumPara(numPara+1);
        int numRegistro1, numRegistro2, numRegistro3;
        if(numPara == 0) //Es el primer para
        {
            numRegistro1 = 2;
            numRegistro2 = 3;
            numRegistro3 = 4;
        }
        else //Es el segundo para
        {
            numRegistro1 = 5;
            numRegistro2 = 6;
            numRegistro3 = 7;
        }

        int valorDesde = 0;
        int valorHasta = 0;
        int valorAvance = 0;

        if(_desde instanceof Variable)
        {
            Variable vDesde = (Variable)_desde;
            valorDesde = (int)vDesde.get_valor().value;
            System.out.println("El desde era variable y el valor es " + valorDesde);
        }
        else
        {
            ExpresionGenerico desde = (ExpresionGenerico)_desde;
            valorDesde = (int)desde.get_symbol().value;
            System.out.println("El desde era numero y el valor es " + valorDesde);
        }

        if(_hasta instanceof Variable)
        {
            Variable vHasta = (Variable)_hasta;
            valorHasta = (int)vHasta.get_valor().value;
            System.out.println("El hasta era variable y el valor es " + valorHasta);
        }
        else
        {
            ExpresionGenerico hasta = (ExpresionGenerico)_hasta;
            valorHasta = (int)hasta.get_symbol().value;
            System.out.println("El hasta era numero y el valor es " + valorHasta);
        }

        if(_avance instanceof Variable)
        {
            Variable vAvance = (Variable)_avance;
            valorAvance = (int)vAvance.get_valor().value;
            System.out.println("El avance era variable y el valor es " + valorAvance);
        }
        else
        {
            ExpresionGenerico avance = (ExpresionGenerico)_avance;
            valorAvance = (int)avance.get_symbol().value;
            System.out.println("El avance era numero y el valor es " + valorAvance);
        }


        if (_hijoMasIzq != null) //Tiene codigo
        {
            result = result + "\tli\t\t$t" + numRegistro1 + ", " + valorDesde + "\n";
            result = result + "\tli\t\t$t" + numRegistro2 + ", " + valorHasta + "\n";
            result = result + "\tli\t\t$t" + numRegistro3 + ", " + valorAvance + "\n";
            result = result + "para" + numPara + ":\n";
            if (_hermanoDerecho != null) //Tiene codigo y hermano
            {
                result = result + _hijoMasIzq.compilar();
                result = result + "\tadd\t\t$t" + numRegistro1 + ", $t" + numRegistro1 + ", $t" + numRegistro3 + "\n";
                result = result + "\tblt\t\t$t" + numRegistro1 + ", $t" + numRegistro2 + "\n";
                result = result + _hermanoDerecho.compilar();
            }
            else //Tiene codigo pero no hermano
            {
                result = result + _hijoMasIzq.compilar();
                result = result + "\tadd\t\t$t" + numRegistro1 + ", $t" + numRegistro1 + ", $t" + numRegistro3 + "\n";
                result = result + "\tblt\t\t$t" + numRegistro1 + ", $t" + numRegistro2 + "\n";
            }
            return result;
        }
        else //No tiene codigo
        {
            if (_hermanoDerecho != null) //Tiene hermano derecho
                return _hermanoDerecho.compilar();
            else //No tiene ni codigo ni hermano
                return "";
        }
    }
}
