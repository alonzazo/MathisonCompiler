package SemanticAnalizer;

public class ExpresionGenerico extends ComponenteConcreto implements Expresion {
    protected String _nombre;
    protected Tipo _tipo;

    public void setTipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public ExpresionGenerico(String nombre, Tipo tipo) {
        this._nombre = nombre;
        this._tipo = tipo;
    }

    public ExpresionGenerico(String nombre) {
        this._nombre = nombre;
        this._tipo = null;
    }

    public ExpresionGenerico(Tipo tipo) {
        this._nombre = null;
        this._tipo = tipo;
    }

    public ExpresionGenerico() {
        this._nombre = null;
        this._tipo = null;
    }

    @Override
    public String getNombre() {
        return _nombre;
    }

    @Override
    public Tipo getTipo() {
        return _tipo;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }
}
