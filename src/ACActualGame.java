import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ACActualGame extends BasicGameState {
    
    private ArrayList<ACAsteroid> rocks;
    private int timer1=0;
    private TrueTypeFont font;
    private double timer2;


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        rocks = new ArrayList();
      
        for (int i = 0; i < 3; i++) {
            int xloc = (int) (Math.random() * 750);
            int yloc = (int) (Math.random() * 550);
            rocks.add(new ACAsteroid(xloc, yloc));
        }
        ACAsteroid.setGameSize(800, 600);
        rocks.get(0).setChosen();
         font = new TrueTypeFont(new java.awt.Font("Jokerman",0,18),true);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException { 
  if(rocks.size()>0) timer2++;
        timer1++;
        if(timer1==500){
            timer1=0;
            int xloc = (int) (Math.random() * 750);
            int yloc = (int) (Math.random() * 550+50);
            rocks.add(new ACAsteroid(xloc, yloc));
        }
        Input in = gc.getInput();
        int mx = in.getMouseX();
        int my = in.getMouseY();

        for (ACAsteroid a : rocks) {
            a.move();
        }
        if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            //get trough all rocks 

            for (int j = 0; j < rocks.size(); j++) {
                if (rocks.get(j).isHit(mx, my) && rocks.get(j).isChosen()) {
                    rocks.remove(j);
                    if (rocks.size() > 0) 
                    pickrock();                   
                    break;
                }
            }
        }
        if(rocks.size()==0){
            sbg.enterState(2,new FadeOutTransition(), new FadeInTransition());
        }
    }
    
     public void pickrock() {
        int x = (int) (Math.random() * rocks.size());
        rocks.get(x).setChosen();
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        for (ACAsteroid a : rocks) {
            a.draw();
            g.setColor(new Color(0,100,0));
            g.fill(new Rectangle(0,0,800,50));
            font.drawString(10,10,"Rocks to go: " + rocks.size(), Color.white);
            font.drawString(700,10,"Time: " + timer2/100, Color.white);
        }
    }
    
    public int getID() {
       return 1;  //this id will be different for each screen
    }

    
}
