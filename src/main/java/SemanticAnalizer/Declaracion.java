package SemanticAnalizer;

public class Declaracion extends ComponenteConcreto {

    private boolean _arreglo;
    private String _nombre;
    private Object _tamano;

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public boolean is_arreglo() {
        return _arreglo;
    }

    public void set_arreglo(boolean _arreglo) {
        this._arreglo = _arreglo;
    }

}
