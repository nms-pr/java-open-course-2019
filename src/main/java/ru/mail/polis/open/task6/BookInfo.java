package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BookInfo {

    private int shelfPlaceId;
    private long takeTimestamp;
    private Visitor currentTaker;

    private List<TakerInfo> takersHistory;

    BookInfo(int shelfPlaceId) {
        this.shelfPlaceId = shelfPlaceId;
        takersHistory = new ArrayList<TakerInfo>();
    }

    int getShelfPlaceId() {
        return shelfPlaceId;
    }

    List<TakerInfo> getTakersHistory() {
        return Collections.unmodifiableList(takersHistory);
    }

    void markBookAsTaken(Visitor visitor) throws LibraryException {
        if (currentTaker != null) {
            throw new LibraryException("Book is already taken");
        }
        takeTimestamp = System.currentTimeMillis();
        currentTaker = visitor;
        shelfPlaceId = -1;
    }

    void markBookAsReturned(int shelfPlaceId) throws LibraryException {
        if (currentTaker == null) {
            throw new LibraryException("Book isn't was taken");
        }
        this.shelfPlaceId = shelfPlaceId;
        long returnTimestamp = System.currentTimeMillis();
        TakerInfo takerInfo =
                new TakerInfo(currentTaker.getCredentials(), takeTimestamp, returnTimestamp);
        takersHistory.add(takerInfo);
        currentTaker = null;
    }

    Visitor getCurrentTaker() {
        return currentTaker;
    }

    boolean isBookTaken() {
        return currentTaker != null;
    }

    class TakerInfo {
        private long takeTimestamp;
        private long returnTimestamp;
        private String takerCredentials;

        public TakerInfo(String takerCredentials, long takeTimestamp, long returnTimestamp) {
            this.takerCredentials = takerCredentials;
            this.takeTimestamp = takeTimestamp;
            this.returnTimestamp = returnTimestamp;
        }

        public long getReturnTimestamp() {
            return returnTimestamp;
        }

        public String getTakerCredentials() {
            return takerCredentials;
        }

        public long getTakeTimestamp() {
            return takeTimestamp;
        }
    }
}
