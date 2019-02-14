/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinme.model.ui;

import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static sun.net.www.http.HttpClient.New;

/**
 *
 * @author 2098325
 */
public class Main {
    CinemaServices cine;
     public static void main(String a[]) {
         AnnotationConfigApplicationContext cont=new AnnotationConfigApplicationContext();
         cont.scan("edu.eci.arsw.cinme.model.ui");
         cont.refresh();
         
         
         
     
     
     
     }
    
    
}
