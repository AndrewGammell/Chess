package com.andrewgammell.chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board{

	private final byte XAXIS = 8;
	private final byte YAXIS = 8;
	public static boolean whiteTurn = true;
	JPanel[][] squares;
	Piece pieceRef;
	ArrayList<Piece> list;
	JFrame frame;
	
	

	public Board(){
	}


	public JFrame makeBoard(){

		squares = new JPanel[XAXIS][YAXIS];

		frame = new JFrame("Chess Board");
		frame.setLayout(new GridLayout(XAXIS,YAXIS));
		frame.setSize(600,600);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i = 0; i < XAXIS; i++){

			for(int j = 0; j < YAXIS; j++){

				squares[i][j]=new JPanel();
				
				 if((i+j) % 2 == 0){
					squares[i][j].setBackground(Color.white);
				}else{
					squares[i][j].setBackground(Color.black);
				}
				
				squares[i][j].addMouseListener(new MouseEvents(i,j));
				frame.add(squares[i][j]);
				squares[i][j].setLayout(new BorderLayout());;
			}
		}
		return frame;
	}//makeBoard

	private void listOfPieces(){
		list = new ArrayList<Piece>();
		pieceRef = new Rook(true,0,0);
		list.add(pieceRef);
		pieceRef = new Knight(true,0,1);
		list.add(pieceRef);
		pieceRef = new Bishop(true,0,2);
		list.add(pieceRef);
		pieceRef = new King(true,0,3);
		list.add(pieceRef);
		pieceRef = new Queen(true,0,4);
		list.add(pieceRef);
		pieceRef = new Bishop(true,0,5);
		list.add(pieceRef);
		pieceRef = new Knight(true,0,6);
		list.add(pieceRef);
		pieceRef = new Rook(true,0,7);
		list.add(pieceRef);
		for(int i=0; i<8;i++){
			for(int j=0; j<8;j++){
				if((i==1)^(i==6)){
					if(i==1){
						pieceRef = new Pawn(true,i,j);
						list.add(pieceRef);
					}else if(i==6){
						pieceRef = new Pawn(false,i,j);
						list.add(pieceRef);
					}
				}
			}
		}
		pieceRef = new Rook(false,7,0);
		list.add(pieceRef);
		pieceRef = new Knight(false,7,1);
		list.add(pieceRef);
		pieceRef = new Bishop(false,7,2);
		list.add(pieceRef);
		pieceRef = new King(false,7,3);
		list.add(pieceRef);
		pieceRef = new Queen(false,7,4);
		list.add(pieceRef);
		pieceRef = new Bishop(false,7,5);
		list.add(pieceRef);
		pieceRef = new Knight(false,7,6);
		list.add(pieceRef);
		pieceRef = new Rook(false,7,7);
		list.add(pieceRef);
		
		
		
	}// end of listOfPieces

	public void setBoard(){
		listOfPieces();
		for(int i=0; i<list.size();i++){
			pieceRef = list.get(i);
			squares[pieceRef.getX()][pieceRef.getY()].add(pieceRef.getPiece());
			
		}
	}// end setBoard
	
	public void movePiece(int fromX, int fromY, int toX, int toY){
		
			pieceRef = findPiece(fromX,fromY);
			if(pieceRef != null){
				 if(pieceRef.isSwappable(fromX, fromY, toX, toY)){
					swapPieces(fromX,fromY,toX,toY);
					whiteTurn = !whiteTurn;
				}
				if(pieceRef.isValidMove(fromX,fromY,toX,toY)){
					if(isOccupied(toX,toY)){
						if(isTakable(fromX,fromY,toX,toY)){
							setPiece(fromX,fromY,toX,toY);
							pieceRef.increamentMoveCount();
							if(checkForCheck()){
					//		JOptionPane.showMessageDialog(frame, "You Got Pwned. Check");
							}
							whiteTurn = !whiteTurn;
						}
					}else if(!isOccupied(toX,toY)){
						setPiece(fromX,fromY,toX,toY);
						pieceRef.increamentMoveCount();
						if(checkForCheck()){
					//		JOptionPane.showMessageDialog(frame, "You Got Pwned. Check");
						}
						whiteTurn = !whiteTurn;
					}
				}
			}
	}// end movePiece

	private boolean isOccupied(int toX, int toY){
		Piece piece = findPiece(toX, toY);
			if(piece != null){
				return true;
			}
		return false;
	}// end isOccupied

	private void removePiece(int toX, int toY){
		for(int i=0; i<list.size();i++){
			if((list.get(i).getX() == toX) && (list.get(i).getY() == toY)){
				list.remove(i);
				squares[toX][toY].removeAll();
			}
		}
	}// end removePiece
	
	public boolean isTakable(int fromX, int fromY, int toX, int toY){
	
		Piece from = findPiece(fromX,fromY);
		Piece to = findPiece(toX,toY);
		return from.isWhite() != to.isWhite();
	}// end of isTakable
	
	public Piece findPiece(int indexX,int indexY){
		Piece piece;
		for(int i=0;i<list.size();i++){
			piece = list.get(i);
			if(piece.getX()==indexX && piece.getY()==indexY){
				return piece;
			}
		}
		return null;
	}// End of findPiece
	

	
	public void swapPieces(int fromX, int fromY, int toX, int toY){
		
		Piece pieceRef1 = findPiece(fromX, fromY);
		Piece pieceRef2 = findPiece(toX, toY);
		
		pieceRef1.setPosition(toX,toY);
		pieceRef2.setPosition(fromX,fromY);
		
		squares[toX][toY].add(pieceRef1.getPiece());
		squares[fromX][fromY].add(pieceRef2.getPiece());
		
		pieceRef1.increamentMoveCount();
		pieceRef2.increamentMoveCount();
		
		frame.repaint();
	}// end swapPieces
	
	public void setPiece(int fromX, int fromY, int toX, int toY){
		Piece pieceRef1 = findPiece(fromX, fromY);
		removePiece(toX,toY);
		pieceRef1.setPosition(toX,toY);
		squares[toX][toY].add(pieceRef.getPiece());
		frame.repaint();
	}// end setPiece
	
	 public boolean checkForCheck(){
		 Piece whiteKing = findKing(true);
		 Piece blackKing = findKing(false);
		 Piece pieceRef1 = null;
		 for(int i=0; i<8; i++){
			 for(int j=0; j<8; j++){
				 pieceRef1 = findPiece(i,j);
				 if(pieceRef1 != null){
					 if(pieceRef1.isWhite() != whiteKing.isWhite()){
						 if(pieceRef1.isValidMove(pieceRef1.getX(), pieceRef1.getY(), whiteKing.getX(),whiteKing.getY())){
								return true; 
						 }
					 } 
				 }
				 
				 if(pieceRef1 != null){
					 if(pieceRef1.isWhite() != blackKing.isWhite()){
						 if(pieceRef1.isValidMove(pieceRef1.getX(), pieceRef1.getY(), blackKing.getX(),blackKing.getY())){
								return true; 
						 }
					 }
				 }
				 
			 }
		 }
		 return false;
	 }// end of isInCheck
	 
	 public Piece findKing(boolean white){
		 Piece pieceRef1 = null;
		 for(int i=0;i<MainApp.board.list.size();i++){
			 for(int j=0; j<MainApp.board.list.size();j++){
				 pieceRef1 = findPiece(i,j);
				 if(pieceRef1 != null){
					 if(pieceRef1.isKing()){
						if(pieceRef1.isWhite() == white){
							return pieceRef1;
						}
					 }
				 }
			 }
		 }
		 return pieceRef1;
	 }// end findKing






}//End Of Class
