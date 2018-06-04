package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Mientras extends Estructura {

    public Mientras(){
        System.out.println("Mientras");
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
