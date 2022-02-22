package cinema;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Cinema {

    private static final DecimalFormat df = new DecimalFormat("0.00");
	public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int rowNum = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int seatNum = scanner.nextInt();
        
        String[][] allSeats = new String[rowNum+1][seatNum+1];
        
        for(int i = 1; i<=rowNum;i++) {
        	for(int j = 1; j<= seatNum; j++) {
        		allSeats[i][j] = " S";
        	}
        }
        
        int menuChosen = 0;
        
        do {
        	System.out.println();
        	System.out.println("1. Show the seats");
        	System.out.println("2. Buy a ticket");
        	System.out.println("3. Statistics");
        	System.out.println("0. Exit");
        	System.out.print("> ");
        	menuChosen = scanner.nextInt();
        	
        	switch (menuChosen) {
        	  case 1:
        		  showTheSeats(rowNum, seatNum, allSeats);
        		  break;
        	  case 2:
        		  allSeats = buyTicket(rowNum, seatNum, allSeats, scanner);
        		  break;
        	  case 3:
        		  showStatistics(rowNum, seatNum, allSeats);
        		  break;
        	  case 0:        	    
        		  break;
        	  default:
        		  break;
        	}
        	
        } while(menuChosen != 0);  
    }
    
    public static void showTheSeats(int rowNum, int seatNum, String[][] allSeats) {
		System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for(int i = 1; i <= seatNum; i++) {
        	System.out.print(" " + i);
        }
        System.out.println();
        for(int i = 1; i <= rowNum; i++) {
        	System.out.print(i);
        	for(int j = 1; j <= seatNum; j++) {
        			System.out.print(allSeats[i][j]);
            }
        	System.out.println();
        }
	}
	
    public static void showStatistics(int rowNum, int seatNum, String[][] allSeats) {
    	System.out.println();
    	int totalSeats = rowNum*seatNum;
    	int numOfPurchasedTickets = getNumOfPurchasedTickets(rowNum, seatNum, allSeats);
    	double percentage = ((double)numOfPurchasedTickets/(double)totalSeats)*100;

    	System.out.println("Number of purchased tickets: "+numOfPurchasedTickets);
    	System.out.println("Percentage: "+df.format(percentage)+"%");
    	System.out.println("Current income: $"+calCurrentIncome(rowNum, seatNum, allSeats));
    	System.out.println("Total income: $"+calTotalIncome(rowNum, seatNum));

    }
    
    public static int getNumOfPurchasedTickets(int rowNum, int seatNum, String[][] allSeats) {
    	int result = 0;
    	for(int i = 1; i <= rowNum; i++) {        	
        	for(int j = 1; j <= seatNum; j++) {
        		if(allSeats[i][j].trim().equalsIgnoreCase("B")) {
        			result++;
        		}
            }
        }
    	return result;
    }
    
    public static int calCurrentIncome(int rowNum, int seatNum, String[][] allSeats) {
    	int currentIncome = 0;
    	for(int i = 1; i <= rowNum; i++) {        	
        	for(int j = 1; j <= seatNum; j++) {
        		if(allSeats[i][j].trim().equalsIgnoreCase("B")) {
        			currentIncome = currentIncome + getTicketPrice(rowNum, seatNum, i);
        		}
            }
        }
    	return currentIncome;
    }
    
    public static int calTotalIncome(int rowNum, int seatNum) {
    	int result = 0;
    	if(rowNum * seatNum <= 60) {
    		result = rowNum * seatNum * 10;

        }else {
        	int frontRowNum = 0;
        	int backRowNum = 0;
        	frontRowNum = rowNum / 2;
        	backRowNum = rowNum - frontRowNum;
        	result = frontRowNum * seatNum * 10 + backRowNum * seatNum * 8;
        }
    	return result;
    }
    
    public static int getTicketPrice(int rowNum, int seatNum, int rowNumBought) {
    	int ticketPrice = 0;
        if(rowNum * seatNum <= 60) {
        	ticketPrice = 10;
        }else {
        	int frontRowNum = 0;
        	frontRowNum = rowNum / 2;
        	if(rowNumBought <= frontRowNum) {
        		ticketPrice = 10;
        	}else {
        		ticketPrice = 8;
        	}
        }
        return ticketPrice;
    }
    
	public static String[][] buyTicket(int rowNum, int seatNum, String[][] allSeats, Scanner scanner){
	 	System.out.println();
        
	 	boolean errorOccur = true;
	 	int rowNumBought = 0;
	 	int seatNumBought = 0;
	 	while(errorOccur) {
	 		errorOccur = false;
	 		System.out.println("Enter a row number:");
	        System.out.print("> ");
	        rowNumBought = scanner.nextInt();
	        System.out.println("Enter a seat number in that row:");
	        System.out.print("> ");
	        seatNumBought = scanner.nextInt();
	        
	        System.out.println();
	        
	        if(rowNumBought > rowNum || seatNumBought > seatNum || rowNumBought <= 0 || seatNumBought <= 0) {
	        	errorOccur = true;
	        	System.out.println("Wrong input!");
	        	System.out.println();
	        }else if(allSeats[rowNumBought][seatNumBought].trim().equalsIgnoreCase("B")) {
	        	errorOccur = true;
	        	System.out.println("That ticket has already been purchased!");
	        	System.out.println();
	        }	      
	 	}
        
        int ticketPrice = getTicketPrice(rowNum, seatNum, rowNumBought);
        System.out.println("Ticket price: $" + ticketPrice);
        allSeats[rowNumBought][seatNumBought] = " B";
		return allSeats;
	}
}