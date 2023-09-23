package programacion2.obligatorio1;

//Joaquin Hernandez (257620)
//Alfonso Saizar (305968)

public class Movimiento {
    private int cols;
    private int filas;

    public Movimiento(int col,int fila){
        this.setFilas(fila);//filas o i
        this.setCols(col);//columnas o j
    }

    public void setCols(int col) {
        this.cols = col;
    }
    
    public int getCols(){
        return this.cols;
    }

    public void setFilas(int fila) {
        this.filas = fila;
    }

    public int getFilas(){
        return this.filas;
    }
    
    @Override
    public String toString(){
        return "(" + (this.getFilas()+1) + "," + (this.getCols()+1) + ")";
    }
}