package GeneradorCodigo;

import java.util.HashMap;
import java.util.Stack;

public class ManejadorDePila {

    HashMap<String, Integer> direcciones = new HashMap<>();
    Stack<String> pila = new Stack<>();
    int tamano = 0;

    public int getPosicionEnPila(String variable, int bytes){
        if (!direcciones.containsKey(variable)){
            pila.push(variable);
            direcciones.put(variable, tamano); //Al lado derecho va el offset
            tamano += bytes;
        }
        return direcciones.get(variable);
    }

    public int getPosicionEnPila(String variable){
            return direcciones.get(variable);
    }

    public int getTamanoPila(){
        return tamano;
    }

    public void sacarDePila(){
        tamano = direcciones.get(pila.peek());
        direcciones.remove(pila.peek());
        pila.pop();
    }
}
