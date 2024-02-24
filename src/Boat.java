package src;

public abstract class Boat {
    private final int team;
    private Coordinates location;
    private int direction;
    private int health;
    private final int strength;
    private final int vision;

    public Boat(int team, Coordinates location, int direction, int health, int strength, int vision) {
        this.team = team;
        this.location = location;
        this.direction = direction;
        this.health = health;
        this.strength = strength;
        this.vision = vision;
    }

    public int getTeam() {
        return team;
    }

    public Coordinates getLocation() {
        return location;
    }


    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getVision() {
        return vision;
    }

    public char getDirection() {
        if(direction == 0) return '↑';
        if(direction == 1) return '↗';
        if(direction == 2) return '→';
        if(direction == 3) return '↘';
        if(direction == 4) return '↓';
        if(direction == 5) return '↙';
        if(direction == 6) return '←';
        if(direction == 7) return '↖';
        return ' ';
    }
    public int getNumDirection(){
        return direction;
    }

    public abstract String getID();
    public abstract String act(int[] array, World world);
    public abstract String getActions();


    public String move(World world){
        Coordinates oldCords = getLocation();
        Coordinates newCords = world.getAdjacentLocation(oldCords,direction);
        if (!world.isLocationValid(newCords)) {

            return getID() + " cannot move off the map.\n";
        }

        else if(!world.isLocationOccupied(newCords) && world.isLocationValid(newCords)) {

            world.setOccupant(this, newCords);
            world.setOccupant(null, oldCords);
          //  setLocation(newCords);
            location = newCords;


            return getID() + " moves from " + oldCords + " to " + newCords + ".\n";
        }
        else if( world.isLocationValid(newCords)) {
            return getID() + " cannot move to " + newCords + " as it is occupied.\n";
        }
        else
            return "";
    }

    public String turn(int direct){
        String strDirection = "";
        String leftRight;
        direction = direction + direct;
        if(direction < 0)
            direction += 8;

        direction %= 8;
        if(direct == -1){
            leftRight = "left";
        }
        else{
            leftRight = "right";
        }

        if(direction == 0) strDirection = "N";
        if(direction == 1) strDirection = "NE";
        if(direction == 2) strDirection = "E";
        if(direction == 3) strDirection = "SE";
        if(direction == 4) strDirection = "S";
        if(direction == 5) strDirection = "SW";
        if(direction == 6) strDirection = "W";
        if(direction == 7) strDirection = "NW";


        return getID() + " turned " + leftRight + ", now facing " + strDirection + ".\n";

    }

    public String takeHit(int strength, World world){
        health -= strength;

        if( health <= 0) {
            world.setOccupant(null,getLocation());
            location = null;
            //Fix destroying the boat
            //How do I remove the boat from the map once it sinks?


            return getID() + " has been sunk!\n";
        }
        return getID() + " takes " + strength + " damage.\n";

    }

    public void setLocation(Coordinates cords){
        location = cords;

    }

    public String toString(){
        return getID();
    }

}
