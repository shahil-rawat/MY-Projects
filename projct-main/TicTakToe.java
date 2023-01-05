public class TicTakToe {
	//Mutable class with one important mutable field - board
	//board is private, for encapsulation purpose
	//Only way to modify this 'board' field is through specific functions which are mentioned in the Game.java
    private char[][] board;

    public TicTakToe()  {
        board = new char[3][3];//array for the tic tak toe board
        setDefaults();
    }

    private void setDefaults() {
        for(int x=0;x<3;x++) {
            for(int y=0;y<3;y++) {
                board[x][y] = ' ';//blank means not played yet
            }
        }
    }
    
    public boolean executePlay(int x, int y, char play) {
        if(board[x][y] == ' ') {
            board[x][y] = play;
            return true;
        }
        return false;
    }

    public char gameOver() {
    	//Checking patterns
    	//If this method detects a 3 in a row, then the penalty game is over and we display the winner or draw result
        // Check for rows
        for(int x=0;x<3;x++) {
            if(board[x][0] == board[x][1] && board[x][0] == board[x][2] && board[x][0] != ' ')
                return board[x][0];
        }
        // Check for columns
        for(int y=0;y<3;y++) {
            if(board[0][y] == board[1][y] && board[0][y] == board[2][y] && board[0][y] != ' ')
                return board[0][y];
        }

        // Diagonals
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ')
            return board[1][1];
        
        if(board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != ' ')
            return board[1][1];
        
        for(int x=0;x<3;x++) {
            for(int y=0;y<3;y++) {
                if(board[x][y] == ' ')
                    return ' ';
            }
        }

        return 'd';
    }

    

    @Override
    public String toString() {
    	//For displaying the board in console
        StringBuilder builder = new StringBuilder("");
        for(int x=0;x<3;x++) {
            for(int y=0;y<3;y++) {
                if(board[x][y] == ' ')
                    builder.append("- ");
                else{
                    builder.append(board[x][y]);
                    builder.append(" ");
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
