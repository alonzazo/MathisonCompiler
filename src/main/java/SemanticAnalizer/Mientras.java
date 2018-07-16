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

        /*Inicializa la final*/
        String result = "";

        /*Agarra el numero de la ettiqueta*/
        int numMientras = Programa.getInstance().getNumMientras();
        String etiqueta = "mientras" + numMientras;

        /*Actualiza las etiquetas*/
        Programa.getInstance().setNumMientras(numMientras + 1);

        /*Definicion del inicio*/
        result += "inicio_mientras:"+numMientras+": "+"\n";

        result += _condicion.compilar();

        if (_hijoMasIzq != null){
            if (_hermanoDerecho != null){

                /*Brinque a etiqueta si esta correcta la condicion, sino siga*/
                    result += "\tbnez\t\t$v0, " + etiqueta + "\n";

                /*Definicion de lo que sigue*/
                result +=    "mientras_retorno" + numMientras + ":\n" +
                            _hermanoDerecho.compilar();

                    /*Lo que esta adentro del mientras y salta a la condicion*/
                     result+=etiqueta + "\n"
                             + _hijoMasIzq.compilar()
                             + "\tj\t\tinicio_mientras:" + numMientras +"\n";     //Brinca a la condicion

                return result;
            }
            else //Tiene algo adentro pero no sigue nada
                {
                 /*Brinque a etiqueta si esta correcta la condicion, sino siga*/
                result += "\tbnez\t\t$v0, " + etiqueta + "\n";

                  /*Hay que salir del programa*/
                result +=    "mientras_retorno" + numMientras + ":\n";

                /*----Deberia salir aqui---------*/

                /*Lo que esta adentro del mientras y salta a la condicion*/
                result+=etiqueta + "\n"
                        + _hijoMasIzq.compilar()
                        + "\tj\t\tinicio_mientras:" + numMientras +"\n";     //Brinca a la condicion

                return  result;
            }
        } else //Adentro del while no hay nada
            {
                /*Brinque a etiqueta si esta correcta la condicion, sino siga*/
                result += "\tbnez\t\t$v0, " + etiqueta + "\n";

                /*Definicion de lo que sigue*/
                result +=    "mientras_retorno" + numMientras + ":\n";

                /*Si no esta vacio se compila lo que sigue*/
                if (_hermanoDerecho != null)
                {
                    result += _hermanoDerecho.compilar();
                }

                else /*Si esta vacio*/

                {
                    //Deberia salir
                }

                result+=etiqueta + "\n"
                        + "\tj\t\tinicio_mientras:" + numMientras +"\n";     //Brinca a la condicion

             return result;
        }
    }
}
