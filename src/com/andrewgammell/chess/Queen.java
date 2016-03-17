package com.andrewgammell.chess;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Queen extends Piece{
	private boolean white;
	private int x;
	private int y;
	public JLabel piece;

	public Queen(boolean White,int x, int y){
		this.white = White;
		this.x = x;
		this.y = y;
		makeQueen();
	}//end Constructor

	private void makeQueen(){
		if(white){
			this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/whitequeen.png")));
		}else{
			this.piece = new JLabel(new ImageIcon(Pawn.class.getResource("/pieces/blackqueen.png")));
		}
		piece.setVisible(true);
		piece.setForeground(Color.RED);
	}// end makeQueen


	public boolean isWhite(){
		return this.white;
	}//end isWhite

	public int getX(){
		return x;
	}//end getX

	public int getY(){
		return y;
	}// end getY

	public JLabel getPiece(){
		return piece;
	}// end getPiece

	public void setPosition(int x, int y){
		this.x = x;
		this.y= y;
	}// end setPosition


	public boolean isValidMove(int fromX, int fromY, int toX, int toY){
		Piece pieceRef = MainApp.board.findPiece(toX,toY);
		
		if(white && Board.whiteTurn){
			if(isDiagValidMove(fromX, fromY, toX, toY) || isStraightValidMove( fromX, fromY, toX, toY)){
				if(pieceRef != null){
					if(pieceRef.isWhite() != white){
						return true;
					}else if(pieceRef.isWhite() == white){
						return false;
					}
				}else{
					return true;
				}
			}
		}

		if(!white && !Board.whiteTurn){
			if(isDiagValidMove(fromX, fromY, toX, toY) || isStraightValidMove( fromX, fromY, toX, toY)){
				if(pieceRef != null){
					if(pieceRef.isWhite() != white){
						return true;
					}else if(pieceRef.isWhite() == white){
						return false;
					}
				}else{
					return true;
				}
			}
		}
		return false;
	}// end isValidMove


	private boolean isDiagValidMove(int fromX, int fromY,int toX, int toY){
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
		return false;
	}// end diagValidMove
	

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
				for(int j=1; j<8; j++){
					if(pieceRef1.getX()+j==toX && pieceRef1.getY()+j==toY) {
						return true;
					}
				}
			}
			if(pieceRef2 != null){
				for(int j=1; j<8; j++){
					if(pieceRef2.getX()-j==toX && pieceRef2.getY()-j==toY) {
						return true;
					}
				}
			}
			if(pieceRef3 != null){
				for(int j=1; j<8; j++){
					if(pieceRef3.getX()+j==toX && pieceRef3.getY()-j==toY) {
						return true;
					}
				}
			}
			if(pieceRef4 != null){
				for(int j=1; j<8; j++){
					if(pieceRef4.getX()-j==toX && pieceRef4.getY()+j==toY) {
						return true;
					}
				}
			} 
		}
		return false;
	}// end isDiagBlocked

	private boolean isStraightValidMove(int fromX, int fromY,int toX, int toY){
		if((fromX==toX || fromY==toY) && !isStraightBlocked(fromX,fromY,toX,toY)){
			return true;
		}
		return false;
	}// end of straightValidMove

	private boolean isStraightBlocked(int fromX, int fromY, int toX, int toY){

		for(int i=0; i<MainApp.board.list.size();i++){
			if(((MainApp.board.list.get(i).getX()>fromX && MainApp.board.list.get(i).getX()<toX) && MainApp.board.list.get(i).getY()==toY) ||
					((MainApp.board.list.get(i).getX()<fromX && MainApp.board.list.get(i).getX()>toX) && MainApp.board.list.get(i).getY()==toY) ||
					((MainApp.board.list.get(i).getY()>fromY && MainApp.board.list.get(i).getY()<toY) && MainApp.board.list.get(i).getX()==toX) ||
					((MainApp.board.list.get(i).getY()<fromY && MainApp.board.list.get(i).getY()>toY && MainApp.board.list.get(i).getX()==toX)))

			{
				return true;
			}
		}
		return false;
	}// end of isStraightBlocked

	public boolean possibleMoves(int fromX, int fromY, int toX, int toY){
		if(white ^ Board.whiteTurn){
			if(isDiagValidMove(fromX, fromY, toX, toY) || isStraightValidMove( fromX, fromY, toX, toY)){
				return true;
			}
		}
		return false;
	}// end possibleMoves



}// End ofClass