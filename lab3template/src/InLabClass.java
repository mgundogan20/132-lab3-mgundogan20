import java.util.Random;

public class InLabClass {
	private static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
	
    private static Random random = new Random();
    
    private static final int boardSize = 8;
    private static int[][] board;
    private static int currentRow;
    private static int currentCol;
    
    private static int[][] access =
    	{{2, 3, 4, 4, 4, 4, 3, 2},
    	{3, 4, 6, 6, 6, 6, 4, 3},
    	{4, 6, 8, 8, 8, 8, 6, 4},
    	{4, 6, 8, 8, 8, 8, 6, 4},
    	{4, 6, 8, 8, 8, 8, 6, 4},
    	{4, 6, 8, 8, 8, 8, 6, 4},
    	{3, 4, 6, 6, 6, 6, 4, 3},
    	{2, 3, 4, 4, 4, 4, 3, 2}};
	
  //------------------methods-----------------
    // setters and getters
    public static int getCurrentCol(){
        return currentCol;
    }
    
    public static int getCurrentRow(){
        return currentRow;
    }

    public static void setCurrentCol(int col){
        InLabClass.currentCol = col;
    }

    public static void setCurrentRow(int row){
        InLabClass.currentRow = row;
    }
    
    private static boolean singleTour(String[] cmdArgs){
    	int seed = Integer.parseInt(cmdArgs[1]);
    	boolean isFull = false;
    	InLabClass.init(InLabClass.boardSize, seed);
        int moveCount = 1;

        while(InLabClass.singleTourIncrement(heuristicGen(board)) == true){
            moveCount++;
            board[getCurrentRow()][getCurrentCol()] = moveCount;
        }


        //printing a response and the final state of the board
        
        System.out.printf("The tour ended with %d moves.%n",moveCount);
        if(moveCount == InLabClass.boardSize*InLabClass.boardSize)
            isFull = true;


        for(int i=0; i<InLabClass.boardSize ;i++){
            System.out.printf("%5d", i);
        }
        System.out.printf("%n%n");

        int rowCount = 0;
        for(int[] row : InLabClass.board){
            System.out.printf("%d", rowCount++);
            for(int cell : row) {
            	System.out.printf("%5d", cell);
            }
            System.out.println();
        }

        return isFull;
    }
    
    //utility
    private static boolean singleTourIncrement(int[][] heuristic){

        //loops through the moves of a knight, moves the knight to the first detected valid location
        //if there is a valid move, singleTourIncrement moves the knight and returns true
        //if not, it returns false
    	int min = 64;
    	int minRow = -1;
    	int minCol = -1;
        for(int i=0; i<8 ;i++){
            int[] move = InLabClass.move(i); //returns -1, -1 if the move is not possible
            if(move[0] != -1) {
	            if(heuristic[move[0]][move[1]] < min) {
	            	//transferring priority to the less accessible cell
	            	min = heuristic[move[0]][move[1]];
	            	minRow = move[0];
	            	minCol = move[1];
	            }
            }
        }
        
        if(minRow != -1){ //checks whether the value of minRow has been updated
            setCurrentRow(minRow);
            setCurrentCol(minCol);
            return true;
        }

        return false;
    }
    
    private static int[] move(int moveNumber){
        //moves the knight by horizontal[index], vertical[index] if possible
        //returns an array of length 2
        //this either contains -1, -1 for an invalid move
        //or the coordinates of the target location

        int[] output = new int[2]; 
        int horizontal = InLabClass.horizontal[moveNumber];
        int vertical = InLabClass.vertical[moveNumber];

        int tarCol = horizontal + InLabClass.getCurrentCol();
        int tarRow = vertical + InLabClass.getCurrentRow();

        
        if((tarCol >= 0 && tarCol <= InLabClass.boardSize-1) && (tarRow >= 0 && tarRow <= InLabClass.boardSize-1)){
        	if(InLabClass.board[tarRow][tarCol] != 0){
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
    
    private static boolean isReachable(int curRow, int curCol, int moveNumber) {

        int horizontal = InLabClass.horizontal[moveNumber];
        int vertical = InLabClass.vertical[moveNumber];

        int tarCol = horizontal + curCol;
        int tarRow = vertical + curRow;

        
        if((tarCol >= 0 && tarCol <= InLabClass.boardSize-1) && (tarRow >= 0 && tarRow <= InLabClass.boardSize-1)){
        	if(InLabClass.board[tarRow][tarCol] == 0)
                return true;
        	else                
                return false;
        }
        else
        	return false;
    }
    
    private static void init(int size, int randomSeed){
    	
    	InLabClass.board = new int[size][size];
        if(randomSeed != -1){
            random.setSeed(randomSeed);
        }

        setCurrentRow(random.nextInt(size));
        setCurrentCol(random.nextInt(size));

        board[getCurrentRow()][getCurrentCol()] = 1;
    }
  
    private static int[][] heuristicGen(int[][] board){
    	int[][] heurTable = new int[InLabClass.boardSize][InLabClass.boardSize];
    	
    	for(int i=0; i<InLabClass.boardSize ;i++) {
    		for(int j=0; j<InLabClass.boardSize ;j++) {
    			for(int moveNum=0; moveNum<8 ;moveNum++) {
    				if(isReachable(i, j, moveNum)) {
    					heurTable[i][j]++;
    				}
    			}
    		}
    	}
    	
    	return heurTable;
    }
    
    
    
	public static void inLabProblem(String[] cmdArr) {
		int iterations = Integer.parseInt(cmdArr[2]);
		int fulls = 0;
		
		for(int i=0; i<iterations ;i++) {
			System.out.printf("Trial no %d%n", i+1);
			if(singleTour(cmdArr))
				fulls++;
		}
		
		System.out.printf("There are %d full tours in %d trials%n", fulls, iterations);
		
		
		//1000 heuristic iterations:
		
		fulls = 0;
		for(int i=0; i<1000 ;i++) {
			System.out.printf("Trial no %d%n", i+1);
			if(singleTour(cmdArr))
				fulls++;
		}
		
		System.out.printf("There are %d full tours in %d trials%n", fulls, 1000);
		
		
		
		
		//After trying out a couple of times I generally saw 980-990 full tours in 1000 trials
		//In the preLab it was almost always 0 full tours with the thousandTours
		//Even with fullTour function which pushes until it finds a full tour, it took more than
		//20000-30000 tours to even find ONE full tour, long story short heuristic version is much more efficient.
	}
}






















