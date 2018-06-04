package SemanticAnalizer;

public class Sino extends Estructura {

    public Sino(){
        System.out.println("Sino");
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}