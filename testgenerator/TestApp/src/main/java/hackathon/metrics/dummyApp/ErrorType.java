package hackathon.metrics.dummyApp;

/**
 * Created by frans on 16.06.15.
 */
public enum ErrorType {
    ILLEGAL_STATE_EXCEPTION(0),
    YOURE_SO_STUPID_EXCEPTION(1),
    INTERNAL_SERVER_ERROR(2);

    int index;

    ErrorType(int index){
        this.index = index;
    }
}
