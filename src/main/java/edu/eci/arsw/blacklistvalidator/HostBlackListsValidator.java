/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import edu.eci.arsw.threads.SearchThread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class HostBlackListsValidator {

    private static final int BLACK_LIST_ALARM_COUNT=5;
    
    /**
     * Check the given host's IP address in all the available black lists,
     * and report it as NOT Trustworthy when such IP was reported in at least
     * BLACK_LIST_ALARM_COUNT lists, or as Trustworthy in any other case.
     * The search is not exhaustive: When the number of occurrences is equal to
     * BLACK_LIST_ALARM_COUNT, the search is finished, the host reported as
     * NOT Trustworthy, and the list of the five blacklists returned.
     * @param ipaddress suspicious host's IP address.
     * @return  Blacklists numbers where the given host's IP address was found.
     */
    public List<Integer> checkHost(String ipaddress, int threads){
        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
        LinkedList<Integer> blackListOcurrences=new LinkedList<>();
        for(int i=0; i<threads; i++){
            // TODO: hacer los hilos y asignarles los rangos, juntar las ocurrencias e imprimir el resultado
        }
        if (ocurrencesCount>=BLACK_LIST_ALARM_COUNT){
            skds.reportAsNotTrustworthy(ipaddress);
        }
        else{
            skds.reportAsTrustworthy(ipaddress);
        }
        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()});
        return blackListOcurrences;
    }
    private List<List<Integer>> ranges(int threads){
        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
        int totalLists = skds.getRegisteredServersCount(); // Total de listas
        int numberOfSegments = threads;
        List<List<Integer>> segmentRanges = new ArrayList<>();
        // Calcula el tamaño base de cada segmento
        int baseSize = totalLists / numberOfSegments;
        int remainder = totalLists % numberOfSegments;

        // Definir y mostrar los rangos de cada segmento
        int startIndex = 0;

        System.out.println("Número de segmentos: " + numberOfSegments);

        for (int i = 0; i < numberOfSegments; i++) {
            int segmentSize = (i < remainder) ? baseSize + 1 : baseSize;
            int endIndex = startIndex + segmentSize - 1;

            // Crear una lista para el rango actual y agregarla a la lista de segmentos
            List<Integer> range = new ArrayList<>();
            range.add(startIndex);
            range.add(endIndex);
            segmentRanges.add(range);

            // Actualizar el índice de inicio para el siguiente segmento
            startIndex = endIndex + 1;
        }

      /*  // Mostrar los rangos almacenados para verificar
        for (int i = 0; i < segmentRanges.size(); i++) {
            List<Integer> range = segmentRanges.get(i);
            System.out.printf("Segmento %d: Listas de %d a %d%n", i + 1, range.get(0), range.get(1));
        }*/
        return segmentRanges;
    }
    
    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());
    
    
    
}
