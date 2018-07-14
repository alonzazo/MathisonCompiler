package SemanticAnalizer;

public class Devolver extends Sentencia {

    private Tipo _tipo;
    private Expresion expresion;

    public Devolver(){
        System.out.println("Devolver");
    }


    public Devolver(Expresion expresion){ this.expresion = expresion;}

    public Tipo get_tipo() {
        return _tipo;
    }

    public void set_tipo(Tipo tipo) {
        _tipo = tipo;
    }

    public String getNombre()
    {
        return expresion.toString();
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError{
        Componente padreActual = getPadre();
        for (; !(padreActual instanceof Metodo); padreActual = padreActual.getPadre());

        ((Metodo) padreActual).set_tieneDevolver(true);

        if (((Metodo) padreActual).get_tipo() == null)
            throw new SemanticError("No se espera sentencias DEVOLVER en PROCEDIMIENTO: " + ((Metodo) padreActual).get_nombre());
        //Revisamos que el tipo de retorno sea el mismo que el que se espera.
        expresion.setPadre(this);
        if (expresion.evaluarTipo() != ((Metodo) padreActual).get_tipo())
            throw new SemanticError("Tipos incompatibles en sentencia DEVOLVER:\nTipo esperado: " + ((Metodo) padreActual).get_tipo() + " Tipo dado: " + expresion.evaluarTipo()); //TODO Colocar en variables antes para no correr el método dos veces.

        //Evaluamos las demás sentencias
        if (getHermanoDerecho() != null)
            getHermanoDerecho().evaluarSemantica();

        return true;
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
            return "Devolver{" + expresion.toString() + '}';
        }

    }

    @Override
    public String compilar() throws SemanticError {
        if (_hermanoDerecho != null)
            return _hermanoDerecho.compilar();
        return "";
    }
}
