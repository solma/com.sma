public class HanoiTower{
    public static void main(String[] args){
        new HanoiTower().main();
    }
    
    public void main(){
        move(7, 'A', 'C', 'B');
    }
    
    public void move(int noOfPlates, char o, char d, char buffer){
        if(noOfPlates>1) move(noOfPlates-1, o, buffer, d);
        System.out.println(String.valueOf(o)+" ---> "+String.valueOf(d));
        if(noOfPlates>1) move(noOfPlates-1, buffer, d, o);
    }
    
}