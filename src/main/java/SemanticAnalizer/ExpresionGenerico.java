package SemanticAnalizer;

public class ExpresionGenerico extends ComponenteConcreto implements Expresion {
    private String _nombre;
    private Tipo _tipo;
    private boolean _esMetodo;

    public void setTipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public ExpresionGenerico(String nombre, Tipo tipo) {
        this._nombre = nombre;
        this._tipo = tipo;
        this._esMetodo = false;
    }

    public ExpresionGenerico(String nombre, boolean esMetodo) {
        this._nombre = nombre;
        this._tipo = null;
        this._esMetodo = esMetodo;
    }

    public ExpresionGenerico(Tipo tipo) {
        this._nombre = null;
        this._tipo = tipo;
        this._esMetodo = false;
    }

    public ExpresionGenerico() {
        this._nombre = null;
        this._tipo = null;
        this._esMetodo = false;
    }

    @Override
    public String getNombre() {
        return _nombre;
    }

    @Override
    public Tipo getTipo() {
        return _tipo;
    }

    public boolean esMetodo() {
        return _esMetodo;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }
}
