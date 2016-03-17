package com.andrewgammell.chess;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class MouseEvents implements MouseListener {
	private int indexX;
	private int indexY;
	public static int fromX;
	public static int fromY;
	public static byte click = 0;
	public static JPanel pan;


	public MouseEvents(int indexX, int indexY){
		this.indexX = indexX;
		this.indexY = indexY;
	}
	@Override
	public void mouseClicked(MouseEvent e) {

		switch(click){

		case(0):{fromX = indexX;
		fromY = indexY;
		click = 1;
		showPossibleMoves(fromX,fromY);
		//				 System.out.println("From " +fromX +" "+ fromY );
		pan = MainApp.board.squares[fromX][fromY];
		pan.setBorder(showSelection());
		}break;
		case(1):{
			removePossibleMoves(fromX,fromY,indexX,indexY);
			//				System.out.println("To "+ indexX +" "+ indexY);
			MainApp.board.movePiece(fromX,fromY,indexX,indexY);
			pan.setBorder(removeSelection());
			click = 0;
		}break;

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public Border showSelection(){
		Border selected = BorderFactory.createLineBorder(new Color(0,0,255),5);
		return selected;
	}
	public Border showMoves(){
		Border selected = BorderFactory.createLineBorder(new Color(255,0,255),5);
		return selected;
	}

	public Border removeSelection(){
		Border selected = BorderFactory.createLineBorder(Color.BLACK);
		return selected;
	}
	public void showPossibleMoves(int fromX, int fromY){
		Piece pieceRef = MainApp.board.findPiece(fromX,fromY);
		JPanel pan;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(pieceRef != null){
					if(pieceRef.isValidMove(fromX, fromY, i, j)){
						pan = MainApp.board.squares[i][j];
						pan.setBorder(showMoves());
					}
				}
			}
		}
	}
	public void removePossibleMoves(int fromX,int fromY, int toX,int toY){
		Piece pieceRef = MainApp.board.findPiece(fromX,fromY);
		JPanel pan;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(pieceRef != null){
					if(pieceRef.isValidMove(fromX, fromY, i, j)){
						pan = MainApp.board.squares[i][j];
						pan.setBorder(removeSelection());
					}
				}
			}
		}
	}
}//End Of Class
