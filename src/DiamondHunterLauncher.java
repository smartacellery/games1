
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;


public class DiamondHunterLauncher extends StateBasedGame {
    public DiamondHunterLauncher(String title){
        super(title);
    }
    public void initStatesList(GameContainer gc) throws SlickException{
        this.addState(new DHIntroScreen());
        this.addState(new DHMainGame());
        this.addState(new DHGameOverScreen());
    }
    public static void main(String args[]) throws SlickException{
        DiamondHunterLauncher game= new DiamondHunterLauncher("Diamond Hunter");
        AppGameContainer app= new AppGameContainer(game);
        app.setDisplayMode(800, 640, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(200);
        app.start();
    }
}
