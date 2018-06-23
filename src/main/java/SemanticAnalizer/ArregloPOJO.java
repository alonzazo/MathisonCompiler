package SemanticAnalizer;

public class ArregloPOJO {
    private Tipo _tipo;
    private String _nombreTipo;
    private Expresion _expresionTamano;

    public ArregloPOJO(Tipo tipo){
        _tipo = tipo;
    }

    public ArregloPOJO(Tipo tipo, String nombreTipo){
        _tipo = tipo;
        _nombreTipo = nombreTipo;
    }

    public ArregloPOJO(Tipo tipo, Expresion expresionTamano){
        _tipo = tipo;
        _expresionTamano = expresionTamano;
    }

    public ArregloPOJO(Tipo tipo, String nombreTipo, Expresion expresionTamano){
        _tipo = tipo;
        _nombreTipo = nombreTipo;
        _expresionTamano = expresionTamano;
    }

    public Tipo get_tipo(){return _tipo;}

    public String get_nombreTipo(){return _nombreTipo;}

    public Expresion get_expresionTamano(){return _expresionTamano;}
}
