package practiceProject.cmap.config.exception.handler;

import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.GeneralException;

public class CommonHandler extends GeneralException {

    public CommonHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
