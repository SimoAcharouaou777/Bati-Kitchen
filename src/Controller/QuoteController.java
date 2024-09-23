package Controller;

import Model.Quote;
import Service.QuoteService;

public class QuoteController {
    private static QuoteService quoteService = new QuoteService();

    public static void addQuote(Quote quote){
        quoteService.addQuote(quote);
    }
    public static Quote getQuoteByProjectId(int projectId){
        return quoteService.getQuoteByProjectId(projectId);
    }

}
