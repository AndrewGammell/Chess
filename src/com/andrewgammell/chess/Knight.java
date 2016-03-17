package com.andrewgammell.chess;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Knight extends Piece{
	
	private boolean white;
	 private int x;
	 private int y;
	 public JLabel piece;
	 private int moveCount = 0;
	 
	public Knight(boolean White,int x, int y){
		this.white = White;
		this.x = x;
		this.y = y;
		makeKnight();
	}
	 
	 private void makeKnight(){
		
		 if(white){
			 this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/whiteknight.png")));
		 }else{
			this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/blackknight.png")));
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
	 public boolean isValidMove(int fromX, int fromY, int toX, int toY){
		 Piece pieceRef = MainApp.board.findPiece(toX,toY);
		if(white && Board.whiteTurn){
			 if((fromX+2 == toX && fromY+1==toY) ||
					 (fromX+2 == toX && fromY-1==toY)||
					 	(fromX-2 == toX && fromY+1==toY)||
					 		(fromX-2 == toX && fromY-1==toY)||
					 			(fromX+1 == toX && fromY+2==toY)||
					 				(fromX+1 == toX && fromY-2==toY)||
					 					(fromX-1 == toX && fromY+2==toY)||
					 						(fromX-1 == toX && fromY-2==toY)){
				 if(pieceRef != null){
					 if(pieceRef.isWhite() != white){
						 return true;
					 }
				 }else if(pieceRef == null){
					 return true;
				 }
				 
			 }
		}else if(!white && !Board.whiteTurn){
			 if((fromX+2 == toX && fromY+1==toY) ||
					 (fromX+2 == toX && fromY-1==toY)||
					 	(fromX-2 == toX && fromY+1==toY)||
					 		(fromX-2 == toX && fromY-1==toY)||
					 			(fromX+1 == toX && fromY+2==toY)||
					 				(fromX+1 == toX && fromY-2==toY)||
					 					(fromX-1 == toX && fromY+2==toY)||
					 						(fromX-1 == toX && fromY-2==toY)){
				 
				 if(pieceRef != null){
					 if(pieceRef.isWhite() != white){
						 return true;
					 }
				 }else if(pieceRef == null){
					 return true;
				 }
			 }
		}
		 return false;
	 }// end isValidMove
	 
//	 public boolean possibleMoves(int fromX, int fromY, int toX, int toY){
//		 if(white ^ Board.whiteTurn){
//			 if((fromX+2 == toX && fromY+1==toY) ||
//					 (fromX+2 == toX && fromY-1==toY)||
//					 	(fromX-2 == toX && fromY+1==toY)||
//					 		(fromX-2 == toX && fromY-1==toY)||
//					 			(fromX+1 == toX && fromY+2==toY)||
//					 				(fromX+1 == toX && fromY-2==toY)||
//					 					(fromX-1 == toX && fromY+2==toY)||
//					 						(fromX-1 == toX && fromY-2==toY)){
//				 
//				 return true;
//			 }
//		}
//		 return false;
//	 }// end possibleMoves

	 
}
