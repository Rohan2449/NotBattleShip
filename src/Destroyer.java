package src;

public class Destroyer extends Boat implements Attacker {

    public Destroyer(int teamID, Coordinates cords, int direction){
        super( teamID, cords, direction, 2, 2,1);
    }

    public String getID(){
        return "D" + getTeam();
    }

    public String getActions(){
        return """
                Choose two of the following actions for the Destroyer:
                1. Move
                2. Turn left
                3. Turn right
                4. Attack
                """;
    }
    public String act(int[] choices, World world) {
        String result = "";
        for (int choice : choices) {
            if (choice == 1) {
                result += move(world);
            }
            if (choice == 2) {
                result += turn(-1);
            }
            if (choice == 3) {
                result += turn(1);
            }
            if (choice == 4) {
                result += attack(world);
            }
        }
        return result;
    }

    public String attack(World world){
        Coordinates adjacent = world.getAdjacentLocation(getLocation(), getNumDirection());

        if(world.isLocationOccupied(adjacent) &&  world.getOccupant(adjacent).getTeam() != getTeam()){
             return world.getOccupant(adjacent).takeHit(getStrength(), world);

        }
        else
            return "There are no boats in range currently.\n";


    }

    public String takeHit(int strength, World world){
        if(Math.random()> 0.5){
            return super.takeHit(strength, world);
        }
        else
            return getID() + " avoids the attack!\n";
    }


}
