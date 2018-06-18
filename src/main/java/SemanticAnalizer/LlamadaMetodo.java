package SemanticAnalizer;

import java.util.List;

public class LlamadaMetodo extends Sentencia implements Expresion, Nombre {

    private List<ExpresionGenerico> _parametros;
    private String _nombre;
    private Tipo _tipo;

    public LlamadaMetodo(String nombre){
        _nombre = nombre;
    }

    public LlamadaMetodo(List params, String nombre){
        _parametros = params;
        _nombre = nombre;
    }

    @Override
    public String getNombre() {
        return _nombre;
    }

    @Override
    public Tipo getTipo() {
        return _tipo;
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString() {
        return "LlamadaMetodo{" +  _nombre + '}';
    }

    @Override
    public String get_nombre() {
        return _nombre;
    }

    @Override
    public Tipo get_tipo() {
        return _tipo;
    }

    public List<ExpresionGenerico> get_parametros() {
        return _parametros;
    }

    public void set_parametros(List<ExpresionGenerico> _parametros) {
        this._parametros = _parametros;
    }
}
