package SemanticAnalizer;

import java.util.HashMap;

public abstract class ComponenteConcreto implements Componente{
    protected Componente _ultimoHijo;
    protected Componente _padre;
    protected Componente _hijoMasIzq;
    protected Componente _hermanoDerecho;
    protected HashMap<String, Nombre> _tblSimbolosLocales;

    @Override
    public Componente getPadre() {
        return _padre;
    }

    @Override
    public Componente setPadre(Componente padre) {
        _padre = padre;
        return _padre;
    }

    @Override
    public Componente getHermanoDerecho() {
        return _hermanoDerecho;
    }

    @Override
    public Componente getHijoMasIzq() {
        return _hijoMasIzq;
    }

    /**
     * Coloca un hermano a la derecha de este componente. Setea el último
     * componente correspondiente a ese nuevo hermano. Si el hermano no
     * tiene más hermano se queda como último componente.
     * @param hermano Hermano por colocar
     * @return Hermano colocado.
     */
    @Override
    public Componente setHermanoDerecho(Componente hermano) {
        if ( _hermanoDerecho == null ){
            _hermanoDerecho = hermano;
        } else {
            _hermanoDerecho = hermano;
            Componente i = _hermanoDerecho;
            while ( i.getHermanoDerecho() != null ){
                i = _hermanoDerecho;
            }
            _ultimoHijo = i;
        }
        return hermano;
    }

    /**
     *  Coloca un hijo lo más izquierdo del componente actual. Si no hay hijo se coloca como ultimo hijo, si ya
     *  tiene hijos los liga a este y pone este como el más izquierdo.
     *  @param hijo Hijo por colocar
     *  @return Hijo colocado
     */
    @Override
    public Componente setHijoMasIzq( Componente hijo ) {
        if ( _hijoMasIzq == null ){
            _hijoMasIzq = hijo;
            _ultimoHijo = _hijoMasIzq;
        } else {
            hijo.setHermanoDerecho(_hijoMasIzq);
            _hijoMasIzq = hijo;
        }
        return _hijoMasIzq;
    }

    public Componente setUltimoHijo(Componente _ultimoHijo) {
        this._ultimoHijo = _ultimoHijo;
        return  _ultimoHijo;
    }

    /**
     *  Busca el ultimo hijo y lo setea como ultimo hijo. Recorrido O(n) en listas.
     * @return Ultimo hijo.
     */
    public Componente buscarUltimoHijo(){
        if (_hijoMasIzq == null) return null;

        Componente i = _hijoMasIzq;
        while (i.getHermanoDerecho() != null)
            i = i.getHermanoDerecho();
        _ultimoHijo = i;
        return _ultimoHijo;
    }

    @Override
    public Componente getUltimoHijo() {
        return _ultimoHijo;
    }

    @Override
    public HashMap<String, Nombre> getTblSimbolosLocales() {
        return _tblSimbolosLocales;
    }

    @Override
    public void setTblSimbolosLocales(HashMap<String, Nombre> tabla)
    {
        _tblSimbolosLocales = tabla;
    }
}
