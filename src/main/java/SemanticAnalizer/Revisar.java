package SemanticAnalizer;


public class Revisar extends Estructura {

    public Revisar(){
        System.out.println("Revisar");
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
