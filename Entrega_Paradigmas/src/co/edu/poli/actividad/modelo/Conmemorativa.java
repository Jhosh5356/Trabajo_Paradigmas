package co.edu.poli.actividad.modelo;

import co.edu.poli.actividad.modelo.Certificacion;
import co.edu.poli.actividad.modelo.Coleccion;
import co.edu.poli.actividad.modelo.Conservacion;
import co.edu.poli.actividad.modelo.Especificacion;
import co.edu.poli.actividad.modelo.Moneda;
import co.edu.poli.actividad.modelo.Pais;

public class Conmemorativa extends Moneda {

    private String evento;
    
    private int annyoConmemoracion;

    public Conmemorativa(String id, double peso, Pais pais, Especificacion especificacion, Coleccion coleccion,
                         int annyo_creacion, Conservacion conservacion, Certificacion certificacion, int valor,
                         String evento, int annyoConmemoracion) {

        super(id, peso, pais, especificacion, coleccion, annyo_creacion, conservacion, certificacion, valor);

        this.evento = evento;
        this.annyoConmemoracion = annyoConmemoracion;
    }

    public Conmemorativa() {
        super("GEN002");
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public int getAnnyoConmemoracion() {
        return annyoConmemoracion;
    }

    public void setAnnyoConmemoracion(int annyoConmemoracion) {
        this.annyoConmemoracion = annyoConmemoracion;
    }

    @Override
    public void mostrarInfo(String mensaje) {
        System.out.println("««« Conmemorativa »»»" +
                "\n Evento: " + evento +
                "\n Año Conmemoración: " + annyoConmemoracion +
                "\n toString: " + super.toString() + "\n");
    }

    @Override
    public Object devolverSuper() {
        return this;
    }
}
