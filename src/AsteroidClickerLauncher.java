import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class AsteroidClickerLauncher extends StateBasedGame {

    public AsteroidClickerLauncher(String title) {
        super(title);
    }
    
    public void initStatesList(GameContainer gc) throws SlickException {
       //list other screens in here - first one is opening screen
       this.addState(new ACIntro());
       this.addState(new ACActualGame());
       this.addState(new ACGameOver());
    }

    public static void main(String args[]) throws SlickException {
        AsteroidClickerLauncher game = new AsteroidClickerLauncher("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
