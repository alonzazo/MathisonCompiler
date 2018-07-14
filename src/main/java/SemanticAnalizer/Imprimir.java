package SemanticAnalizer;

public class Imprimir extends Sentencia {

    Expresion _expresion;

    public Imprimir(){
    }

    public Imprimir(Expresion expresion){
        _expresion = expresion;
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError {
        _expresion.evaluarTipo();
        return true;
    }

    @Override
    public String toString() {
        return "Imprimir{" + _expresion + '}';
    }

    //@Override
    public String compilar() throws SemanticError {
        //TODO Compilar del imprimir
        return "";
    }
}
