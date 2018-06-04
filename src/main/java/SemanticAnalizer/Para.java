package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Para extends Estructura {

    public Para(){
        System.out.println("Para");
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
