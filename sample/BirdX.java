package sample;

import flockbase.Bird;
import flockbase.Flock;
import flockbase.Position;
import java.lang.Math;
import java.io.PrintStream;
import java.util.ArrayList;


public class BirdX extends flockbase.Bird{
    private int speed = 10;
    private static int max_speed = 50;
    private boolean Leader;

    public BirdX(){
    }

    public String getName(){
        String name;
        if(!this.Leader)
            name = "a";
        else
            name = "IMT2017009(Leader)";
        return name;
    }
    protected boolean isNear(int val1, int val2){
        
        flockbase.Flock flock = this.getFlock();
        ArrayList<flockbase.Bird> birds = flock.getBirds();
        for(flockbase.Bird bird: birds){
            double distance = Math.pow(Math.pow((bird.getPos().getX() - val1),2) + Math.pow((bird.getPos().getY() - val2),2), 0.5);
            if (distance < 10 && bird != this)
                return true;
        }
        return false;
    }
    
    

    protected void updatePos(){
        Position currentPos = this.getPos();
        int currentX = currentPos.getX();
        int currentY = currentPos.getY();
        flockbase.Flock flock = this.getFlock();

        
        if(!Leader){
            Position leaderPos = flock.getLeader().getPos();
            setTarget(leaderPos.getX(), leaderPos.getY());
        }
        int TargetX = getTarget().getX();
        int TargetY = getTarget().getY();
        
        double dx = 0.0D;
        double dy = 0.0D;
        double slope = (TargetY-currentY)/(double)(TargetX-currentX);
        if (Math.abs(currentX - TargetX) < 7){
            if (Math.abs(currentY - TargetY) < 7){
                dx = 0.0D;
                dy = 0.0D;
            }
            else if (currentY > TargetY)
                dy = -speed;
            else
                dy = speed;
        }
        else if(currentY == TargetY){
            if (currentX > TargetX)
                dx = -speed;
            else
                dx = speed;
        }
            
        else{
            if (currentX > TargetX)
                dx = -speed;
            else
                dx = speed;
            if (dy < max_speed)
                dy = slope*dx;
            else{
                dy = 10*dx/10;
            }
        }
        while(dy > max_speed){
            dx = dx/2;
            dy = dy/2;
        }
        System.out.println(currentX + " : cuurent X");
        System.out.println(currentY + " : current Y");
        if(((currentX + (int)dx) < 990 )&& ((currentY + (int)dy) < 990) || ((currentX - (int)dx) > 10)&& ((currentY - (int)dy) > 10)){
            if (isNear(currentX + (int)dx,  currentY+(int)dy)){
                if (this.Leader){
                this.setPos(currentX + 10, currentY +10);
                }
                else{
                        this.setPos(currentX - (int)dx, currentY - (int)dy);
                }
            }
            else{
                    this.setPos(currentX + (int)dx, currentY + (int)dy);
            }
        }
    }

    public void becomeLeader(){
        Leader = true;
    }

    public void retireLead(){
        Leader = false;
    }

}


