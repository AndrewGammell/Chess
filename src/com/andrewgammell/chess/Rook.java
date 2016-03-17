package com.andrewgammell.chess;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Rook extends Piece{
	private boolean white;
	private int x;
	private int y;
	public JLabel piece;
	private int moveCount = 0;

	public Rook(){
	}

	public Rook(boolean White,int x, int y){
		this.white = White;
		this.x = x;
		this.y = y;
		makeRook();
	}


	private void makeRook(){

		if(white){
			this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/whiterook.png")));
		}else{
			this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/blackrook.png")));
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

	public void setPosition(int x, int y){
		this.x = x;
		this.y= y;
	}
	public JLabel getPiece(){
		return piece;
	}
	
	public void increamentMoveCount(){
		++moveCount;
	}
	 public int getMoveCount(){
		 return moveCount;
	}

	public boolean isValidMove(int fromX,int fromY, int toX, int toY){
Piece pieceRef = MainApp.board.findPiece(toX,toY);
	if(white && Board.whiteTurn){
			if((fromX==toX || fromY==toY) && !isStraightBlocked(fromX,fromY,toX,toY)){
				if(pieceRef != null){
					if(pieceRef.isWhite() != white){
						return true;
					}else{
						return false;
					}
				}else if(pieceRef == null){
					return true;
				}
			}else if(isSwappable(fromX, fromY, toX,toY)){
				return true;
			}
	}		
	if(!white && !Board.whiteTurn){
		if((fromX==toX || fromY==toY) && !isStraightBlocked(fromX,fromY,toX,toY)){
			if(pieceRef != null){
				if(pieceRef.isWhite() != white){
					return true;
				}else{
					return false;
				}
			}else if(pieceRef == null){
				return true;
			}
		}else if(isSwappable(fromX, fromY, toX,toY)){
			return true;
		}
}		
		
		return false;
	}// end is ValidMove

	private boolean isStraightBlocked(int fromX, int fromY, int toX, int toY){
		
		for(int i=0; i<MainApp.board.list.size();i++){
				 if(((MainApp.board.list.get(i).getX()>fromX && MainApp.board.list.get(i).getX()<toX) && MainApp.board.list.get(i).getY()==toY) ||
					((MainApp.board.list.get(i).getX()<fromX && MainApp.board.list.get(i).getX()>toX) && MainApp.board.list.get(i).getY()==toY) ||
					((MainApp.board.list.get(i).getY()>fromY && MainApp.board.list.get(i).getY()<toY) && MainApp.board.list.get(i).getX()==toX) ||
					((MainApp.board.list.get(i).getY()<fromY && MainApp.board.list.get(i).getY()>toY) && MainApp.board.list.get(i).getX()==toX))
				 {
					

				return true;
				 }
		}
		return false;
	}// end of isStraightBlocked
	
	public boolean isSwappable(int fromX, int fromY, int toX, int toY){
			Piece pieceRef1 = MainApp.board.findPiece(toX,toY);
			if(pieceRef1 != null){
				if(pieceRef1.isKing()){
					if(pieceRef1.getMoveCount() == 0 && moveCount == 0){
						if(pieceRef1.isWhite() == white){
							return true;
						}
					}
				}
			}
		return false;
	}// end isSwappable
	
//	public boolean possibleMoves(int fromX, int fromY, int toX, int toY){
//		if(white ^ Board.whiteTurn){
//			if((fromX==toX || fromY==toY) && !isStraightBlocked(fromX,fromY,toX,toY)){
//				return true;
//			}
//	}		
//		
//		return false;
//	}
	 
	
	
	
	
}// End of Class
