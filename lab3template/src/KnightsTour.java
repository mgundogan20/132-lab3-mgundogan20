import java.util.Random;

public class KnightsTour {
    private static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    
    private static Random random = new Random();
    
    private static int boardSize;
    private static int[][] board;
    private static int currentRow;
    private static int currentCol;
    





    //------------------methods-----------------
    // setters and getters
    public static int getCurrentCol(){
        return currentCol;
    }
    
    public static int getCurrentRow(){
        return currentRow;
    }

    public static int getBoardSize(){
        return boardSize;
    }

    public static void setCurrentCol(int col){
        KnightsTour.currentCol = col;
    }

    public static void setCurrentRow(int row){
        KnightsTour.currentRow = row;
    }

    public static void setBoardSize(int n){
        KnightsTour.boardSize = n;
    }

    //public methods
    public static void singleTour(String[] cmdArgs){
    	int seed = Integer.parseInt(cmdArgs[1]);
        setBoardSize(Integer.parseInt(cmdArgs[0]));
    	
    	KnightsTour.init(getBoardSize(), seed);
        int moveCount = 1;

        while(KnightsTour.singleTourIncrement() == true){
            moveCount++;
            board[getCurrentRow()][getCurrentCol()] = moveCount;
        }


        //printing a response and the final state of the board

        System.out.printf("The tour ended with %d moves.%n",moveCount);
        if(moveCount == getBoardSize()*getBoardSize())
            System.out.println("This was a full tour%n  ");
        else
            System.out.printf("This was not a full tour%n  ");

        //polish the printer

        for(int i=0; i<KnightsTour.getBoardSize() ;i++){
            System.out.printf("%5d", i);
        }
        System.out.printf("%n%n");

        int rowCount = 0;
        for(int[] row : KnightsTour.board){
            System.out.printf("%2d", rowCount++);
            for(int cell : row) {
            	System.out.printf("%5d", cell);
            }
            System.out.println();
        }
        


    }

    public static void thousandTours(String[] cmdArgs){
        int seed = Integer.parseInt(cmdArgs[1]);
        setBoardSize(Integer.parseInt(cmdArgs[0]));
    	//this variable keeps track of the occurrence of various tour lengths
        //such that dataBawse[i] is the number of tours with a total length of i
        int[] dataBase = new int[65]; 
        
    	for(int i=0; i<1000 ;i++){
            dataBase[KnightsTour.singleTourModified(seed)]++;
        }

        System.out.println("#tours   tour length");
    	for(int i=1; i<65 ;i++) {
            System.out.printf("%-10d%-3d%n",dataBase[i], i);
    	}

    }
    
    public static void fullTour(String[] cmdArgs){
        int seed = Integer.parseInt(cmdArgs[1]);
        setBoardSize(Integer.parseInt(cmdArgs[0]));
    	//this variable keeps track of the occurrence of various tour lengths
        //such that dataBawse[i] is the number of tours with a total length of i
        int[] dataBase = new int[65]; 
        
    	while(dataBase[64] == 0){
            dataBase[KnightsTour.singleTourModified(seed)]++;
        }
        int totalTries = 0;

        System.out.printf("#tours   tour length%n%n");
    	for(int i=1; i<65 ;i++) {
            System.out.printf("%-10d%-3d%n",dataBase[i], i);
            totalTries += dataBase[i];
        }
        System.out.printf("%nIt took %d tries to get a full tour%n", totalTries);

    }

    //utility
    private static boolean singleTourIncrement(){

        //loops through the moves of a knight, moves the knight to the first detected valid location
        //if there is a valid move, singleTourIncrement moves the knight and returns true
        //if not, it returns false

        for(int i=0; i<8 ;i++){
            int[] move = KnightsTour.move(i);
            if(KnightsTour.move(i)[0] != -1){
                setCurrentRow(move[0]);
                setCurrentCol(move[1]);
                return true;
            }
        }

        return false;
    }

    private static boolean singleTourIncrement(int randIndex){

        //loops through the moves of a knight, moves the knight to the first detected valid location
        //if there is a valid move, singleTourIncrement moves the knight and returns true
        //if not, it returns false
        for(int i=0; i<8 ;i++){
        	int[] move = KnightsTour.move((randIndex+i)%8);
            
            if(KnightsTour.move((randIndex+i)%8)[0] != -1){
                setCurrentRow(move[0]);
                setCurrentCol(move[1]);
                return true;
            }
        }

        return false;
    }

    private static int singleTourModified(int seed){
    	
        KnightsTour.init(getBoardSize(), seed);
        
        int moveCount = 1;

        int randInd = random.nextInt(8);
        while(KnightsTour.singleTourIncrement(randInd) == true){
            moveCount++;
            board[getCurrentRow()][getCurrentCol()] = moveCount;
            randInd = random.nextInt(8);
        }

        return moveCount;
    }

    private static int[] move(int moveNumber){
        //moves the knight by horizontal[index], vertical[index] if possible
        //returns an array of length 2
        //this either contains -1, -1 for an invalid move
        //or the coordinates of the target location

        int[] output = new int[2]; 
        int horizontal = KnightsTour.horizontal[moveNumber];
        int vertical = KnightsTour.vertical[moveNumber];

        int tarCol = horizontal + KnightsTour.getCurrentCol();
        int tarRow = vertical + KnightsTour.getCurrentRow();

        
        if((tarCol >= 0 && tarCol <= getBoardSize()-1) && (tarRow >= 0 && tarRow <= getBoardSize()-1)){
        	if(board[tarRow][tarCol] != 0){
                output[0] = -1;
                output[1] = -1;

                return output;
            }
        	else {
        		output[0] = tarRow;
                output[1] = tarCol;
                
                return output;
        	}
        }
        else{
            output[0] = -1;
            output[1] = -1;
            
            return output;
        }
    }

    private static void init(int size, int randomSeed){
        
        
        KnightsTour.board = new int[size][size];
        if(randomSeed != -1){
            random.setSeed(randomSeed);
        }

        setCurrentRow(random.nextInt(size));
        setCurrentCol(random.nextInt(size));

        board[getCurrentRow()][getCurrentCol()] = 1;
    }
}
