package SemanticAnalizer;

public class Devolver extends Sentencia {

    private Object _valor;

    public Devolver(){
        System.out.println("Devolver");
    }

    public Devolver(Object valor){
        _valor = valor;
        System.out.println("Devolver");
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString() {
        return "Devolver{" + '}';
    }
}
