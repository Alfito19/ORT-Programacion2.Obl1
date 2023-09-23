package programacion2.obligatorio1;

//Joaquin Hernandez (257620)
//Alfonso Saizar (305968)

public class Movimiento {
    private int x;
    private int y;

    public Movimiento(int col,int fila){
        this.setY(fila);//filas o i
        this.setX(col);//columnas o j
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public int getX(){
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY(){
        return this.y;
    }
    
    @Override
    public String toString(){
        return "(" + (this.getY()+1) + "," + (this.getX()+1) + ")";
    }
}