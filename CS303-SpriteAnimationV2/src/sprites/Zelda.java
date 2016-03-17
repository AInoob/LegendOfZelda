package sprites;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings({ "unchecked", "rawtypes","serial","resource" })
public class Zelda extends JFrame
{
	public static void main(String[] args) throws Exception {
        Zelda frame=new Zelda();
        frame.setVisible(true);
    }
	//0:up 1:down 2:left 3:right 4:sword 5:arrow
	private Stack<Integer> keysPressed=new Stack<Integer>();
    private int amplifyIndex=createAmplifyingIndex();
  //list of creatures, including link and monsters, called when update and paint
	private List<ICreature> creatures;
	private List<ICreature> deadCreatures;
	private List<Weapon> weapons;
	private List<Weapon> destroiedWeapons;
    //data contains user data, like location.
	private HashMap<String,String> data=new HashMap();
    public JFrame frame=this;//called by timer, to pack the JFrame, so the size is correspond with panel2
    private int imageLength=Tile.imageLength();//length of a tile
    private Link link;
    private MapRoom map;
    //panel1 is for drawing, panel2 amplify panel1 and display
    private JPanel panel,panel2;
    private final double REFERENCE_SCREEN_WIDTH_RATIO = (double)3/4;//how big the window should be in terms of screenWidth
    //teleport contains where a point can teleport link to, teleport is load from map file in getMapArr()
	private HashMap<PairDouble,MapWithPos> teleport=new HashMap();
    private Timer timer;
    
	private int width,height;//width and height of the panel1

	public Zelda() throws FileNotFoundException {
    	Scanner linkData=new Scanner(new File("data"));
    	while(linkData.hasNextLine()){
    		String line=linkData.nextLine();
    		String [] l=line.split("\\s+");
    		data.put(l[0], l[1]);
    	}
    	link=new Link(Double.parseDouble(data.get("posX")),Double.parseDouble(data.get("posY")));
    	deadCreatures=new LinkedList<ICreature>();
    	creatures=new LinkedList<ICreature>();
    	creatures.add(link);
    	weapons=new LinkedList<Weapon>();
    	destroiedWeapons=new LinkedList<Weapon>();
        panel=new JPanel() {
            public void paint(Graphics graphics) {
                Graphics2D g=(Graphics2D)graphics;
                //g.clearRect(0, 0, width, height);
                map.draw(g);
                for(ICreature creature:creatures){
                	BufferedImage image=creature.getImage();
                    g.drawImage(image, (int)(creature.getPosX()*imageLength+creature.getMovePosX()), (int)(creature.getPosY()*imageLength+creature.getMovePosY()), image.getWidth(), image.getHeight(), null);    	
                }
                for(Weapon weapon:weapons){
                	BufferedImage image=weapon.getImage();
                	if(image!=null){
                		g.drawImage(image, (int)(weapon.getPosX()*imageLength+weapon.getMovePosX()), (int)(weapon.getPosY()*imageLength+weapon.getMovePosY()), image.getWidth(), image.getHeight(), null);    	
                	}
                }
            }
        };
        //amplify the things in panel1
        panel2=new JPanel(){
        	public void paint(Graphics graphics){
        		BufferedImage panelBuffImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                Graphics gg = panelBuffImg.createGraphics();
                panel.paint(gg);
        		Graphics2D g=(Graphics2D)graphics;
        		g.clearRect(0, 0, width*amplifyIndex, height*amplifyIndex);
        		for(int i=0;i<width;i++){
        			for(int j=0;j<height;j++){
        				g.setColor(new Color(panelBuffImg.getRGB(i, j)));
        				g.fillRect(i*amplifyIndex, j*amplifyIndex, amplifyIndex, amplifyIndex);
        			}
        		}
        	}
        };
        //we only need to display the amplified panel
        this.getContentPane().add(panel2, BorderLayout.CENTER);
        this.setResizable(true);
        loadMap();
        this.setLocation(100,100);
        
        addWindowListener(new WindowAdapter() {
        	//write back data when closing
            public void windowClosing(WindowEvent e) {
            	try (PrintStream out = new PrintStream(new FileOutputStream("data"))) {
            	    Set<String> s=data.keySet();
            	    for(String elem: s){
            	    	out.println(elem+" "+data.get(elem));
            	    }
            	} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.exit(0);
            }
        });
        
        addKeyboardListeners();
        // create a timer to handle animation and tons of things
        createTimer();
        timer.start();
    }
	//check whether the given blocks are able to pass or not
    private boolean ableToMove(List<PairInt> blocks) {
    	for(PairInt block:blocks){
    		if(!map.isPassable(block.x, block.y)){
    			return false;
    		}
    	}
    	return true;
	}
    //add the newest pressed key into the top of the stack
    private void pressed(int i){
    	if(!keysPressed.contains(i)){
    		keysPressed.add(new Integer(i));
    	}
    	else if(keysPressed.peek()!=i){
    		keysPressed.remove(new Integer(i));
    		keysPressed.add(new Integer(i));
    	}
    }
    //remove the released key
    private void released(int i){
    	if(keysPressed.contains(i)){
    		keysPressed.remove(new Integer(i));
    	}
    	else{
    		System.err.println("WUT");
    	}
    }
	private void addKeyboardListeners() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	if (e.getKeyChar()=='a'||e.getKeyCode()==KeyEvent.VK_LEFT) {
                	pressed(2);
                } else if (e.getKeyChar()=='s'||e.getKeyCode()==KeyEvent.VK_DOWN) {
                	pressed(1);
                } else if (e.getKeyChar()=='d'||e.getKeyCode()==KeyEvent.VK_RIGHT) {
                	pressed(3);
                } else if (e.getKeyChar()=='w'||e.getKeyCode()==KeyEvent.VK_UP) {
                	pressed(0);
                } else if(e.getKeyChar()=='j'){
                	pressed(4);
                } else if(e.getKeyChar()=='k'){
                	pressed(5);
                }
            }
            public void keyReleased(KeyEvent e){
            	if (e.getKeyChar()=='a'||e.getKeyCode()==KeyEvent.VK_LEFT) {
                	released(2);
                } else if (e.getKeyChar()=='s'||e.getKeyCode()==KeyEvent.VK_DOWN) {
                	released(1);
                } else if (e.getKeyChar()=='d'||e.getKeyCode()==KeyEvent.VK_RIGHT) {
                	released(3);
                } else if (e.getKeyChar()=='w'||e.getKeyCode()==KeyEvent.VK_UP) {
                	released(0);
                } else if(e.getKeyChar()=='j'){
                	released(4);
                } else if(e.getKeyChar()=='k'){
                	released(5);
                }
            }
        });
    }
    //Assuming the monitor is on its best resolution, this should scale the image appropriately
	public int createAmplifyingIndex(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    double screenWidth = screenSize.getWidth();
	    return (int)(screenWidth*REFERENCE_SCREEN_WIDTH_RATIO/(16*16));
	}

	private void createTimer() {
        // Create timer for animation and tons of things
        timer = new Timer(22, new ActionListener() {
        	/*
        	 * this function will:
        	 * update creatures state
        	 * get weapons
        	 * update weapons
        	 * remove destroyed weapons
        	 * check will creatures hit by weapons or not
        	 * remove dead creatures
        	 * check collision between link and monsters
        	 * let creatures do the next move
        	 * check teleport things
        	 * paint
        	 * write in data
        	 */
            public void actionPerformed(ActionEvent e) {
            	//update and get weapons
            	updateCreatureAndWeapons();
            	//check if any weapon hit any creature
            	weaponHitCreatures();
            	//check collision between link and monsters
            	linkHitCreatures();
            	removeDeadCreatures();
            	//do the next move
            	creaturesDoNextMove();
            	telePortLink();
            	
                panel.repaint();
                panel2.repaint();
                data.put("posX", (""+link.getPosX()));
                data.put("posY", (""+link.getPosY()));
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
    }

	protected void removeDeadCreatures() {
		if(link.dead()){
			JOptionPane.showMessageDialog(frame,
				    "Stoopid Link, you are dead.",
				    "Game Over",
				    JOptionPane.ERROR_MESSAGE);
			//frame.dispose();
			System.out.println("Stoopid");
			System.exit(0);
		}
    	//remove dead creatures
    	for(ICreature creature: deadCreatures){
        	   creatures.remove(creature);         		
    	}
    	deadCreatures.clear();
	}
	protected void telePortLink() {
		PairDouble temp=new PairDouble(link.getPosX(),link.getPosY());
    	String teleMap=null;
    	double teleX=-1;
    	double teleY=-1;
    	//if link is at a teleport place, teleport link
        if(teleport.containsKey(temp)){
        	MapWithPos temp2=teleport.get(temp);
        	teleMap=temp2.map;
        	teleX=temp2.x;
        	teleY=temp2.y;
        }
        //if link is at boundary and trying to move out of boundary
        else{
        	if(!keysPressed.isEmpty()){
        		int newMapNumber;
            	char newMapLetter;
        		int currentKey=keysPressed.get(0);
        		if(currentKey==0&&link.getPosY()==0){
        			newMapNumber = Integer.parseInt(data.get("map").charAt(3)+"")-1;
                	newMapLetter = (char) (data.get("map").charAt(4));
                	teleMap="map" + newMapNumber + "" + newMapLetter;
                	teleX=link.getPosX();
                	teleY=10;
        		}
        		else if(currentKey==1&&link.getPosY()==10){
        			newMapNumber = Integer.parseInt(data.get("map").charAt(3)+"")+1;
                	newMapLetter = (char) (data.get("map").charAt(4));
        			teleMap="map" + newMapNumber + "" + newMapLetter;
        			teleX=link.getPosX();
                	teleY=0;
        		}
        		else if(currentKey==2&&link.getPosX()==0){
        			newMapNumber = Integer.parseInt(data.get("map").charAt(3)+"");
                	newMapLetter = (char) (data.get("map").charAt(4) -1);
        			teleMap="map" + newMapNumber + "" + newMapLetter;
        			teleX=15;
                	teleY=link.getPosY();
        		}
        		else if(currentKey==3&&link.getPosX()==15){
        			newMapNumber = Integer.parseInt(data.get("map").charAt(3)+"");
                	newMapLetter = (char) (data.get("map").charAt(4) +1);
        			teleMap="map" + newMapNumber + "" + newMapLetter;
        			teleX=0;
                	teleY=link.getPosY();
        		}
        	}
        }
        if(teleMap!=null){
        	HashMap<PairDouble,MapWithPos> tempTele=(HashMap<PairDouble, MapWithPos>) teleport.clone();
        	String tempMap=data.get("map");
        	teleport.clear();
        	data.put("map", teleMap);
        	try {
				loadMap();
				link.setPosX(teleX);
            	link.setPosY(teleY);
			} catch (FileNotFoundException e2) {
				data.put("map", tempMap);
				teleport=tempTele;
				System.out.println("map does not exist");
			}
        }
	}
	protected void creaturesDoNextMove() {
		for(ICreature creature:creatures){
    		if(creature.readyToAct()){
    			//add link nextMove
    			if(creature instanceof Link&&keysPressed.size()!=0&&link.noNextMoves()){
        			if(keysPressed.peek()==4){
        				link.attack(0);
        			}
        			else if(keysPressed.peek()==5){
        				link.attack(1);
        			}
        			else{
        				link.setNextMove(keysPressed.peek());
        			}
        		}
    			//if it's a monster and the monster has next move, go for it
				if(creature.peekNextMove()!=-1){
        			goDirection(creature,creature.getNextMove());
        		}
    		}
    	}
	}
	protected void linkHitCreatures() {
		for(ICreature creature: creatures){
    		//if the creature just finish a move, round the number to a.5 or a.0
    		if(creature.readyToAct()){
        		double tempX=creature.getPosX(),tempY=creature.getPosY();
				//round to x.0 or x.5
				if(tempX*2!=((int)(tempX*2))){
					tempX=((int)(tempX*2+0.2))/(double)2;
					creature.setPosX(tempX);
				}
				if(tempY*2!=((int)(tempY*2))){
					tempY=((int)(tempY*2+0.2))/(double)2;
					creature.setPosY(tempY);
				}
				//check if link hit any monster
				if(!(creature instanceof Link)){
					if((!link.invincible())&&(!creature.invincible())&&Math.abs(creature.getPosX()-link.getPosX())<0.51&&Math.abs(creature.getPosY()-link.getPosY())<0.51){
						if(link.getPosX()==creature.getPosX()){
							if(link.getPosY()<creature.getPosY()){
								creature.fallBack(1);
        						link.fallBack(0);
							}
							else if(link.getPosY()>creature.getPosY()){
								creature.fallBack(0);
        						link.fallBack(1);
							}
							else{
    							creature.fallBack(link.getFacing());
        						link.fallBack();
    						}
						}
						else if(link.getPosY()==creature.getPosY()){
							if(link.getPosX()>creature.getPosX()){
								creature.fallBack(2);
        						link.fallBack(3);
							}
							else if(link.getPosX()<creature.getPosX()){
								creature.fallBack(3);
        						link.fallBack(2);
							}
							else{
    							creature.fallBack(link.getFacing());
        						link.fallBack();
    						}
						}
						else{
							creature.fallBack(link.getFacing());
    						link.fallBack();
						}
						link.setInvincible(20);
						link.getDamage(10);
						creature.setInvincible(20);
						creature.getDamage(10);
						if(creature.dead()){
							deadCreatures.add(creature);
						}
					}
        		}
        	}
    	}
	}
	protected void weaponHitCreatures() {
		for(ICreature creature: creatures){
    		if(!creature.invincible()){
    			for(Weapon weapon:weapons){
        			if(creature instanceof Link){
        				if(!(weapon.getOwner() instanceof Link)){
        					if(creature.willBeHitBy(weapon)){
    							creature.hitBy(weapon);
    							creature.setInvincible(22);
    						}
        				}
        			}
        			else{
        				if(weapon.getOwner() instanceof Link){
        					if(creature.willBeHitBy(weapon)){
        						creature.setInvincible(22);
    							creature.hitBy(weapon);
    							creature.fallBack(weapon.direction());
    						}
        				}
        			}
    			}
    			if(creature.dead()){
					deadCreatures.add(creature);
				}
    		}
    	}
	}
	protected void updateCreatureAndWeapons() {
		for(ICreature creature: creatures){
    		List<Object> objects=creature.update();
    		if(objects!=null){
    			for(Object object:objects){
        			if(object instanceof Weapon){
        				weapons.add((Weapon)object);
        			}
        		}
    		}
    	}
    	for(Weapon weapon:weapons){
    		weapon.update();
    	}
    	//check if creatures will be hit by weapons
    	//and add dead creatures to deadCreatures
    	for(Weapon weapon:weapons){
			if(weapon.getDuration()==0){
				destroiedWeapons.add(weapon);
			}
			else{
				weapon.reductDuration();
			}
		}
    	for(Weapon weapon:destroiedWeapons){
			weapons.remove(weapon);
		}
	}
	//decide whether the creature move or just turn
	private void goDirection(ICreature creature,int direction){
		if(ableToMove(creature.getBlocks(direction))){
			creature.goDirection(direction);
        }
        else{
        	creature.turnDirection(direction);
        }
	}
	
    private void loadMap() throws FileNotFoundException {
		Scanner mapScanner=new Scanner(new File("res/"+data.get("map")));
		List<List>mapList=new ArrayList<List>();
		boolean inProferLines=false;
		creatures.clear();//remove all creatures from last map
		this.deadCreatures.clear();
		this.weapons.clear();
		this.destroiedWeapons.clear();
		creatures.add(link);
		while(mapScanner.hasNextLine()){
			String line=mapScanner.nextLine();
			//AInoob is the separation between map blocks data and other things
			if(line.equals("AInoob")){
				inProferLines=true;
				line=mapScanner.nextLine();
			}
			if(!inProferLines){
				ArrayList<Integer>row=new ArrayList<Integer>();
				String []blocks=line.split("\\s+");
				for(String block:blocks){
					row.add(Integer.parseInt(block));
				}
				mapList.add(row);
			}
			else{
				String []blocks=line.split("\\s+");
				switch(blocks[0]){
					case "tele":		teleport.put(new PairDouble(Integer.parseInt(blocks[1]),Integer.parseInt(blocks[2])), new MapWithPos(blocks[3],Integer.parseInt(blocks[4]),Integer.parseInt(blocks[5])));
										break;
					case "MonsterA":	creatures.add(new MonsterA(Integer.parseInt(blocks[1]),Double.parseDouble(blocks[2]),Double.parseDouble(blocks[3]),Integer.parseInt(blocks[4])));
										break;
				}
				
			}
		}
		int mapArr[][];
		mapArr=new int[mapList.get(0).size()][];
		for(int i=0;i<mapArr.length;i++){
			mapArr[i]=new int[mapList.size()];
			for(int j=0;j<mapArr[i].length;j++){
				mapArr[i][j]=(int) mapList.get(j).get(i);
			}
		}
		map=new MapRoom(mapArr);
    	width=map.getWidth();
    	height=map.getHeight();
    	panel.setPreferredSize(new Dimension(width, height));
    	panel2.setPreferredSize(new Dimension(width*amplifyIndex, height*amplifyIndex));
    	frame.pack();
		return ;
	}
}