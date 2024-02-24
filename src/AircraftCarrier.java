package src;


public class AircraftCarrier extends Boat implements Attacker{
    private boolean hasPlanes;

    public AircraftCarrier(int team, Coordinates location, int direction){
        super(team, location, direction, 5,1, 1);
        hasPlanes = true;
    }

    public String getID(){
        return "A" + getTeam();
    }

    public String getActions(){
        String actions = "Choose any of the following actions for the Aircraft Carrier:\n1. Move\n2. Turn left\n3. Turn right";
        if(!hasPlanes)
            return actions;
        else
            return actions  + "\n4. Launch Plane";
    }

    public String act(int[] choices, World world){
        int choice = choices[0];
            if(choice==1){
                return move(world);
            }
            if (choice == 2){
                return turn(-1);
            }
            if(choice == 3){
                return turn(1);
            }
            if(choice == 4){
                return attack(world);
            }
        return "";
    }
    public String attack(World world){
        double successRate = 1.0;
        String airRaid = "Air Raid\n";
        Coordinates cords = new Coordinates();
        if(!hasPlanes){
            return getID() +" has no planes remaining.\n";
        }

        for(int r = getLocation().getY() - getVision(); r <= getLocation().getY() + getVision(); r++){
            for( int c = getLocation().getX() - getVision(); c <= getLocation().getX() + getVision(); c++){
                if(hasPlanes) {
                    cords.setCoordinates(c, r);
                    if (world.isLocationValid(cords) && world.getOccupant(cords) != null && world.getOccupant(cords).getTeam() != getTeam() && world.isLocationOccupied(cords)
                        && !(cords.getX() == getLocation().getX() && cords.getY() == getLocation().getY())) {
                        airRaid += "\n" + world.getOccupant(cords).takeHit(getStrength(), world);
                        successRate *= 0.8;
                        if (Math.random() > successRate) hasPlanes = false;

                    }
                }
            }

        }
        if(!hasPlanes)
            return airRaid + "\nThe planes have been destroyed!\n";
        if(airRaid.equals("Air Raid\n"))
            return "There are no boats in range currently.\n";

        return airRaid + "\n";

    }
}
