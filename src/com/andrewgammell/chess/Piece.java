package com.andrewgammell.chess;

import java.awt.Image;

import javax.swing.JLabel;

abstract class Piece {
	
	boolean white;
	int x;
	int y;
	Image II;
	
	

	 boolean isValidMove(int x, int y, int i, int j){
		 return false;
	 }

	boolean possibleMoves(int i, int j, int k, int l){
		return false;
	}

	abstract int getX();
	
	abstract int getY();
	 
	abstract void setPosition(int x, int y);
	 
	abstract boolean isWhite();
	 
	abstract JLabel getPiece();
	
//	public boolean isValidMove(){
//		return false;
//	}
	
	public void increamentMoveCount(){
		
	}
	 public int getMoveCount(){
		 return -1;
	 }
	public boolean isSwappable(int i, int j, int k, int l){
		return false;
	}
	public boolean isKing(){
		return false;
	}
	
}





