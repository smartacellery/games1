import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
//import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;
public class DHMainGame extends BasicGameState{
    int timer=0;
    DHGuy guy;
    ArrayList<DHGameObject> diamonds = new ArrayList();
    DHTerrain terrain;
    DHGameObject house;
    DHGameObject ghost,ghost2;
    
    public void init(GameContainer gc,StateBasedGame sb) throws SlickException{
    guy=new DHGuy(20,170);
    ghost= new DHGhost(100,170,"images/ghost.png");
    ghost2= new DHGhost(700,170,"images/ghost.png");
    house=new DHCollectItem(280,400,"images/house.png");
    terrain= new DHTerrain();
    for(int i=0;i<10;i++){
        DHGameObject d=new DHCollectItem(0,0,"images/diamond.png");
        ((DHCollectItem)d).place(terrain.getBarriers());
        diamonds.add(d);
    }
}
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException{
        Input in=gc.getInput();
        guy.move(in,terrain.getBarriers());
        timer++;
        for(int j=0;j<diamonds.size();j++){
            if(guy.isHitting(diamonds.get(j))){
                diamonds.remove(j);
                if(diamonds.isEmpty()&&terrain.hasExitRoad()==false){
                    terrain.addExitRoad();
                }
            }
        }
        if(guy.isHitting(ghost)||guy.isHitting(ghost2)){
            DHGameOverScreen.setMessage("You were captured by the ghosts - you LOSE!",200);
            sbg.enterState(2,new FadeOutTransition(), new FadeInTransition());
        }
        else if(guy.isHitting(house)){
            DHGameOverScreen.setMessage("You got all the diamonds and found your way home - you WIN!", 100);
            sbg.enterState(2,new FadeOutTransition(), new FadeInTransition());
        }
        if (timer==5){
            for (DHGameObject bd : diamonds) {
                bd.move(terrain.getBarriers());
            }
            timer=0;
        }
        if(timer%2==0){
            ghost.move(terrain.getBarriers());
            ghost2.move(terrain.getBarriers());
        }
    }
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g) throws SlickException{
        terrain.draw();
        guy.draw();
        if(terrain.hasExitRoad())
            house.draw();
        for (DHGameObject dm : diamonds){
            dm.draw();
        }
        ghost.draw();
        ghost2.draw();
        
    }
    public int getID(){
        return 1;
    }
}

