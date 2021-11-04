import javax.swing.Timer;
import java.awt.event.*;

public class TimerTest {
    public static void main(String[] args){
        class CountDown implements ActionListener{
            public CountDown(int initialCount){
                count = initialCount;
            }
            public void actionPerformed(ActionEvent event){
                if (count >= 0)
                    System.out.println(count);
                if(count == 0)
                    System.out.println("Liftoff!");
                count--;
            }
            private int count;
        }
        System.out.println("Start");
        CountDown listener = new CountDown(10);
        final int DELAY = 1000;
        Timer t = new Timer(DELAY, listener);
        t.start();
        System.out.println("Done");
        while(true);
    }
}
