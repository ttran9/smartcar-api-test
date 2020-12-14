package tran.example.smartcartest.service.vehicle;

import com.smartcar.sdk.SmartcarException;
import com.smartcar.sdk.Vehicle;
import com.smartcar.sdk.data.VehicleInfo;
import com.smartcar.sdk.data.VehicleTirePressure;
import org.springframework.stereotype.Service;
import tran.example.smartcartest.model.dto.VehicleDto;

import java.util.List;

/*
 * A component/bean defining method(s) creating HTTP requests to the Smartcar API to grab vehicle information.
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    public VehicleServiceImpl() { }

    /*
     * Making calls to the Smartcar API and adding vehicle information to a list.
     */
    @Override
    public void fillVehicleList(List<VehicleDto> vehicleList, String[] vehicleIds, String accessToken) throws SmartcarException {
        for(String vehicleId : vehicleIds) {
            Vehicle vehicle = new Vehicle(vehicleId, accessToken);
            VehicleInfo info = vehicle.info();
            VehicleTirePressure tirePressure = vehicle.tirePressure().getData();

            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setId(info.getId());
            vehicleDto.setMake(info.getMake());
            vehicleDto.setModel(info.getModel());
            vehicleDto.setYear(info.getYear());

            vehicleDto.setInformation(info.getMake() + " " + info.getModel() + " " + info.getYear());

            vehicleDto.setDistance(vehicle.odometer().getData().getDistance());
            vehicleDto.setFrontLeft(tirePressure.getFrontLeft());
            vehicleDto.setFrontRight(tirePressure.getFrontRight());
            vehicleDto.setBackLeft(tirePressure.getBackLeft());
            vehicleDto.setBackRight(tirePressure.getBackRight());

            vehicleDto.setVin(vehicle.vin());

            vehicleList.add(vehicleDto);
        }
    }
}
