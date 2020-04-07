package VendingMachine;

import java.util.*;

/**
 * @author jiqbal
 * {@inheritDoc}
 */
public class Container <T extends Drink, I> {
        
        
        private Map<T,I> items = new HashMap<T,I>();
        
        /**
         * @param item Item to to added
         * @param count Item count to be added
         * 
         * 
         */
        public void addItem(T item, I count) {
                items.put(item, count);
                /***
                 * This methods adds item with count I in items hashmap
                 * @return void : return nothing
                 * 
                 */
        }
        
        /**
         * @param item Drink 
         * @return I Container for the item.
         */
        public I getItemCount(T item) {
                return items.get(item);
                /***
                 * This method returns an item from items hashmap
                 */
        }
}

