package arls;

public class alien extends ship {

	private static int dx;
	public alien(){
		super();
		dx=0;
	}
	public alien(int posx, int posy){
		super(posx, posy, 57,81, "kg_waj_cg_sd-034.png");
		dx=1;
	}
	public void changeDx(){
		dx=-dx;
	}
	public void setX(){
		setX(dx);
	}
}