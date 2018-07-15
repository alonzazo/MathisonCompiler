package SemanticAnalizer;

import java.util.HashMap;

public class ManejadorDeRegistros {
    private HashMap<String, Integer> registros;

    //TODO Reparticion de registros, durante el cual seleccionamos el conjunto de variables que residirán en los registros en cada punto del programa

    //TODO Asignación de registros, durante la cual elegimos el registro específico en el que residirá una variable.
    public ManejadorDeRegistros() {
        registros.put("$v0", null);
        registros.put("$v1", null);

        registros.put("$a0", null);
        registros.put("$a1", null);
        registros.put("$a2", null);
        registros.put("$a3", null);

        registros.put("$t0", null);
        registros.put("$t1", null);
        registros.put("$t2", null);
        registros.put("$t3", null);
        registros.put("$t4", null);
        registros.put("$t5", null);
        registros.put("$t6", null);
        registros.put("$t7", null);

        registros.put("$s0", null);
        registros.put("$s1", null);
        registros.put("$s2", null);
        registros.put("$s3", null);
        registros.put("$s4", null);
        registros.put("$s5", null);
        registros.put("$s6", null);
        registros.put("$s7", null);

        registros.put("$t8", null);
        registros.put("$t9", null);

        registros.put("$k0", null);
        registros.put("$k0", null);
    }

    public HashMap<String, Integer> getRegistros() {
        return registros;
    }

    public void setRegistros(HashMap<String, Integer> registros) {
        this.registros = registros;
    }

}
