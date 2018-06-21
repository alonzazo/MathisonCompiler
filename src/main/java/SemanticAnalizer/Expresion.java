package SemanticAnalizer;

public interface Expresion extends Componente{
    String getNombre();
    Tipo getTipo();
    Tipo evaluarTipo() throws SemanticError;
}
