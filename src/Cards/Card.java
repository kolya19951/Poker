package Cards;

/**
 * Created by Денис on 15.05.2015.
 */
public class Card {
    public byte rank;
    public char suit;
    public byte suitNum;

    public Card()
    {
    }

    public Card(byte r, byte s)
    {
        rank = (byte) (r);//here!!!!!!!!!!!!!!!! - 2
        suitNum = s;
        if (s == 0)
            suit = '?';
        if (s == 1)
            suit = '?';
        if (s == 2)
            suit = '?';
        if (s == 3)
            suit = '?';
    }

    /*public Card(String s)
    {
        //проверка формы ввода
        boolean two1 = false;
        boolean two2 = false;
        boolean three2 = false;
        boolean three3 = false;

        //1 sym 2
        if (s.length() == 2)
        {
            two1 = ((s.ge >= '2') && (s[0] <= '9')) || (s[0] == 'J') || (s[0] == 'j') || (s[0] == 'Q') || (s[0] == 'q') || (s[0] == 'K') || (s[0] == 'k') || (s[0] == 'A') || (s[0] == 'a');
            two2 = (s[1] == '/') || (s[1] == '*') || (s[1] == '-') || (s[1] == '+') || (s[1] == '?') || (s[1] == '?') || (s[1] == '?') || (s[1] == '?');
            if (two1 && two2)
            {
                if ((s[1] == '/') || (s[1] == '?'))
                    suit = '?';
                if ((s[1] == '*') || (s[1] == '?'))
                    suit = '?';
                if ((s[1] == '-') || (s[1] == '?'))
                    suit = '?';
                if ((s[1] == '+') || (s[1] == '?'))
                    suit = '?';

                if ((s[0] > '1') && (s[0] <= '9'))
                {
                    rank = (byte)s[0];
                    rank -= 48;
                }

                if ((s[0] == 'j') || (s[0] == 'J'))
                    rank = 11;
                if ((s[0] == 'q') || (s[0] == 'Q'))
                    rank = 12;
                if ((s[0] == 'k') || (s[0] == 'K'))
                    rank = 13;
                if ((s[0] == 'a') || (s[0] == 'A'))
                    rank = 14;
            }
        }
        else
        {
            if ((s.Length == 3) && (s[0] == '1'))
            {
                three2 = (s[1] == '0') || (s[1] == '1') || (s[1] == '2') || (s[1] == '3') || (s[1] == '4');
                three3 = (s[2] == '/') || (s[2] == '*') || (s[2] == '-') || (s[2] == '+') || (s[2] == '?') || (s[2] == '?') || (s[2] == '?') || (s[2] == '?');
                if (three2 && three3)
                {
                    if (s[1] == '0')
                        rank = 10;
                    if (s[1] == '1')
                        rank = 11;
                    if (s[1] == '2')
                        rank = 12;
                    if (s[1] == '3')
                        rank = 13;
                    if (s[1] == '4')
                        rank = 14;

                    if ((s[2] == '/') || (s[2] == '?'))
                        suit = '?';
                    if ((s[2] == '*') || (s[2] == '?'))
                        suit = '?';
                    if ((s[2] == '-') || (s[2] == '?'))
                        suit = '?';
                    if ((s[2] == '+') || (s[2] == '?'))
                        suit = '?';
                }
                else
                {
                    rank = 2;
                    suit = '?';
                }
            }
            else
            {
                rank = 2;
                suit = '?';
            }
        }

        if (suit == '?')
            suitNum = 0;
        if (suit == '?')
            suitNum = 1;
        if (suit == '?')
            suitNum = 2;
        if (suit == '?')
            suitNum = 3;
    }*/

    public Card(Card c)
    {
        rank = c.rank;
        suit = c.suit;
        suitNum = c.suitNum;
    }

    public String ToString()
    {
        String s = new String("");
        if (rank != 0)
        {
            if (rank < 11)
                s = Integer.toString(rank);
            else
            {
                //if (rank == 10)
                //    s = "T";
                if (rank == 11)
                    s = "J";
                if (rank == 12)
                    s = "Q";
                if (rank == 13)
                    s = "K";
                if (rank == 14)
                    s = "A";
            }
            s = s + "," + Byte.toString(suitNum);
            //s = s + "," + Byte.toString((byte) 0);
            //s = s + "," + Byte.toString((byte) 0);
            /*if (suit == '?')
                s = s + "?";????
            if (suit == '?')
                s = s + "?";
            if (suit == '?')
                s = s + "?";
            if (suit == '?')
                s = s + "?";*/
        }
        return s;
    }
}

class Suit {
    private byte index;

    Suit (String name) {
        switch (name) {
            case "hearts": {
                index = 0;
                break;
            }
            case "tiles": {
                index = 1;
                break;
            }
            case "clovers": {
                index = 2;
                break;
            }
            case "pikes": {
                index = 3;
                break;
            }
            default:
                index = 4;
                break;
        }
    }

    String GetSuit () {
        switch (index) {
            case 0: {
                return "hearts";
            }
            case 1: {
                return "tiles";
            }
            case 2: {
                return "clovers";
            }
            case 3: {
                return "pikes";
            }
            default:
                return "NAN";
        }
    }
}

class Rank {
    private byte index;

    Rank (int rank) {
        index = (byte) (rank - 2);
    }

    int GetRank () {
        return index + 2;
    }

    public String toString() {
        return Byte.toString(index);
    }
}