package SemanticAnalizer;

public class Variable extends ExpresionGenerico implements Nombre{


    private boolean _arreglo;
    private int _tamano;
    private Expresion _expresionTamano;
    private Object _valor;

    public Variable(){
        System.out.println("Variable");
    }

    @Override
    public Tipo evaluarTipo() throws SemanticError{
        //Se busca en las tablas de simbolos la referencia
        Componente i = this._padre;
        for (; !i.getTblSimbolosLocales().containsKey(getNombre()); i = i.getPadre());          //-----**------

        if (i == null)
            throw new SemanticError("Referencia no declarada en " + this._padre.toString() + ": " + getNombre());

        //Se checkean la referencia encontrada si es realmente una Variable
        if (i.getTblSimbolosLocales().get(getNombre()) instanceof Variable){
            _tipo = i.getTblSimbolosLocales().get(getNombre()).get_tipo();
        }else
            throw new SemanticError("Referencia a variable " + getNombre() + " no declarada");

        return _tipo;
    }

    public Variable(boolean _arreglo, String _nombre, Tipo _tipo, int _tamano) {
        this._arreglo = _arreglo;
        this._nombre = _nombre;
        this._tipo = _tipo;
        this._tamano = _tamano;
    }

    public Variable(String _nombre, Tipo _tipo, boolean _arreglo) {
        this._arreglo = _arreglo;
        this._nombre = _nombre;
        this._tipo = _tipo;
    }

    public Variable(String _nombre, Tipo _tipo, Expresion _expresionTamano, boolean _arreglo) {
        this._arreglo = _arreglo;
        this._nombre = _nombre;
        this._tipo = _tipo;
        this._expresionTamano = _expresionTamano;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "_arreglo=" + _arreglo +
                ", _nombre='" + _nombre + '\'' +
                ", _tipo=" + _tipo +
                ", _tamano=" + _tamano +
                '}';
    }

    public Variable(String nombre){
        _nombre = nombre;

        System.out.println("Variable " + nombre);
    }

    public boolean is_arreglo() {
        return _arreglo;
    }

    public void set_arreglo(boolean _arreglo) {
        this._arreglo = _arreglo;
    }

    public int get_tamano() {
        return _tamano;
    }

    public void set_tamano(int _tamano) {
        this._tamano = _tamano;
    }

    public Variable(String nombre, Object valor){
        _nombre = nombre;
        _valor = valor;
        System.out.println("Variable" + nombre);
    }

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


