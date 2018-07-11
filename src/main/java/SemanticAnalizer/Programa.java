package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Programa {

    private Componente raiz;

    private static Programa instance;

    private Programa(){
    }

    public static Programa getInstance(){
        if (instance == null) instance = new Programa();
        return instance;
    }

    public Componente getRaiz() {
        return raiz;
    }

    public void setRaiz(Componente raiz) {
        this.raiz = raiz;
    }

    @Override
    public String toString() {
        return "Programa";
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
}
