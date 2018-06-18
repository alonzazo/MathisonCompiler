package SemanticAnalizer;

public class Leer extends Sentencia {

    String _variable;
    public Leer(){
        _variable = "";
    }

    public Leer(String _variable) {
        this._variable = _variable;
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString() {
        return "Leer{" + _variable +'}';
    }
}
