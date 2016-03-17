package com.andrewgammell.chess;

public class MainApp {
	static Board board = new Board();
	
	public static void main(String[] args) {
		
		//MoveWindow moveWindow = new MoveWindow();
		
		try{
			board.makeBoard();
			board.setBoard();
			//moveWindow.displayMoveWindow();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}//end of Main
	
	
}//end of Class
