package src;

public class World {
    private Boat[][] map;
    private final int NORTH = 0;
    private final int NORTHEAST = 1;
    private final int EAST = 2;
    private final int SOUTHEAST = 3;
    private final int SOUTH = 4;
    private final int SOUTHWEST = 5;
    private final int WEST = 6;
    private int NORTHWEST = 7;

    public World(int w, int h){

     if (w < 4) w=4;
     if (w > 10)w=10;
     if (h < 4) h = 4;
     if (h >10) h =10;
     map = new Boat[h][w];
    }

    public int getWidth(){
        return map[0].length;
    }
    public  int getHeight(){
        return map.length;
    }
    public Boat getOccupant(Coordinates cord){
        return(map[cord.getY()][cord.getX()]);
    }
    public boolean isLocationValid(Coordinates cord){
        if(cord == null){
            return false;
        }
        return cord.getX() >=0 && cord.getX() < getWidth() && cord.getY() >=0 && cord.getY() < getHeight();
    }
    public boolean isLocationOccupied(Coordinates cord){
        if(cord != null && isLocationValid(cord))
            return getOccupant(cord) != null;
        return false;

    }
    public boolean
    setOccupant(Boat boat, Coordinates cord){
        if(isLocationValid(cord) && !isLocationOccupied(cord) || boat == null && isLocationValid(cord)){

            map[cord.getY()][cord.getX()] = boat;


//            boat.setLocation(cord);
            return true;
        }
        else
        //    map[cord.getY()][cord.getX()] = null;

        return false;
    }
    public Coordinates getAdjacentLocation(Coordinates cord, int i){
        if(!isLocationValid(cord))
            return null;
        int x = cord.getX();
        int y= cord.getY();
        if(i == NORTH) {
            y--;
        }
        if(i == NORTHEAST) {
            y--;
            x++;
        }
        if(i == NORTHWEST){
            y--;
            x--;
        }
        if(i == SOUTH) {
            y++;
        }
        if(i == SOUTHEAST){
            y++;
            x++;
        }
        if(i == SOUTHWEST){
            y++;
            x--;
        }
        if(i == WEST){
            x--;
        }
        if(i == EAST){
            x++;
        }

        Coordinates result = new Coordinates(x,y);

        if(!isLocationValid(result)) {

            return null;
        }

        return result;
    }

    public String drawTeamMap(Boat[] boats, int view){
        String[][]stringArr = new String[getHeight()][getWidth()];
        for(int i =0; i< getHeight(); i++){
            for(int j = 0; j < getWidth(); j++){
                    stringArr[i][j] = "###";
            }
        }
       if(view ==3 || view ==2){
            for(Boat b: boats) {
                setOccupant(b,b.getLocation());
                if(b.getHealth()>0) {
                    Coordinates cord = b.getLocation();
                    for (int i = cord.getY() - b.getVision(); i <= cord.getY() + b.getVision(); i++) {
                        for (int j = cord.getX() - b.getVision(); j <= cord.getX() + b.getVision(); j++) {
                            if (isLocationValid(new Coordinates(j, i))) {

                                if (isLocationOccupied(new Coordinates(j, i))) {
                                    if (view == 2)
                                        stringArr[i][j] = map[i][j].getDirection() + map[i][j].getID();
                                    if (view == 3)
                                        stringArr[i][j] = map[i][j].getHealth() + map[i][j].getID();


                                } else
                                    stringArr[i][j] = "~~~";
                            }
                        }
                    }
                }


            }
       }

       String result = "@";
       for(int i = 0; i < getWidth(); i++){
           result+= "  "+(i + 1);
       }
       for(int i = 0; i < getHeight(); i++){
            result+= "\n"+ (char)(65+i)+ " ";
            for(int j = 0; j < getWidth(); j++){
                result += stringArr[i][j];
            }
       }
       return result;

    }

    public void printMap(){
        for(Boat[] boats : map){
            for(Boat boat : boats){
                System.out.print(boat + " ");
            }
            System.out.println();
        }
    }
}
