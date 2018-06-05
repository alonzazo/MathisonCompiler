package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Programa extends ComponenteConcreto{

    public List<Clase> _listaClases;
    public List<Metodo> _listaMetodos;

    public Programa(){
        _listaClases = new LinkedList<Clase>();
        _listaMetodos = new LinkedList<Metodo>();
    }

    public Programa(Clase c){
        _listaClases = new LinkedList<Clase>();
        _listaMetodos = new LinkedList<Metodo>();
        _listaClases.add(c);
    }

    public Programa(Metodo m){
        _listaMetodos = new LinkedList<Metodo>();
        _listaClases = new LinkedList<Clase>();
        _listaMetodos.add(m);
    }

    @Override
    public Componente getHermanoDerecho() {
        return null;
    }

    @Override
    public HashMap<String, Nombre> getTblSimbolosLocales() {
        return _tblSimbolosLocales;
    }

    @Override
    public Componente getPadre() {
        return null;
    }

    @Override
    public Componente setHermanoDerecho(Componente hermanoDer) {
        return  null;
    }

    @Override
    public String toString() {
        return "Programa{}";
    }
}
