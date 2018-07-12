package SemanticAnalizer;

import java.util.HashMap;
import java.util.List;

public class LlamadaMetodo extends Sentencia implements Expresion, Nombre {

    private List<Expresion> _parametros;
    private String _nombre;
    private Tipo _tipo;

    public LlamadaMetodo(String nombre){
        _nombre = nombre;
    }

    public LlamadaMetodo(List params, String nombre){
        _parametros = params;
        _nombre = nombre;
    }

    @Override
    public String getNombre() {
        return _nombre;
    }

    public void setTipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    @Override
    public Tipo getTipo() {
        return _tipo;
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError {
        boolean result = true;

        if (Programa.getInstance().getMetodosNativos().containsKey(_nombre)){
            if (_parametros.size() == 0){
                _tipo = Programa.getInstance().getMetodosNativos().get(_nombre);
            }
            else
                throw new SemanticError("Cantidad de parámetros en " + _nombre + "() no coincide con los esperados:\nCantidad esperada: 0 Cantidad dada: " + _parametros.size());
        } else {

            List<Variable> parametrosEsperados;

            //Se le asignan padres a las expresiones de los parámetros y si no tienen tablas de simbolos se les coloca vacías.
            //NOTA: Se colocan vacías para que en tipoCorrectoParametros y aquí mismo haya garantía de que siempre getTblSimbolosLocales devuelva algo. VER ASTERISCO POR EJEMPLO
            for (Expresion exp: _parametros)
                for (; exp != null; exp = (Expresion) exp.getHermanoDerecho()) {
                    exp.setPadre(this);
                    if (exp.getTblSimbolosLocales() == null) exp.setTblSimbolosLocales(new HashMap<String, Nombre>());
                }

            //Se busca en las tablas de simbolos la referencia
            Componente i = this._padre;
            for (; !i.getTblSimbolosLocales().containsKey(getNombre()); i = i.getPadre());          //-----**------

            if (i == null)
                throw new SemanticError("Referencia no declarada en " + this._padre.toString() + ": " + getNombre());

            //Se checkean la referencia encontrada si es realmente un Metodo
            if (i.getTblSimbolosLocales().get(getNombre()) instanceof Metodo){
                Metodo metodo = (Metodo) i.getTblSimbolosLocales().get(getNombre());
                parametrosEsperados = metodo.getParametros();
            }else
                throw new SemanticError("Referencia a método " + getNombre() + "() no declarada");

            //Se checkea la cantidad de parametros
            if (_parametros.size() == parametrosEsperados.size()){
                Expresion expresion;
                Variable variable;
                //Se checkean los tipos de los parametros
                for (int numParametro = 0; numParametro < _parametros.size(); numParametro++){
                    expresion = _parametros.get(numParametro);
                    variable = parametrosEsperados.get(numParametro);
                    //TODO Falta verificar si es arreglo
                    if (expresion.evaluarTipo() != variable.get_tipo())
                        throw new SemanticError("Tipo de parametro #" + numParametro +" no coincide con su declaración en método " + getNombre());
                }
            } else
                throw new SemanticError("Cantidad de parámetros de llamada a método "+ getNombre() + "(" + _parametros.size() + ") no coincide con la de la declaración (" + parametrosEsperados.size() +")");
        }

        if (this.getHermanoDerecho() != null)
            this.getHermanoDerecho().evaluarSemantica();

        return result;
    }

    @Override
    public String toString() {
        return "LlamadaMetodo{" +  _nombre + '}';
    }

    @Override
    public String get_nombre() {
        return _nombre;
    }

    @Override
    public Tipo evaluarTipo() throws SemanticError{
        if (Programa.getInstance().getMetodosNativos().containsKey(_nombre))
            return Programa.getInstance().getMetodosNativos().get(_nombre);

        //Se busca en las tablas de simbolos la referencia
        Componente i = this._padre;
        while ((i instanceof Sentencia) || (i != null && !i.getTblSimbolosLocales().containsKey(getNombre()))) i = i.getPadre();          //-----**------

        if (i == null)
            throw new SemanticError("Referencia no declarada en " + this._padre.toString() + ": " + getNombre());

        //Se checkean la referencia encontrada si es realmente un Metodo
        if (i.getTblSimbolosLocales().get(getNombre()) instanceof Metodo){
            _tipo = i.getTblSimbolosLocales().get(getNombre()).get_tipo();
        }else
            throw new SemanticError("Referencia a método " + getNombre() + "() no declarada");

        return _tipo;
    }

    @Override
    public Tipo get_tipo() {
        return _tipo;
    }

    public List<Expresion> get_parametros() {
        return _parametros;
    }

    public void set_parametros(List<Expresion> _parametros) {
        this._parametros = _parametros;
    }

    /*private boolean tipoCorrectoParametros(Expresion param, Tipo tipoEsperado, Componente padre) throws SemanticError {
        HashMap<String,Tipo> metodosNativos = new HashMap<String,Tipo>();
        metodosNativos.put("raiz",Tipo.NUMERICO);
        Expresion iter = param;
        do{
            //Se revisa si es un metodo nativo
            if (metodosNativos.containsKey(iter.getNombre())) ((LlamadaMetodo) iter).setTipo(metodosNativos.get(iter.getNombre()));

            if (iter.getTipo() != null){
                if(!iter.getTipo().equals(tipoEsperado)){
                    throw new SemanticError("Tipos no compatibles en " + padre.toString() + ":\nTipo de expresion dada: " + iter.getTipo() + "\nTipo de expresion esperada: " + tipoEsperado.toString());
                    //return false;
                }
            } else if (iter.getNombre() != null){
                HashMap<String, Nombre> tabla = padre.getTblSimbolosLocales();
                Componente aux = padre;

                if(iter instanceof LlamadaMetodo){ // si es una llamada a metodo
                    Tipo t = null;
                    boolean esta = aux.getTblSimbolosLocales().containsKey(iter.getNombre());
                    Nombre var = aux.getTblSimbolosLocales().get(iter.getNombre());

                    if(var != null){
                        t = var.get_tipo();
                    }
                    do {
                        if (!esta || !(var instanceof Metodo) || t != tipoEsperado ) {
                            if((aux = aux.getPadre()) != null){
                                esta = aux.getTblSimbolosLocales().containsKey(iter.getNombre());
                                var = aux.getTblSimbolosLocales().get(iter.getNombre());
                                if(var != null){
                                    t = var.get_tipo();
                                }
                            }else{
                                aux = null;
                            }
                        }else{
                            break;
                        }
                    } while (aux != null);
                    if(aux == null) {
                        throw new SemanticError("Tipos no compatibles");
                    }
                    ((LlamadaMetodo) iter).evaluarSemantica();
                } else { // si es una variable
                    Tipo t = null;
                    boolean esta = aux.getTblSimbolosLocales().containsKey(iter.getNombre());
                    Nombre var = aux.getTblSimbolosLocales().get(iter.getNombre());
                    if(var != null){
                        t = var.get_tipo();
                    }
                    do {
                        if (!esta || !(var instanceof Variable) || t != tipoEsperado ) {
                            if((aux = aux.getPadre()) != null){
                                esta = aux.getTblSimbolosLocales().containsKey(iter.getNombre());
                                var = aux.getTblSimbolosLocales().get(iter.getNombre());
                                if(var != null){
                                    t = var.get_tipo();
                                }
                            }else{
                                aux = null;
                            }
                        }else{
                            break;
                        }
                    } while (aux != null);
                    if(aux == null) {
                        throw new SemanticError("Tipos no compatibles");
                    }
                }
            } else {
                return false;
            }
            //System.out.println(iter.toString());
            iter = (Expresion) iter.getHermanoDerecho();
        }while(iter != null);
        return true;
    }*/
}
