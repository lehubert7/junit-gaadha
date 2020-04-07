package VendingMachine;

/**
 * @author jiqbal
 * 
 * {@inheritDoc}
 * This class is used to implement a Drink Chamber having three different Drink types
 */

public class DrinkChamber {
    /**
     * Containers for various types of drinks 
     */
    Container<Cola, Integer> colaContainer = new Container<Cola, Integer>();
    Container<Coffee, Integer> coffeeContainer = new Container<Coffee, Integer>();
    Container<OrangeJuice, Integer> ojContainer = new Container<OrangeJuice, Integer>();
    
    Cola cola = new Cola();
    Coffee coffee = new Coffee();
    OrangeJuice oj = new OrangeJuice();
    
    /**
     * 
     * This method load the Drinks into Drink Chamber with default quantity
     */
    public void loadInventory() {
            colaContainer.addItem(cola, new Integer(10));
            coffeeContainer.addItem(coffee, new Integer(10));
            ojContainer.addItem(oj, new Integer(10));
    }
    
    /**
     * 
     * @return Cola : Drink 
     * 
     *This method reduce the cola count by reducing the count - 1 and returns a cola drink. If the drink count become less then zero it prints an error of err console "cola" and returns a null.
     */
    public Cola takeACola(){
		if (getColaCount().intValue() - 1 < 0)
                {
				System.err.println("cola");
                return null;
                }
				
        colaContainer.addItem(cola, getColaCount().intValue() - 1);
        return cola; 
}
    
    /**
     * 
     * @return Coffee : Drink 
     *This method reduce the Coffee count by reducing the count - 1 and returns a coffee drink. If the drink count become less then zero it prints an error of err console "coffee" and returns a null.     
     **/
    public Coffee takeACoffee() {
        if (getCoffeeCount().intValue() - 1 < 0)
        	    {
        		System.err.println("cofee");
        		return null;
        	    }
        coffeeContainer.addItem(coffee, getCoffeeCount().intValue() - 1);
        return coffee;
}
    
//    public void takeAOJ() throws Exception {
//            if (getOJCount().intValue() - 1 < 0)
//                    throw new OutOfStockException("orange juice");
//            ojContainer.addItem(oj, getColaCount().intValue() - 1);
//    }
//    
    
    /**
     *
     * @return OrangeDrink: Drink
     * 
     *This method reduce the Orange Juice count by reducing the count -1. If the drink count become less then zero it prints an error of err console "orange juice", and return a null.
     */
    public OrangeJuice takeAOJ(){
        if (getOJCount().intValue() - 1 < 0)
        	{
        	System.err.println("orange juice");
        	return null;
        	}
        ojContainer.addItem(oj, getOJCount().intValue() - 1);
        return oj; 
}
    
    /**
     * 
     * @return Integer : cola count from DrinkChamber
     */
    public Integer getColaCount() {
            return colaContainer.getItemCount(cola);
    }
    
    /**
     * 
     * @return Integer : coffee count from DrinkChamber
     */
    public Integer getCoffeeCount() {
            return coffeeContainer.getItemCount(coffee);
    }
    
    /**
     * 
     * @return Integer : orange juice count from DrinkChamber
     */
    public Integer getOJCount() {
            return ojContainer.getItemCount(oj);
    }
}