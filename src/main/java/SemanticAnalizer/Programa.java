package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Programa implements Componente{

    public List<Clase> _listaClases;
    public List<Metodo> _listaMetodos;

    public Programa(){

    }

    public Programa(Clase c){
        _listaClases = new LinkedList<Clase>();
        _listaClases.add(c);
    }

    public Programa(Metodo m){
        _listaMetodos = new LinkedList<Metodo>();
        _listaMetodos.add(m);
    }

    @Override
    public Componente getHermanoDerecho() {
        return null;
    }

    @Override
    public Componente getHijoMasIzq() {
        return null;
    }

    @Override
    public HashMap<String, Nombre> getTblSimbolosLocales() {
        return null;
    }

    @Override
    public Componente getPadre() {
        return null;
    }

}
