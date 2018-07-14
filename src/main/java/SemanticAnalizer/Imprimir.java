package SemanticAnalizer;

import java.util.List;

public class Imprimir extends Sentencia {

    String _valor;

    public Imprimir(){
    }

    public Imprimir(String valor){
        _valor = valor;
    }

    public Imprimir(Object valor){
        _valor = valor.toString();
    }

    public Imprimir(double valor){
        _valor = "" + valor;
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString() {
        return "Imprimir{" +  _valor + '}';
    }
    
}
