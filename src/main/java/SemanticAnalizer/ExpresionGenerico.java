package SemanticAnalizer;

public class ExpresionGenerico extends ComponenteConcreto implements Expresion {
    private String _nombre;

    public ExpresionGenerico(String nombre) {
        this._nombre = nombre;
    }

    @Override
    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }
}
