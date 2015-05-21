package Cards;

public class CollectionCards
{
    /*byte cardCount;
    Card[] collection = new Card[7];
    boolean[][] table = new boolean[14][4];
    byte[] flashDataMas = new byte[4];
    boolean[] straightDataMas = new boolean[15];
    byte[] pairDataMas = new byte[14];
    byte sameSuitMax;
    byte sameSuitMaxIndex;
    //public bool flashID;
    byte straightFlashIndex;
    byte straightIndex;

    public CollectionCards(Card c1, Card c2, Card c3, Card c4, Card c5, Card c6, Card c7)
    {
        cardCount = 7;
        collection[0] = new Card(c1);
        collection[1] = new Card(c2);
        collection[2] = new Card(c3);
        collection[3] = new Card(c4);
        collection[4] = new Card(c5);
        collection[5] = new Card(c6);
        collection[6] = new Card(c7);

        tableBuilder();
    }

    public CollectionCards(Card c1, Card c2, Card c3, Card c4, Card c5, Card c6)
    {
        cardCount = 6;
        collection[0] = new Card(c1);
        collection[1] = new Card(c2);
        collection[2] = new Card(c3);
        collection[3] = new Card(c4);
        collection[4] = new Card(c5);
        collection[5] = new Card(c6);

        tableBuilder();
    }

    public CollectionCards(Card c1, Card c2, Card c3, Card c4, Card c5)
    {
        cardCount = 5;
        collection[0] = new Card(c1);
        collection[1] = new Card(c2);
        collection[2] = new Card(c3);
        collection[3] = new Card(c4);
        collection[4] = new Card(c5);

        tableBuilder();
    }

    public void addNewHand (Hand hand)
    {
        collection[5] = hand.c1;
        collection[6] = hand.c2;
        cardCount = 7;
        tableBuilder();
    }

    private  void tableBuilder()
    {
        //inicialization
        sameSuitMax = 0;
        sameSuitMaxIndex = 0;
        //flashID = false;
        for (int i = 0; i < 14; i++)
        {
            straightDataMas[i] = false;
            for (int j = 0; j < 4; j++)
                table[i][j] = false;
        }

        for (int i = 0; i < 13; i++)
            pairDataMas[i] = 0;

        for (int i = 0; i < 4; i++)
            flashDataMas[i] = 0;
        //inicialization

        for (int i = 0; i < cardCount; i++)
        {
            if (collection[i].suit == '?')
            {
                table[collection[i].rank - 1, 0] = true;
                straightDataMas[collection[i].rank - 1] = true;
                pairDataMas[collection[i].rank - 1]++;
                if (collection[i].rank == 14)
                {
                    table[0, 0] = true;
                    straightDataMas[0] = true;
                }
                flashDataMas[0]++;
            }
            if (collection[i].suit == '?')
            {
                table[collection[i].rank - 1, 1] = true;
                straightDataMas[collection[i].rank - 1] = true;
                pairDataMas[collection[i].rank - 1]++;
                if (collection[i].rank == 14)
                {
                    straightDataMas[0] = true;
                    table[0][1] = true;
                }
                flashDataMas[1]++;
            }
            if (collection[i].suit == '?')
            {
                table[collection[i].rank - 1, 2] = true;
                straightDataMas[collection[i].rank - 1] = true;
                pairDataMas[collection[i].rank - 1]++;
                if (collection[i].rank == 14)
                {
                    table[0, 2] = true;
                    straightDataMas[0] = true;
                }
                flashDataMas[2]++;
            }
            if (collection[i].suit == '?')
            {
                table[collection[i].rank - 1, 3] = true;
                straightDataMas[collection[i].rank - 1] = true;
                pairDataMas[collection[i].rank - 1]++;
                if (collection[i].rank == 14)
                {
                    table[0, 3] = true;
                    straightDataMas[0] = true;
                }
                flashDataMas[3]++;
            }
        }

        for (int i = 0; i < 4; i++)
            if (sameSuitMax < flashDataMas[i])
            {
                sameSuitMax = flashDataMas[i];
                sameSuitMaxIndex = (byte) i;
            }
        //checkFlash();
    }

    private bool checkFlash()
    {
        if (sameSuitMax >= 5)
            return true;
        else
            return false;
    }

    public bool checkStraight()
    {
        byte counter = 0;
        for (byte i = 14; i > 0; i--)
        {
            if (straightDataMas[i])
            {
                counter++;
                if (counter >= 5)
                {
                    straightIndex = i;
                    return true;
                }
            }
            else
                counter = 0;
        }
        return false;
    }

    public bool checkStraightFlash()
    {
        byte counter = 0;
        for (byte i = 13; i > 0; i--)
        {
            if (table[i, sameSuitMaxIndex])
            {
                counter++;
                if (counter >= 5)
                {
                    straightFlashIndex = i;
                    return true;
                }
            }
            else
            counter = 0;
        }
        return false;
    }

    public string checkCombination()
    {
        byte kareIndex = 0;

        //вспомогательный массив (4, 1, 1, 0) - фулл хаус
        byte[] counter = new byte[4] {0, 0, 0, 0};
        for (int i = 1; i < 14; i++)
        {
            if (pairDataMas[i] > 0)
                counter[pairDataMas[i] - 1]++;
            if (pairDataMas[i] == 4)
            {
                kareIndex = (byte)i;
            }
        }

        if (counter[3] > 0)
        {
            for (int i = 13; i > 1; i--)
                if ((i != kareIndex) && straightDataMas[i])
                    return string.Format("Kare of {0} with kicker {1}", kareIndex + 1, i + 1);
            return string.Format("Kare of {0}", kareIndex);
        }
        else
        {
            if (checkFlash())
            {
                if (checkStraightFlash())
                {
                    return string.Format("Straight Flash from {0} to {1}", straightFlashIndex + 1, straightFlashIndex + 5);
                }
                else
                {
                    byte[] com = new byte[5];
                    byte j = 0;
                    byte i = 13;
                    while ((j < 5) || (i <= 0))
                    {
                        if (table[i, sameSuitMaxIndex])
                        {
                            com[j] = (byte)(i + 1);
                            j++;
                        }
                        i--;
                    }
                    return string.Format("Flash {0} + {1} + {2} + {3} + {4}", com[0], com[1], com[2], com[3], com[4]);
                }
            }

            if (checkStraight())
            {
                return string.Format("Straight from {0} to {1}", straightIndex + 1, straightIndex + 5);
            }

            if (counter[1] > 0)
                if (counter[2] > 0)
                    return "Full house";
                else
                if (counter[1] == 1)
                    return "Pair";
                else
                    return "Two pairs";
            else
            if (counter[2] > 0)
                return "Set";
        }
        return "Kicker";
    }

    public int checkCombinationRank()
    {
        byte kareIndex = 0;

        //вспомогательный массив (4, 1, 1, 0) - фулл хаус
        byte[] counter = new byte[4] { 0, 0, 0, 0 };
        for (int i = 1; i < 14; i++)
        {
            if ((pairDataMas[i] > 0)&&(pairDataMas[i] < 5))
                counter[pairDataMas[i] - 1]++;
            if (pairDataMas[i] == 4)
            {
                kareIndex = (byte)i;
            }
        }

        if (counter[3] > 0)
        {
                /*for (int i = 13; i > 1; i--)
                    if ((i != kareIndex) && straightDataMas[i])
                        return string.Format("Kare of {0} with kicker {1}", kareIndex + 1, i + 1);*/
            /*return 7;
        }
        else
        {
            if (checkFlash())
            {
                if (checkStraightFlash())
                {
                    return 8;
                }
                else
                {
                    byte[] com = new byte[5];
                    byte j = 0;
                    byte i = 13;
                    while ((j < 5) && (i < 13))
                    {
                        if (table[i, sameSuitMaxIndex])
                        {
                            com[j] = (byte)(i + 1);
                            j++;
                        }
                        i--;
                    }
                    return 5;
                }
            }

            if (checkStraight())
            {
                return 4;
            }

            if (counter[1] > 0)
                if (counter[2] > 0)
                    return 6;
                else
                if (counter[1] == 1)
                    return 1;
                else
                    return 2;
            else
            if (counter[2] > 0)
                return 3;
        }
        return 0;
    }

    public int bigger(CollectionCards col, int myComb)
    {
        switch (myComb)
        {
            case 0:
            {
                break;
            }
            case 1:
            {
                break;
            }
            case 2:
            {
                break;
            }
            case 3:
            {
                break;
            }
            case 4:
            {
                break;
            }
            case 5:
            {
                break;
            }
            case 6:
            {
                break;
            }
            case 7:
            {
                break;
            }
            default:
                break;
        }


        byte kareIndex = 0;

        //вспомогательный массив (4, 1, 1, 0) - фулл хаус
        byte[] counter = new byte[4] { 0, 0, 0, 0 };
        for (int i = 1; i < 14; i++)
        {
            if ((pairDataMas[i] > 0) && (pairDataMas[i] < 5))
                counter[pairDataMas[i] - 1]++;
            if (pairDataMas[i] == 4)
            {
                kareIndex = (byte)i;
            }
        }

        if (counter[3] > 0)
        {
                /*for (int i = 13; i > 1; i--)
                    if ((i != kareIndex) && straightDataMas[i])
                        return string.Format("Kare of {0} with kicker {1}", kareIndex + 1, i + 1);*/
           /* return 7;
        }
        else
        {
            if (checkFlash())
            {
                if (checkStraightFlash())
                {
                    return 8;
                }
                else
                {
                    byte[] com = new byte[5];
                    byte j = 0;
                    byte i = 13;
                    while ((j < 5) && (i < 13))
                    {
                        if (table[i, sameSuitMaxIndex])
                        {
                            com[j] = (byte)(i + 1);
                            j++;
                        }
                        i--;
                    }
                    return 5;
                }
            }

            if (checkStraight())
            {
                return 4;
            }

            if (counter[1] > 0)
                if (counter[2] > 0)
                    return 6;
                else
                if (counter[1] == 1)
                    return 1;
                else
                    return 2;
            else
            if (counter[2] > 0)
                return 3;
        }
        return 0;
    }*/
}