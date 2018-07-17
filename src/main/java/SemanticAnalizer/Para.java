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

        _desde.evaluarSemantica();
        _hasta.evaluarSemantica();
        _avance.evaluarSemantica();

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
        //Evaluamos la asignacion
        Asignacion asig = new Asignacion(_variable);
        asig.set_expresion(_desde);
        asig.setPadre(this);
        asig.evaluarSemantica();

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
        Componente padreActual = this._padre;
        for (;padreActual != null && !(padreActual instanceof Metodo);
             padreActual = padreActual.getPadre());
        Metodo metodoActual = ((Metodo)padreActual);

        if (_hijoMasIzq != null) //Tiene codigo
        {
            String result = "\t#Para\n";
            int numPara = Programa.getInstance().getNumPara();
            String etiqueta = "para" + numPara;             //Sacamos la etiqueta del para
            Programa.getInstance().setNumPara(numPara+1);   //Incrementamos la cantidad de Para

            if (_declaracion != null){
                result += "\t#Declaracion del contador\n"+
                        _declaracion.compilar();
            }
            //Creamos una asignacion para aprovechar su método
            Asignacion asig = new Asignacion(_variable);
            asig.set_expresion(_desde);
            asig.setPadre(this);
            asig.setTipo(Tipo.NUMERICO);
            asig.evaluarSemantica();

            //Compilamos el contador
            result += asig.compilar();
                    /*"\tsw\t\t$v0, 0($sp)\t#desde -> pila \n" +
                    "\taddi\t$sp, $sp, -4# Guardamos en contador en 4($sp)\n";
            metodoActual.getPilaLocal().getPosicionEnPila("derechaExpresion" +metodoActual.getPilaLocal().getTamanoPila() , 4);*///Simplemente hacemos un campo

            //Evaluamos la condicion: Creamos una expresion que seria variable != hasta
            Variable contador = new Variable(_variable, Tipo.NUMERICO, false);
            Operacion operacion = new Operacion(Operacion.TipoOperador.MAYOROIGUAL,contador, _hasta);

            operacion.setPadre(this);

            result += etiqueta + ":\n" +
                    operacion.compilar() +
                    "\tbnez\t\t$v0, ret_" + etiqueta + "# contador == hasta -> ret_" +etiqueta +"\n"; //Si sí son distintos sigue recto,  si no salta a ret

            //Compilamos el contenido
            result += "#Contenido de " + etiqueta +"\n"+
                    _hijoMasIzq.compilar() +
                    "\tsw\t\t$v0, 0($sp)\t#desde -> pila \n" +
                    "\taddi\t$sp, $sp, -4\n";
            metodoActual.getPilaLocal().getPosicionEnPila("derechaExpresion" +metodoActual.getPilaLocal().getTamanoPila() , 4);//Simplemente hacemos un campo

            //Creamos una asignacion contes sea contador = contador + avance
            Operacion operacionAvance = new Operacion(Operacion.TipoOperador.SUMA, contador, _avance);
            Asignacion avanceAsig = new Asignacion(_variable);
            avanceAsig.set_expresion(operacionAvance);
            avanceAsig.setPadre(this);
            asig.setTipo(Tipo.NUMERICO);
            avanceAsig.evaluarSemantica();

            result += "#Seccion avance " + etiqueta +"\n"+
                    avanceAsig.compilar() +
                    "\taddi\t\t$sp, $sp, 4\n #devolvemos la pila a su estado" +
                    "\tlw\t\t$v0, 0($sp)\n" +
                    "\tj\t\t" + etiqueta + "\n";
            metodoActual.getPilaLocal().sacarDePila();
            /*result += "\taddi\t\t$sp, $sp, 4\n" +
                    "\tlw\t\t$v0, 0($sp)\n";*/       //Cargamos el contador de nuevo
            //metodoActual.getPilaLocal().sacarDePila();

            result += "ret_" + etiqueta +":\n";

            if (_hermanoDerecho != null) //Tiene codigo y hermano
                return result + _hermanoDerecho.compilar();
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
