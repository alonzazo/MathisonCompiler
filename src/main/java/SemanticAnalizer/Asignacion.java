package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Asignacion extends Sentencia {
    private String _nombre;
    private Object _valor;
    private Expresion _expresion;
    private Tipo _tipo;

    public Tipo getTipo() {
        return _tipo;
    }

    public void setTipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public Asignacion(){ }

    public Asignacion(String nombre)
    {
        _nombre = nombre;
    }

    public Object get_valor() {
        return _valor;
    }

    public void set_valor(Object _valor) {
        this._valor = _valor;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Expresion get_expresion() {
        return _expresion;
    }

    public void set_expresion(Expresion expresion) {
        this._expresion = expresion;
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError{
        return tipoDatosCorrecto(_expresion,_tipo,this);
    }

    @Override
    public String toString() {
        return "Asignacion{" +  _nombre + '}';
    }

    private boolean tipoDatosCorrecto(Expresion primero, Tipo tipoEsperado, Componente padre) throws SemanticError {
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
                        throw new SemanticError("Tipos no compatibles");
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
                        throw new SemanticError("Tipos no compatibles");
                    }
                }
            } else {
                return false;
            }
            //System.out.println(iter.toString());
            iter = (ExpresionGenerico) iter.getHermanoDerecho();
        }while(iter != null);
        return true;
    }
}
