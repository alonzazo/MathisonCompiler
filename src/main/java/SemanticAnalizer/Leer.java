package SemanticAnalizer;

public class Leer extends Sentencia {

    private Expresion _variable;
    private final int numMaxEntrada = 128;
    public Leer(){
        _variable = null;
    }

    public Leer(Expresion _variable) {
        this._variable = _variable;
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError{

        if (this.getHermanoDerecho() != null)
            this.getHermanoDerecho().evaluarSemantica();

        return true;
    }

    @Override
    public String toString() {
        return "Leer{" + _variable +'}';
    }

    //@Override
    public String compilar() throws SemanticError {
        String result = "";

        if (_variable == null)
            result = "\t#leer()\n" +
                    "\tli \t\t$v0, 5\n" +
                    "\tsyscall";

        //Revisamos el tipo de variable variable
        _variable.setPadre(this);
        Tipo tipo = _variable.evaluarTipo();
        switch (tipo){
            case CADENA:
                result = "\t#leer("  + _variable.getNombre() + ")\n" +
                        "\tli $v0, 8\n" +
                        "\tla $a0, " + _variable.getNombre() +"\n" +
                        "\tli $a1, 2\n" +
                        //"\tli $a1, " + Programa.getInstance().getTamanoMaximoCadena() +"\n" + //TODO Agregar soporte para strings
                        "\tsyscall\n\n";
                break;
            case BOOLEANO: //Para el caso de booleano aplica igual el caso de numerico
            case NUMERICO:
                result = "\t#leer(" + _variable.getNombre() + ")\n" +
                        "\tli \t\t$v0, 5\n" +
                        "\tsyscall\n" +
                        "\tsw\t\t$v0, " + _variable.getNombre() + "\n\n";
                break;
        }

        //Compilamos los demás componentes
        if (_hermanoDerecho != null)
            return result + _hermanoDerecho.compilar();
        return result;
    }
}
