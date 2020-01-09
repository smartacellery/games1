import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import java.util.ArrayList;
public class DHGhost extends DHGameObject {
    
    int xdir,ydir;
    public static final int HALL1=110, HALL2=330,HALL3=525;
    public static final int MAIN_HALL=170;
    public static final int UP=-1, DOWN=1, LEFT=-1, RIGHT=1, STOP=0;
    
    public DHGhost(int x, int y, String s) throws SlickException{
        super(x, y, s);
        xdir=RIGHT;
        ydir=STOP; 
        hitbox.setWidth(image.getWidth());
        hitbox.setHeight(image.getHeight());
    }
    @Override
    public void move(ArrayList<Rectangle> barriers){
        int x=(int)hitbox.getX();
        int y= (int)hitbox.getY();
        
        if(y>600||y<=0)
            ydir=-ydir;
        if(x>=760||x<=0)
            xdir=-xdir;
        if(xdir==RIGHT &&(x==HALL1||x==HALL3)){
        xdir=STOP;
        ydir=DOWN;
        }
        if(y==MAIN_HALL && ydir==UP){
            ydir=STOP;
            xdir=RIGHT;
            }
        if(x==HALL2 && xdir==LEFT){
            y--;
            xdir=STOP;ydir=UP;
        }
        if(y==MAIN_HALL && ydir==DOWN && x==HALL2){
            ydir=STOP;xdir=LEFT;
        }
        x+=xdir;
        y+=ydir;
        hitbox.setY(y);
        hitbox.setX(x);
    }
    
}

