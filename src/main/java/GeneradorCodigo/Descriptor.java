package GeneradorCodigo;

public class Descriptor {
    /*PROPIEDADES
    * etiqueta: descriptores valor
    * */
    private String etiqueta;
    private String descriptor;
    private String valor;

    public Descriptor(String etiqueta, String descriptor, String valor) {
        this.etiqueta = etiqueta;
        this.descriptor = descriptor;
        this.valor = valor;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
