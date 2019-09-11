package sample;

import flockbase.Bird;
import flockbase.Flock;
import java.util.ArrayList;


public class FlockY extends flockbase.Flock{
    private ArrayList<Bird> birds = new ArrayList<Bird>();
    Bird leader = null;
    
    public FlockY(){
    }

    public void addBird(Bird b){
        this.birds.add(b);
        b.setFlock(this);
    }

    public void setLeader(Bird setLeader){
        if(leader!=null){
            leader.retireLead();
        }
        this.leader = setLeader;
        setLeader.becomeLeader();
    }

    public ArrayList<Bird> getBirds(){
        return this.birds;
    }

    public Bird getLeader(){
        return this.leader;
    }
   
    public flockbase.Flock split(int pos){
        Bird altleader = birds.get(pos);
        Flock newFlock = new FlockY();
        altleader.becomeLeader();
        newFlock.addBird(altleader);
        newFlock.setLeader(altleader);
        for(int i=0; i<pos; i++){
            newFlock.addBird(birds.get(i));
        }
        birds.remove(pos);
        for(int i=0; i<pos-1; i++){
            birds.remove(i);
        }
        return newFlock;        
    }

    public void joinFlock(Flock f){
        this.getLeader().retireLead();
        for(Bird bird : this.getBirds()){
            f.addBird(bird);
        }
    }

}

