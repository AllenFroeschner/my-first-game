package arls;

public class ship {
	
	private int x;
	private int y;
	private int w;
	private int h;
	private String pic;
	
	public ship(){
		x=0;
		y=0;
		w=0;
		h=0;
		pic="";
		
	}
	
	
	public ship(int posx, int posy, int width, int height, String p){
		
		x=posx;
		y=posy;
		w=width;
		h=height;
		pic=p;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getW(){
		return w;
	}
	public int getH(){
		return h;
	}
	public String getPic(){
		return pic;
	}
	public void limitX(int x1){
		x=x1;
		}
	
	public void setX(int dx){
		
		x+=dx;
	}

}
