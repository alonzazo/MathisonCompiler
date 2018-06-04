package SemanticAnalizer;

public class Leer extends Sentencia {

    public Leer(){
        System.out.println("Leer");
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString() {
        return "Leer{" + '}';
    }
}
