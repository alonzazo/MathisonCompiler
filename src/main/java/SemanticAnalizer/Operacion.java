package SemanticAnalizer;

import java.util.LinkedList;

public class Operacion extends ComponenteConcreto implements Expresion {

    public enum TipoOperador {
        SUMA, RESTA, MULTIPLICACION, DIVISION,  // OP. NUMERICOS
        IGUAL, DISTINTO,                        // OP. IDENTIDAD
        MAYORQUE, MAYOROIGUAL,                  // OP. LOGICOS COMPARACION
        MENORQUE, MENOROIGUAL,                  // OP. LOGICOS COMPARACION
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

    @Override
    public Tipo evaluarTipo() throws SemanticError {
        if ( _tipoSalida == null ){
            if ( _tipoOperacion == TipoOperador.SUMA ){
                if (_expIzq.evaluarTipo() == Tipo.NUMERICO && _expDer.evaluarTipo() == Tipo.NUMERICO)
                    return Tipo.NUMERICO;
                else if ( (_expIzq.evaluarTipo() == Tipo.NUMERICO || _expIzq.evaluarTipo() == Tipo.CADENA) &&
                        (_expDer.evaluarTipo() == Tipo.NUMERICO || _expDer.evaluarTipo() == Tipo.CADENA))
                    return Tipo.CADENA;
                else
                    throw new SemanticError("Tipo de expresión inválido:\n" + _expDer.toString() + _expIzq.toString());
            } else if ( _tipoOperacion == TipoOperador.RESTA || _tipoOperacion == TipoOperador.MULTIPLICACION || _tipoOperacion == TipoOperador.DIVISION){
                if (_expIzq.evaluarTipo() == Tipo.NUMERICO && _expDer.evaluarTipo() == Tipo.NUMERICO)
                    return Tipo.NUMERICO;
                else
                    throw new SemanticError("Tipo de expresión inválido: Se esperaba Numerico\n" + _expDer.toString() + _expIzq.toString());
            } else if (_tipoOperacion == TipoOperador.MENORQUE || _tipoOperacion == TipoOperador.MENOROIGUAL || _tipoOperacion == TipoOperador.MAYORQUE || _tipoOperacion == TipoOperador.MAYOROIGUAL){
                if (_expIzq.evaluarTipo() == Tipo.NUMERICO && _expDer.evaluarTipo() == Tipo.NUMERICO)
                    return Tipo.BOOLEANO;
                else
                    throw new SemanticError("Tipo de expresión inválido: Se esperaba Numerico\n" + _expDer.toString() + _expIzq.toString());
            } else if (_tipoOperacion == TipoOperador.IGUAL || _tipoOperacion == TipoOperador.DISTINTO){
                if (_expIzq.evaluarTipo() == _expDer.evaluarTipo())
                    return Tipo.BOOLEANO;
                throw new SemanticError("Tipo de expresión incomparables:\n" + _expDer.toString() + _expIzq.toString());
            } else if (_tipoOperacion == TipoOperador.Y || _tipoOperacion == TipoOperador.O){
                if (_expIzq.evaluarTipo() == Tipo.BOOLEANO && _expDer.evaluarTipo() == Tipo.BOOLEANO)
                    return Tipo.BOOLEANO;
                else
                    throw new SemanticError("Tipo de expresión inválido: Se esperaba Booleano\n" + _expDer.toString() + _expIzq.toString());
            } else {
                throw new SemanticError("Operación sin operador");
            }
        }
        return _tipoSalida;
    }

    @Override
    public String compilar() throws SemanticError {
        Componente padreActual = this._padre;
        for (;padreActual != null && !(padreActual instanceof Metodo);
             padreActual = padreActual.getPadre());
        Metodo metodoActual = ((Metodo)padreActual);

        String result = "\t#Operacion " + _tipoOperacion + "\n";
        if ( _tipoSalida == null ){
            if ( _tipoOperacion == TipoOperador.SUMA ){
                if (_expIzq.evaluarTipo() == Tipo.NUMERICO && _expDer.evaluarTipo() == Tipo.NUMERICO){
                    /*result += _expIzq.compilar() +
                            "\tmove\t$t0, $v0\n" +
                            _expDer.compilar() +
                            "\tadd\t\t$v0, $t0, $v0\n\n";*/
                    result += _expIzq.compilar() +
                            "\tsw\t\t$v0, 0($sp)\t#Operacion " + _tipoOperacion + "\n" +
                            "\taddi\t$sp, $sp, -4\n";
                    metodoActual.getPilaLocal().getPosicionEnPila("derechaExpresion" +metodoActual.getPilaLocal().getTamanoPila() , 4);//Simplemente hacemos un campo
                    result += _expDer.compilar() +
                            "\taddi\t$sp, $sp, 4\n" +
                            "\tlw\t\t$t0, 0($sp)\n" +
                            "\tadd\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                    metodoActual.getPilaLocal().sacarDePila();
                }
                else if ( (_expIzq.evaluarTipo() == Tipo.NUMERICO || _expIzq.evaluarTipo() == Tipo.CADENA) &&
                        (_expDer.evaluarTipo() == Tipo.NUMERICO || _expDer.evaluarTipo() == Tipo.CADENA))
                    result += _expIzq.compilar() + _expDer.compilar();//TODO Concatenacion entre entre numerico y cadena
                else
                    throw new SemanticError("Tipo de expresión inválido:\n" + _expDer.toString() + _expIzq.toString());
            } else if ( _tipoOperacion == TipoOperador.RESTA || _tipoOperacion == TipoOperador.MULTIPLICACION || _tipoOperacion == TipoOperador.DIVISION){
                if (_expIzq.evaluarTipo() == Tipo.NUMERICO && _expDer.evaluarTipo() == Tipo.NUMERICO) {
                    /*result += _expIzq.compilar() +
                            "\tmove\t$t0, $v0\n" +
                            _expDer.compilar();*/
                    result += _expIzq.compilar() +
                            "\tsw\t\t$v0, 0($sp)\t#Operacion " + _tipoOperacion + "\n" +
                            "\taddi\t$sp, $sp, -4\n";
                    metodoActual.getPilaLocal().getPosicionEnPila("derechaExpresion" +metodoActual.getPilaLocal().getTamanoPila() , 4);//Simplemente hacemos un campo
                    result += _expDer.compilar() +
                            "\taddi\t$sp, $sp, 4\n" +
                            "\tlw\t\t$t0, 0($sp)\n";
                    metodoActual.getPilaLocal().sacarDePila();
                    switch (_tipoOperacion){
                        case RESTA:
                            result += "\tsub\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                        case MULTIPLICACION:
                            result += "\tmul\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                        case DIVISION:
                            result += "\tdiv\t\t$t0, $v0\n" +
                                    "\tmflo\t\t$v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                    }
                }
                else
                    throw new SemanticError("Tipo de expresión inválido: Se esperaba Numerico\n" + _expDer.toString() + _expIzq.toString());
            } else if (_tipoOperacion == TipoOperador.MENORQUE || _tipoOperacion == TipoOperador.MENOROIGUAL || _tipoOperacion == TipoOperador.MAYORQUE || _tipoOperacion == TipoOperador.MAYOROIGUAL){
                if (_expIzq.evaluarTipo() == Tipo.NUMERICO && _expDer.evaluarTipo() == Tipo.NUMERICO){
                    /*result += _expIzq.compilar() +
                            "\tmove\t$t0, $v0\n" +
                            _expDer.compilar();*/
                    result += _expIzq.compilar() +
                            "\tsw\t\t$v0, 0($sp)\t#Operacion " + _tipoOperacion + "\n" +
                            "\taddi\t$sp, $sp, -4\n";
                    metodoActual.getPilaLocal().getPosicionEnPila("derechaExpresion" +metodoActual.getPilaLocal().getTamanoPila() , 4);//Simplemente hacemos un campo
                    result += _expDer.compilar() +
                            "\taddi\t$sp, $sp, 4\n" +
                            "\tlw\t\t$t0, 0($sp)\n";
                    metodoActual.getPilaLocal().sacarDePila();
                    switch (_tipoOperacion){
                        case MENORQUE:
                            result += "\tslt\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                        case MENOROIGUAL:
                            result += "\tsle\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                        case MAYORQUE:
                            result += "\tsgt\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                        case MAYOROIGUAL:
                            result += "\tsge\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                    }
                }
                else
                    throw new SemanticError("Tipo de expresión inválido: Se esperaba Numerico\n" + _expDer.toString() + _expIzq.toString());
            } else if (_tipoOperacion == TipoOperador.IGUAL || _tipoOperacion == TipoOperador.DISTINTO){
                if (_expIzq.evaluarTipo() == _expDer.evaluarTipo()){
                    /*result += _expIzq.compilar() +
                            "\tmove\t$t0, $v0\n" +
                            _expDer.compilar();*/
                    result += _expIzq.compilar() +
                            "\tsw\t\t$v0, 0($sp)\t#Operacion " + _tipoOperacion + "\n" +
                            "\taddi\t$sp, $sp, -4\n";
                    metodoActual.getPilaLocal().getPosicionEnPila("derechaExpresion" +metodoActual.getPilaLocal().getTamanoPila() , 4);//Simplemente hacemos un campo
                    result += _expDer.compilar() +
                            "\taddi\t$sp, $sp, 4\n" +
                            "\tlw\t\t$t0, 0($sp)\n";
                    metodoActual.getPilaLocal().sacarDePila();;
                    switch (_tipoOperacion){
                        case IGUAL:
                            result += "\tseq\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                        case DISTINTO:
                            result += "\tsne\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                    }
                } else
                    throw new SemanticError("Tipo de expresión incomparables:\n" + _expDer.toString() + _expIzq.toString());
            } else if (_tipoOperacion == TipoOperador.Y || _tipoOperacion == TipoOperador.O){
                if (_expIzq.evaluarTipo() == Tipo.BOOLEANO && _expDer.evaluarTipo() == Tipo.BOOLEANO){
                    /*result += _expIzq.compilar() +
                            "\tmove\t$t0, $v0\n" +
                            _expDer.compilar();*/
                    result += _expIzq.compilar() +
                            "\tsw\t\t$v0, 0($sp)\t#Operacion " + _tipoOperacion + "\n" +
                            "\taddi\t$sp, $sp, -4\n";
                    metodoActual.getPilaLocal().getPosicionEnPila("derechaExpresion" +metodoActual.getPilaLocal().getTamanoPila() , 4);//Simplemente hacemos un campo
                    result += _expDer.compilar() +
                            "\taddi\t$sp, $sp, 4\n" +
                            "\tlw\t\t$t0, 0($sp)\n";
                    metodoActual.getPilaLocal().sacarDePila();;
                    switch (_tipoOperacion){
                        case Y:
                            result += "\tand\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                        case O:
                            result += "\tor\t\t$v0, $t0, $v0\t#Operacion " + _tipoOperacion + "\n\n";
                            break;
                    }
                }
                else
                    throw new SemanticError("Tipo de expresión inválido: Se esperaba Booleano\n" + _expDer.toString() + _expIzq.toString());
            } else {
                throw new SemanticError("Operación sin operador");
            }
        }
        return result;
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
    public boolean evaluarSemantica() throws SemanticError {
        _expIzq.evaluarSemantica();
        _expDer.evaluarSemantica();
        return true;
    }

    @Override
    public String getNombre() {
        return null;
    }

    @Override
    public Tipo getTipo() {
        return null;
    }

    @Override
    public Componente setPadre(Componente padre) {
        _expIzq.setPadre(padre);
        _expDer.setPadre(padre);
        return super.setPadre(padre);
    }




}
