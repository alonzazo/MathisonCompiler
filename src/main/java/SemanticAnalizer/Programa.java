package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Programa extends ComponenteConcreto{

    private static Programa instance;

    private Componente raiz;
    private HashMap<String,Tipo> metodosNativos = new HashMap<>();

    private Programa(){
        metodosNativos.put("raiz",Tipo.NUMERICO);
    }

    public static Programa getInstance(){
        if (instance == null) instance = new Programa();
        return instance;
    }

    public static void resetInstance(){instance = new Programa();}

    public Componente getRaiz() {
        return raiz;
    }

    public void setRaiz(Componente raiz) {
        this.raiz = raiz;
    }

    public HashMap<String, Tipo> getMetodosNativos() {
        return metodosNativos;
    }

    public void setMetodosNativos(HashMap<String, Tipo> metodosNativos) {
        this.metodosNativos = metodosNativos;
    }

    @Override
    public String toString() {
        return imprimirArbol();
    }

    public String imprimirArbol() {
        //Recorrido en profundidad primero
        if (raiz == null) return "";
        return toStringAux("", 0,  raiz);
    }

    private String toStringAux(String text,int indexLevel, Componente actual){
        if (actual == null) return text;

        text += '\n';
        for (int i = 0; i < indexLevel; i++) text += "|\t";

        text += actual.toString();

        if (actual.getTblSimbolosLocales() != null  && !actual.getTblSimbolosLocales().isEmpty()) {
            text += '\n';
            for (int i = 0; i < indexLevel; i++) text += "|\t";
            text += "TABLA SIMBOLOS: " + actual.getTblSimbolosLocales().toString();
        }

        if ( actual.getHijoMasIzq() != null){
            text = toStringAux(text, indexLevel + 1, actual.getHijoMasIzq());
        }
        if ( actual.getHermanoDerecho() != null ){
            text = toStringAux(text, indexLevel, actual.getHermanoDerecho());
        }
        return text;
    }

    public boolean evaluarSemantica() throws SemanticError {
        for (Componente componente = raiz; componente != null; componente = componente.getHermanoDerecho())
            componente.setPadre(this);
        _tblSimbolosLocales = new HashMap<>();
        return raiz.evaluarSemantica();
    }

    public String compilar() throws SemanticError {
        return "";
    }

    public String compilar(String path) throws SemanticError {
        return "";
    }
}
