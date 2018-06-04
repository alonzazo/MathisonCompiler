package SemanticAnalizer;

public class ArbolSemantico {

    private Componente _RAIZ;
    private Componente _currentContext;
    private Componente _currentNode;
    private Componente _firstOfTheContext;
    private Componente _lastOfTheContext;


    public ArbolSemantico(){
    }

    public Componente get_RAIZ() {
        return _RAIZ;
    }

    public Componente get_currentNode() {
        if (_currentNode != null) return _currentNode;
        return null;
    }

    public void set_currentNode(Componente _actualNode) {
        this._currentNode = _actualNode;
    }

    /**
     * Sube al contexto superior del nodo en el que está.
     * @return Retorna el nodo actual, y si es el raíz, retorna null.
     */
    public Componente goUp(){
        if (_currentNode.getPadre() != null){
            _currentNode = _currentNode.getPadre();
            _currentContext = _currentNode.getPadre();
            return _currentNode;
        }
        return null;
    }

    /**
     * Coloca un componente al final del contexto actual
     * @param componente Componente por colocar
     * @return Componente colocado.
     */
    public Componente putAtTheEndOfContext(Componente componente){
        if (_firstOfTheContext == null){
            _firstOfTheContext = componente;
            _lastOfTheContext = componente;
        }else {
            _lastOfTheContext.setHermanoDerecho(componente);
            _lastOfTheContext = componente;
        }
        return  _lastOfTheContext;
    }

    /**
     * Coloca un componente en el principio del contexto actual, si no hay ninguno
     * lo pone como primero del contexto. Setea el padre de los hermano al que substituye.
     * @param componente Componente por agregar
     * @return Componente agregado
     */
    public Componente putAtTheBeginOfContext(Componente componente){
        if (_firstOfTheContext == null){
            _firstOfTheContext = componente;
            _lastOfTheContext = componente;
        }else {
            componente.setPadre(_firstOfTheContext.getPadre());
            componente.setHermanoDerecho(_firstOfTheContext);
            componente.getPadre().setHijoMasIzq(componente);
            _firstOfTheContext = componente;
        }
        return  _firstOfTheContext;
    }


    /**
     * Setea el padre del contexto actual si el contexto no está vacío y si
     * no hay otro padre ya seteado.
     * @param padre Padre por asignar
     * @return Retorna el componente de entrada si no es agregado y si no fue agregado null.
     */
    public Componente setFatherToCurrentContext(Componente padre){
        //Contexto vacio
        if (_firstOfTheContext == null){
            return null;
        }
        //Contexto no vacio
        else {
            if (_firstOfTheContext.getPadre()==null){// Contexto no vacio sin padre
                padre.setHijoMasIzq(_firstOfTheContext);
                Componente i = _firstOfTheContext;
                do {
                    i.setPadre(padre);
                    i = i.getHermanoDerecho();
                } while (i != null);
                _currentNode = padre;
                if (padre instanceof Programa) _RAIZ = padre;
                return _firstOfTheContext.getPadre();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        //Recorrido en profundidad primero
        if (_RAIZ == null) return "";
        return toStringAux("", 0,  _RAIZ);
    }

    private String toStringAux(String text,int indexLevel, Componente actual){
        if (actual == null) return text;

        text += '\n';
        for (int i = 0; i < indexLevel; i++) text += "|\t";

        text += actual.toString();

        if ( actual.getHijoMasIzq() != null){
            text = toStringAux(text, indexLevel + 1, actual.getHijoMasIzq());
        }
        if ( actual.getHermanoDerecho() != null ){
            text = toStringAux(text, indexLevel, actual.getHermanoDerecho());
        }
        return text;
    }

    //TODO: Pendientes métodos
}
