package tran.example.smartcartest.controller;

import com.smartcar.sdk.AuthClient;
import com.smartcar.sdk.SmartcarException;
import com.smartcar.sdk.data.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tran.example.smartcartest.model.VehicleDto;
import tran.example.smartcartest.service.exchange.ExchangeService;
import tran.example.smartcartest.service.vehicle.VehicleService;

import java.util.LinkedList;
import java.util.List;

/*
 * Defines a route to display information about vehicles from a specific car brand (such as a Jaguar).
 */
@Controller
public class InformationController {

    private String access;

    private VehicleService vehicleService;

    private ExchangeService exchangeService;

    public InformationController(VehicleService vehicleService, ExchangeService exchangeService) {
        this.vehicleService = vehicleService;
        this.exchangeService = exchangeService;
    }

    @GetMapping("/vehicle")
    public String vehicle(Model model) {
        String viewName = "vehicle";
        access = exchangeService.getAccessToken();
        if(access.equals("") || access.length() < 1) {
            // this really means no access token was assigned (don't want to be too informative).
            model.addAttribute("error", "access server error");
            viewName = "error";
        } else {
            try {
                SmartcarResponse<VehicleIds> vehicleIdResponse = AuthClient.getVehicleIds(access);
                List<VehicleDto> vehicleList = new LinkedList<>();
                String[] vehicleIds = vehicleIdResponse.getData().getVehicleIds();

                vehicleService.fillVehicleList(vehicleList, vehicleIds, access);
                model.addAttribute("list", vehicleList);
                // {
                //   "id": "36ab27d0-fd9d-4455-823a-ce30af709ffc",
                //   "make": "TESLA",
                //   "model": "Model S",
                //   "year": 2014
                // }
            } catch (SmartcarException exception) {
                System.out.println("in information controller");
                System.out.println(exception.getMessage());
                viewName = "error";
                model.addAttribute("error", "cannot get information for chosen vehicle please select another.");
            }
        }
        return viewName;
    }

}
