package SemanticAnalizer;

import java_cup.runtime.Symbol;

public class Variable extends ExpresionGenerico implements Nombre{


    private boolean _arreglo;
    private int _tamano;
    private Expresion _expresionTamano;
    private Symbol _valor;

    public Variable(){
        System.out.println("Variable");
    }

    @Override
    public Tipo evaluarTipo() throws SemanticError{
        //Se busca en las tablas de simbolos la referencia
        Componente i = this._padre;
        while(i != null && (i.getTblSimbolosLocales() == null || !i.getTblSimbolosLocales().containsKey(getNombre()))){
             i = i.getPadre();
        }

        if (i == null)
            throw new SemanticError("Referencia no declarada en " + this._padre.toString() + ": " + getNombre());

        //Se checkean la referencia encontrada si es realmente una Variable
        if (i.getTblSimbolosLocales().get(getNombre()) instanceof Variable){
            _tipo = i.getTblSimbolosLocales().get(getNombre()).get_tipo();
        }else
            throw new SemanticError("Referencia a variable " + getNombre() + " no declarada");

        try{
            evaluarIndice();
        }
        catch (SemanticError e){
            throw new SemanticError("Indice invalido: \n" + e.getMessage());
        }

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

    public Variable(String _nombre, boolean _arreglo) {
        this._arreglo = _arreglo;
        this._nombre = _nombre;
    }

    public Variable(String _nombre, Expresion expresionTamano, boolean _arreglo) {
        this._arreglo = _arreglo;
        this._nombre = _nombre;
        this._expresionTamano = expresionTamano;
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

    public Variable(String nombre, Symbol valor){
        _nombre = nombre;
        _valor = valor;
        System.out.println("Variable" + nombre);
    }

    public String getNombre(){
        return _nombre;
    }

    public Expresion get_expresionTamano() {
        return _expresionTamano;
    }

    public void set_expresionTamano(Expresion _expresionTamano) {
        this._expresionTamano = _expresionTamano;
    }

    public boolean evaluarIndice() throws SemanticError {
        if (_arreglo == true && _expresionTamano != null){
            setPadreExpresiones();
            return _expresionTamano.evaluarTipo() == Tipo.NUMERICO;
        }

        else
            return true; //Si la asginacion no es a un arreglo
    }

    private void setPadreExpresiones(){
        if (_expresionTamano instanceof Operacion)
            for (Componente i = ((Operacion) _expresionTamano).get_primeraHoja(); i != null; i = i.getHermanoDerecho())
                i.setPadre(this);
        else
            _expresionTamano.setPadre(this);
    }

    public Tipo get_tipo(){
        return  _tipo;
    }

    @Override
    public String compilar() throws SemanticError {
        String result = "";
        //Buscamos el método padre
        Componente padreActual = this._padre;
        for (;padreActual != null && !(padreActual instanceof Metodo);
             padreActual = padreActual.getPadre());

        //Verificamos si es una variable declarada
        if (Programa.getInstance().getSectionData().containsKey(_nombre)){
            if (!_arreglo){
                if (_tipo == Tipo.NUMERICO || _tipo == Tipo.BOOLEANO){
                    result += "\tlw\t\t$v0, " + this.getEtiqueta()+"\n";
                } else {
                    result += "\tla\t\t$v0, " + this.getEtiqueta()+"\n";
                }
            } else {
                if (_tipo == Tipo.NUMERICO || _tipo == Tipo.BOOLEANO){
                    result += _expresionTamano.compilar();
                    String reg = Programa.getInstance().getRegistros().asignarRegTemporal();
                    result += "\tmul\t\t$v0, $v0, 4\n" +                   //Multiplicamos por 4 para calcular el numero de bytes
                            "\tmove\t\t" + reg + ", $v0\n" +              //Movemos el resultado de la expresion en un reg temporal
                            "\tla\t\t$v0, " + this.getEtiqueta() + "\n" +   //Cargamos en $v0 la direccion de la variable
                            "\tadd\t\t$v0, $v0, " + reg + "\n" +            //Sumamos a esa variable el valor del reg temporal
                            "\tlw\t\t$v0, 0($v0)\n";                        //Cargamos el valor de la direccion
                    Programa.getInstance().getRegistros().liberarRegTemporal(reg);
                } else {
                    result += _expresionTamano.compilar();
                    String reg = Programa.getInstance().getRegistros().asignarRegTemporal();
                    result += "\tmul\t\t$v0, $v0, 4\n" +                   //Multiplicamos por 4 para calcular el numero de bytes
                            "\tmove\t\t" + reg + ", $v0\n" +              //Movemos el resultado de la expresion en un reg temporal
                            "\tla\t\t$v0, " + this.getEtiqueta() + "\n" +   //Cargamos en $v0 la direccion de la variable
                            "\tadd\t\t$v0, $v0, " + reg + "\n" +            //Sumamos a esa variable el valor del reg temporal
                    Programa.getInstance().getRegistros().liberarRegTemporal(reg);
                }
            }
        } else{


            //Si lo encontramos buscamos la posición en la pila
            if (padreActual != null) {
                Metodo metodoPadre = (Metodo) padreActual;
                int posicion = metodoPadre.getPilaLocal().getPosicionEnPila(_nombre);

                result += "\tlw\t\t$v0, " + (metodoPadre.getPilaLocal().getTamanoPila() - posicion) +"($sp)\n";
            }

        }
        return result;
    }

    public Symbol get_valor(){
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

    public void set_valor(Symbol _valor) {
        this._valor = _valor;
    }
}


