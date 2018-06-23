package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

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
        Expresion e = _expresion instanceof Operacion ? ((Operacion) _expresion).get_primeraHoja() : _expresion;
        return tipoDatosCorrecto(e,_tipo,this) && evaluarIndice();
    }

    public boolean evaluarIndice() throws SemanticError {
        try{
            if (_expresionIndice != null) {
                setPadreExpresiones();
                return _expresionIndice.evaluarTipo() == Tipo.NUMERICO;
            }
            else
                return true; //Si la asginacion no es a un arreglo
        }catch (SemanticError e){
            throw new SemanticError("Índice inválido en " + this.toString() + "\n" + e.getMessage());
        }

    }

    private void setPadreExpresiones(){
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


}
