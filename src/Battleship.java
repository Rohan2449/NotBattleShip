package src;

public class Battleship extends Boat implements Attacker{
    public Battleship(int teamID, Coordinates cords, int direction){
        super( teamID, cords, direction, 4, 3,1);
    }

    public String getID(){
        return "B" + getTeam();
    }
    public String getActions(){
        return """
                Choose any of the following actions for the Battleship:
                1. Move
                2. Turn left
                3. Turn right
                4. Attack
                """;
    }

    public String act(int[] choices, World world) {
        String result = "";
        for(int choice : choices) {
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
        String result = "Fire cannons!";
        if(world.isLocationOccupied(adjacent) && world.getOccupant(adjacent).getTeam() != getTeam() ){
            return  result + "\n" + world.getOccupant(adjacent).takeHit(getStrength(), world) + "\n";

        }
        else
            return "There are no boats in range currently.\n";
    }

}

