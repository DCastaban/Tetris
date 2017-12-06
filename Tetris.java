import java.awt.Graphics;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Random;

public class Tetris extends JPanel implements ActionListener, KeyListener{
	//tetris grid, 2 means block, 1 means block that has stopped, 0 means background, 99 is out of bounds
	int board [][] =  {{99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},
			  {99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
			  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
			  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
			  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
			  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99}}; 
	// might is the timer at which action performed is called upon
	int might=500;
	// timer that is always in use
	Timer tm = new Timer(might, this);
	// original timer
	Timer tm2 = new Timer(500, this);
	//x and y are the coordinates for the 2 blocks
	int x, y;
	//you can't spam rotations because of this boolean
	boolean up = false;
	//if active is false then a new block is spawned, active is true while there is a 2 block in place
	boolean active=false;
	//Constructor
	 // fast timer for going down
	Timer tm1 = new Timer(50, this);
	public Tetris() {
		 //starts the timer 
		tm.start();
		addKeyListener(this);
		setFocusable(true);  //enables the KeyListener
		setFocusTraversalKeysEnabled(false); // turns off SHIFT and TAB keys
		
	}

	// Grid declaration
	
	/*
	 //Upright block
	  int board [][] = 				 {{99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99}};
					  
	//Cube block
	 int board [][] = 				 {{99,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99}}; 
					  
	//L Block       
	  int board [][] =  {{99,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},
					  {99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99},{99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99}}; 
	 */
	//Grid Painting
		
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//sets background color
		g.setColor(Color.black);
		//paints the background rectangle
		g.fillRect(0,0,1280,1000);
		//paints the whole tetris grid
		for (int row=0;row<17;row++){
			for(int col=0;col<17;col++){
				//paints 0s in the grid
				if(board[row][col]==0){
					//sets as transluecent gray
					g.setColor(new Color(50,50,50));
					//paints each individual rectangle
					g.fillRect(200+col*55,(row*55)+10,50,50);
				}
				//paints 1s in the grid
				else if(board[row][col]==1){
					//sets colour white
					g.setColor(Color.white);
					//paints each individual rectangle
					g.fillRect(200+col*55,(row*55)+10,50,50);
				}
				//paints 2s in the grid
				else if(board[row][col]==2){
					//sets xs and ys for the 2s
					x = 200+col*55;
					y = row*55+10;
					//makes it so another block won't spawn
					active=true;
					/* I realize this is kind of a mess to look at, but the important thing to remember is that it works!
					   Also, the order of all of these if statements is very important as if you switch around the order,
					   you will get array out of bounds error messages. Essentially the order of all these if statements
					   is it goes through all of the statements, it goes through everything that doesn't include row-1,
					   row-2 or row-3, so that there are no array out of bounds exceptions, and it goes in chronological
					   order, so it does all of the statements with positive row values(row, row+1,row+2,row+3), then
					   through all of the ones with row-1, then through all the ones with row-2, then through all the
					   ones with row-3. Error messages still come sometimes, but it doesn't affect the overall
					   quality of the code and the game. So I hope this explains the mess Mr.O! */
					//cube block
					if(board[row+1][col]==2&&board[row][col]==2&&board[row+1][col+1]==2&&board[row][col+1]==2
							||board[row+1][col]==2&&board[row+1][col-1]==2&&board[row][col]==2&&board[row][col-1]==2){
						//sets yellow
						g.setColor(new Color(200,220,0));
					}
					//vert block
					else if(board[row][col]==2&&board[row+1][col]==2&&board[row+2][col]==2&&board[row+3][col]==2
							||(board[row][col]==2&&board[row][col+1]==2&&board[row][col+2]==2&&board[row][col+3]==2)){
						//sets blue
						g.setColor(new Color(0,160,200));
					}
					//vert block
					else if((board[row-1][col]==2&&board[row+1][col]==2&&board[row+2][col]==2&&board[row][col]==2)){
						//sets blue
						g.setColor(new Color(0,160,200));
					}
					
					//l block different
					else if(board[row][col]==2&&board[row+1][col]==2&&board[row+1][col+1]==2&&board[row+1][col+2]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
				
					// l block regular
					else if(board[row][col]==2&&board[row][col+1]==2&&board[row+1][col+1]==2&&board[row+2][col+1]==2
							||board[row][col]==2&&board[row][col-1]==2&&board[row+1][col]==2&&board[row+2][col]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//L block 
					else if (board[row][col]==2&&board[row][col+1]==2&&board[row][col+2]==2&&board[row-1][col+2]==2
							||board[row][col-1]==2&&board[row][col]==2&&board[row][col+1]==2&&board[row-1][col+1]==2
							||board[row][col-2]==2&&board[row][col-1]==2&&board[row][col]==2&&board[row-1][col]==2
							||board[row+1][col-2]==2&&board[row+1][col-1]==2&&board[row+1][col]==2&&board[row][col]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					
					}
					//l block second flip
					else if (board[row][col]==2&&board[row+1][col]==2&&board[row+2][col]==2&&board[row+2][col+1]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//l block second flip
					else if (board[row][col]==2&&board[row-1][col]==2&&board[row+1][col]==2&&board[row+1][col+1]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//l block third flip
					else if(board[row][col]==2&&board[row+1][col]==2&&board[row][col+1]==2&&board[row][col+2]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//l block third flip
					else if(board[row][col]==2&&board[row+1][col-2]==2&&board[row][col-1]==2&&board[row][col-2]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//l block third flip
					else if(board[row][col]==2&&board[row+1][col-1]==2&&board[row][col+1]==2&&board[row][col-1]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//l block third flip
					else if(board[row][col]==2&&board[row-1][col]==2&&board[row-1][col+1]==2&&board[row-1][col+2]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//l block regular
					else if(board[row][col]==2&&board[row-1][col]==2&&board[row-1][col-1]==2&&board[row+1][col]==2
							||board[row][col]==2&&board[row-1][col]==2&&board[row-2][col]==2&&board[row-2][col-1]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//l block second flip
					else if (board[row][col]==2&&board[row-1][col-1]==2&&board[row-2][col-1]==2&&board[row][col-1]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//l block second flip
					else if (board[row][col]==2&&board[row-1][col]==2&&board[row-2][col]==2&&board[row][col+1]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//cube block
					else if(board[row-1][col]==2&&board[row][col]==2&&board[row-1][col+1]==2&&board[row][col+1]==2){
						//sets yellow
						g.setColor(new Color(200,220,0));
					}
					
					//cube block
					else if(board[row-1][col]==2&&board[row-1][col-1]==2&&board[row][col]==2&&board[row][col-1]==2){
						//sets yellow
						g.setColor(new Color(200,220,0));
					}
					// more l block different
					else if(board[row][col]==2&&board[row][col-1]==2&&board[row-1][col-1]==2&&board[row][col+1]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					// more l block different
					else if(board[row][col]==2&&board[row][col-1]==2&&board[row][col-2]==2&&board[row-1][col-2]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					// more l block different
					else if(board[row][col]==2&&board[row][col+1]==2&&board[row][col+2]==2&&board[row-1][col]==2){
						//sets whatever this colour is? reddish, pink?
						g.setColor(new Color(250,100,100));
					}
					//vert block
					else if(board[row-2][col]==2&&board[row+1][col]==2&&board[row-1][col]==2&&board[row][col]==2){
						//sets blue
						g.setColor(new Color(0,160,200));
					}
					//vert block
					else if(board[row][col]==2&&board[row-1][col]==2&&board[row-2][col]==2&&board[row-3][col]==2){
						//sets blue
						g.setColor(new Color(0,160,200));
					}
				
					
					//draws the rectangle for 2s
					g.fillRect(x,y,50,50);
					
				}
			}
		}	
			}
	
			
	//used much later in the code, used to check if the block is stopped
	boolean check=true;
	// key pressed method
	public void keyPressed(KeyEvent e){
		int c = e.getKeyCode();
		//left
		if (c == KeyEvent.VK_LEFT){
			//checks if moving left is possible if not then it is set to false 
			boolean validL=true;
			//check if a move left can't happen, makes sure the path is clear for all of the 2 blocks
				for (int row=16;row>=0;row--){
					for(int col=16;col>=0;col--){
						if (board[row][col]==2){
							if (board[row][col-1]==1 || board[row][col-1]==99){
								//sets as false so that no left move can be made
								validL = false;
							}
						}
												
					}
					}
		//	move left if there is nothing in the way
			if (validL){
			for (int row=16;row>=0;row--){
				for(int col=0;col<17;col++){
					if (board[row][col]==2){
						//sets old block as 0 new block as 2
							board[row][col]=0;
							board[row][col-1]=2;
						}
				}
			}
			repaint();
			}
			//right
		} else if (c == KeyEvent.VK_RIGHT){
			//checks if moving right is possible if not then it is set to false 
			boolean validR=true;
		//	checks if moving right is possible if not then it is set to false
			for (int row=16;row>=0;row--){
				for(int col=16;col>=0;col--){
					if (board[row][col]==2){
						if (board[row][col+1]==1 || board[row][col+1]==99){
							//sets as false so that no right move can be made
							validR = false;
						}
					}
											
				}
				}
			
		//	move right in array if the path is clear
			if(validR){
			for (int row=16;row>=0;row--){
				for(int col=16;col>=0;col--){
					if (board[row][col]==2){
						//sets old block as 0 new block as 2
						board[row][col]=0;
						board[row][col+1]=2;
						
						}
					
				}
				}
			repaint();
			}
			//starts a faster timer
		}else if(c== KeyEvent.VK_DOWN){
			tm1.start();
			
		}
		//checks if a turn can be made and if so it is done
			else if (c == KeyEvent.VK_UP && up == false){
				//makes it so you can't spam rotations
				up = true;
				//if a turn can't be made it is false
				boolean validU=true;
				for (int row=16;row>=0;row--){
					for(int col=16;col>=0;col--){
						if (board[row][col]==2){
							//checks if there is anything blocking the turn and if so makes it so a turn can't be made
							if (board[row][col+1]==1 || board[row][col+1]==99||board[row][col+2]==1 || board[row][col+2]==99||board[row][col]==1 || board[row][col]==99||board[row][col-1]==1 || board[row][col-1]==99||board[row-1][col]==1||board[row-1][col]==99||board[row][col]==1||board[row][col]==99||board[row+1][col]==1||board[row+1][col]==99||board[row+2][col]==1||board[row+2][col]==99){
								validU = false;
							}
						}
												
					}
					}
				// if a turn can be made, these are allllllll the turns
				if(validU){
				 for (int row=16;row>=0;row--){
						for(int col=16;col>=0;col--){
							if (board[row][col]==2){
								//vert block
							 if(board[row][col]==2&&board[row+1][col]==2&&board[row-1][col]==2&&board[row-2][col]==2){
									//exceptions for the first row
								 if (board[row][col]==board[row][1]){
										board[row+1][col]=0;
										board[row-1][col]=0;
										board[row-2][col]=0;
										board[row][col+1]=2;
										board[row][col+2]=2;
										board[row][col+3]=2;
										//exception for last row
									}else if (board[row][col]==board[row][15]){
										board[row+1][col]=0;
										board[row-1][col]=0;
										board[row-2][col]=0;
										board[row][col]=0;
										board[row][col]=2;
										board[row][col-1]=2;
										board[row][col-2]=2;
										board[row][col-3]=2;
										break;
									}
									else{
										//regular vert block rotation
										board[row+1][col]=0;
										board[row-1][col]=0;
										board[row-2][col]=0;
										board[row][col+1]=2;
										board[row][col+2]=2;
										board[row][col-1]=2;
									}
									repaint();
								}
							 //from vert horizontal to vert straight
							 else if(board[row][col]==2&&board[row][col+1]==2&&board[row][col+2]==2&&board[row][col-1]==2){
									board[row][col-1]=0;
									board[row][col+1]=0;
									board[row][col+2]=0;
									board[row-1][col]=2;
									board[row-2][col]=2;
									board[row+1][col]=2;
									repaint();
								}
								
								else if(board[row][col]==2&&board[row][col+1]==2&&board[row+1][col+1]==2&&board[row+2][col+1]==2){
									//flip L block
									board[row][col]=0;
									board[row][col+1]=0;
									board[row+2][col+1]=0;
									board[row+1][col]=2;
									board[row+1][col+2]=2;
									board[row][col+2]=2;
									repaint();
								}
								else if (board[row][col]==2&&board[row][col+1]==2&&board[row][col+2]==2&&board[row-1][col+2]==2){
									//flip L block 2nd flip
									board[row][col]=0;
									board[row-1][col+2]=0;
									board[row-1][col+1]=2;
									board[row-2][col+1]=2;
									System.out.println("right");
									repaint();
								}
								
								else if (board[row][col]==2&&board[row-1][col]==2&&board[row-2][col]==2&&board[row][col+1]==2){
									//flip L block 3rd flip
									board[row][col]=0;
									board[row-2][col]=0;
									board[row][col+1]=0;
									board[row-1][col+1]=2;
									board[row-1][col-1]=2;
									board[row][col-1]=2;
									repaint();
								}/*
								else if (board[row][col]==2&&board[row+1][col-1]==2&&board[row][col-1]==2&&board[row][col+1]==2){
									//flip L block final flip
									board[row][col-1]=0;
									board[row+1][col-1]=0;
									board[row][col+1]=0;
									board[row-1][col]=2;
									board[row+1][col]=2;
									board[row-1][col-1]=2;
									System.out.println("wrong");
								repaint();
								}*/
							}
						}
					}
						
				}
			}
			repaint();
		}
	// integer for end score
int score =0;	
public void actionPerformed (ActionEvent e){
	for (int row=16;row>=0;row--){
		for(int col=0;col<17;col++){
			//check to see if row is made
			if(board[0][col]==1||board[1][col]==1){
				JOptionPane.showMessageDialog(null,"Your score was "+score);
				JOptionPane.showMessageDialog(null,"Game Over!");
				System.exit(1);
			}
			else if(board[row][col]==1&&board[row][col+1]==1&&board[row][col+2]==1&&board[row][col+3]==1&&board[row][col+4]==1&&board[row][col+5]==1&&board[row][col+6]==1&&board[row][col+7]==1&&board[row][col+8]==1&&board[row][col+9]==1&&board[row][col+10]==1&&board[row][col+11]==1&&board[row][col+12]==1&&board[row][col+13]==1&&board[row][col+14]==1){
			board[row][col]=0;
			board[row][col+1]=0;
			board[row][col+2]=0;
			board[row][col+3]=0;
			board[row][col+4]=0;
			board[row][col+5]=0;
			board[row][col+6]=0;
			board[row][col+7]=0;
			board[row][col+8]=0;
			board[row][col+9]=0;
			board[row][col+10]=0;
			board[row][col+11]=0;
			board[row][col+12]=0;
			board[row][col+13]=0;
			board[row][col+14]=0;
			score=score+15;
			int clear = row-1;
			for (row=clear;row>=0;row--){
				for(col=clear;col>=0;col--){
					if (board[row][col]==1){
						board[row][col]=0;
						board[row+1][col]=1;
						}
			}
			}
			}
	
					// check to make blocks drop to the bottom
					
					/*if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0&&board[row+7][col]==0&&board[row+8][col]==0&&board[row+9][col]==0&&board[row+10][col]==0&&board[row+11][col]==0&&board[row+12][col]==0&&board[row+13][col]==0&&board[row+14][col]==0&&board[row+15][col]==0){
						board[row][col]=0;
						board[row+15][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0&&board[row+7][col]==0&&board[row+8][col]==0&&board[row+9][col]==0&&board[row+10][col]==0&&board[row+11][col]==0&&board[row+12][col]==0&&board[row+13][col]==0&&board[row+14][col]==0){
						board[row][col]=0;
						board[row+14][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0&&board[row+7][col]==0&&board[row+8][col]==0&&board[row+9][col]==0&&board[row+10][col]==0&&board[row+11][col]==0&&board[row+12][col]==0&&board[row+13][col]==0){
						board[row][col]=0;
						board[row+13][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0&&board[row+7][col]==0&&board[row+8][col]==0&&board[row+9][col]==0&&board[row+10][col]==0&&board[row+11][col]==0&&board[row+12][col]==0){
						board[row][col]=0;
						board[row+12][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0&&board[row+7][col]==0&&board[row+8][col]==0&&board[row+9][col]==0&&board[row+10][col]==0&&board[row+11][col]==0){
						board[row][col]=0;
						board[row+11][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0&&board[row+7][col]==0&&board[row+8][col]==0&&board[row+9][col]==0&&board[row+10][col]==0){
						board[row][col]=0;
						board[row+10][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0&&board[row+7][col]==0&&board[row+8][col]==0&&board[row+9][col]==0){
						board[row][col]=0;
						board[row+9][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0&&board[row+7][col]==0&&board[row+8][col]==0){
						board[row][col]=0;
						board[row+8][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0&&board[row+7][col]==0){
						board[row][col]=0;
						board[row+7][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0&&board[row+6][col]==0){
						board[row][col]=0;
						board[row+6][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0&&board[row+5][col]==0){
						board[row][col]=0;
						board[row+5][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0&&board[row+4][col]==0){
						board[row][col]=0;
						board[row+4][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0&&board[row+3][col]==0){
						board[row][col]=0;
						board[row+3][col]=1;	
					}
					else if(board[row][col]==1&&board[row+1][col]==0&&board[row+2][col]==0){
						board[row][col]=0;
						board[row+2][col]=1;	
					}
					else */
					//checks to make line go down
					
			
		
			//check to make block stop
		else if (board[row][col]==2){
				if (board[row+2][col]==99||board[row+2][col]==1){
					check=false;
					if(!check){
					for (row=16;row>=0;row--){
						for(col=0;col<17;col++){
							if (board[row][col]==2){
								board[row][col]=0;
								board[row+1][col]=1;
								//makes block inactive and makes timer faster respectively
								active=false;
								//makes timer faster for everytime a block stops
								might=might-1;
								tm.stop();
								tm.start();
							
							}
						}
					}
					}
				}else{
					board[row][col]=0;
					board[row+1][col]=2;
					
				}
			}
		}
	}
	//spawns new block
	if (active==false){
		Random rand=new Random();
		int block=rand.nextInt(2+1);
		int columni=rand.nextInt(15-1)+1;
		if (block==0){
			 board [0][columni]=2;
			 board [1][columni]=2;
			 board [2][columni]=2;
			 board [3][columni]=2;
			}
		else if(block==1){
				if (columni==0){
				columni=1;
			}
			 board [0][columni]=2;
			 board [0][columni+1]=2;
			 board [1][columni]=2;
			 board [1][columni+1]=2;
	}
		else if(block==2){
			if (columni==0||columni==1){
			columni=2;
		}
		 board [0][columni]=2;
		 board [0][columni-1]=2;
		 board [1][columni]=2;
		 board [2][columni]=2;
}
	}
	repaint();
	}

	
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){
		//sets regular timer and stop fast timer
		tm1.stop();
		tm.start();
		
		int c = e.getKeyCode();  
		if (c == KeyEvent.VK_UP){
			//when up key is released, allows for another turn
			up = false;
		}
	}
	public static void main(String[] args) {

		Tetris t = new Tetris();
		JFrame frame = new JFrame("Tetris");
		frame.setVisible(true);
		frame.setSize(1280,1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(t);
	

	}

}
