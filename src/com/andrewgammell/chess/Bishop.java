package com.andrewgammell.chess;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bishop extends Piece{
	private boolean white;
	 private int x;
	 private int y;
	 public JLabel piece;

	 private int moveCount = 0;
	public Bishop(){
	}
	 
	public Bishop(boolean White,int x, int y){
		this.white = White;
		this.x = x;
		this.y = y;
		makeBishop();
	}
	 
	 private void makeBishop(){
		
		 if(white){
			 this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/whitebishop.png")));
		 }else{
			this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/blackbishop.png")));
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
			this.y = y;
	 }
	 
	 public boolean isValidMove(int fromX, int fromY, int toX, int toY){
		if(white && Board.whiteTurn){
			if((fromX != toX && fromY != toY) && (toX<8 && toY<8) && (toX >= 0 && toY >= 0)){
				for(int i=0;i<8;i++){
					if((fromX+i == toX && fromY+i == toY) || 
							(fromX-i == toX && fromY-i == toY ) ||
								(fromX+i == toX && fromY-i == toY) ||
									(fromX-i == toX && fromY+i == toY)){
								if(!isDiagBlocked(fromX,fromY,toX,toY)){
									return true;
								}
					}
				}
			}
		}
		
		if(!white && !Board.whiteTurn){
			if((fromX != toX && fromY != toY) && (toX<8 && toY<8) && (toX >= 0 && toY >= 0)){
				for(int i=0;i<8;i++){
					if((fromX+i == toX && fromY+i == toY) || 
							(fromX-i == toX && fromY-i == toY ) ||
								(fromX+i == toX && fromY-i == toY) ||
									(fromX-i == toX && fromY+i == toY)){
								if(!isDiagBlocked(fromX,fromY,toX,toY)){
									return true;
								}
					}
				}
			}
		}
		return false;
	 }// end isValidMove
	 
	 private boolean isDiagBlocked(int fromX, int fromY,int toX, int toY){
		 int num = fromX-toX;
		 int num2 = toX-fromX;
		 int num3 ;
		 if(num>num2){
			 num3 = num;
		 }else{
			 num3 = num2;
		 }
		 for(int i=1; i<num3;i++){
				Piece pieceRef1 = MainApp.board.findPiece(fromX+i,fromY+i);
				Piece pieceRef2 = MainApp.board.findPiece(fromX-i,fromY-i);
				Piece pieceRef3 = MainApp.board.findPiece(fromX+i,fromY-i);
				Piece pieceRef4 = MainApp.board.findPiece(fromX-i,fromY+i);
				
				if(pieceRef1 != null){
					for(int j=1; j<7; j++){
						if(pieceRef1.getX()+j==toX && pieceRef1.getY()+j==toY) {
							return true;
						}
					}
				}
				if(pieceRef2 != null){
					for(int j=1; j<7; j++){
						if(pieceRef2.getX()-j==toX && pieceRef2.getY()-j==toY) {
							return true;
						}
					}
				}
				if(pieceRef3 != null){
					for(int j=1; j<7; j++){
						if(pieceRef3.getX()+j==toX && pieceRef3.getY()-j==toY) {
							return true;
						}
					}
				}
				if(pieceRef4 != null){
					for(int j=1; j<7; j++){
						if(pieceRef4.getX()-j==toX && pieceRef4.getY()+j==toY) {
							return true;
						}
					}
				} 
			}
		

		 return false;
	 }// end isDiagBlocked
	 
	 public boolean possibleMoves(int fromX, int fromY, int toX, int toY){
		 if(white ^ Board.whiteTurn){
				if((fromX != toX && fromY != toY) && (toX<8 && toY<8) && (toX >= 0 && toY >= 0)){
					for(int i=0;i<8;i++){
						if((fromX+i == toX && fromY+i == toY) || 
								(fromX-i == toX && fromY-i == toY ) ||
									(fromX+i == toX && fromY-i == toY) ||
										(fromX-i == toX && fromY+i == toY)){
									if(!isDiagBlocked(fromX,fromY,toX,toY)){
										return true;
									}
						}
					}
				}
			}
			 return false;
	 }// end possibleMoves
	 
	 
}//End Of Class
