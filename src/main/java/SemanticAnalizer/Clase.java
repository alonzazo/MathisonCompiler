package SemanticAnalizer;

public class Clase extends ComponenteConcreto implements Nombre {

    private String _nombre;

    public Clase(){
        _nombre = "";
        System.out.println("Clase");
    }

    public Clase(String nombre){
        _nombre = nombre;
        System.out.println("Clase " + nombre);
    }

    @Override
    public String get_nombre() {
        return null;
    }

    @Override
    public Tipo get_tipo() {
        return Tipo.NO_PRIMITIVO;
    }

    public void set_nombre(String nombre) {
        this._nombre = nombre;
    }

    @Override
    public String toString() {
        return "Clase{" +  _nombre + '}';
    }
}
