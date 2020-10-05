package arls;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.event.*;

public class Game extends JPanel implements Runnable, KeyListener {
	
	/**
	 * 
	 */
	private int key;
	private int dx;
	private int lives;
	private BufferedImage back;
	private ImageIcon background;
	private spaceship zoom;
	Delay2 d = new Delay2();
	
	private ArrayList<alien> aliens;
	private ArrayList<shipLzr> lazzrr;
	private ArrayList<alienLzr> Alazzrr;
	boolean move_rt;
	boolean move_lt;

	
	public Game(){
		
		new Thread(this).start();
		background= new ImageIcon("3-spaceweather.jpg");
		zoom= new spaceship(getWidth()/2);
		aliens= new ArrayList<alien>();
		Alazzrr= new ArrayList<alienLzr>();
		lazzrr= new ArrayList<shipLzr>();
		create_aliens();
		key=-1;
		this.addKeyListener((KeyListener) this);
		move_rt= false;
		move_lt= false;
		dx=0;
		lives=3;
	}
	public void display_lazzrr(Graphics g2d){
		for (shipLzr b: lazzrr)
		g2d.drawImage(new ImageIcon(b.getPic()).getImage(),b.getX(), b.getY(), b.getW(),b.getH(),this);
	}
	public void move_lazzrr(){
		for (shipLzr b: lazzrr){
		b.setY(-5);
		}
		
		}
public void move_rect(){
		
		if(move_rt){
			zoom.setX(+2);
		}
		if(zoom.getX()+ zoom.getW() +dx>700) {
			zoom.limitX(700-zoom.getW());
		}
		if(move_lt){
			zoom.setX(-2);
	    }
		if(zoom.getX()+dx<0) {
			zoom.limitX(0);
		}
	}
	public void create_alienLzr(){
		
		if(aliens.size()>0){
		int randomalien = (int)(Math.random()*(100-1+1)+0);
		if (randomalien==1){
		
			
		randomalien=(int)(Math.random()*(aliens.size()-1+1));
		Alazzrr.add(new alienLzr((aliens.get(randomalien).getX()),(aliens.get(randomalien).getY())));
		}
	}
	}

	public void display_Alazzrr(Graphics g2d){
		for (alienLzr c: Alazzrr)
		g2d.drawImage(new ImageIcon(c.getPic()).getImage(),c.getX(), c.getY(), c.getW(),c.getH(),this);
	}
	public void move_Alazzrr(){
		for (alienLzr c: Alazzrr){
		c.setY(5);
		}
		
		}
	
	public void create_aliens(){
		for (int j=0; j<4; j++){
		for (int i=0; i<7; i++){
		aliens.add(new alien(85*i,80*j));
	}}}
	public void display_aliens(Graphics g2d){
		for (alien a: aliens)
		g2d.drawImage(new ImageIcon(a.getPic()).getImage(),a.getX(), a.getY(), a.getW(),a.getH(),this);
	}
	
	public void move_aliens(){
		alien max=getMaxX();
		alien min=getMinX();
		if(min!=null && max!=null){
		if (max.getX()+max.getW()>getSize().width|| min.getX()<0)
			max.changeDx();
		
		for(alien a: aliens){
			a.setX();
		}
		}
	}
	public boolean collidee(){ 
		for (int c =Alazzrr.size()-1; c>=0;c--) {
			if ( zoom!=null &&(Alazzrr.get(c).getX()+Alazzrr.get(c).getW()>=zoom.getX() && Alazzrr.get(c).getX()<=zoom.getX()+zoom.getW() && Alazzrr.get(c).getY()+ Alazzrr.get(c).getH()>=zoom.getY() && Alazzrr.get(c).getY()<=zoom.getY()+zoom.getH())){
			removall(c);
			shot();
			return true;
			}
		}
		return false;
		}
	
	public void collide(){ 
		for (int b =lazzrr.size()-1; b>=0;b--) {
		for (int a =aliens.size()-1; a>=0;a--) {
			
			if (lazzrr.size()>0 &&(lazzrr.get(b).getX()+lazzrr.get(b).getW()>=aliens.get(a).getX() && lazzrr.get(b).getX()<=aliens.get(a).getX()+aliens.get(a).getW() && lazzrr.get(b).getY()+ lazzrr.get(b).getH()>=aliens.get(a).getY() && lazzrr.get(b).getY()<=aliens.get(a).getY()+aliens.get(a).getH())){
			removal(b,a);
			}
		}
		}
	}
public void removall(int c) {
	Alazzrr.remove(c);
 	}
	public void removal(int b, int a) {
		
		lazzrr.remove(b);
		aliens.remove(a);
 	}
	public void move_spaceship(){
		
			
	}
	public void keyPressed(KeyEvent ke){
		key = ke.getKeyCode();
		
		
		if(key==39 && zoom.getX()+ zoom.getW() +dx<getWidth()){
			zoom.setX(+5);
		}
		
		if(key==37 && zoom.getX()+ zoom.getW() +dx>0){
			zoom.setX(-5);
		}
		if(key==32|| key==38){
			lazzrr.add(new shipLzr(zoom.getX(),zoom.getY()));
		}
	}
	public void keyReleased(KeyEvent ke){
		key = ke.getKeyCode();
		
	
	}
	public void keyTyped(KeyEvent ke){
		key = ke.getKeyCode();
		
	
	}
		
	
	public alien getMaxX(){
		if(aliens.size()>0){
		alien max =aliens.get(0);
		for(alien a: aliens){
			if (a.getX()>max.getX())
				max=a;
		}
		return max;
	}
		return null;
	}
	public alien getMinX(){
		if(aliens.size()>0){
		alien min =aliens.get(0);
		for(alien a: aliens){
			if (a.getX()<min.getX())
				min=a;
		}
		return min;
	}
	return null;
	}

	
	public void run()
	{
		try
		{
			while(true)
			{
				Thread.currentThread();
				Thread.sleep(5);
				repaint();
			}
		}catch(Exception e)
		{
			
		}
	}
	public void shot(){
		zoom=null;
		aliens.clear();
		lives-=1;
		d.wait(1000);
		create_aliens();
		zoom= new spaceship(getWidth()/2);
	}
	public boolean win(Graphics g2d){
	
			if(aliens.isEmpty()&& zoom!=null){
				
				background= new ImageIcon("ed28d8f8-3fa0-4276-9877-f34f991ebbff.gif");
				g2d.setColor(Color.WHITE);
				Font MyFont = new Font("Rockwell Extra Bold", Font.BOLD, 50); // 
				g2d.setFont(MyFont);
				g2d.drawString(" YOU WIN? ", 220, 300);
				return true;
			}
			return false;
		}
	public boolean bad(Graphics g2d){
		
		if(lives==0){
			
			background= new ImageIcon("1509296587_giphy (17).gif");
			g2d.setColor(Color.WHITE);
			Font MyFont = new Font("Rockwell Extra Bold", Font.BOLD, 50); // 
			g2d.setFont(MyFont);
			g2d.drawString("YOU ARE BAD? ", 200, 300);
			return true;
		}
		return false;
	}


	
	
	public void paint(Graphics g){
		
		Graphics2D twoDgraph= (Graphics2D) g;
		//take a snap shot of the current screen and name it image
		//that is the exact same width and height as the current screen
		if(back==null)
			back = (BufferedImage) (createImage(getWidth(),getHeight()));
		Graphics g2d = back.createGraphics();
		g2d.drawString("key "+ key, 100,100);
		
		g2d.clearRect(0,0,getSize().width, getSize().height);
		g2d.drawImage(background.getImage(), 0, 0,getWidth(),getHeight(),this);
		if(!(bad(g2d)||win(g2d)|| collidee()))
		{
			if(zoom!=null)
		g2d.drawImage(new ImageIcon(zoom.getPic()).getImage(),zoom.getX(), zoom.getY(), zoom.getW(),zoom.getH(),this);
		display_aliens(g2d);
		display_lazzrr(g2d);
		display_Alazzrr(g2d);
		
		move_aliens();
		move_lazzrr();
		move_Alazzrr();
		collide();
		create_alienLzr();
	
		}
		twoDgraph.drawImage(back, null, 0, 0);
		
	}
}
