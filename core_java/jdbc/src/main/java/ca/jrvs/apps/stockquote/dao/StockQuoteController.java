package ca.jrvs.apps.stockquote.dao;

import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

public class StockQuoteController {

    private QuoteService quoteService;
    private PositionService positionService;
    private final Logger logger = Logger.getLogger(String.valueOf(StockQuoteController.class));

    public StockQuoteController(QuoteService quoteService, PositionService positionService){
        this.quoteService = quoteService;
        this.positionService = positionService;
    }


    public PositionService getPositionService() {
        return positionService;
    }

    public QuoteService getQuoteService() {
        return quoteService;
    }

    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    /**
     * User interface for our application
     */
    public void initClient() {
        //TO DO
        Scanner scanner = new Scanner(System.in);
        System.out.println(menu);
        while(true){
            System.out.println(select_1);
            int choice_1 = scanner.nextInt();
            if (choice_1 == 1){
                System.out.println("Insert the symbol from where you want to check you data in the database:");
                String symbol = scanner.next();
                Optional<Quote> quoteData = quoteService.fetchQuoteDataFromAPI(symbol);
                if(quoteData.isEmpty()){
                    logger.info("The data could not be found in the databases. Try again later!!\n");
                }
                else {
                    logger.info("Here are your quote Data:\n");
                    System.out.println(quoteData.get().toString());
                }

            }
            else if (choice_1 == 2){
                System.out.println("Insert the symbol of the stock you want to buy:");
                String symbol = scanner.next();
                Optional<Quote> quoteData = quoteService.fetchQuoteDataFromAPI(symbol);
                if(quoteData.isEmpty()){
                    logger.info("This stock is not for sale\n");
                }
                else {
                    System.out.println("Insert the number of shares:");
                    int numOfShares = scanner.nextInt();
                    positionService.buy(symbol, numOfShares, numOfShares * quoteData.get().getPrice());
                    logger.info("Your stock was bought !!!!\n");
                }

            }

            else if (choice_1 == 3){
                System.out.println("Insert the symbol of the stock you want to sell:");
                String symbol = scanner.next();
                positionService.sell(symbol);
                System.out.println("Stock correctly sold!!\n");

            }

            else if (choice_1 == 4){
                System.out.println("Thanks for using the app and have a great Day :)!!!");
                break;
            }

            else {
                System.out.println("!!!!  Pick a number between 1 and 4 for the options and press enter  !!!!");
            }

        }
        scanner.close();

    }


    public static final String menu = " $$$$$$\\ $$$$$$$$\\  $$$$$$\\   $$$$$$\\  $$\\   $$\\ $$$$$$$$\\        $$$$$$\\  $$\\   $$\\  $$$$$$\\ $$$$$$$$\\ $$$$$$$$\\        $$$$$$\\  $$$$$$$\\  $$$$$$$\\  \n" +
            "$$  __$$\\\\__$$  __|$$  __$$\\ $$  __$$\\ $$ |  $$ |$$  _____|      $$  __$$\\ $$ |  $$ |$$  __$$\\\\__$$  __|$$  _____|      $$  __$$\\ $$  __$$\\ $$  __$$\\ \n" +
            "$$ /  \\__|  $$ |   $$ /  $$ |$$ /  $$ |$$ |  $$ |$$ |            $$ /  $$ |$$ |  $$ |$$ /  $$ |  $$ |   $$ |            $$ /  $$ |$$ |  $$ |$$ |  $$ |\n" +
            "\\$$$$$$\\    $$ |   $$ |  $$ |$$ |  $$ |$$ |  $$ |$$$$$\\          $$ |  $$ |$$ |  $$ |$$ |  $$ |  $$ |   $$$$$\\          $$$$$$$$ |$$$$$$$  |$$$$$$$  |\n" +
            " \\____$$\\   $$ |   $$ |  $$ |$$ |  $$ |$$ |  $$ |$$  __|         $$ |  $$ |$$ |  $$ |$$ |  $$ |  $$ |   $$  __|         $$  __$$ |$$  ____/ $$  ____/ \n" +
            "$$\\   $$ |  $$ |   $$ |  $$ |$$ $$\\$$ |$$ |  $$ |$$ |            $$ $$\\$$ |$$ |  $$ |$$ |  $$ |  $$ |   $$ |            $$ |  $$ |$$ |      $$ |      \n" +
            "\\$$$$$$  |  $$ |    $$$$$$  |\\$$$$$$ / \\$$$$$$  |$$$$$$$$\\       \\$$$$$$ / \\$$$$$$  | $$$$$$  |  $$ |   $$$$$$$$\\       $$ |  $$ |$$ |      $$ |      \n" +
            " \\______/   \\__|    \\______/  \\___$$$\\  \\______/ \\________|       \\___$$$\\  \\______/  \\______/   \\__|   \\________|      \\__|  \\__|\\__|      \\__|      \n" +
            "                                  \\___|                               \\___|                                                                           \n" +
            "                                                                                                                                                      \n" +
            "                                                                                                                                                      \n" ;

    public static final String select_1 = "Select an option:\n" +
            "1) Fetch data from API\n" +
            "2) Buy some stocks \n" +
            "3) Sell some stocks\n" +
            "4) Quit\n";



}