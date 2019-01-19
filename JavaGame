package de.tu_clausthal.in.informatikwerkstatt.sudoku2;

public class Game {
    //Die Basis der Sudoku-Initialisierungsdaten 
    private final String str = "360000000004230800000004200070460003820000014500013020001900000007048300000000045";
    //Array-Länge 81, teilen Sie den String und legen Sie ihn in das Array 

    private int sudoku[] = new int[9 * 9];

    //Zur Speicherung von Daten, die von jeder Zelle verwendet wurden
    //Dreidimensionales Array, die erste Dimension enthält die Jiugong x-Achsen-Koordinaten (Länge 9), die zweite Dimension die Jiugong y-ax-Koordinaten, und die dritte Speicherdaten, die an dieser Stelle nicht mehr verfügbar sind
    private int used[][][] = new int[9][9][];



    public Game() {
        //Aufgerufen im Konstruktor, Zuweisung eines Wertes zu einem Array von Sudoku-Mitgliedvariablen
        sudoku = fromPuzzlesString(str);
        calculateAllUsedTiles();
    }

    //Gibt eine Formgebung zurück, die auf der Position der neun Gongri x-Achse und der Y-Achse basiert
    private int getTile(int x, int y) {
        return sudoku[y * 9 + x];
        //zum Beispiel， y=3 x=1,3*9+1=28，denn die 28th Zahlen ist 7
    }

    //Gibt eine Schnur zurück, die auf der Position der neun Gongri x-Achse und der Y-Achse basiert.
    String getTileString(int x, int y) {
        //Gibt die Zahl zurück, die die Koordinaten ausfüllen sollen, basierend auf dem Jiugong im Netz
        int v = getTile(x, y);
        if (v == 0) {
            return "";//Der erhaltene Wert ist 0, um einen leeren String zurückzugeben, füllen Sie nicht aus
        } else
            return String.valueOf(v);
    }

    //Generieren Sie ein Array von Forming-Arrays auf der Grundlage einer String-Daten, die Initialisierungsdaten des sogenannten Sudoku-Spiels
    protected int[] fromPuzzlesString(String src) {//Generieren Sie ein Plastik-Array mit einer Länge von 81
        int[] sudo = new int[src.length()];

        for (int i = 0; i < sudo.length; i++) {
            //charAt(i)Entfernen Sie den Charakter aus der i position
            //String charAt - 0 In eine Plastiknummer, und dann in einer Reihe von komplett gespeichert.，
            //Loop-Ende, um ein Array zu erhalten, das dem Array eins nach dem anderen im String entspricht

            sudo[i] = src.charAt(i) - '0';

        }
        return sudo;
    }
    //Zur Berechnung der entsprechenden nicht verfügbaren Daten für alle Zellen
    public void calculateAllUsedTiles(){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                used[x][y]=  calculateUsedTiles(x,y);
            }
        }
    }
    //Entfernen Sie nicht verfügbare Daten aus einer Zelle
    public int[] getUsedTilesByCoor(int x,int y){//Coor Koordinieren，Holen Sie sich diese Zelle nicht verfügbaren Daten auf der Grundlage von x y-Achsen-Koordinaten
        return used[x][y];
    }

    //Daten berechnen, die in einer Zelle nicht mehr verfügbar sind
    public int[] calculateUsedTiles(int x, int y) {
        //Alle verwendeten Zahlen werden im C-Array gespeichert
        int c[] = new int[9];
        //Berechnen Sie, welche Daten in der y-Achsen-Richtung nicht verfügbar sind
        for (int i = 0; i < 9; i++) {
            if (i == y) //Das ausgewählte Gitter
                  continue;
                int t = getTile(x, i);//Die x-Achsen-Koordinate ist ein Gitter mit einer x, y-Achsen-Koordinate von i
                if (t != 0)
                    c[t - 1] = t;//Weisen Sie den T-Wert der t-1-Position im Array zu
                    //Das heißt, die Zellen, die in den Zahlen gefüllt haben, werden innerhalb des Arrays platziert.

            }

        //Berechnen Sie, welche Daten nicht in der x-Achsen-Richtung verfügbar sind
        //Die Verwendung ist gleich wie die y-Achse
        for (int i = 0; i < 9; i++) {
            if (i == x)
                continue;
                int t = getTile(i, y);
                if (t != 0)
                    c[t - 1] = t;

        }

        //In jedem kleinen neun 3x3 Feld Zahl kann nicht wiederholt werden
        int startx = (x/3)*3;
        int starty = (y/3)*3;
        for (int i = startx; i<startx+3;i++){

            for (int j = starty;j<starty+3;j++)
            {
                if (i==startx&&j==starty)
                    continue;
                int t = getTile(i,j);
                if (t != 0)
                    c[t - 1] = t;
            }
        }

        //compress
        //Befreien Sie sich von der 0 im C-Array.
        int nused = 0;
        for (int t : c){
            if (t != 0)
               nused++;
        }
        int c1[] = new int[nused];
        nused = 0;
        for (int t : c){
            if (t != 0)
                c1[nused++] = t;
        }
        //Gibt den c1 zurück, der komprimiert wurde
        return c1;
    }


    protected boolean setTileIfValid(int x, int y, int value) {
        int tiles[] = getUsedTiles(x,y);//the number which is already be used in the small feld
        if (value!=0){
            for (int tile:tiles){
                if (tile==value)
                    return false; 

            }
        }
        setTile(x,y,value);
        calculateAllUsedTiles();
        return true;

    }
    protected int[] getUsedTiles(int x, int y) {
        return used[x][y];
    }
    private void setTile(int x, int y, int value) {
        sudoku[ y * 9 +x] = value;
    }

}
