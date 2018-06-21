package SemanticAnalizer;

public class Operacion extends ComponenteConcreto implements Expresion {

    public enum TipoOperador {
        SUMA, RESTA, MULTIPLICACION, DIVISION,  // OP. NUMERICOS
        IGUAL, DISTINTO,                        // OP. IDENTIDAD
        MAYORQUE, MAYOROIGUAL,                  // OP. LOGICOS NUMERICOS
        MENORQUE, MENOROIGUAL,                  // OP. LOGICOS NUMERICOS
        Y, O                                    // OP. LOGICOS
    }

    private Tipo _tipoSalida;
    private Expresion _expIzq;
    private Expresion _expDer;
    private TipoOperador _tipoOperacion;
    private Expresion _primeraHoja;
    private Expresion _ultimaHoja;

    public Expresion get_primeraHoja() {
        return _primeraHoja;
    }

    public void set_primeraHoja(Expresion _primeraHoja) {
        this._primeraHoja = _primeraHoja;
    }

    public Expresion get_ultimaHoja() {
        return _ultimaHoja;
    }

    public void set_ultimaHoja(Expresion _ultimaHoja) {
        this._ultimaHoja = _ultimaHoja;
    }

    public Operacion(TipoOperador tipo, Expresion expIzquierda, Expresion expDerecha){
        _tipoOperacion = tipo;
        _expIzq = expIzquierda;
        _expDer = expDerecha;

        if (expIzquierda instanceof Operacion && expDerecha instanceof  Operacion){
            _primeraHoja = ((Operacion) expIzquierda).get_primeraHoja();
            _ultimaHoja = ((Operacion) expDerecha).get_ultimaHoja();
            ((Operacion) expIzquierda).get_ultimaHoja().setHermanoDerecho(((Operacion) expDerecha).get_primeraHoja());
        }
        else if (expIzquierda instanceof Operacion){
            _primeraHoja = ((Operacion) expIzquierda).get_primeraHoja();
            _ultimaHoja = expDerecha;
            ((Operacion) expIzquierda).get_ultimaHoja().setHermanoDerecho(expDerecha);
        }
        else if (expDerecha instanceof Operacion){
            _primeraHoja = expIzquierda;
            _ultimaHoja = ((Operacion) expDerecha).get_ultimaHoja();
            expIzquierda.setHermanoDerecho(((Operacion) expDerecha).get_primeraHoja());
        }
        else {
            _primeraHoja = expIzquierda;
            _ultimaHoja = expDerecha;
            expIzquierda.setHermanoDerecho(expDerecha);
        }

    }

    public Operacion(TipoOperador tipo, Expresion expIzquierda, Expresion expDerecha, Tipo tipoSalida){
        _tipoOperacion = tipo;
        _expIzq = expIzquierda;
        _expDer = expDerecha;
        _tipoSalida = tipoSalida;

        _tipoOperacion = tipo;
        _expIzq = expIzquierda;
        _expDer = expDerecha;

        if (expIzquierda instanceof Operacion && expDerecha instanceof  Operacion){
            _primeraHoja = ((Operacion) expIzquierda).get_primeraHoja();
            _ultimaHoja = ((Operacion) expDerecha).get_ultimaHoja();
            ((Operacion) expIzquierda).get_ultimaHoja().setHermanoDerecho(((Operacion) expDerecha).get_primeraHoja());
        }
        else if (expIzquierda instanceof Operacion){
            _primeraHoja = ((Operacion) expIzquierda).get_primeraHoja();
            _ultimaHoja = expDerecha;
            ((Operacion) expIzquierda).get_ultimaHoja().setHermanoDerecho(expDerecha);
        }
        else if (expDerecha instanceof Operacion){
            _primeraHoja = expIzquierda;
            _ultimaHoja = ((Operacion) expDerecha).get_ultimaHoja();
            expIzquierda.setHermanoDerecho(((Operacion) expDerecha).get_primeraHoja());
        }
        else {
            _primeraHoja = expIzquierda;
            _ultimaHoja = expDerecha;
            expIzquierda.setHermanoDerecho(expDerecha);
        }
    }

    public Tipo get_tipoSalida() {
        return _tipoSalida;
    }

    public void set_tipoSalida(Tipo _tipoSalida) {
        this._tipoSalida = _tipoSalida;
    }

    public Expresion get_expIzq() {
        return _expIzq;
    }

    public void set_expIzq(Expresion _expIzq) {
        this._expIzq = _expIzq;
    }

    public Expresion get_expDer() {
        return _expDer;
    }

    public void set_expDer(Expresion _expDer) {
        this._expDer = _expDer;
    }

    public TipoOperador get_tipoOperacion() {
        return _tipoOperacion;
    }

    public void set_tipoOperacion(TipoOperador _tipoOperacion) {
        this._tipoOperacion = _tipoOperacion;
    }

    @Override
    public String getNombre() {
        return null;
    }

    @Override
    public Tipo getTipo() {
        return null;
    }
}
