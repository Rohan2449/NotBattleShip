package src;

public class Submarine extends ScoutBoat implements Attacker{
    private int numOfTorpedoes;

    public Submarine(int teamID, Coordinates coordinates, int direction, int numOfTorpedoes){
        super(teamID, coordinates, direction, 3,2);
        this.numOfTorpedoes = numOfTorpedoes;

    }

    public String getID(){
        return "S" + getTeam();
    }

    public String getActions(){
        String string = "Choose any of the following actions for the Submarine:\n1. Move\n2. Turn Left\n3. Turn Right\n4. Submerge";
        if(numOfTorpedoes > 0)
            return string + "\n5. Fire torpedoes";
        else
            return string;
    }
    public String act(int[] choices, World world){

        int choice = choices[0];
        if (choice == 1){
            return move(world);
        }
        if (choice == 2){
            return turn(-1);
        }
        if (choice == 3){
            return turn(1);
        }
        if(choice == 4){
            return submerge(world);
        }
        if(choice == 5){
            return attack(world);
        }
        return "";
    }

    public String attack(World world){
        int direction = getNumDirection();
        Coordinates firstAdjacent = world.getAdjacentLocation(getLocation(),direction);
        Coordinates secondAdjacent = world.getAdjacentLocation(firstAdjacent, direction);
        if((!world.isLocationOccupied(firstAdjacent) || world.getOccupant(firstAdjacent).getTeam() == getTeam())
            && (!world.isLocationOccupied(secondAdjacent) || world.getOccupant(secondAdjacent).getTeam() == getTeam())){
            return "There are no boats in range currently.";
        }
        if( numOfTorpedoes > 0) {
            if (world.isLocationOccupied(firstAdjacent)) {
                return "Fire torpedoes! " + world.getOccupant(firstAdjacent).takeHit(getStrength(), world);
            }
            if (world.isLocationValid(secondAdjacent)) {
                return "Fire torpedoes! " + world.getOccupant(secondAdjacent).takeHit(getStrength(), world);
            }
        }
        return getID() + " has no torpedoes remaining.";
    }

    public String submerge(World world){
        Coordinates originalLocation = getLocation();
        String oldCords = originalLocation.toString();
        world.setOccupant(null, originalLocation);
        while (true){
            int x = (int)(Math.random() * world.getWidth());
            int y = (int)(Math.random() * world.getHeight());

            if(Math.abs(originalLocation.getX() - x) >= 2 && Math.abs(originalLocation.getY() - y) >= 2){
                setLocation(new Coordinates(x,y));
                return getID() + " moves from " + oldCords + " to " + getLocation() + ".";
            }

        }
    }
}




