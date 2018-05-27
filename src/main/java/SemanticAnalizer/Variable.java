package SemanticAnalizer;

public class Variable implements Nombre{



    private String _nombre;
    private Tipo _tipo;
    private Object _valor;

    public String getNombre(){
        return _nombre;
    }

    public Tipo get_tipo(){
        return  _tipo;
    }

    public Object get_valor(){
        return _valor;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void set_tipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public void set_valor(Object _valor) {
        //TODO Agregar restricciones
        this._valor = _valor;
    }
}


