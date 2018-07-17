package SemanticAnalizer;

import GeneradorCodigo.Descriptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Programa extends ComponenteConcreto{

    private static Programa instance;

    private Componente raiz;
    private HashMap<String,Metodo> metodosNativos = new HashMap<>();

    //Para generacion de código
    private HashMap<String, Descriptor> sectionData = new HashMap<>();
    private int numCadenasGenericas = 0;
    private int numSi = 0;
    private int numMientras = 0;
    private int numPara = 0;
    private final int tamanoMaximoCadena = 128;
    private String sectionFooter = "";

    private Programa(){
        //Agregamos a metodos nativos raiz(n)
        metodosNativos.put("raiz",new MetodoRaiz());
        metodosNativos.put("salir", new MetodoSalir());
    }

    public static Programa getInstance(){
        if (instance == null) instance = new Programa();
        return instance;
    }

    public static void resetInstance(){instance = new Programa();}

    public Componente getRaiz() {
        return raiz;
    }

    public void setRaiz(Componente raiz) {
        this.raiz = raiz;
    }

    public HashMap<String, Metodo> getMetodosNativos() {
        return metodosNativos;
    }

    public void setMetodosNativos(HashMap<String, Metodo> metodosNativos) {
        this.metodosNativos = metodosNativos;
    }

    public int getNumCadenasGenericas() {
        return numCadenasGenericas;
    }

    public void setNumCadenasGenericas(int numCadenasGenericas) {
        this.numCadenasGenericas = numCadenasGenericas;
    }

    public int getNumMientras() {
        return numMientras;
    }

    public void setNumMientras(int numMientras) {
        this.numMientras = numMientras;
    }

    public int getNumPara() { return numPara; }

    public void setNumPara(int numPara) {this.numPara = numPara; }

    public int getNumSi() {
        return numSi;
    }

    public void setNumSi(int numSi) {
        this.numSi = numSi;
    }

    public int getTamanoMaximoCadena() {
        return tamanoMaximoCadena;
    }

    @Override
    public String toString() {
        return imprimirArbol();
    }

    public String imprimirArbol() {
        //Recorrido en profundidad primero
        if (raiz == null) return "";
        return toStringAux("", 0,  raiz);
    }

    private String toStringAux(String text,int indexLevel, Componente actual){
        if (actual == null) return text;

        text += '\n';
        for (int i = 0; i < indexLevel; i++) text += "|\t";

        text += actual.toString();

        if (actual.getTblSimbolosLocales() != null  && !actual.getTblSimbolosLocales().isEmpty()) {
            text += '\n';
            for (int i = 0; i < indexLevel; i++) text += "|\t";
            text += "TABLA SIMBOLOS: " + actual.getTblSimbolosLocales().toString();
        }

        if ( actual.getHijoMasIzq() != null){
            text = toStringAux(text, indexLevel + 1, actual.getHijoMasIzq());
        }
        if ( actual.getHermanoDerecho() != null ){
            text = toStringAux(text, indexLevel, actual.getHermanoDerecho());
        }
        return text;
    }

    public boolean evaluarSemantica() throws SemanticError {
        for (Componente componente = raiz; componente != null; componente = componente.getHermanoDerecho())
            componente.setPadre(this);
        _tblSimbolosLocales = new HashMap<>();
        return raiz.evaluarSemantica();
    }

    public String compilar() throws SemanticError {
        String result = "";

        //Inicializamos las pilas
        for (Componente i = raiz; i != null ; i = i.getHermanoDerecho())
            if (i instanceof Metodo)
                ((Metodo) i).inicializarPila();

        result = "\t.text\n" + raiz.compilar();

        //Agrega las variables estáticas
        if (sectionData.size() > 0){
            String sectionData = "\t.data\n";
            for (Map.Entry<String, Descriptor> i: this.sectionData.entrySet())
                sectionData += i.getKey() + ": " + i.getValue().getDescriptor() + " " + i.getValue().getValor() + "\n";
            result = sectionData + "\n" + result;
        }
        result += sectionFooter;
        return result;
    }

    public String compilar(String path) throws Exception {
        String result = "";

        //Creamos el archivo de salida
        PrintWriter printer = new PrintWriter(path);
        //Escribimos el codigos
        result = compilar();
        printer.println(result);
        //Cerramos
        printer.close();

        return result;
    }


    //Concerniente a generación de código
    public HashMap<String,Descriptor> getSectionData(){return sectionData;}

    public String getSectionFooter() {
        return sectionFooter;
    }

    public void setSectionFooter(String sectionFooter) {
        this.sectionFooter = sectionFooter;
    }
}
