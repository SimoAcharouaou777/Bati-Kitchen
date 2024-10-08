package Service;

import Model.Quote;
import Repository.QuoteRepository;

public class QuoteService {
    private QuoteRepository quoteRepository = new QuoteRepository();

    public void addQuote(Quote quote){
        quoteRepository.addQuote(quote);
    }
    public Quote getQuoteByProjectId(int projectId){
        return quoteRepository.getQuoteByProjectId(projectId);
    }
}
