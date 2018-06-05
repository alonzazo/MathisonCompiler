package SemanticAnalizer;

public class Devolver extends Sentencia {

    private Object _valor;

    public Devolver(){
    }

    public Devolver(Object valor){
        _valor = valor;
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString() {
        return "Devolver{"  + _valor + '}';
    }
}
