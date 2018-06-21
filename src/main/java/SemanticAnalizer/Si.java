package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Si extends Estructura {

    Expresion _expresion;

    public Si(){
    }

    public Si(Componente sentencias){
        _hijoMasIzq = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return tipoDatosCorrecto(_expresion, Tipo.BOOLEANO, this);
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
}
