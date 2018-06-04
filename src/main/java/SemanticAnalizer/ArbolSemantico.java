package SemanticAnalizer;

import javafx.util.Pair;

import java.util.Stack;

public class ArbolSemantico {

    private Componente _RAIZ;
    private Componente _currentContext;
    private Componente _currentNode;
    private Componente _firstOfTheContext;
    private Componente _lastOfTheContext;
    private Stack<Pair<Componente,Componente>> _contexts;

    public ArbolSemantico(){
        _contexts = new Stack<Pair<Componente, Componente>>();
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
        //popCurrentContext();
        if (_firstOfTheContext == null){
            _firstOfTheContext = componente;
            _lastOfTheContext = componente;
        }else {
            if (_firstOfTheContext.getPadre() != null){
                componente.setPadre(_firstOfTheContext.getPadre());
                componente.getPadre().setHijoMasIzq(componente);
            }
            componente.setHermanoDerecho(_firstOfTheContext);
            _firstOfTheContext = componente;
            resetLastOfContext();
        }
        //pushCurrentContext();
        return  _firstOfTheContext;
    }

    private Componente resetLastOfContext(){
        if (_firstOfTheContext != null){
            Componente i = _firstOfTheContext;
            while (i.getHermanoDerecho() != null) i = i.getHermanoDerecho();
            _lastOfTheContext = i;
            return _lastOfTheContext;
        }
        return null;
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

                //Restauramos el contexto anterior
                popCurrentContext();

                //Si el contexto anterior no era nulo, coloca el padre actual como el último del contexto anterior
                if (_lastOfTheContext != null){
                    _lastOfTheContext.setHermanoDerecho(_currentNode);
                    _lastOfTheContext = _currentNode;
                    pushCurrentContext();
                    flush();
                } else {
                    _firstOfTheContext = _currentNode;
                    _lastOfTheContext = _currentNode;
                    pushCurrentContext();
                    flush();
                }

                if (padre instanceof Programa) _RAIZ = padre;
                return _currentNode;
            }
        }
        return null;
    }

    public void pushCurrentContext(){
        _contexts.push(new Pair<Componente, Componente>(_firstOfTheContext, _lastOfTheContext));
    }

    public Pair<Componente,Componente> popCurrentContext(){
        if (!_contexts.empty()){
            Pair<Componente, Componente> result = _contexts.pop();
            _firstOfTheContext = result.getKey();
            _lastOfTheContext = result.getValue();
            return result;
        }
        else {
            flush();
            return null;
        }
    }

    private void flush(){
        _firstOfTheContext = null;
        _lastOfTheContext = null;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
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
