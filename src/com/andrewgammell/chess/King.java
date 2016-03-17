package com.andrewgammell.chess;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class King extends Piece{
	 private boolean king = true;
	 private boolean white;
	 private int x;
	 private int y;
	 private int moveCount = 0;
	 public JLabel piece;
	 
	public King(boolean White,int x, int y){
		this.white = White;
		this.x = x;
		this.y = y;
		makeKing();
	}
	 
	 private void makeKing(){
		
		 if(white){
			 this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/whiteking.png")));
		 }else{
			 this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/blackking.png")));
		 }
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
	 public int getMoveCount(){
		 return moveCount;
	 }
	 
	 public boolean isKing(){
		 return king;
	 }
	 
	 public boolean isValidMove(int fromX, int fromY,int toX,int toY){
		 if (white && Board.whiteTurn){
			 if (isSwappable(fromX,fromY,toX,toY)){
				 return true;
			 }
			 if(!isBlocked(toX,toY)){
				if(!willBeInCheck( toX, toY)){
					 if((fromX+1 == toX || fromX-1 == toX) && fromY == toY ){
						 return true;
					 }else if((fromY+1 == toY || fromY-1 == toY) && fromX == toX){
						 return true;
					 }else if(fromX+1 == toX && (fromY+1 == toY || fromY-1 == toY)){
						 return true;
					 }else if(fromX-1 == toX && (fromY+1 == toY || fromY-1 == toY)){
						 return true;
					 }
				 }
				}
			 
		 }
		 
		 if(!white && !Board.whiteTurn){
			 if (isSwappable(fromX,fromY,toX,toY)){
				 return true;
			 }
			 if(!isBlocked(toX,toY)){
				if(!willBeInCheck(toX,toY)){
					 if((fromX+1 == toX || fromX-1 == toX) && fromY == toY ){
						 return true;
					 }else if((fromY+1 == toY || fromY-1 == toY) && fromX == toX){
						 return true;
					 }else if(fromX+1 == toX && (fromY+1 == toY || fromY-1 == toY)){
						 return true;
					 }else if(fromX-1 == toX && (fromY+1 == toY || fromY-1 == toY)){
						 return true;
					 } 
				}
			 }
		 }
		 
		 return false;
	 }
	 
	 private boolean isBlocked(int toX, int toY){
		 		Piece pieceRef = MainApp.board.findPiece(toX,toY);
		 		if(pieceRef != null){
		 			 if(pieceRef.isWhite() == white){
						 return true;
					 }else{
						 return false;
					 }
		 		}
		 return false;
	 }// end of isBlocked
	 
	 public boolean isSwappable(int fromX, int fromY,int toX,int toY){
		 if(moveCount == 0){
			 Piece pieceRef1 = MainApp.board.findPiece(toX,toY);
			 if(pieceRef1 != null){
				 if(pieceRef1.getMoveCount()==0){
					 return true;
				 }
			 }
		 }
		
		 
		 return false;
	 }// end isSwappable
	 
	 
	 private boolean willBeInCheck( int toX, int toY){
		 
		 Piece pieceRef = null;
		 for(int i=0; i<8; i++){
			 for(int j=0; j<8; j++){
				 pieceRef = MainApp.board.findPiece(i,j);
				 if(pieceRef != null){
					 if(pieceRef.isWhite() == white){
						 return false;
					 }else if(pieceRef.isWhite() != white){
						 if(!pieceRef.possibleMoves(pieceRef.getX(), pieceRef.getY(), toX, toY)){
							 return true;
						 }
					 }
				 }
			 }
		 }
		return false;
	 }// end of willBeInCheck
	 
	 
	 
	 
}//end class
