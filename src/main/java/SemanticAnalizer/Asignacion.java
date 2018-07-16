package SemanticAnalizer;

import java.util.HashMap;

public class Asignacion extends Sentencia {
    private String _nombre;
    private Object _valor;
    private Expresion _expresion;
    private Expresion _expresionIndice;
    private Tipo _tipo;

    public Asignacion(){ }

    public Asignacion(String nombre)
    {
        _nombre = nombre;
    }

    public Asignacion(String nombre, Expresion expresionIndice){
        _nombre = nombre;
        _expresionIndice = expresionIndice;
    }

    public Tipo getTipo() {
        return _tipo;
    }

    public void setTipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public Object get_valor() {
        return _valor;
    }

    public void set_valor(Object _valor) {
        this._valor = _valor;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Expresion get_expresion() {
        return _expresion;
    }

    public void set_expresion(Expresion expresion) {
        this._expresion = expresion;
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError{
        verificarExistencias();
        _tblSimbolosLocales = new HashMap<>();

        //Se evalúa el indice y la expresión
        if (!evaluarIndice())
            throw new SemanticError("Resultado final de expresión no era de tipo esperado:\nTipo esperado: " + _tipo.toString() + " Expresión de tipo: " + _expresionIndice.evaluarTipo());
        if (!evaluarExpresion())
            throw new SemanticError("Resultado final de expresión en índice no era de tipo esperado:\nTipo esperado: " + Tipo.NUMERICO + " Expresión de tipo: " + _expresionIndice.evaluarTipo());

        //Evaluamos las demás componentes:
        if (this.getHermanoDerecho() != null)
            this.getHermanoDerecho().evaluarSemantica();

        return true;
    }

    private boolean verificarExistencias() throws SemanticError{
        //Checkeamos si estamos asignando a una palabra reservada.
        if (Programa.getInstance().getMetodosNativos().containsKey(_nombre))
            throw new SemanticError("Palabra reservada para método nativo: " + _nombre);

        //Buscamos alguna tabla de simbolos que lo contenga
        Componente padreActual = getPadre();
        for (; padreActual != null; padreActual = padreActual.getPadre())
            if (padreActual.getTblSimbolosLocales().containsKey(_nombre))
                break;

        if (padreActual == null) throw new SemanticError("Referencia no declarada en " + getPadre().toString() + ": " + _nombre);

        //Seteamos el tipo
        setTipo(padreActual.getTblSimbolosLocales().get(_nombre).get_tipo());

        return true;
    }

    private boolean evaluarExpresion() throws SemanticError {
        try{
            _expresion.setPadre(this);
            _expresion.evaluarSemantica();
            return _expresion.evaluarTipo() == _tipo;
        }catch (SemanticError e){
            throw new SemanticError("Expresión inválido en " + this.toString() + "\n" + e.getMessage());
        }
    }

    public boolean evaluarIndice() throws SemanticError {
        try{
            if (_expresionIndice != null) {
                setPadreExpresionesIndice();
                return _expresionIndice.evaluarTipo() == Tipo.NUMERICO;
            }
            else
                return true; //Si la asginacion no es a un arreglo
        }catch (SemanticError e){
            throw new SemanticError("Índice inválido en " + this.toString() + "\n" + e.getMessage());
        }

    }

    private void setPadreExpresiones(){
        if (_expresion instanceof Operacion)
            for (Componente i = ((Operacion) _expresion).get_primeraHoja(); i != null; i = i.getHermanoDerecho())
                i.setPadre(this);
        else
            _expresion.setPadre(this);
    }

    private void setPadreExpresionesIndice(){
        if (_expresionIndice instanceof Operacion)
            for (Componente i = ((Operacion) _expresionIndice).get_primeraHoja(); i != null; i = i.getHermanoDerecho())
                i.setPadre(this);
        else
            _expresionIndice.setPadre(this);
    }
    @Override
    public String toString() {
        return "Asignacion{" +  _nombre + '}';
    }

    @Override
    public String compilar() throws SemanticError {
        String result = "";

        //Compilamos la expresion
        result += "\t#Asignacion\n" +
                _expresion.compilar();

        //Buscamos la referencia en la pila y en el heap
        if (Programa.getInstance().getHeap().containsKey(_nombre)){
            if (_tipo == Tipo.NUMERICO || _tipo == Tipo.BOOLEANO){
                result += "\tsw\t\t$v0, " + _nombre + "\t#Asignacion\n\n";
            } else {                                        // asignacion de cadena Cadenas
                result += "\tsw\t\t$v0, " + _nombre + "\t#Asignacion\n\n";
            }
        } else{
            //Buscamos el método padre
            Componente padreActual = this._padre;
            for (;padreActual != null && !(padreActual instanceof Metodo);
                 padreActual = padreActual.getPadre());

            //Si lo encontramos buscamos la posición en la pila
            if (padreActual != null) {
                Metodo metodoPadre = (Metodo) padreActual;
                int posicion = metodoPadre.getPilaLocal().getPosicionEnPila(_nombre);

                result += "\tsw\t\t$v0, " + (metodoPadre.getPilaLocal().getTamanoPila() - posicion) +"($sp)\t#Asignacion\n\n";
            }

        }

        if (_hermanoDerecho != null)
            return result + _hermanoDerecho.compilar();
        return result;
    }
}
