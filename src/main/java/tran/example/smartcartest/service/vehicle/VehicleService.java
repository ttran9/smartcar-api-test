package tran.example.smartcartest.service.vehicle;

import com.smartcar.sdk.SmartcarException;
import tran.example.smartcartest.model.VehicleDto;

import java.util.List;

/*
 * Interface to declare expected behaviors such as adding vehicles from a HTTP Get request.
 */
public interface VehicleService {

    public void fillVehicleList(List<VehicleDto> vehicleList, String[] vehicleIds, String accessToken) throws SmartcarException;

}
