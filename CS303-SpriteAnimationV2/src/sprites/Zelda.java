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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import sprites.Utilities.*;
/*
 * 
 * 
 */
@SuppressWarnings("serial")
public class Zelda extends JFrame
{
    private JPanel panel,panel2;
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap<String,String> data=new HashMap();
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap<PairDouble,MapWithPos> teleport=new HashMap();
    private Link link;
    private Timer timer;
    private MapRoom map;
    private int [][]mapArr;
    private int width,height;
    public JFrame aa=this;
    private int imageLength=Tile.imageLength();
    private List<Utilities.Creature> creatures;
    
    private final int REFERENCE_SCREEN_HEIGHT = 720;
    private final int REFERENCE_SCREEN_WIDTH = 1024;
    
    
    private int amplifyIndex=createAmplifyingIndex();
    
    @SuppressWarnings("resource")
	public Zelda() throws FileNotFoundException {

    	
    	
    	Scanner sData=new Scanner(new File("data"));
    	while(sData.hasNextLine()){
    		String line=sData.nextLine();
    		String [] l=line.split("\\s+");
    		data.put(l[0], l[1]);
    	}
    	link=new Link(Double.parseDouble(data.get("posX")),Double.parseDouble(data.get("posY")));
    	creatures=new LinkedList<Utilities.Creature>();
    	creatures.add(link);
        mapArr= getMapArr();
    	map=new MapRoom(mapArr);
    	width=map.getWidth();
    	height=map.getHeight();
        panel=new JPanel() {
            public void paint(Graphics graphics) {
                Graphics2D g=(Graphics2D)graphics;
                g.clearRect(0, 0, width, height);
                map.draw(g);
                for(Utilities.Creature creature:creatures){
                	BufferedImage image=creature.getImage();
                    g.drawImage(image, (int)(creature.getPosX()*imageLength+creature.getMovePosX()), (int)(creature.getPosY()*imageLength+creature.getMovePosY()), image.getWidth(), image.getHeight(), null);    	
                }
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
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
        panel2.setPreferredSize(new Dimension(width*amplifyIndex, height*amplifyIndex));
 //       this.getContentPane().add(panel, BorderLayout.WEST);
        this.getContentPane().add(panel2, BorderLayout.CENTER);
        this.setResizable(true);
        this.pack();
        this.setLocation(100,100);
        
        addWindowListener(new WindowAdapter() {
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
        // create a timer to handle animation
        createTimer();
        timer.start();
        
    }
    
    
    
    @SuppressWarnings({ "rawtypes", "resource" })
	private int[][] getMapArr() throws FileNotFoundException {
		Scanner mapScanner=new Scanner(new File("res/"+data.get("map")));
		List<List>mapList=new ArrayList<List>();
		boolean inProperty=false;
		while(mapScanner.hasNextLine()){
			String line=mapScanner.nextLine();
			if(line.equals("AInoob")){
				inProperty=true;
				line=mapScanner.nextLine();
			}
			
			if(!inProperty){
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
					case "MonsterA":	creatures.add(new Utilities.MonsterA(Double.parseDouble(blocks[1]),Double.parseDouble(blocks[2]),Integer.parseInt(blocks[3])));
										break;
				}
				
			}
		}
		int mapArr[][];
		mapArr=new int[mapList.size()][];
		for(int i=0;i<mapArr.length;i++){
			mapArr[i]=new int[mapList.get(i).size()];
			for(int j=0;j<mapArr[i].length;j++){
				mapArr[i][j]=(int) mapList.get(i).get(j);
			}
		}
		int[][]reverseMapArr=new int[mapArr[0].length][mapArr.length];
		for(int i=0;i<mapArr.length;i++){
			for(int j=0;j<mapArr[0].length;j++){
				reverseMapArr[j][i]=mapArr[i][j];
			}
		}
		return reverseMapArr;
	}



	private void addKeyboardListeners() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // if link is not in move, get the new move
            	if(link.readyToAct()){            		
                    if (e.getKeyChar()=='a'||e.getKeyCode()==KeyEvent.VK_LEFT) {
                        goDirection(link,0);
                    } else if (e.getKeyChar()=='s'||e.getKeyCode()==KeyEvent.VK_DOWN) {
                    	goDirection(link,1);
                    } else if (e.getKeyChar()=='d'||e.getKeyCode()==KeyEvent.VK_RIGHT) {
                    	goDirection(link,2);
                    } else if (e.getKeyChar()=='w'||e.getKeyCode()==KeyEvent.VK_UP) {
                    	goDirection(link,3);
                    } else if(e.getKeyChar()=='j'){
                    	//System.out.println("sword");
                    	link.attack();
                    }
//                    System.out.println(temp);
                    
            	}
            }
        });
    }
	
	private void goDirection(Utilities.Creature creature,int direction){
		switch(direction){
		case 0: 
			if(ableToMove(creature.getBlocks(-1, 0))){
	        	//System.out.println("left");
				creature.left();
	        }
	        else{
	        	creature.turnLeft();
	        	//System.out.println("unable to move");
	        }
			break;
		case 1: 
			if(ableToMove(creature.getBlocks(0, 1))){
			   	//System.out.println("down");
				creature.down();
	        }
	        else{
	        	creature.turnDown();
	        	//System.out.println("unable to move");
	        }
			break;
		case 2:
			if(ableToMove(creature.getBlocks(1, 0))){
            	//System.out.println("right");
				creature.right();
            }
            else{
            	creature.turnRight();
            	//System.out.println("unable to move");
            }
			break;
		case 3:
			if(ableToMove(creature.getBlocks(0, -1))){
            	//System.out.println("up");
				creature.up();
            }
            else{
            	creature.turnUp();
            	//System.out.println("unable to move");
            }
			break;
		default: System.err.println("Wut?! 111");
		}
	}
    
    private boolean ableToMove(List<PairInt> blocks) {
    	for(PairInt block:blocks){
    		if(!map.isPassable(block.x, block.y)){
    			return false;
    		}
    	}
    	return true;
	}



	private void createTimer() {
        // Create timer for animation
        timer = new Timer(22, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //generationLabel.setText("Generation: "+ generation);
                //System.out.println("timer");
            	for(Utilities.Creature creature: creatures){
            		creature.update();
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
        				if((!(creature instanceof Link))&&creature.getNextMove()!=-1){
                			goDirection(creature,creature.getNextMove());
                			creature.setNextMove(-1);
                		}
                	}
            	}
            	
            	PairDouble temp=new PairDouble(link.getPosX(),link.getPosY());
//            	System.out.println(link.getPosX()+" "+link.getPosY());
                if(teleport.containsKey(temp)){
                	MapWithPos temp2=teleport.get(temp);
                	teleport.clear();
                	data.put("map", temp2.map);
                	try {
						mapArr= getMapArr();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
                	map=new MapRoom(mapArr);
                	width=map.getWidth();
                	height=map.getHeight();
                	panel.setPreferredSize(new Dimension(width, height));
                	panel2.setPreferredSize(new Dimension(width*amplifyIndex, height*amplifyIndex));
                	link.setPosX(temp2.x);
                	link.setPosY(temp2.y);
                	aa.pack();
                }
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

	//Assuming the monitor is on its best resolution, this should scale the image approriately
	public int createAmplifyingIndex(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    double screenWidth = screenSize.getWidth();
	    double screenHeight = screenSize.getHeight();
	    
//	    System.out.println(screenWidth);
//	    System.out.println(screenHeight);
	    
	    return 3 * (int) Math.min(screenWidth/REFERENCE_SCREEN_WIDTH, screenHeight/REFERENCE_SCREEN_HEIGHT);
	}
	
    public static void main(String[] args) throws Exception {
        Zelda frame=new Zelda();
        frame.setVisible(true);
    }
}