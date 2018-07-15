package GeneradorCodigo;

import java.util.HashMap;
import java.util.Stack;

public class ManejadorDePila {

    HashMap<String, Integer> direcciones = new HashMap<>();
    Stack<String> pila = new Stack<>();
    int tamano = 0;

    public int getPosicionEnPila(String variable, int bytes){
        if (direcciones.containsKey(variable))
            return direcciones.get(variable);
        else {
            pila.push(variable);
            tamano += bytes;
            direcciones.put(variable, tamano);
            return tamano;
        }
    }

    public int getPosicionEnPila(String variable){
            return direcciones.get(variable);
    }

    public int getTamanoPila(){
        return tamano;
    }
}
