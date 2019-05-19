package ru.mail.polis.open.project.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Keeps statistics about users requests
 */
public class UserSearchStatisticsProvider {

    private final Map<String, Map<String, Integer>> requests;

    public UserSearchStatisticsProvider() {

        requests = new HashMap<>();
    }

    public void onRequest(String requestType, String city) {
        if (!requests.containsKey(requestType)) {
            requests.put(requestType, new HashMap<>());
        }

        requests.get(requestType).compute(
            city,
            (key, oldValue) -> oldValue == null ? 1 : oldValue + 1
        );
    }

    /**
     * Returns most frequent request from users on given requestType
     * @param count - how much items have to be returned
     * @param requestType - utils pool to return from
     * @return list of strings - most frequent requests
     */
    public List<String> getMostFrequent(int count, String requestType) {

        if (!requests.containsKey(requestType)) {
            return List.of();
        }
        return requests
            .get(requestType)
            .entrySet()
            .stream()
            .sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
            .limit(count)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    Map<String, Integer> getRequestCounts(String requestType) {

        if (!requests.containsKey(requestType)) {
            return Collections.emptyMap();
        }

        return requests.get(requestType);
    }

    /**
     * Clears all info about chat
     */
    public void clear() {
        requests.clear();
    }
}
