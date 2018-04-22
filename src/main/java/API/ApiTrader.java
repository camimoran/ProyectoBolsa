package API;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yo on 6/4/2018.
 */
public class ApiTrader {

    private List<Stock> myExchanges = new ArrayList<Stock>();

    public ApiTrader(String ... exchangesNames) throws IOException {
        myExchanges = this.setExchanges(exchangesNames);
       // this.showExchangesDetails();
    }

    private List<Stock> setExchanges(String ... names) throws IOException {
       List madridExchangeStocks = new ArrayList<Stock>();
        for(String name : names){
            Stock stock = YahooFinance.get(name);
            madridExchangeStocks.add(stock);
        }
        return madridExchangeStocks;
    }

    private void showExchangesDetails(){
        System.out.println(getMyExchanges().size());
        for (Stock stock : this.getMyExchanges() ) {
            System.out.println(stock);
            BigDecimal price = stock.getQuote().getPrice();
            BigDecimal change = stock.getQuote().getChangeInPercent();
            BigDecimal peg = stock.getStats().getPeg();
            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
            stock.print();
        }
    }

    /**
     * @return the myExchanges
     */
    public List<Stock> getMyExchanges() {
        return myExchanges;
    }


    public String getIdYahoo(String company) {
        for(Stock stock : this.getMyExchanges()){
            if(stock.getName() == company){
                return stock.getQuote().getSymbol();
            }
        }
        return "";
    }
    
    public BigDecimal getPriceByCompany(String company){
        for(Stock stock : this.getMyExchanges()){
            if(stock.getName().equals(company)){
                return stock.getQuote().getPrice();
            }
        }
        return new BigDecimal(00);
    }
}
