import Lan.Server;
import Tables.Dealer;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        dealer.seatClients();
        dealer.Play();
    }
}
