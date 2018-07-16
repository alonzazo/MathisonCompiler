package SemanticAnalizer;

public class MetodoSalir extends Metodo {

    public MetodoSalir() {
        super("salir",null);
    }

    @Override
    public String compilar() throws SemanticError {
        inicializarPila();

        String result =
                "salir:\n" +
                "\tli\t\t$v0, 10\t#exit()\n" +
                "\tsyscall\n\n";

        return result;
    }
}
