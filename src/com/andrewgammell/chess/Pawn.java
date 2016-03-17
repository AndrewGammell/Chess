package com.andrewgammell.chess;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class Pawn extends Piece{
	private boolean white;
	private int x;
	private int y;
	public JLabel piece;
	public ImageIcon icon;
	private int moveCount = 0;

	public Pawn(boolean White,int x, int y){
		this.white = White;
		this.x = x;
		this.y = y;
		makePawn();
	}

	private void makePawn(){
		if(white){
				this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/whitepawn.png")));
		}else{
				this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/blackpawn.png")));
		}
		piece.setVisible(true);
		piece.setForeground(Color.RED);
	}

	public boolean isWhite(){
		return this.white;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public JLabel getPiece(){
		return piece;
	}

	public void setPosition(int x, int y){
		this.x = x;
		this.y= y;
	}
	
	public void increamentMoveCount(){
		++moveCount;
	}

	public boolean isValidMove(int fromX,int fromY,int toX,int toY){
		Piece pieceRef = MainApp.board.findPiece(toX,toY);

		if(white && !isBlocked(fromX,fromY,toX,toY) && Board.whiteTurn){
			if(moveCount==0 && (fromX<toX)){
				if((toX-fromX<=2) && fromY==toY){
					return true; 
				} else if((fromX+1==toX && (fromY+1==toY || fromY-1==toY)) && 
						(pieceRef != null)){
					return true;
				}
			}else if(moveCount>0 && (fromX < toX)){
				if((toX-fromX==1) && fromY==toY){
					return true; 
				} else if(fromX+1==toX && (fromY+1==toY || fromY-1==toY) && 
						pieceRef != null){
					return true;

				}
			}
		} 


		if(!white && !isBlocked(fromX,fromY,toX,toY) && !Board.whiteTurn){
			if(moveCount==0 && (fromX > toX)){
				if((fromX-toX<=2) && fromY==toY){
					return true; 
				} else if((fromX-1==toX && (fromY+1==toY || fromY-1==toY)) && 
						(pieceRef != null)){
					return true;
				}
			}else if(moveCount>0 && (fromX > toX)){
				if((fromX-toX==1) && fromY==toY){
					return true; 
				} else if(fromX-1==toX && (fromY+1==toY || fromY-1==toY) && 
						pieceRef != null){
					return true;
				}
			}
		} 

		return false;
	}//end of isValid

	private boolean isBlocked(int fromX, int fromY, int toX, int toY){
		Piece pieceRef1;
		Piece pieceRef2;

		if(white && moveCount==0){
			pieceRef1 = MainApp.board.findPiece(fromX+1,fromY);
			pieceRef2 = MainApp.board.findPiece(fromX+2,fromY);
			if(pieceRef1 != null ){
				if(pieceRef1.getX() == toX && pieceRef1.getY() == toY){
					return true;
				}
			}else if(pieceRef2 != null){
				if(pieceRef2.getX() == toX && pieceRef2.getY() == toY){
					return true;
				}
			}
		}else if( white && moveCount>0){
			pieceRef1 = MainApp.board.findPiece(fromX+1,fromY);
			if(pieceRef1 != null){
				if(pieceRef1.getX() == toX && pieceRef1.getY() == toY){
					return true;
				}
			}
		}


		if(!white && moveCount==0){
			pieceRef1 = MainApp.board.findPiece(fromX-1,fromY);
			pieceRef2 = MainApp.board.findPiece(fromX-2,fromY);
			if(pieceRef1 != null ){
				if(pieceRef1.getX() == toX && pieceRef1.getY() == toY){
					return true;
				}
			}else if(pieceRef2 != null){
				if(pieceRef2.getX() == toX && pieceRef2.getY() == toY){
					return true;
				}
			}
		}else if(!white && moveCount>0){
			pieceRef1 = MainApp.board.findPiece(fromX-1,fromY);
			if(pieceRef1 != null){
				if(pieceRef1.getX() == toX && pieceRef1.getY() == toY){
					return true;
				}
			}
		}
		return false;
	}//end isBlocked
	
	public boolean possibleMoves(int fromX, int fromY, int toX, int toY){
		Piece pieceRef = MainApp.board.findPiece(toX,toY);
		
		if((white ^ Board.whiteTurn) && !isBlocked(fromX,fromY,toX,toY) ){
			if(moveCount==0){
				if((toX-fromX<=2) && fromY==toY){
					return true; 
				} else if((fromX+1==toX && (fromY+1==toY || fromY-1==toY)) && 
						(pieceRef != null)){
					return true;
				}
			}else if(moveCount>0){
				if((toX-fromX==1) && fromY==toY){
					return true; 
				} else if(fromX+1==toX && (fromY+1==toY || fromY-1==toY) && 
						pieceRef != null){
					return true;

				}
			}
		} 
		return false;
	}






}// end of class
