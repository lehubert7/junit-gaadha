/* DISCLAIMER: THIS CODE IS A MODIFIED VERSION OF THE ONE PROVIDED AT 
 * https://code.google.com/a/eclipselabs.org/p/vending-machine/ under
 * Eclipse Public Licence 1.0
 */



package VendingMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




/**
 * @author jiqbal
 * 
 * {@inheritDoc}
 * Vending Machine
 */

public class VendingMachine {
        /**
         * @author jiqbal
         * Represents Different coins as Enumerators
         */
        private enum Coin {
            //TC = 20 cent, FC = 50 cent, OE = 1 euro, TE = 2 Euro
        	TC(0.2), FC(0.5), OE(1.0), TE(2.0);
        	
        	/**
        	 * @param value  double 
        	 * Method to return Enumerator double value
        	 */
        	Coin(double value) { this.value = value; }
            private final double value;
       //     public double value() { return value; }
        }
        
    	private String displayMenu;
    	private final String NLC = System.getProperty("line.separator"); //New line character form OS properties.
        
        /**
         * @author jiqbal
         *
         * Enumerators for Drink types and Quit command
         */
        private enum SelectionMenu {
                COLA,
                ORANGE_JUICE,
                COFFEE,
                QUIT
        }
        
        private static final String EXIT = "Exit"; 
        
        public DrinkChamber drinkChamber;
        
        /**
         *  Represents total amount paid during a drink dispensing session. 
         */
        public double amountPaid; 
        
       
        /**
         * @param amountPaid Double
         *
         * Setter for amountPaid 
         */
        public void setAmountPaid(double amountPaid) {
                this.amountPaid = amountPaid;
        }
        
        
        
        /**
         *
         * 
         * This method is responsible for creation of a drink chamber and loading it with default number of drinks.
         */
        public void powerUpVendingMechine() {
                drinkChamber = new DrinkChamber();
                drinkChamber.loadInventory();
        }
        
        public String getDisplayMenu() {
			return displayMenu;
		}

		

		
		/**
		 * @return String
		 * 
		 *  Creates a Menu string with information.
		 */
		private String createDisplayMenu()
		{
			displayMenu="";
			StringBuilder sb = new StringBuilder();
			
			 sb.append("Please select your drink from the menu:");sb.append(NLC);
			 sb.append("\t" + SelectionMenu.COLA + "\t\tprice: [" + drinkChamber.cola.price + "] euro" + "\tstill have: [" + drinkChamber.getColaCount().toString() + "]can"); sb.append(NLC);
			 sb.append( "\t" + SelectionMenu.COFFEE + "\t\tprice: [" + drinkChamber.coffee.price + "] euro" + "\tstill have: [" + drinkChamber.getCoffeeCount().toString() + "]can"); sb.append(NLC);
			 sb.append( "\t" + SelectionMenu.ORANGE_JUICE + "\tprice: [" + drinkChamber.oj.price + "] euro" + "\tstill have: [" +  drinkChamber.getOJCount().toString() + "]can"); sb.append(NLC);
			 sb.append("\t" + "QUIT");sb.append(NLC);sb.append(NLC);
					 sb.append( "Enter:");sb.append(NLC);
			
			
			displayMenu = sb.toString();
			return(displayMenu);
		}
		
             
        /**
         * Displays Menu on console.
         */
        public void DisplayMenu() {
        	System.out.println(createDisplayMenu());
        }
		
		
        /**
         * @param change Double the amount to returns in terms of Coins 
         * @return int[]  list of coins to be returned corresponding to TE OE FC TC 
         */
        public int[] calculateReturningCoins(double change) {
        	   //calculates the change in coins, assumes unlimited coins available
        	
        		int[] coinList = new int[4];//number of coins corresponding to TE OE FC TC 
        	
                //System.out.print("Returning coin for change: "+ change );
                
                if ( change / Coin.TE.value >= 1 ) { 
                        int twoEuro = (int)(change / Coin.TE.value);
                        change = change - (twoEuro * Coin.TE.value);
                        coinList[0] = (int)twoEuro;
                        
                        //System.out.println(twoEuro + " 2 Euro "); 
                }
                if ( change / Coin.OE.value >= 1 ) { 
                        int oneEuro = (int)(change / Coin.OE.value);
                        change = change - (oneEuro * Coin.OE.value);
                        System.out.println(oneEuro + " 1 Euro ");
                        coinList[1] = (int)oneEuro;
                        
                        //System.out.println(oneEuro + " 1 Euro "); 
                } 
                if ( change / Coin.FC.value >= 1 ) {        
                		int fiftyCent = (int)(change / Coin.FC.value);
                        change = change - (fiftyCent * Coin.FC.value);
                        coinList[2] = (int)fiftyCent;
                        //System.out.println(fiftyCent + " 50 cents "); 
                } 
                if ( change / Coin.TC.value >= 1 ) { 
                	    int twentyCent = (int)(change / Coin.TC.value);
                        change = change - (twentyCent * Coin.TC.value);
                        coinList[3] = (int)twentyCent;
                        //System.out.println(twentyCent + " 20 cents "); 
                }      
            return coinList;
        }
        
     
        
        /**
         * @param change: double  value of returning amount to show in coins.
         * @return String message printed on console for returning the coins.
         */
        public String displayReturningCoins(double change){
        	// displays the change
        	
        	int[] coins = calculateReturningCoins(change);
        	
        	StringBuilder sb = new StringBuilder();
        	
        	sb.append("Your Change is ");sb.append(NLC);
        	sb.append("\t" + coins[0] +" x 2Euro" );sb.append(NLC);
        	sb.append("\t" + coins[1] +" x 1Euro" );sb.append(NLC);
        	sb.append("\t" + coins[2] +" x 50Cent" );sb.append(NLC);
        	sb.append("\t" + coins[3] +" x 20Cent" );sb.append(NLC);
        	
        	
        	System.out.println(sb.toString());
        	
             return(sb.toString());
                    	
        }
        
        
        
        /**
         * @param price Double price of the drink
         * @param insertedCoins String of coins. insertedCoins is tokenized using spaces, e.g., OE OE OE for 3 Euro.
         * @return double the amount to be paid back.
         */
        public double calculateChange(double price, String insertedCoins) {
        	//calculates the change to be reuturned based on user input
        	//insertedCoins is tokenized using spaces, eg OE OE OE for 3 Euros
        	    
                StringTokenizer st = new StringTokenizer(insertedCoins);
                
                while(st.hasMoreElements()) {
                        String coin = st.nextToken();
                        
                        if (coin.equals("TC")) { amountPaid += Coin.TC.value; }
                        else if (coin.equals("FC")) { amountPaid += Coin.FC.value;}
                        else if (coin.equals("OE")) { amountPaid += Coin.OE.value; }
                        else if (coin.equals("TE")) { amountPaid += Coin.TE.value; }
                        else {System.out.printf("Wrong coin type!"); }
                      
                }
                System.out.println("You have paid " + amountPaid + " Euro");
                return amountPaid - price;
        }
        
        
      
        
        
        /**
         * @param selection String drink selected 
         * @param price double price of the drink
         * @return boolean return true if user gets the drink and change if any.
         */
       
        public Boolean captureMoney(String selection, double price){ //throws Exception {
        	    //int max_prompts = 100, counter =0;
        	    
        		//receives coins from the user. 
        	    BufferedReader coins = new BufferedReader(new InputStreamReader(System.in));
        	        	               
                while(true ){
                String amount = null;
                
                try {
                        amount = coins.readLine();
                        if (amount != null) {
                                double change = calculateChange(price, amount);
                                                             
                                if ( change >= 0.0) {
                                        //processSelection(selection, true);
                                        if (change > 0.0) {
                                                System.out.println("Your change is: " + change + " EURO");
                                                displayReturningCoins(change);
                                                System.out.println("Thank you for your business, you see again!"+ NLC+NLC+NLC+NLC);
                                                change = 0;
                                         }
                                        break; 
                                   	} else {
                                        System.out.println("You did not put enough money, please put in more coins.");
                                   	}
                                
                                        
                                } 
                        else {
                              System.out.println("Error in reading input.");
                              return false;
                           	}		
                } catch (IOException e) {
                        System.out.println("Error in reading input.");
                        System.exit(1);
                }       
              }
                
                return true;
                
        }
        
   
              
        /**
         * @param selection String drink selected
         * @param paymentOK boolean represents that payment is okay.
         * @return Drink to be taken 
         */
        public Drink processSelection(String selection, boolean paymentOK) {
            double price = 0.0;
            Drink drink = null;
            switch (SelectionMenu.valueOf(selection)) {
                    case COLA: 
                            price = drinkChamber.cola.price;
                            System.out.printf("The price is %.2f Euro, please insert a coin."+NLC, drinkChamber.cola.price);
                            if (captureMoney(selection, price)) { drink = drinkChamber.takeACola();}
                            else {System.out.printf("ERROR");}
                            break;
                    case COFFEE: 
                            price = drinkChamber.coffee.price;
                            System.out.printf("The price is %f EURO, please insert a coin."+NLC, drinkChamber.coffee.price);
                            if (captureMoney(selection, price)) { drink = drinkChamber.takeACoffee(); } 
                            else {System.out.printf("ERROR");}
                            break;
                    case ORANGE_JUICE: 
                            price = drinkChamber.oj.price;
                            System.out.printf("The price is %f EURO, please insert a coin."+NLC, drinkChamber.oj.price);
                            if (captureMoney(selection, price)) { drink = drinkChamber.takeAOJ(); } 
                            else {System.out.printf("ERROR");} 
                            break;
                    case QUIT:
            }
    
        
        return drink;
        }
        
        
        /**
         * @return EXIT: String 
         */
        public String captureInputAndRespond(){
        	//user is allowed to take max 10 drinks.
        	    	
        	BufferedReader choosen = new BufferedReader(new InputStreamReader(System.in));
        	for (int i = 0; i < 10; i++){
        		this.DisplayMenu();
        		String selection = null;
        		
        		try {
                    selection = choosen.readLine();                    
        		} catch (IOException e) {
                    System.out.println("Error in reading input.");
                    System.exit(1);
        		}
        		
        		 //check if valid options
        		 if (selection.equals("QUIT") || selection.equals("COLA") || selection.equals("COFFEE") || selection.equals("ORANGE_JUICE")){
        			 if (!selection.equals("QUIT")) {
	         			 System.out.printf("You have selected " + selection + NLC);        		
	        			 processSelection(selection, false);
	        			 System.out.println("DRINK DELIVERED! "+NLC+NLC); 
	        			 //reseting the amount paid
	        			 amountPaid=0;
	        		 }
	        		 else {
	        			 System.out.println("Exiting...BYE!");   
	        			 System.exit(1);			 
	        		 }
        		 }
        		 else { System.out.println("Wrong input try again!!!"+NLC+NLC);}   
        	}
        	return EXIT;
         }
        	
        
        public static void main(String[] args) {
                VendingMachine vm = new VendingMachine();
                vm.powerUpVendingMechine();
                while(true) {
                        vm.setAmountPaid(0.0);
                        if (vm.captureInputAndRespond().equals("Exit"))
                                break;
                }
        }

		/**
		 * @return  double
		 * Getter for amountPaid
		 */
		public double getAmountPaid() {
			return amountPaid;
		}
		 
		
		
	/**
	 * @return DrinkChamber Getter for Drink Chamber
	 */
	public DrinkChamber getDrinkChamber() {
				return drinkChamber;
			}

	
	/**
	 * @param drinkChamber DrinkChamber Setter for drink chamber.
	 */
	public void setDrinkChamber(DrinkChamber drinkChamber) {
				this.drinkChamber = drinkChamber;
			}
}
