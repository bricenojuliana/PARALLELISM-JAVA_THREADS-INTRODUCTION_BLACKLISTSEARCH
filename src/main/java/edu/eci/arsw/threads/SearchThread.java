package edu.eci.arsw.threads;
import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;

public class SearchThread extends Thread{
    int lowerLimit;
    int upperLimit;
    String ipaddress;
    LinkedList<Integer> blackListOcurrences;
    private static final int BLACK_LIST_ALARM_COUNT=5;
    public SearchThread(int lowerLimit, int upperLimit, String ipadress) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.ipaddress = ipadress;
        blackListOcurrences = new LinkedList<>();
    }
    public void run() {

        int ocurrencesCount=0;

        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();

        int checkedListsCount=0;

        for (int i=lowerLimit;i < upperLimit && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            if (skds.isInBlackListServer(i, ipaddress)){
                blackListOcurrences.add(i);
                ocurrencesCount++;
            }
        }
    }

    public LinkedList<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }
}
