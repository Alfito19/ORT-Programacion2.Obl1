package programacion2.obligatorio1;

//Joaquin Hernandez (257620)
//Alfonso Saizar (305968)

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
public class tableroCartas {
    //argumentos de objeto, no hay instancia de clase 
    private Carta[][] tablero;
    private ArrayList<Movimiento> soluciones;
    private ArrayList<Movimiento> historial;
    private Instant tiempoInicial;
    private int nivel;
    private int movs;

    public tableroCartas(){
        this.tablero = new Carta[5][6];
        setNivel(3);
        this.soluciones = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.movs = 0;
    }
    
    public void setTiempoInicial(){
        this.tiempoInicial = Instant.now();
    }
    
    public Instant getTiempoInicial(){
        return this.tiempoInicial;
    }
    
    public void aumentarMovs(){
        this.movs++;
    }

    public int getMovs(){
        return this.movs;
    }

    public ArrayList<Movimiento> getHistorial(){
        return this.historial;
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
            System.out.println("El archivo no existe, se generará el tablero predeterminado.");
            setTableroPredeterminado();
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
        this.movs = 3;
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
        this.setNivel(unNivel);
        while(k <= unNivel){
            int filaRandom = (int)(Math.random() * filas) + 0;
            int columnaRandom = (int)(Math.random() * cols) + 0;
            this.agregarMov(columnaRandom+1,filaRandom+1);
            k++;
        }
    }

    public void agregarMov(int unaColumna,int unaFila){
        int columna = unaColumna-1;
        int fila = unaFila-1;
        boolean sePuedeRetroceder = false;
        if(this.getMovs() < this.getNivel()){
            this.soluciones.add(new Movimiento(columna,fila));
            aplicarMov();
        }
        else{
            if(this.soluciones.size() > 1 && !this.getHistorial().isEmpty() && columna == (this.historial.get(this.historial.size()-1).getCols())+1 && fila == (this.historial.get(this.historial.size()-1).getFilas())+1) {
                sePuedeRetroceder = true;
            }
            else if(columna == (-2) && fila == (-2)){
                sePuedeRetroceder = true;
            }
            if(sePuedeRetroceder){
                if(columna == (this.soluciones.get(this.soluciones.size()-1).getCols()) && fila == (this.soluciones.get(this.soluciones.size()-1).getFilas())){
                    columna = this.soluciones.get(this.soluciones.size()-1).getCols();
                    fila = this.soluciones.get(this.soluciones.size()-1).getFilas();
                }
                else{
                    columna = this.historial.get(this.historial.size()-1).getCols();
                    fila = this.historial.get(this.historial.size()-1).getFilas();
                }
                // Si soluciones tiene elementos y el movimiento que intentamos aplicar es igual al ultimo movimiento de soluciones, aplica el movimiento y lo borra de la lista.
                if(getSoluciones().size() > 1 && columna == (this.soluciones.get(this.soluciones.size()-1).getCols()) && fila == (this.soluciones.get(this.soluciones.size()-1).getFilas())){
                    this.historial.add(new Movimiento(columna,fila));
                    aplicarMov();
                    this.soluciones.remove(this.soluciones.size()-1);
                }
                // Si soluciones no tiene elementos o movimiento que intentamos aplicar no es igual al ultimo elemento de soluciones, lo agrega a solucion y aplica el movimiento
                else if(getSoluciones().isEmpty()||!(columna == (this.soluciones.get(this.soluciones.size() - 1).getCols()) && fila == (soluciones.get(soluciones.size() - 1).getFilas()))){
                    this.soluciones.add(new Movimiento(columna,fila));
                    this.historial.add(new Movimiento(columna,fila));
                    aplicarMov();
                }
            }
            else{
                //Si el movimiento no está repetido con el ultimo de soluciones, se agrega a soluciones, y en caso de ser un movimiento del usuario, se agrega a historial
                if(getSoluciones().isEmpty()||!(columna == (this.soluciones.get(this.soluciones.size() - 1).getCols()) && fila == (soluciones.get(soluciones.size() - 1).getFilas()))){
                    this.soluciones.add(new Movimiento(columna,fila));
                    this.historial.add(new Movimiento(columna,fila));
                    aplicarMov();
                }
                else{
                    this.historial.add(new Movimiento(columna,fila));
                    aplicarMov();
                    this.soluciones.remove(this.soluciones.size()-1);
                }
            }
        }     
        
    }
    //llama directamente al ultimo elemento de la lista de soluciones para no tener que pasarle por parametro al mismo
    public void aplicarMov(){
        this.aumentarMovs();
        Movimiento mov = this.soluciones.get(this.soluciones.size()-1);
        Carta c = this.tablero[mov.getFilas()][mov.getCols()];
        switch(c.getTipo()){
            case "|":
                this.cambioColumna(mov.getCols());
                break;
            case "-":
                this.cambioFila(mov.getFilas());
                break;
            case "/":
                this.diagDer(mov);
                break;
            default:
                this.diagIzq(mov);
        }
    }

    public boolean movimientoValido(int unaCol,int unaFila){
        boolean c1 = (this.getTablero().length >= unaFila  && unaFila >= -1 && unaFila != 0);
        boolean c2 = (this.getTablero()[0].length >= unaCol && unaCol >= -1 && unaCol != 0);
        if (unaCol == (-1) && unaFila == (-1) && this.historial.isEmpty()){
            return false;
        }
        else{
            return c1 && c2;
        }
    }

    public boolean checkWin(){
        boolean condicion = true;
        for(int i=0;i<this.getTablero().length && condicion;i++){
            for(int j=1;j<this.getTablero()[0].length && condicion;j++){
                if (i>0 && j==1){
                    condicion = this.getTablero()[i][j].compara(this.getTablero()[i][j-1])
                            && this.getTablero()[i][j-1].compara(this.getTablero()[i-1][this.getTablero()[i-1].length-1]);
                }
                else{
                    condicion = this.getTablero()[i][j].compara(this.getTablero()[i][j-1]);
                }
            }
        }
        return condicion;
    }
    
    public void cambioFila(int fila){
        Carta[][] cambioTablero = this.getTablero();
        for(int j = 0; j < cambioTablero[fila].length; j++){
            cambioTablero[fila][j].cambiarColor();
        }
        this.setTableroSistema(cambioTablero);
    }

    public void cambioColumna(int columna){
        Carta[][] cambioTablero = this.getTablero();
        for(int i = 0; i < cambioTablero.length; i++){
            cambioTablero[i][columna].cambiarColor();
        }
        this.setTableroSistema(cambioTablero);
    }

    //izquierda a derecha (\)
    public void diagIzq(Movimiento m){
        int cX = 0;
        int cY = 0;
        Carta[][] cambioTablero = this.getTablero();
        if(m.getCols() > m.getFilas()){
            cX = m.getCols() - m.getFilas();
        }
        else if(m.getFilas() > m.getCols()){
            cY = m.getFilas() - m.getCols();
        }
        while(cY < cambioTablero.length && cX < cambioTablero[0].length){
            cambioTablero[cY++][cX++].cambiarColor();
        }
        this.setTableroSistema(cambioTablero);
    }
    
    //derecha a izquierda (/)
    public void diagDer(Movimiento m){
        int cX=m.getCols();
        int cY=m.getFilas();
        Carta[][] cambioTablero = this.getTablero();
        while(cX != 0 && cY != cambioTablero.length-1){
            cX--;
            cY++;
        }
        while(cY >= 0 && cX < cambioTablero[0].length){
            cambioTablero[cY][cX].cambiarColor();
            cY--;
            cX++;
        }
        this.setTableroSistema(cambioTablero);
    }
    
    public String finDelJuego(String respuesta3, String respuesta4){
        String vuelta="Fin del juego " + "\n";
        Duration duracion = Duration.between(this.getTiempoInicial(), Instant.now());
        // Obtiene los minutos y segundos de la duración
        long minutos = duracion.toMinutes();
        long segundos = duracion.minusMinutes(minutos).getSeconds();
        // Imprime el resultado en formato Minutos:Segundos
        vuelta.concat("Tiempo transcurrido: " + minutos + " minutos y " + segundos + " segundos" + "\n");
        if (!respuesta3.equalsIgnoreCase("X") && !respuesta4.equalsIgnoreCase("X")){
            vuelta.concat("¿Desea volver a jugar? Y para si, N para no");
        } 
        return vuelta;
    }

    public Carta[][] tableroAnterior(){
        tableroCartas tab = new tableroCartas();
        tab.setTableroSistema(this.getTablero());
        tab.soluciones = this.getSoluciones();
        tab.historial = this.getHistorial();
        tab.setNivel(this.getNivel());
        Movimiento mov = this.historial.get(this.historial.size()-1);
        Carta c = tab.tablero[mov.getFilas()][mov.getCols()];
        switch(c.getTipo()){
            case "|":
                tab.cambioColumna(mov.getCols());
                break;
            case "-":
                tab.cambioFila(mov.getFilas());
                break;
            case "/":
                tab.diagDer(mov);
                break;
            default:
                tab.diagIzq(mov);
        }
        return tab.getTablero();
    }
}
