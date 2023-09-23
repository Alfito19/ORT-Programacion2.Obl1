package programacion2.obligatorio1;

//Joaquin Hernandez (257620)
//Alfonso Saizar (305968)

import java.io.*;
import java.util.*;
public class tableroCartas {
    //argumentos de objeto, no hay instancia de clase 
    private Carta[][] tablero;
    private ArrayList<Movimiento> soluciones = new ArrayList<>();
    private int nivel;

    public tableroCartas(){
        this.tablero = new Carta[5][6];
        setNivel(3);
    }

    public void setNivel(int aLevel){
        this.nivel=aLevel;
    }
    public int getNivel(){
        return this.nivel;
    }
    
    public ArrayList<Movimiento> getSoluciones(){
        return this.soluciones;
    }

    public Carta[][] getTablero(){
        return this.tablero;
    }

    public void setTableroSistema(Carta[][] tableroSistema){
        this.tablero=tableroSistema;
    }

    public void setTableroArchivo(){
        try{
            Scanner input = new Scanner(new File(".\\Test\\datos.txt"));
            int filas= input.nextInt();
            int col= input.nextInt();
            input.nextLine();
            Carta[][] unTableroArchivo = new Carta[filas][col];
            for (int i = 0; i<unTableroArchivo.length;i++){
                for(int j=0;j<unTableroArchivo[i].length;j++){
                    char[] dato = input.next().toCharArray();
                    String tipo = "" + dato[0];
                    String color = "" + dato[1];
                    unTableroArchivo[i][j] = new Carta(color,tipo);
                }
            }
            input.nextLine();
            this.setNivel(input.nextInt());
            input.nextLine();
            for(int i=1;i<=this.getNivel();i++){
                int unaFila = input.nextInt()-1;
                int unaColumna = input.nextInt()-1;
                this.soluciones.add(new Movimiento(unaColumna,unaFila));
            }
            this.tablero=unTableroArchivo;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void setTableroPredeterminado(){
        this.nivel = 3;
        char[] elementosPredeterminados = {
            '|','A','|','A','-','R','/','A','|','R','-','R',
            '-','R','/','A','/','A','|','A','-','R','-','R',
            '-','R','-','R','|','A','-','R','/','R','-','R',
            '\\','R','-','R','|','R','\\','R','|','A','|','R',
            '\\','R','/','R','/','R','|','A','/','A','\\','A'
        };

        Carta[][] unTableroPredeterminado = new Carta[5][6];
        int k = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 6; j++){
                unTableroPredeterminado[i][j] = new Carta(""+elementosPredeterminados[k+1],""+elementosPredeterminados[k]);
                k+=2;
            }
        }
        this.tablero = unTableroPredeterminado;
        this.soluciones.add(new Movimiento(3,3));
        this.soluciones.add(new Movimiento(5,4));
        this.soluciones.add(new Movimiento(3,4));
    }

    public void setTableroRandom(int filas, int cols,int unNivel){
        char[] color = {'A', 'R'};
        char[] tipos = {'/','-','|','\\'};
        char elColor = color[(int)(Math.random() * 2) + 0];
        Carta[][] unTableroRandom = new Carta[filas][cols];
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < cols; j++){
                unTableroRandom[i][j] = new Carta(""+elColor,""+tipos[(int)(Math.random() * 4) + 0]);
            }
        }
        this.tablero = unTableroRandom;
        int k = 1;
        while(k <= unNivel){
            int filaRandom = (int)(Math.random() * filas) + 0;
            int columnaRandom = (int)(Math.random() * cols) + 0;
            this.agregarMov(columnaRandom,filaRandom);
            k++;
        }
    }

    //se ejecuta cada vez que se agrega o elimina un movimiento
    //luego de esto se tiene que mostrar el tablero
    public void retroceder(){
        this.aplicarMov();
        this.soluciones.remove(soluciones.size()-1);
    }

    public void agregarMov(int unaColumna,int unaFila){
        this.soluciones.add(new Movimiento(unaColumna,unaFila));
        aplicarMov();
    }

    public void mostrarHistorial(){
        for(int i=this.getNivel(); i < this.getSoluciones().size(); i++){
            System.out.println(this.getSoluciones().get(i));
        }
    }

    public void mostrarSoluciones(){
        for(int i = 0; i < this.getSoluciones().size(); i++){
            System.out.println(this.getSoluciones().get(i));
        }
    }
    //llama directamente al ultimo elemento de la lista de soluciones para no tener que pasarle por parametro al mismo
    public void aplicarMov(){
        Movimiento mov = this.soluciones.get(this.soluciones.size()-1);
        Carta c = this.tablero[mov.getY()][mov.getX()];
        switch(c.getTipo()){
            case "|":
                this.cambioColumna(mov.getX());
                break;
            case "-":
                this.cambioFila(mov.getY());
                break;
            case "/":
                this.diagDer(mov);
                break;
            default:
                this.diagIzq(mov);
        }
    }

    public boolean checkWin(){
        boolean cond = true;
        for(int i=0;i<this.getTablero().length && cond;i++){
            for(int j=1;j<this.getTablero()[0].length && cond;j++){
                cond = this.getTablero()[i][j].compara(this.getTablero()[i][j-1]);
            }
        }
        return cond;
    }
    
    public void cambioFila(int fila){
        Carta[][] plat = this.getTablero();
        for(int j = 0; j<plat[fila].length; j++){
            plat[fila][j].cambiarColor();
        }
        this.setTableroSistema(plat);
        //Esto podria ser this.tablero = plat?
    }

    public void cambioColumna(int columna){
        Carta[][] plat = this.getTablero();
        for(int i = 0; i<plat.length; i++){
            plat[i][columna].cambiarColor();
        }
        this.setTableroSistema(plat);
        //Esto podria ser this.tablero = plat?
    }

    //izquierda a derecha (\)
    public void diagIzq(Movimiento m){
        int cX=0;
        int cY=0;
        Carta[][] plat = this.getTablero();
        if(m.getX()>m.getY()){
            cX=m.getX()- m.getY();
        }
        else if(m.getY()>m.getX()){
            cY=m.getY()- m.getX();
        }
        while(cY<plat.length && cX<plat[0].length){
            plat[cY++][cX++].cambiarColor();
        }
        this.setTableroSistema(plat);
        //Esto podria ser this.tablero = plat?
    }
    
    //derecha a izquierda (/)
    public void diagDer(Movimiento m){
        int cX=m.getX();
        int cY=m.getY();
        Carta[][] plat = this.getTablero();
        while(cX!=0 && cY!=plat.length-1){
            cX--;
            cY++;
        }
        while(cY>=0 && cX<plat[0].length){
            plat[cY][cX].cambiarColor();
            cY--;
            cX++;
        }
        this.setTableroSistema(plat);
        //Esto podria ser this.tablero = plat?
    }

    public void imprimirTablero(){
        System.out.print(Carta.NC+" ");
        for (int j = 0; j < this.tablero[0].length; j++) {
            System.out.print("   "+(j+1));
        }
        System.out.println();
        // Imprimir las filas de la matriz
        for (int i = 0; i < this.tablero.length; i++) {
            // Imprimir la línea superior de la fila
            System.out.print("  +");
            for (int j = 0; j < this.tablero[i].length; j++) {
                System.out.print("---+");
            }

            System.out.println();
            
            // Imprimir los elementos de la fila
            System.out.print(i + 1 + " |");
            for (int j = 0; j < this.tablero[i].length; j++) {
                System.out.printf(" %s |", this.getTablero()[i][j]+Carta.NC);
            }
            System.out.println();
        }
        // Imprimir la línea inferior de la matriz
        System.out.print("  +");
        for (int j = 0; j < this.tablero[0].length; j++) {
            System.out.print("---+");
        }
    }
}
