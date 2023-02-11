import processing.core.PApplet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main extends PApplet {
    final int NUM_PANELS_HORIZONTAL = 4; // the horizontal quantity of panels
    final int NUM_PANELS_VERTICAL = 4; // the vertical quantity of panels
    private ArrayList<Panel> panels;
    public static PApplet app;

    public Main(){
        super();
        app = this;
    }

    public static void main(String[] args){
        PApplet.main("Main");
    }
    public void settings(){
        size(600, 600);
    }
    public void setup(){
        panels = new ArrayList <Panel>();
        //[NUM_PANELS_HORIZONTAL * NUM_PANELS_VERTICAL];

        int index = 0;
        for (int i = 0; i < NUM_PANELS_VERTICAL; i++){
            for (int j = 0; j < NUM_PANELS_HORIZONTAL; j++){
                int w = width/NUM_PANELS_HORIZONTAL;
                int h = height/NUM_PANELS_VERTICAL;
                int x = j * w;
                int y = i * h;
                Panel s;
                if (i % 4 == 0){
                    s = new Panel(x, y, w, h);
                } else if (i % 4 == 1) {
                    s = new TintedPanel(x, y, w, h);
                } else if (i % 4 == 2){
                    s = new ContrastedPanel(  x, y, w, h);
                } else {
                    s = new RotatingPanel( x, y, w, h);
                }
                s.setupImage("data/bunny.png");
                panels.add(index, s);
                index++;
            }
        }
    }

    public void draw(){
        fancyBackground();
        for(Panel p: panels){
            p.display();
        }
    }
        /*for (int i = 0; i < panels.size(); i++){
            Panel s = panels.get(i);
            s.display();
        }
    }*/

    public void mouseClicked(){
        for(Panel p: panels){
            p.handleMouseClicked(mouseX, mouseY);
        }
    }

     public void keyPressed(){
        if(key == 's'){
            Panel lastPanel = panels.remove(15);
            Panel firstPanel = panels.remove(0);
            panels.add(firstPanel);
            panels.add(0, lastPanel);
            int x = lastPanel.getX();
            int y = lastPanel.getY();
            lastPanel.setX(firstPanel.getX());
            lastPanel.setY(firstPanel.getY());
            firstPanel.setX(x);
            firstPanel.setY(y);
        }

        if(key == 'r'){
            Random rand = new Random();
            int i = rand.nextInt(15);
            System.out.println(i);
            Panel o = panels.remove(i);
            int x = o.getX();
            int y = o.getY();
            int w = o.getWidth();
            int h = o.getHeight();
            TintedPanel r = new TintedPanel(x, y, w, h);
            panels.add(i, r);
        }
    }

    private void fancyBackground(){
        loadPixels();

        for (int x = 0; x < width; x++ ) {
            for (int y = 0; y < height; y++ ) {

                int loc = x + y * width;

                if (x % 2 == 0) {
                    pixels[loc] = color(255);
                } else {
                    pixels[loc] = color(0);
                }
            }
        }

        updatePixels();
    }



}