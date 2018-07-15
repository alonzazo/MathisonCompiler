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
        _expresion.setPadre(this);
        _expresion.evaluarTipo();

        if (this.getHermanoDerecho() != null)
            this.getHermanoDerecho().evaluarSemantica();

        return true;
    }

    @Override
    public String toString() {
        return "Imprimir{" + _expresion + '}';
    }

    //@Override
    public String compilar() throws SemanticError {
        String result = "";
        //TODO Compilar del imprimir
        Tipo tipoExpresion = _expresion.evaluarTipo();
        if (tipoExpresion == Tipo.NUMERICO){
            String cadena = ((ExpresionGenerico) _expresion).getEtiqueta();
            if (_expresion instanceof Variable){
                result = "\t#imprimir(" + cadena +")\n" +
                        "\tlw \t\t$a0, " + cadena + "\n" +
                        "\tli \t\t$v0, 1\t\t##println(int)\n" +
                        "\tsyscall\n\n";
            } else {
                result = "\t#imprimir(" + cadena +")\n" +
                        "\tli \t\t$a0, " + cadena + "\n" +
                        "\tli \t\t$v0, 1\t\t##println(int)\n" +
                        "\tsyscall\n\n";
            }

        } else if (tipoExpresion == Tipo.CADENA){
            String cadena = ((ExpresionGenerico) _expresion).getEtiqueta();
            if (_expresion instanceof Variable){
                result = "\t#imprimir(" + cadena+")\n" +
                        "\tli $v0, 4 \t\t\t\t# Carga system call code para el print\n" +
                        "\tla $a0, " + cadena + " \t\t# Carga la direccion a a0\n" +
                        "\tsyscall\n\n";
            }else {
                result = "\t#imprimir(" + Programa.getInstance().getHeap().get(cadena).getValor()+")\n" +
                        "\tli $v0, 4 \t\t\t\t# Carga system call code para el print\n" +
                        "\tla $a0, " + cadena + " \t\t# Carga la direccion a a0\n" +
                        "\tsyscall\n\n";
            }
        } else {
            //TODO agregar todo la evaluación lógica de la expresion
            result = ((ExpresionGenerico) _expresion).getEtiqueta();;
        }
        if (_hermanoDerecho != null)
            return result + _hermanoDerecho.compilar();
        return result;
    }
}
