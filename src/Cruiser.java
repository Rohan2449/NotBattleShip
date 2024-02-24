package src;

public class Cruiser extends ScoutBoat{
    public Cruiser(int teamID, Coordinates cords, int direction){
        super( teamID, cords, direction, 3, 3);
    }

    public String getID(){
        return "C"+ getTeam();
    }

    public String getActions(){
        return "Choose two of the following actions for the Cruiser:\n1. Move\n2. Turn left\n3. Turn right";

    }
    public String act(int[] choices, World world){
        String result = "";
        for(int choice : choices){
            if(choice==1){
                result+= move(world);
            }
            if (choice == 2){
                result+= turn(-1);
            }
            if(choice == 3){
                result+= turn(1);
            }
            result += "\n";
        }
        return result;
    }
}

