package SemanticAnalizer;

import java.util.HashMap;

public class Si extends Estructura {

    Expresion _expresion;

    public Si(){
    }

    public Si(Expresion expresion, Componente sentencias){
        _expresion = expresion;
        _hijoMasIzq = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        /*setPadreExpresiones();*/
        _expresion.setPadre(this);
        return _expresion.evaluarTipo() == Tipo.BOOLEANO;
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError {
        setPadreDeMisHijos();

        _tblSimbolosLocales = new HashMap<>();

        if (!evaluarCondicion())
            throw new SemanticError("Expresión de tipo inesperada en estructura SI: \nTipo esperado: " + Tipo.BOOLEANO + " Tipo dado: " + _expresion.evaluarTipo());

        //Evaluamos las demás sentencias, en este caso hacemos profundidad primero.
        if (this.getHijoMasIzq() != null)
            getHijoMasIzq().evaluarSemantica();
        else
            System.out.println("ADVERTENCIA: Estructura SI sin sentencias -> SE IGNORARÁ");

        if (this.getHermanoDerecho() != null)
            getHermanoDerecho().evaluarSemantica();

        return false;
    }

    private void setPadreExpresiones(){
        if (_expresion instanceof Operacion)
            for (Componente i = ((Operacion) _expresion).get_primeraHoja(); i != null; i = i.getHermanoDerecho())
                i.setPadre(this);
        else
            _expresion.setPadre(this);
    }


    private boolean tipoCorrectoParametros(Expresion primero, Tipo tipoEsperado, Componente padre) throws SemanticError {
        HashMap<String,Tipo> metodosNativos = new HashMap<String,Tipo>();
        metodosNativos.put("raiz",Tipo.NUMERICO);
        Expresion iter = primero;
        do{
            //Se revisa si es un metodo nativo
            if (metodosNativos.containsKey(iter.getNombre())) ((LlamadaMetodo) iter).setTipo(metodosNativos.get(iter.getNombre()));

            if (iter.getTipo() != null){
                if(!iter.getTipo().equals(tipoEsperado)){
                    throw new SemanticError("Tipos no compatibles en " + padre.toString() + ":\nTipo de expresion dada: " + iter.getTipo() + "\nTipo de expresion esperada: " + tipoEsperado.toString());
                    //return false;
                }
            } else if (iter.getNombre() != null){
                HashMap<String, Nombre> tabla = padre.getTblSimbolosLocales();
                Componente aux = padre;

                if(iter instanceof LlamadaMetodo){ // si es una llamada a metodo
                    Tipo t = null;
                    boolean esta = aux.getTblSimbolosLocales().containsKey(iter.getNombre());
                    Nombre var = aux.getTblSimbolosLocales().get(iter.getNombre());

                    if(var != null){
                        t = var.get_tipo();
                    }
                    do {
                        if (!esta || !(var instanceof Metodo) || t != tipoEsperado ) {
                            if((aux = aux.getPadre()) != null){
                                esta = aux.getTblSimbolosLocales().containsKey(iter.getNombre());
                                var = aux.getTblSimbolosLocales().get(iter.getNombre());
                                if(var != null){
                                    t = var.get_tipo();
                                }
                            }else{
                                aux = null;
                            }
                        }else{
                            break;
                        }
                    } while (aux != null);
                    if(aux == null) {
                        throw new SemanticError("Referencia a método " + iter.getNombre() + "() no declarada");
                    }
                } else { // si es una variable
                    Tipo t = null;
                    boolean esta = aux.getTblSimbolosLocales().containsKey(iter.getNombre());
                    Nombre var = aux.getTblSimbolosLocales().get(iter.getNombre());
                    if(var != null){
                        t = var.get_tipo();
                    }
                    do {
                        if (!esta || !(var instanceof Variable) || t != tipoEsperado ) {
                            if((aux = aux.getPadre()) != null){
                                esta = aux.getTblSimbolosLocales().containsKey(iter.getNombre());
                                var = aux.getTblSimbolosLocales().get(iter.getNombre());
                                if(var != null){
                                    t = var.get_tipo();
                                }
                            }else{
                                aux = null;
                            }
                        }else{
                            break;
                        }
                    } while (aux != null);
                    if(aux == null) {
                        throw new SemanticError("Variable no declarada: " + iter.getNombre());
                    }
                }
            } else {
                return false;
            }
            //System.out.println(iter.toString());
            iter = (Expresion) iter.getHermanoDerecho();
        }while(iter != null);
        return true;
    }

    @Override
    public String compilar() throws SemanticError {
        String result = "\t#si\n";
        int numSi = Programa.getInstance().getNumSi();
        String etiqueta = "si" + numSi;
        Programa.getInstance().setNumSi(numSi + 1);

        result += _expresion.compilar();

        if (_hijoMasIzq != null){
            if (_hermanoDerecho != null){
                if (_hermanoDerecho instanceof Sino){
                    result = result +
                            "\tbeqz\t\t$v0, si_retorno" + numSi + "\n" +//Si el resultado es falso salte
                            _hijoMasIzq.compilar() +                 //Bloque del si
                            _hermanoDerecho.compilar();            //Aquí se encuentra lo que sigue después del si, puede ser un sino
                } else {
                    result = result +
                            "\tbeqz\t\t$v0, si_retorno" + numSi + "\n" +//Si el resultado es falso salte
                            _hijoMasIzq.compilar() +                 //Bloque del si
                            "si_retorno" + numSi + ":\n" +
                            _hermanoDerecho.compilar();            //Aquí se encuentra lo que sigue después del si, puede ser un sino
                }

                return result;
            }
            else {
                result = result +
                        "\tbeqz\t\t$v0, si_retorno" + numSi + "\n" +//Si el resultado es falso salte
                        _hijoMasIzq.compilar() +                 //Bloque del si
                        "si_retorno" + numSi + ":\n";
                return result;
            }
        } else {
            if (_hermanoDerecho != null) return _hermanoDerecho.compilar();
            else return "";
        }
    }
}
