package SortingSearching;

import java.util.Comparator;

import order.Product;

public class Sort {
    
    public static Comparator<Product> prodNameComparator= new Comparator<Product>() {

        public int compare(Product prod1, Product prod2) {
            String ProductName1 = prod1.getName();
            String ProductName2 = prod2.getName();

            
            return ProductName1.compareTo(ProductName2);

        }
    };


    public static Comparator<Product> AscProdPriceComparator = new Comparator<Product>() {

        public int compare(Product prod1, Product prod2) {
            double ProductPrice1 = prod1.getPrice();
            double ProductPrice2 = prod2.getPrice();

            if (ProductPrice1 < ProductPrice2)
                return 1;
            if (ProductPrice1 > ProductPrice2)
                return -1;
            return 0;

        }
    };

    public static Comparator<Product> DesProdPriceComparator = new Comparator<Product>() {

        public int compare(Product prod1, Product prod2) {
            double ProductPrice1 = prod1.getPrice();
            double ProductPrice2 = prod2.getPrice();

            if ( ProductPrice1 < ProductPrice2)
                return -1;
            if (ProductPrice1 > ProductPrice2)
                return 1;
            return 0;

        }

        
    };

    public static Comparator<Product> ProdRatingComparator = new Comparator<Product>() {

        public int compare(Product prod1, Product prod2) {
            double ProductPrice1 = prod1.calcAvRating();
            double ProductPrice2 = prod2.calcAvRating();

            if (ProductPrice1 < ProductPrice2)
                return 1;
            if (ProductPrice1 > ProductPrice2)
                return -1;
            return 0;

        }
    };


}
