import java.util.Random;

public class ActivePentomino {
    private int xPosition;
    private int yPosition;
    private int[][] Piece;
    private int rotation;
    private int randomint;
    public ActivePentomino(){
        
        
        Random rand = new Random();
        randomint = rand.nextInt(12);
        if(randomint == 0){
            rotation = 0;
            Piece = PentominoDatabase.data[randomint][rotation];
        }
        else if(randomint == 1 || randomint == 2){
            rotation = rand.nextInt(2);
            Piece = PentominoDatabase.data[randomint][rotation];
        }
            
        else{
            rotation = rand.nextInt(4);
            Piece = PentominoDatabase.data[randomint][rotation];
        }
            
        yPosition = 0;
        xPosition = 0;
    }
    public int getPieceId(){
        return randomint;
    }
    public void shiftLeft(){
        xPosition--;
    }
    public void shiftRight(){
        xPosition++;
    }
    public void shiftDown(){
        yPosition++;
    }
    public void rotateClockwise(){
        if(randomint == 0){
            Piece = PentominoDatabase.data[randomint][rotation];
        }
        else{
            rotation++;
            if((randomint == 1 || randomint == 2) && rotation == 2){
                rotation = 0;
            }
            else if((randomint > 2 && randomint < 12) && rotation == 4){
                rotation = 0;
            }
            Piece = PentominoDatabase.data[randomint][rotation];
        }
    }
    public void rotateAntiClockwise(){
        if(randomint == 0){
            Piece = PentominoDatabase.data[randomint][rotation];
        }
        else{
            rotation--;
            if((randomint == 1 || randomint == 2) && rotation == -1){
                rotation = 1;
            }
            else if((randomint > 2 && randomint < 12) && rotation == -1){
                rotation = 3;
            }
            Piece = PentominoDatabase.data[randomint][rotation];
        }
    }
    public int GetXPosition(){
        return xPosition;
    }
    public int GetYPosition(){
        return yPosition;
    }
    public void takeXPositon(int X){
        xPosition = X;
    }
    public void takeYPositon(int Y){
        yPosition = Y;
    }
    public int[][] getPiece(){
        return Piece;
    }
}
