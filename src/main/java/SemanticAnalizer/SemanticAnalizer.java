package SemanticAnalizer;

import java.util.HashMap;

public class SemanticAnalizer {
    public static void main(String[] args){
        Nombre unMetodoAhi = new Metodo();
        Nombre unaVariable = new Variable();
        Nombre unaClaseAhi = new Clase();
        HashMap<String, Nombre> map = new HashMap<String, Nombre>();

        map.put( "metodo", unMetodoAhi );
        map.put( "variable", unaVariable );
        map.put( "clase", unaClaseAhi );

        if ( map.get("metodo") instanceof Metodo ){
            Metodo hola = (Metodo) map.get("metodo");
            System.out.println("En efecto, metodo es de tipo metodo");
        } else {
            System.out.println("Busque otra forma");
        }

    }

}
