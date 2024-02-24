package src;

public abstract class ScoutBoat extends Boat{
    public ScoutBoat(int team, Coordinates location, int direction, int health, int vision){
        super(team, location, direction, health, 1, vision);
    }

    public String takeHit(int numAttacks, World world){
        if(Math.random() < 0.25){
            return super.takeHit(numAttacks, world);
        }
        else return getID() + " has avoided the attack!";
        
    }
}
