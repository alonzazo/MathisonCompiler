package SemanticAnalizer;

public class Devolver extends Sentencia {

    private Object _valor;
    private Tipo _tipo;

    public Devolver(){
        System.out.println("Devolver");
    }


    public Devolver(Object valor)
    {
        if(valor instanceof Tipo)
        {
            Tipo tipo = (Tipo) valor;
            _tipo = tipo;
        }
        else
        {
            _valor = valor;
        }
    }

    public Tipo get_tipo() {
        return _tipo;
    }

    public void set_tipo(Tipo tipo) {
        _tipo = tipo;
    }

    public String getNombre()
    {
        Nombre nom  = (Nombre)_valor;
        return nom.get_nombre();
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString()
    {
        if(_tipo != null)
        {
            if(_tipo == Tipo.NUMERICO)
                return "Devolver numerico";
            else if (_tipo == Tipo.CADENA)
                return "Devolver cadena";
            else
                return "Devolver booleano";
        }
        else
        {
            return "Devolver{" + _valor.toString() + '}';
        }

    }
}
