package tran.example.smartcartest.service.exchange;

import com.smartcar.sdk.SmartcarException;
import tran.example.smartcartest.model.domain.ApplicationUser;

/*
 * Interface to enforce expected behaviors used by the ExchangeController.
 */
public interface ExchangeService {

    void generateAccessToken(String code, ApplicationUser applicationUser) throws SmartcarException;

}
