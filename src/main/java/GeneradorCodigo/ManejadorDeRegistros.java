package GeneradorCodigo;

import java.util.HashMap;
import java.util.function.BinaryOperator;

public class ManejadorDeRegistros {
    private HashMap<String, Boolean> registros;

    //TODO Reparticion de registros, durante el cual seleccionamos el conjunto de variables que residirán en los registros en cada punto del programa

    //TODO Asignación de registros, durante la cual elegimos el registro específico en el que residirá una variable.

    public ManejadorDeRegistros() {
        registros.put("$v0", false);
        registros.put("$v1", false);

        registros.put("$a0", false);
        registros.put("$a1", false);
        registros.put("$a2", false);
        registros.put("$a3", false);

        registros.put("$t0", false);
        registros.put("$t1", false);
        registros.put("$t2", false);
        registros.put("$t3", false);
        registros.put("$t4", false);
        registros.put("$t5", false);
        registros.put("$t6", false);
        registros.put("$t7", false);

        registros.put("$s0", false);
        registros.put("$s1", false);
        registros.put("$s2", false);
        registros.put("$s3", false);
        registros.put("$s4", false);
        registros.put("$s5", false);
        registros.put("$s6", false);
        registros.put("$s7", false);

        registros.put("$t8", false);
        registros.put("$t9", false);

        registros.put("$k0", false);
        registros.put("$k0", false);
    }

    public HashMap<String, Boolean> getRegistros() {
        return registros;
    }

    public void setRegistros(HashMap<String, Boolean> registros) {
        this.registros = registros;
    }

    public String asignarRegTemporal(){
        // Buscamos un registro que no esté siendo usado
        int i = 0;
        for (; i < 10 && registros.get("$t" + i);
             i++);

        //Si no encontramos nada devolvemos null
        if (i == 10) return null;

        //Cuando lo encontramos lo marcamos
        registros.put("$t" + i, true);
        return "$t" + i;            //Retornamos que está en uso
    }

    public boolean liberarRegTemporal(String reg){
        if (registros.containsKey(reg)){
            registros.put("reg", false);
            return true;
        }
        return false;
    }

}
