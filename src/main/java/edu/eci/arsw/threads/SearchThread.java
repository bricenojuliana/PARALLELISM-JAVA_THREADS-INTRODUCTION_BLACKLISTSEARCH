package edu.eci.arsw.threads;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SearchThread extends Thread {
    private final int lowerLimit;
    private final int upperLimit;
    private final String ipaddress;
    private final List<Integer> blackListOcurrences;
    private static final int BLACK_LIST_ALARM_COUNT = 5;
    private int checkedListsCount;

    public SearchThread(int lowerLimit, int upperLimit, String ipaddress) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.ipaddress = ipaddress;
        blackListOcurrences = new LinkedList<>();
    }

    public void run() {
        int ocurrencesCount = 0;
        HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();
        checkedListsCount = 0;

        for (int i = lowerLimit; i <= upperLimit && ocurrencesCount < BLACK_LIST_ALARM_COUNT; i++) {
            checkedListsCount++;
            if (skds.isInBlackListServer(i, ipaddress)) {
                blackListOcurrences.add(i);
                ocurrencesCount++;
            }
        }
    }

    public List<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }

    public int getCheckedListsCount() {
        return checkedListsCount;
    }
}
