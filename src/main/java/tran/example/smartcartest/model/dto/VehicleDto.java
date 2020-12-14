package tran.example.smartcartest.model.dto;

import java.text.DecimalFormat;

/*
 * A domain object to hold information to be displayed from vehicles.
 */
public class VehicleDto {

    private String id;
    private String make;
    private String model;
    private int year;

    private String information;

    private String distance;
    private String distanceAsStr;

    private double frontLeft;
    private double frontRight;
    private double backLeft;
    private double backRight;

    private double frontLeftPSI;
    private double frontRightPSI;
    private double backLeftPSI;
    private double backRightPSI;

    private final double KPA_TO_PSI_CONVERSION = 0.145038;
    private final String ONE_DECIMAL_PLACE_PATTERN = "#.#";
    private final String NO_DECIMAL_PLACE_PATTERN = "#";

    private String vin;

    public VehicleDto() { }
    public VehicleDto(String id, String make, String model, int year, double distance, double frontLeft,
                      double frontRight, double backLeft, double backRight) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        setDistance(distance);
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = new DecimalFormat(NO_DECIMAL_PLACE_PATTERN).format(distance);
    }

    public double getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(double frontLeft) {
        this.frontLeft = roundDecimalPlaces(ONE_DECIMAL_PLACE_PATTERN, frontLeft);
        setFrontLeftPSI(frontLeft);
    }

    public double getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(double frontRight) {
        this.frontRight = roundDecimalPlaces(ONE_DECIMAL_PLACE_PATTERN, frontRight);
        setFrontRightPSI(frontRight);
    }

    public double getBackLeft() {
        return backLeft;
    }

    public void setBackLeft(double backLeft) {
        this.backLeft = roundDecimalPlaces(ONE_DECIMAL_PLACE_PATTERN, backLeft);
        setBackLeftPSI(backLeft);
    }

    public double getBackRight() {
        return backRight;
    }

    public void setBackRight(double backRight) {
        this.backRight = roundDecimalPlaces(ONE_DECIMAL_PLACE_PATTERN, backRight);
        setBackRightPSI(backRight);
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getFrontLeftPSI() {
        return frontLeftPSI;
    }

    public void setFrontLeftPSI(double frontLeftPSI) {
        this.frontLeftPSI = roundDecimalPlaces(ONE_DECIMAL_PLACE_PATTERN,frontLeftPSI * KPA_TO_PSI_CONVERSION);
    }

    public double getFrontRightPSI() {
        return frontRightPSI;
    }

    public void setFrontRightPSI(double frontRightPSI) {
        this.frontRightPSI = roundDecimalPlaces(ONE_DECIMAL_PLACE_PATTERN,frontRightPSI * KPA_TO_PSI_CONVERSION);
    }

    public double getBackLeftPSI() {
        return backLeftPSI;
    }

    public void setBackLeftPSI(double backLeftPSI) {
        this.backLeftPSI = roundDecimalPlaces(ONE_DECIMAL_PLACE_PATTERN,backLeftPSI * KPA_TO_PSI_CONVERSION);
    }

    public double getBackRightPSI() {
        return backRightPSI;
    }

    public void setBackRightPSI(double backRightPSI) {
        this.backRightPSI = roundDecimalPlaces(ONE_DECIMAL_PLACE_PATTERN,backRightPSI * KPA_TO_PSI_CONVERSION);
    }

    private double roundDecimalPlaces(String pattern, double value) {
        DecimalFormat df = new DecimalFormat(pattern);
        return Double.parseDouble(df.format(value));
    }

    public String getDistanceAsStr() {
        return distanceAsStr;
    }

    public void setDistanceAsStr(String distanceAsStr) {
        this.distanceAsStr = distanceAsStr;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
