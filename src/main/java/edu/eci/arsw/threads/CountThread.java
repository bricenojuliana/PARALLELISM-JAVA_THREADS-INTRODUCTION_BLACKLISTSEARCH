/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThread extends Thread{
    int lowerLimit;
    int upperLimit;

    public CountThread(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }
    public void run(){
        for(int i=lowerLimit;i<=upperLimit;i++){
            System.out.println(i);
        }
    }
}
