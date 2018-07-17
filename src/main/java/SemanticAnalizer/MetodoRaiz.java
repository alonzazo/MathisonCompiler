package SemanticAnalizer;

import java.util.LinkedList;
import java.util.List;

public class MetodoRaiz extends Metodo {

    public MetodoRaiz() {
        super("raiz",Tipo.NUMERICO, null);
        _parametros = new LinkedList<>();
        _parametros.add(new Variable("n", Tipo.NUMERICO,false));
    }

    @Override
    public String compilar() throws SemanticError {
        inicializarPila();

        String result = "#raiz()\t\n" +
                        "raiz:\n" +
                        "\tsw\t$ra, 0($sp) \t#Guardamos la direccion de retorno\\n\n" +
                        "\taddi\t$sp, $sp, -8\t#Hacemos espacio en pila \n" +
                        "\t\n" +
                        "\tlw\t$t0, 4($sp) \t# Cargamos n\n" +
                        "\tmove\t$t2, $t0\t# x = n\t\n" +
                        "\n" +
                        "\tli\t$v1, 2\t\t#Cargamos n/2\n" +
                        "\tdiv\t$t0, $v1\t\n" +
                        "\tmflo\t$t3\t\n" +
                        "\tli\t$t1, 0 \t\t# Cargamos i = 0\n" +
                        "loop_raiz:\n" +
                        "\tdiv\t$t0, $t2\t#Dividimos $v0 = n/x\n" +
                        "\tmflo\t$v0\t\t\n" +
                        "\t\n" +
                        "\tadd\t$v0, $v0, $t2\t#Sumamos $v0 = x + n/x\n" +
                        "\tli\t$v1, 2\t\t#Cargamos n/2\n" +
                        "\tdiv\t$v0, $v1\t#Dividimos $t2 -> x = (x + n/x)/2\n" +
                        "\tmflo\t$t2\n" +
                        "\tslt\t$v0, $t1, $t3\t# i < n/2\n" +
                        "\taddi\t$t1, $t1, 1\n" +
                        "\tbnez\t$v0, loop_raiz\t# loop\n" +
                        "\t\n" +
                        "\tmove\t$v0, $t2\t# devolvemos x\n" +
                        "\taddi\t$sp, $sp, 8\t# Restauramos la pila\n" +
                        "\tlw\t$ra, 0($sp)\t# Cargamos direccion de retorno\n" +
                        "\tjr\t$ra\t\t# Saltamos a direccion de retorno\n" +
                        "\t\n";
         return result;
    }
}
