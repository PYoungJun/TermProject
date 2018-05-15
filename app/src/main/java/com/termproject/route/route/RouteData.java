package com.termproject.route.route;

public class RouteData {

    private String name;
    private int numOfMap;
    public RouteData(){

    }
    public RouteData(String name,int numOfMap){
        this.name=name;
        this.numOfMap=numOfMap;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getNumOfMap(){
        return numOfMap;
    }
    public void setNumOfMap(){
        this.numOfMap=numOfMap;
    }
}
