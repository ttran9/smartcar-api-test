package tran.example.smartcartest.controller;

import com.smartcar.sdk.AuthClient;
import com.smartcar.sdk.SmartcarException;
import com.smartcar.sdk.data.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.model.dto.VehicleDto;
import tran.example.smartcartest.service.security.CustomUserDetailsService;
import tran.example.smartcartest.service.security.TokenValidationService;
import tran.example.smartcartest.service.vehicle.VehicleService;
import tran.example.smartcartest.utility.controller.ControllerHelper;

import java.util.LinkedList;
import java.util.List;

import static tran.example.smartcartest.configuration.smartcar.constants.SmartCarApiConstants.CHANGE_VEHICLE_BRAND_KEY;
import static tran.example.smartcartest.configuration.smartcar.constants.SmartCarApiConstants.SMARTCAR_CONNECT_LINK;
import static tran.example.smartcartest.utility.constants.KeyConstants.SMARTCAR_CONNECT_LINK_KEY;
import static tran.example.smartcartest.utility.constants.KeyConstants.TITLE_KEY;
import static tran.example.smartcartest.utility.constants.PathConstants.VEHICLE_PATH;
import static tran.example.smartcartest.utility.constants.ValueConstants.VEHICLE_TITLE;
import static tran.example.smartcartest.utility.constants.view.TextConstants.CHANGE_VEHICLE_BRAND_MESSAGE;

/**
 * Defines a route to display information about vehicles from a specific car brand (such as a Jaguar).
 */
@Controller
public class InformationController {

    private String access;
    private VehicleService vehicleService;
    private CustomUserDetailsService customUserDetailsService;
    private TokenValidationService tokenValidationService;

    public InformationController(VehicleService vehicleService, CustomUserDetailsService customUserDetailsService,
                                 TokenValidationService tokenValidationService) {
        this.vehicleService = vehicleService;
        this.customUserDetailsService = customUserDetailsService;
        this.tokenValidationService = tokenValidationService;
    }

    @GetMapping(VEHICLE_PATH)
    public String vehicle(Model model) {
        String viewName = "vehicle";
        ControllerHelper.addAttribute(model, TITLE_KEY, VEHICLE_TITLE);

        String userName = ControllerHelper.getLoggedInUserName();

        try {
            if(tokenValidationService.checkToken(userName)) {
            ApplicationUser user = (ApplicationUser) customUserDetailsService.loadUserByUsername(userName);
            access = user.getCustomToken().getAccessToken();
                SmartcarResponse<VehicleIds> vehicleIdResponse = AuthClient.getVehicleIds(access);
                List<VehicleDto> vehicleList = new LinkedList<>();
                String[] vehicleIds = vehicleIdResponse.getData().getVehicleIds();

                vehicleService.fillVehicleList(vehicleList, vehicleIds, access);
                ControllerHelper.addAttribute(model, SMARTCAR_CONNECT_LINK_KEY, SMARTCAR_CONNECT_LINK);
                ControllerHelper.addAttribute(model, CHANGE_VEHICLE_BRAND_KEY, CHANGE_VEHICLE_BRAND_MESSAGE);

                model.addAttribute("list", vehicleList);
            }
            else {
                model.addAttribute("error", "access server error");
                viewName = "error";
            }
        } catch (SmartcarException exception) {
            System.out.println("in information controller");
            System.out.println(exception.getMessage());
            viewName = "error";
            model.addAttribute("error", "cannot get information for chosen vehicle please select another.");
        }

        return viewName;
    }

}
