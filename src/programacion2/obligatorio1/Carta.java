package programacion2.obligatorio1;

//Joaquin Hernandez (257620)
//Alfonso Saizar (305968)

import java.util.*;

public class Carta implements Cloneable{
    private String color;
    private String tipo;
    //Azul
    public static final String A= "\u001B[34m";
    //Rojo
    public static final String R= "\u001B[31m";
    //Sin color
    public static final String NC= "\u001B[0m";
    
    //Constructor
    public Carta(String unColor,String unTipo){
        if(unColor.equalsIgnoreCase("R")){
            this.setColor(R);
        }
        else if (unColor.equalsIgnoreCase("A")){
            this.setColor(A);
        }
        this.tipo = unTipo;
    }
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return this.tipo;
    }
    
    @Override
    public String toString(){
        return this.getColor()+this.getTipo();
        //lo ideal seria que cuando se quiera mostrar la carta el metodo del sistema agarre el color del objeto Carta
    }
    
    public void cambiarColor(){
        if (this.getColor().equals(R)){
            this.setColor(A);
        }
        else{
            this.setColor(R);
        }
    }

    public boolean compara(Carta unaCarta){
        return this.getColor().equals(unaCarta.getColor());
    }
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();

        } catch (CloneNotSupportedException e) {
            System.out.println("No se puede clonar");
        }
        return o;
    }
}
