package SemanticAnalizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class TblDeclaracionesPendientes extends HashSet<Nombre>{
    private HashMap< Nombre, Integer > cantidadReferencias;


    /**
     * Devuelve la cantidad de referencias encontradas para ese nombre.
     * @param nombre Llave de tipo Nombre
     * @return cantidad de referencias a ese nombre
     */
    public int getNumberRefs(Nombre nombre){

        if (this.contains(nombre))
            return cantidadReferencias.get(nombre);
        return 0;
    }

    /**
     * Adds the specified element to this set if it is not already present.
     * More formally, adds the specified element <tt>e</tt> to this set if
     * this set contains no element <tt>e2</tt> such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns <tt>false</tt>.
     *
     * @param nombre element to be added to this set
     * @return <tt>true</tt> if this set did not already contain the specified
     * element
     */
    @Override
    public boolean add(Nombre nombre) {
        cantidadReferencias.put(nombre, cantidadReferencias.get(nombre) + 1);
        return super.add(nombre);
    }

    public boolean sub(Nombre nombre){
        if (this.cantidadReferencias.containsKey(nombre)) {
            //Si hay más referencias que reduzca la cantidad
            if (this.cantidadReferencias.get(nombre) > 1) {
                cantidadReferencias.put(nombre, cantidadReferencias.get(nombre) - 1);
                return false;
            } else { //Si es 0 o negativa que elimine la entrada
                cantidadReferencias.remove(nombre);
                this.remove(nombre);
            }
        }
        return false;
    }

    /**
     * Removes the specified element from this set if it is present.
     * More formally, removes an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>,
     * if this set contains such an element.  Returns <tt>true</tt> if
     * this set contained the element (or equivalently, if this set
     * changed as a result of the call).  (This set will not contain the
     * element once the call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return <tt>true</tt> if the set contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        cantidadReferencias.remove(o);
        return super.remove(o);
    }

}
