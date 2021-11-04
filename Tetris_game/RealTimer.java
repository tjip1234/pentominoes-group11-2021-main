/** This file contains my attempt to make a working timer
 * The idea is to increase x and y coordinates of active pentomino 
 * after 1 sec passes (we can change the time interval)
 */

/*import javax.swing.Timer;
import java.awt.event.*;
public class RealTimer {

    public static void main(String[] args){
        class MoveDownAuto implements ActionListener{
            public MoveDownAuto(int initialCount){
                count = initialCount;
            }
            public void actionPerformed(ActionEvent event){
                activepentomino.takeXPositon(GetXPosition() - 1);
                activepentomino.takeYPositon(GetYPosition() - 1);
            }
            private int count;
        }
        System.out.println("Start");
        MoveDownAuto listener = new MoveDownAuto(10);
        final int DELAY = 1000;
        Timer t = new Timer(DELAY, listener);
        t.start();
        System.out.println("Done");
        while(true);
    }
}

*/