package SemanticAnalizer;

public class Si extends Estructura {

    public Si(){
        System.out.println("Si");
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
